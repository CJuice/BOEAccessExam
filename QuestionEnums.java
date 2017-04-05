package gov.texas.tceq.BOEAccessTest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public enum QuestionEnums implements Serializable {
	/**
	 * This class of enums serves to store and create the question objects for use in the exam.
	 * @author CSchaefe
	 * @since 1.8
	 */
	
	//ENUM OBJECTS FOR ALL QUESTIONS: Part I
	P1_QA("P1_QA", "What password do I use to initially sign on to BOE?"),
	P1_QB("P1_QB", "Where do I enter the Oracle database logon?"),
	P1_QC("P1_QC", "What password do I provide to connect to the Oracle database and schedule a report?"),
	P1_QD("P1_QD", "Can BOE or ERT reset or update my Novell or Oracle passwords?"),
	P1_QE("P1_QE", "Can BOE make my Novell and Oracle passwords the same?"),

	//ENUM OBJECTS FOR ALL QUESTIONS: Part II
	P2_QA("P2_QA", "How do I schedule a report?"),
	P2_QB("P2_QB", "How do I view the reports history?"),
	P2_QC("P2_QC", "What are prompts?"),
	P2_QD("P2_QD", "How can I set up a report to recur the first day of the month?"),
	P2_QE("P2_QE", "Why would I want to update the Instance Title of a report?"),
		
	//ENUM OBJECTS FOR ALL QUESTIONS: Part III
	P3_QA("P3_QA", "Can I change the format of a report?"),
	P3_QB("P3_QB", "Can I schedule a report to email it to myself or others?"),
	P3_QC("P3_QC", "Do I need to have a Crystal Report License to use BOE"),
	
	//ENUM OBJECTS FOR ALL QUESTIONS: Part IV
	P4_QA("P4_QA", "How do I find why my report failed?"),
	P4_QB("P4_QB", "Is it my responsibility to remove, pause, or delete failed recurring reports?");
	
	//STATE
	private boolean doLogging = true;
	private String questionID;
	private String questionText;
	/**
	 * stores the answer enums for each question object
	 */
	private ArrayList<AnswerEnums> answerList;
	/**
	 * stores text string for each answer object
	 */
	private String[] answerText;
	private String[] answerIDs;
	
	//CONSTRUCTOR
	private QuestionEnums(String questionID, String questionText){
		this.questionID = questionID;
		this.questionText = questionText;
		this.answerList = buildAnswerArrayList(questionID);
		this.setAnswerText(buildAnswerTextArray(this.answerList));
		this.setAnswerIDs(buildAnswerIDArray(this.answerList));
	}
	
	//METHODS
	/**
	 * builds an array of strings that are the answer options (as text) for each question
	 * @param answerList This is the collection of Answer Enums for each question.
	 * @return <code>String</code> array of answer text
	 */
	public String[] buildAnswerTextArray(ArrayList<AnswerEnums> answerList){
		String[] textArray = new String[answerList.size()];
		for(int i = 0; i < answerList.size(); i++){
			String validAnswerIndicator = "";
			//This appends TRUE on the end of the true answers, used in testing so testers can quickly check true answers.
			if(doLogging){
				if(answerList.get(i).getValidAnswer()){
					validAnswerIndicator = "(" + answerList.get(i).getValidAnswer().toString().toUpperCase() + ")";
				}
			}
			textArray[i] = answerList.get(i).getAnswerText() + validAnswerIndicator;
		}			
		return textArray;
	}
	/**
 	 * builds an array of strings that are the answer options ID's for each question. These ID's are 
 	 * inserted into the displayed answer text on the exam, but hidden from user view. The ID is parsed from the 
 	 * users chosen and submitted answers and compared to the ID's of the correct answer possibilities and
 	 * this is how exam grading is achieved
	 * @param answerList This is the collection of Answer Enums for each question.
	 * @return <code>String</code> array of answer ID's
	 */
	public String[] buildAnswerIDArray(ArrayList<AnswerEnums> answerList){
		String[] idArray = new String[answerList.size()];
		for(int i = 0; i < answerList.size(); i++){
			idArray[i] = answerList.get(i).getAnswerID();
		}			
		return idArray;
	}
	
	//Method for assembling ArrayList of answers for a question object.
	/**
	 * Assembles an arraylist of answer enums for each question enum. The order is randomized so that each test appears differently.
	 * This list of enum objects provides access to all features of an answer, such as ID, Text, and so forth from the JSF page.
	 * <p>
	 * Relevant answers to a question are selected off the questionID parameter. At time of design, four answers are displayed in the exam per question. 
	 * <p>
	 * The true answers are grabbed first and stored. The remaining empty spots are filled with false answers. The order is then randomized.
	 * The general structure of the exam code: A relevant collection of answer enums are stored in each question. For each Part of the exam
	 * a relevant collection of question enums are stored. The Parts are assembled into a collection and accessed and displayed in the web.
	 * @param questionID This the ID of the question, as defined in each Question Enums properties.
	 * @return <code>ArrayList</code> of answer enums, in a randomized order, for each question
	 */
	public ArrayList<AnswerEnums> buildAnswerArrayList(String questionID){
		questionID = "p" + questionID.substring(1);
		
		//The number of answer options for each question is 6. Grab the true answers first, then fill out the array with false answers until there are 4 total answers
		ArrayList<AnswerEnums> answerListForAQuestionNONRandomized = new ArrayList<>();
		ArrayList<AnswerEnums> listOfFalseAnswers = new ArrayList<>();
		ArrayList<AnswerEnums> finalListOfRandomlyOrderedAnswerEnumsForAQuestion = new ArrayList<>();
		@SuppressWarnings("unused")
		int numberOfTrueAnswers = 0; //yes, it is used later!
		final int totalNumberOfAnswersNeeded = 4;
		Random random = new Random();
		
		//Grab true answers, store in a Set
		for (AnswerEnums answerEnum : AnswerEnums.values()) {
//			String questionIDinAnswerEnum = "p" + answerEnum.getAnswerID().substring(1);
					
			//Isolate answers for the question of interest.
			if(answerEnum.getQuestionID().equals(questionID)){
					//Iterate over once, grab true and put into Set
				if(answerEnum.getValidAnswer() == true){
					numberOfTrueAnswers += 1;
					answerListForAQuestionNONRandomized.add(answerEnum);	
				}
				
				//Put false answers into a list that will be used to make up the difference between the [number of true answers] and the [total needed (4 answers per question)]
				else{
					listOfFalseAnswers.add(answerEnum);
				}
			}
			else{continue;}
		}
		
		//Grab false answers to make up the difference between the [number of true answers] and the [total needed (4 answers per question)]
		while(answerListForAQuestionNONRandomized.size() < totalNumberOfAnswersNeeded){
			
			//As long as the answer List is less than 4 answers, grab the stored false answers at random and add to the List until it is 4 in length
			int positionInFalseAnswerArray = random.nextInt(listOfFalseAnswers.size());
			AnswerEnums tempAE = listOfFalseAnswers.remove(positionInFalseAnswerArray);
			answerListForAQuestionNONRandomized.add(tempAE);
		}
			
		//Randomize the answers so they are always in a different order when retrieved on the webpage. 
		while (finalListOfRandomlyOrderedAnswerEnumsForAQuestion.size() < totalNumberOfAnswersNeeded) {
			int tempIndex = random.nextInt(answerListForAQuestionNONRandomized.size());
			AnswerEnums tempQE = answerListForAQuestionNONRandomized.remove(tempIndex);
			finalListOfRandomlyOrderedAnswerEnumsForAQuestion.add(tempQE);
		}
		return finalListOfRandomlyOrderedAnswerEnumsForAQuestion;
	}
	
	//GETTERS AND SETTERS
	public String getQuestionID() {
		return questionID;
	}
	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public ArrayList<AnswerEnums> getAnswerList() {
		return answerList;
	}
	public void setAnswerList(ArrayList<AnswerEnums> answerList) {
		this.answerList = answerList;
	}
	public String[] getAnswerText() {
		return answerText;
	}
	public void setAnswerText(String[] answerText) {
		this.answerText = answerText;
	}
	public String[] getAnswerIDs() {
		return answerIDs;
	}
	public void setAnswerIDs(String[] answerIDs) {
		this.answerIDs = answerIDs;
	}
}
