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
	 * It stores the exam results, but not the username and email (see ExamBackingBean) directly from the web form.
	 * It contains a method that checks the users exam results, determines if they have passed or failed.
	 * It sends an email to the ERGIS staff if PASS. Independent of results it routes user to next xhtml page after they hit submit.
	 * It depends on the injection of the ExamBackingBean instance.
	 * @author CSchaefe
	 * @since 1.8
	 *
	 */
	@ManagedProperty (value="#{examBean}")
	private ExamBackingBean examBean;
	
	//STATE
	boolean doLogging = false;
	private String userEmailPreserved;
	private String userNamePreserved;
	private LocalDate todayDate;
	private static final long serialVersionUID = 1L;
	/*Note: assumes there are four parts. would need to revise the number of these if ExamParts.getNumberOfExamParts() != 4*/
	private String[][] examDoubleArrayForAnswersPartI = new String[ExamParts.getNumberOfQuestionsPart1()][];
	private String[][] examDoubleArrayForAnswersPartII = new String[ExamParts.getNumberOfQuestionsPart2()][];
	private String[][] examDoubleArrayForAnswersPartIII = new String[ExamParts.getNumberOfQuestionsPart3()][];
	private String[][] examDoubleArrayForAnswersPartIV = new String[ExamParts.getNumberOfQuestionsPart4()][];

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
		String[] emailRecipients = {"BOEXI@tceq.texas.gov", examBean.getUserEmail()};
		String emailBodyContent = "BOE Access Exam PASS: " + examBean.getUserName() + " ( " + examBean.getUserEmail() + " )";
		
		//Grab the username and email from examBean. Because the bean is viewscope the data is only good for the "index.xhtml" jsf, not the "pass.xhtml" jsf page.
		this.userEmailPreserved = examBean.getUserEmail().toUpperCase();
		this.userNamePreserved = examBean.getUserName().toUpperCase();

		//Build list of user answers that can be iterated over by index.
		ArrayList<String[][]> allUserAnswersInArrayList = new ArrayList<>();
		allUserAnswersInArrayList.add(0, this.examDoubleArrayForAnswersPartI);
		allUserAnswersInArrayList.add(1, this.examDoubleArrayForAnswersPartII);
		allUserAnswersInArrayList.add(2, this.examDoubleArrayForAnswersPartIII);
		allUserAnswersInArrayList.add(3, this.examDoubleArrayForAnswersPartIV);
		
		//increment through the parts (4 at the time of design)
		for(int i = 0; i < ExamParts.getNumberOfExamParts(); i++){
			
			//Get the Part of interest from the examBean in order to grab the true answers
			Part partOfInterest = this.examBean.getExamParts().getExamPartsArrayList().get(i);
			
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
//			EmailNoticeOfPass.sendEmail(examBean.getUserEmail(), examBean.getUserName());
			Mailer mailer = new Mailer();
			mailer.sendEMail(examBean.getUserName(), emailRecipients, emailBodyContent);
			return "pass";
		}
		else{
			return "fail";
		}
	}
	
	//GETTERS AND SETTERS
	public String[][] getExamDoubleArrayForAnswersPartI() {
		return examDoubleArrayForAnswersPartI;
	}

	public void setExamDoubleArrayForAnswersPartI(
			String[][] examDoubleArrayForAnswersPartI) {
		this.examDoubleArrayForAnswersPartI = examDoubleArrayForAnswersPartI;
	}

	public String[][] getExamDoubleArrayForAnswersPartII() {
		return examDoubleArrayForAnswersPartII;
	}

	public void setExamDoubleArrayForAnswersPartII(
			String[][] examDoubleArrayForAnswersPartII) {
		this.examDoubleArrayForAnswersPartII = examDoubleArrayForAnswersPartII;
	}

	public String[][] getExamDoubleArrayForAnswersPartIII() {
		return examDoubleArrayForAnswersPartIII;
	}

	public void setExamDoubleArrayForAnswersPartIII(
			String[][] examDoubleArrayForAnswersPartIII) {
		this.examDoubleArrayForAnswersPartIII = examDoubleArrayForAnswersPartIII;
	}

	public String[][] getExamDoubleArrayForAnswersPartIV() {
		return examDoubleArrayForAnswersPartIV;
	}

	public void setExamDoubleArrayForAnswersPartIV(
			String[][] examDoubleArrayForAnswersPartIV) {
		this.examDoubleArrayForAnswersPartIV = examDoubleArrayForAnswersPartIV;
	}

	public ExamBackingBean getexamBean() {
		return examBean;
	}

	public void setexamBean(ExamBackingBean examBean) {
		this.examBean = examBean;
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
		return "UserEntryBackingBean [examBean=" + examBean
				+ ", userEmailPreserved=" + userEmailPreserved
				+ ", userNamePreserved=" + userNamePreserved + ", todayDate="
				+ todayDate + ", examDoubleArrayForAnswersPartI="
				+ Arrays.toString(examDoubleArrayForAnswersPartI)
				+ ", examDoubleArrayForAnswersPartII="
				+ Arrays.toString(examDoubleArrayForAnswersPartII)
				+ ", examDoubleArrayForAnswersPartIII="
				+ Arrays.toString(examDoubleArrayForAnswersPartIII)
				+ ", examDoubleArrayForAnswersPartIV="
				+ Arrays.toString(examDoubleArrayForAnswersPartIV) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((examBean == null) ? 0 : examBean.hashCode());
		result = prime * result
				+ Arrays.hashCode(examDoubleArrayForAnswersPartI);
		result = prime * result
				+ Arrays.hashCode(examDoubleArrayForAnswersPartII);
		result = prime * result
				+ Arrays.hashCode(examDoubleArrayForAnswersPartIII);
		result = prime * result
				+ Arrays.hashCode(examDoubleArrayForAnswersPartIV);
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
		if (examBean == null) {
			if (other.examBean != null)
				return false;
		} else if (!examBean.equals(other.examBean))
			return false;
		if (!Arrays.deepEquals(examDoubleArrayForAnswersPartI,
				other.examDoubleArrayForAnswersPartI))
			return false;
		if (!Arrays.deepEquals(examDoubleArrayForAnswersPartII,
				other.examDoubleArrayForAnswersPartII))
			return false;
		if (!Arrays.deepEquals(examDoubleArrayForAnswersPartIII,
				other.examDoubleArrayForAnswersPartIII))
			return false;
		if (!Arrays.deepEquals(examDoubleArrayForAnswersPartIV,
				other.examDoubleArrayForAnswersPartIV))
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
