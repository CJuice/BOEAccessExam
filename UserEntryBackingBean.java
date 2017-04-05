package gov.texas.tceq.BOEAccessTest;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean (name="userEntry")
@RequestScoped

public class UserEntryBackingBean implements Serializable {
	/**
	 * This class handles the users form/exam entries.
	 * It stores the exam results, but not the username and email (see TestBackingBean) directly from the web form.
	 * It contains a method that checks the users test results, determines if they have passed or failed.
	 * It sends an email to the ERGIS staff if PASS. Independent of results it routes user to next xhtml page after they hit submit.
	 * It depends on the injection of the TestBackingBean instance
	 * @author CSchaefe
	 * @since 1.8
	 *
	 */
	@ManagedProperty (value="#{testBean}")
	private TestBackingBean testBean;
	
	//STATE
	boolean doLogging = false;
	private String userEmailPreserved;
	private String userNamePreserved;
	private LocalDate todayDate;
	private static final long serialVersionUID = 1L;
	/*Note: assumes there are four parts. would need to revise the number of these if TestParts.getNumberOfTestParts() != 4*/
	private String[][] testDoubleArrayForAnswersPartI = new String[TestParts.getNumberOfQuestionsPart1()][];
	private String[][] testDoubleArrayForAnswersPartII = new String[TestParts.getNumberOfQuestionsPart2()][];
	private String[][] testDoubleArrayForAnswersPartIII = new String[TestParts.getNumberOfQuestionsPart3()][];
	private String[][] testDoubleArrayForAnswersPartIV = new String[TestParts.getNumberOfQuestionsPart4()][];

	//No-Arg Constructor
	/**
	 * default no-arg constructor for bean
	 */
	public UserEntryBackingBean() {
		this.setTodayDate(LocalDate.now());
	}
	
	//METHODS
	/**
	 * Assembles the users exam choices from the web form, evaluates them against the correct possibilities, determines pass or fail, 
	 * and returns a string that routes the user to pass.xhtml or fail.xhtml based on their grade.
	 * @return 	<code>String</code> indicates whether the user passed or failed the exam and this string determines which web
	 * 			page to route to after submit
	 */
	public String checkAnswers(){
		
		// Evaluate the answers, return a string to route the user to a pass.xhtml or fail.xhtml page.
		// If PASS, generate an email notifying ERGIS staff.
		float userScoreOfCorrectlyAnsweredQuestions = 0;
		float totalPossibleNumberOfQuestions = 0;
		String myPattern = ">.*<";
		Pattern pattern = Pattern.compile(myPattern);
		String[] emailRecipients = {"conrad.schaefer@tceq.texas.gov", testBean.getUserEmail()};
		String emailBodyContent = testBean.getUserName() + " ( " + testBean.getUserEmail() + " )" + " passed the BOE Access Test.";
		//grab username and email from testBean. Because is viewscope the data is only good for the index jsf, not the pass jsf page.
		this.userEmailPreserved = testBean.getUserEmail().toUpperCase();
		this.userNamePreserved = testBean.getUserName().toUpperCase();

		//Build list of user answers that can be iterated over by index.
		ArrayList<String[][]> allUserAnswersInArrayList = new ArrayList<>();
		allUserAnswersInArrayList.add(0, this.testDoubleArrayForAnswersPartI);
		allUserAnswersInArrayList.add(1, this.testDoubleArrayForAnswersPartII);
		allUserAnswersInArrayList.add(2, this.testDoubleArrayForAnswersPartIII);
		allUserAnswersInArrayList.add(3, this.testDoubleArrayForAnswersPartIV);
		
		//increment through the parts (4 at the time of design)
		for(int i = 0; i < TestParts.getNumberOfTestParts(); i++){
			
			//Get the Part of interest from the testbean in order to grab the true answers
			Part partOfInterest = this.testBean.getTestParts().getExamPartsArrayList().get(i);
			
			//Grab the exams correct answers for the Part and store them for comparison against users entries.
			ArrayList<String> trueAnswersForExam = partOfInterest.getTrueAnswersArrayList();
			
			//Get the users answers
			String[][] usersAnswersForThisPartByQuestion = allUserAnswersInArrayList.get(i);
			
			//increment through the questions, which are represented by an array of answers for that question. Double array.
			for(int j = 0; j < usersAnswersForThisPartByQuestion.length; j++){
				
				//tally the number of questions so we know what to grade the users point score against to get a grade.
				totalPossibleNumberOfQuestions = totalPossibleNumberOfQuestions + 1;
				
				//For each question, grab users answers one by one, strip out the answer ID and check to see if the ID is in the trueAnswersForExam. 
				//If yes, move on, if no then check next answer.
				//Tally the total number of questions to score. Tally the users score.
				boolean questionAnsweredCorrectly = false;
				for(int k = 0; k < usersAnswersForThisPartByQuestion[j].length; k++){
					if(usersAnswersForThisPartByQuestion[j].length == 4){
						
						//if a user checks all answers as true then we skip the question. Otherwise they could check all boxes, hit at least one of the true answers,
						//and get the question "correct"
						if(doLogging){
							System.out.println("User checked all answers for a question. SKIP");
						}
						break;
					}
					
					//once have a single correct answer, regardless of the total number of true answers for the question, need to break and move to next question.
					//If requirements change and exact matches of user answers with exam answer key is required then this whole section will need to be revised.
					if(questionAnsweredCorrectly){
						break;
					}
					
					//grab each answer
					String answer = usersAnswersForThisPartByQuestion[j][k];
					
					//use regex to pull answer ID
					Matcher matcher = pattern.matcher(answer);
					String answerID;
					/*Does the string have an answerID between the end and start of <span>ANSWER ID IS HERE</span>*/
					if(matcher.find()){
						
						//Strip the >< off the matcher.group() to get the clean answerID. Kind of ghetto. Couldn't get regex to exclude ><. CJS
						String tempRegexString = matcher.group();
						int tempRegexStringLength = tempRegexString.length();
						answerID = tempRegexString.substring(1, tempRegexStringLength - 1);
					}
					else{
						if(doLogging){
							System.out.println("Answer " + "'" + answer + "' did not contain an answerID recognizable by the regex pattern \">.*<\" .");
						}
						continue;
						}
					
					//check to see if the answerID stripped from the answer string is in the array of trueAnswersForExam
					for(String trueAnswerIDOption : trueAnswersForExam){
						if(answerID.equals(trueAnswerIDOption)){
							userScoreOfCorrectlyAnsweredQuestions = userScoreOfCorrectlyAnsweredQuestions + 1;
							questionAnsweredCorrectly = true;
							break;			
						}
						else{continue;}
					}					
				}
			}
		}
		
		float grade = userScoreOfCorrectlyAnsweredQuestions / totalPossibleNumberOfQuestions;
		if(grade >= 0.8){
//			EmailNoticeOfPass.sendEmail(testBean.getUserEmail(), testBean.getUserName());
			Mailer mailer = new Mailer();
			mailer.sendEMail(testBean.getUserName(), emailRecipients, emailBodyContent);
			return "pass";
		}
		else{
			return "fail";
		}
	}
	
	//GETTERS AND SETTERS
	public String[][] getTestDoubleArrayForAnswersPartI() {
		return testDoubleArrayForAnswersPartI;
	}

	public void setTestDoubleArrayForAnswersPartI(
			String[][] testDoubleArrayForAnswersPartI) {
		this.testDoubleArrayForAnswersPartI = testDoubleArrayForAnswersPartI;
	}

	public String[][] getTestDoubleArrayForAnswersPartII() {
		return testDoubleArrayForAnswersPartII;
	}

	public void setTestDoubleArrayForAnswersPartII(
			String[][] testDoubleArrayForAnswersPartII) {
		this.testDoubleArrayForAnswersPartII = testDoubleArrayForAnswersPartII;
	}

	public String[][] getTestDoubleArrayForAnswersPartIII() {
		return testDoubleArrayForAnswersPartIII;
	}

	public void setTestDoubleArrayForAnswersPartIII(
			String[][] testDoubleArrayForAnswersPartIII) {
		this.testDoubleArrayForAnswersPartIII = testDoubleArrayForAnswersPartIII;
	}

	public String[][] getTestDoubleArrayForAnswersPartIV() {
		return testDoubleArrayForAnswersPartIV;
	}

	public void setTestDoubleArrayForAnswersPartIV(
			String[][] testDoubleArrayForAnswersPartIV) {
		this.testDoubleArrayForAnswersPartIV = testDoubleArrayForAnswersPartIV;
	}

	public TestBackingBean getTestBean() {
		return testBean;
	}

	public void setTestBean(TestBackingBean testBean) {
		this.testBean = testBean;
	}

	public String getUserEmailPreserved() {
		return userEmailPreserved;
	}

	public void setUserEmailPreserved(String userEmailPreserved) {
		this.userEmailPreserved = userEmailPreserved;
	}

	public String getUserNamePreserved() {
		return userNamePreserved;
	}

	public void setUserNamePreserved(String userNamePreserved) {
		this.userNamePreserved = userNamePreserved;
	}

	public LocalDate getTodayDate() {
		return todayDate;
	}

	public void setTodayDate(LocalDate todayDate) {
		this.todayDate = todayDate;
	}

	@Override
	public String toString() {
		return "UserEntryBackingBean [testBean=" + testBean
				+ ", userEmailPreserved=" + userEmailPreserved
				+ ", userNamePreserved=" + userNamePreserved + ", todayDate="
				+ todayDate + ", testDoubleArrayForAnswersPartI="
				+ Arrays.toString(testDoubleArrayForAnswersPartI)
				+ ", testDoubleArrayForAnswersPartII="
				+ Arrays.toString(testDoubleArrayForAnswersPartII)
				+ ", testDoubleArrayForAnswersPartIII="
				+ Arrays.toString(testDoubleArrayForAnswersPartIII)
				+ ", testDoubleArrayForAnswersPartIV="
				+ Arrays.toString(testDoubleArrayForAnswersPartIV) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((testBean == null) ? 0 : testBean.hashCode());
		result = prime * result
				+ Arrays.hashCode(testDoubleArrayForAnswersPartI);
		result = prime * result
				+ Arrays.hashCode(testDoubleArrayForAnswersPartII);
		result = prime * result
				+ Arrays.hashCode(testDoubleArrayForAnswersPartIII);
		result = prime * result
				+ Arrays.hashCode(testDoubleArrayForAnswersPartIV);
		result = prime * result
				+ ((todayDate == null) ? 0 : todayDate.hashCode());
		result = prime
				* result
				+ ((userEmailPreserved == null) ? 0 : userEmailPreserved
						.hashCode());
		result = prime
				* result
				+ ((userNamePreserved == null) ? 0 : userNamePreserved
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEntryBackingBean other = (UserEntryBackingBean) obj;
		if (testBean == null) {
			if (other.testBean != null)
				return false;
		} else if (!testBean.equals(other.testBean))
			return false;
		if (!Arrays.deepEquals(testDoubleArrayForAnswersPartI,
				other.testDoubleArrayForAnswersPartI))
			return false;
		if (!Arrays.deepEquals(testDoubleArrayForAnswersPartII,
				other.testDoubleArrayForAnswersPartII))
			return false;
		if (!Arrays.deepEquals(testDoubleArrayForAnswersPartIII,
				other.testDoubleArrayForAnswersPartIII))
			return false;
		if (!Arrays.deepEquals(testDoubleArrayForAnswersPartIV,
				other.testDoubleArrayForAnswersPartIV))
			return false;
		if (todayDate == null) {
			if (other.todayDate != null)
				return false;
		} else if (!todayDate.equals(other.todayDate))
			return false;
		if (userEmailPreserved == null) {
			if (other.userEmailPreserved != null)
				return false;
		} else if (!userEmailPreserved.equals(other.userEmailPreserved))
			return false;
		if (userNamePreserved == null) {
			if (other.userNamePreserved != null)
				return false;
		} else if (!userNamePreserved.equals(other.userNamePreserved))
			return false;
		return true;
	}
	
}
