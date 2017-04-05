package gov.texas.tceq.BOEAccessTest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Part implements Serializable {
	/**
	 * Builds each Part of the exam, at time of design there were 4 parts.
	 * Each part contains question enums, which contain their answer enums.
	 * @author CSchaefe
	 * @since 1.8
	 */
	
	//STATE
	private static final long serialVersionUID = 1L;
	private String[] questionTextArrayForPart;
	private String[] questionIDsArrayForPart;
	private Map<String, String[]> answersTextMapForPart = new LinkedHashMap<>();
	private Map<String, String[]> answersIDMapForPart = new LinkedHashMap<>();
	private List<String> keyListOfQuestionText;
	/*keyListOfQuestionID's was built for and used during testing. Is not used in production but may be needed for future versions*/
	private List<String> keyListOfQuestionIDs;
	private ArrayList<QuestionEnums> questionEnumsInThisPart = new ArrayList<>();
	private ArrayList<String> trueAnswersArrayList;
	
	//CONSTRUCTORS
	/**
	 * default no-arg constructor for bean creation
	 */
	public Part(){		
	}
	/**
	 * Creates a Part by calling the methods that build the pieces that make up a Part of an exam.
	 * The pieces of a Part are relevant radomized question enums, containing their relevant randomized answer enums
	 * @param partNumber This is the number equivalent to each Part of the test. At time of design there were 4 Parts to the exam.
	 * @param numberOfQuestionsInThePart This is the number of questions in a Part. At time of design it was (Part:Questions) 1:4, 2:4, 3:2, 4:1
	 */
	public Part(int partNumber, int numberOfQuestionsInThePart) {
		buildQuestionEnumsArrayForAPart(partNumber, numberOfQuestionsInThePart);
		buildQuestionTextArray(getQuestionEnumsInThisPart());
		//buildQuestionIDArray(getQuestionEnumsInThisPart());
		buildQuestionAnswersTextArraysInMapForEachPart(getQuestionEnumsInThisPart());
		buildTrueAnswersArrayList(getQuestionEnumsInThisPart());
	}

	//METHODS
	/**
	 * Builds an array of question enums for a Part, based on the Part 
	 * number (example: 1, 2, 3, 4), which determines the possible questions to be served as well as the
	 * number of questions to serve in that Part, as predetermined by the requirements/design.
	 * 
	 * @param partNumber This is the number equivalent to each Part of the test. At time of design there were 4 Parts to the exam.
	 * @param numberOfQuestionsInThePart This is the number of questions in a Part. At time of design it was (Part:Questions) 1:4, 2:4, 3:2, 4:1
	 */
	public void buildQuestionEnumsArrayForAPart(int partNumber, int numberOfQuestionsInThePart){
		String partString = "P" + partNumber;
		ArrayList<QuestionEnums> questionListForAPartNONRandomized = new ArrayList<QuestionEnums>();	
		Random random = new Random();
		ArrayList<QuestionEnums> finalListOfRandomlyOrderedQuestionEnumsForAPart = new ArrayList<>();

		//Iterate through QuestionEnums and grab those for the Part of interest
		//TODO: Enums only instantiated the first time the app is run. On reload they are not rebuilt so the answers are not rebuilt.
		//		In future, could try to resolve this so answers are randomized EVERY time the app is reloaded, not just the first load.
		for(QuestionEnums var : QuestionEnums.values()){
			String questionID = var.getQuestionID();
			if(questionID.contains(partString)){
				questionListForAPartNONRandomized.add(var);
			}
			else{continue;}
		}
		while (finalListOfRandomlyOrderedQuestionEnumsForAPart.size() < numberOfQuestionsInThePart) {
			int tempIndex = random.nextInt(questionListForAPartNONRandomized.size());
			QuestionEnums tempQE = questionListForAPartNONRandomized.remove(tempIndex);
			finalListOfRandomlyOrderedQuestionEnumsForAPart.add(tempQE);
		}
		this.questionEnumsInThisPart = finalListOfRandomlyOrderedQuestionEnumsForAPart;
	}
	/**
	 * Builds an array containing the text for each question in the Part. This text is displayed in the jsf page.
	 * @param questionEnumsForThePart Each Part has a collection of Question Enums. This param is that collection.
	 */
	public void buildQuestionTextArray(ArrayList<QuestionEnums> questionEnumsForThePart){
			String[] tempArrayOfQuestionTextForEachPart = new String[questionEnumsForThePart.size()];
			for(int j = 0; j < questionEnumsForThePart.size(); j++){
				String questionString = questionEnumsForThePart.get(j).getQuestionText();
				tempArrayOfQuestionTextForEachPart[j] = questionString;
			}
			setQuestionTextArrayForPart(tempArrayOfQuestionTextForEachPart);
	}
	/**
	 * Builds an array containing the text ID for each question in the Part; was used in
	 * testing to identify the questions when loaded in the web.
	 * @param questionEnumsForThePart Each Part has a collection of Question Enums. This param is that collection.
	 */
	public void buildQuestionIDArray(ArrayList<QuestionEnums> questionEnumsForThePart){
			String[] tempArrayOfQuestionIDsForEachPart = new String[questionEnumsForThePart.size()];
			for(int i = 0; i < questionEnumsForThePart.size(); i++){
				String questionString = questionEnumsForThePart.get(i).getQuestionID();
				tempArrayOfQuestionIDsForEachPart[i] = questionString;
			}
			setQuestionIDsArrayForPart(tempArrayOfQuestionIDsForEachPart);
	}
	/**
	 * Builds two Maps; one Map containing a questions text (key) and an array of text answers (value), 
	 * the other Map containing a questions ID (key) and an array of answer ID's (value)
	 * @param questionEnumsForThePart Each Part has a collection of Question Enums. This param is that collection.
	 */
	public void buildQuestionAnswersTextArraysInMapForEachPart(ArrayList<QuestionEnums> questionEnumsForThePart){
		for(int i = 0; i < questionEnumsForThePart.size(); i++){
			Map<String, String[]> tempMapOfQuestionTextandAnswerTextArray = new LinkedHashMap<>();
			Map<String, String[]> tempMapOfQuestionIDandAnswerIDArray = new LinkedHashMap<>();			
			for(int j = 0; j < questionEnumsForThePart.size(); j++){
				String questionText = questionEnumsForThePart.get(j).getQuestionText();
				String[] answersTextArray = questionEnumsForThePart.get(j).getAnswerText();
				String questionIDArray = questionEnumsForThePart.get(j).getQuestionID();
				String[] answersIDArray = questionEnumsForThePart.get(j).getAnswerIDs();
				
				tempMapOfQuestionTextandAnswerTextArray.put(questionText, answersTextArray);
				tempMapOfQuestionIDandAnswerIDArray.put(questionIDArray, answersIDArray);
			}			
			setAnswersTextMapForPart(tempMapOfQuestionTextandAnswerTextArray);
			setAnswersIDMapForPart(tempMapOfQuestionIDandAnswerIDArray);
			this.keyListOfQuestionText = new ArrayList<String>(tempMapOfQuestionTextandAnswerTextArray.keySet());
			this.keyListOfQuestionIDs = new ArrayList<String>(tempMapOfQuestionIDandAnswerIDArray.keySet());
		}
	}
	/**
	 * Builds a list of all of the true answers for the Part, to be used in processing
	 * a users exam by comparing their answers to the possible correct answers.
	 * @param questionEnumsForThePart Each Part has a collection of Question Enums. This param is that collection.
	 */
	public void buildTrueAnswersArrayList(ArrayList<QuestionEnums> questionEnumsForThePart){
		ArrayList<String> tempAnswersArrayList = new ArrayList<>();
		for(int i = 0; i < questionEnumsForThePart.size(); i++){
			ArrayList<AnswerEnums> answers = questionEnumsForThePart.get(i).getAnswerList();
			for(int j = 0; j < answers.size(); j++){
				if(answers.get(j).getValidAnswer()){
					tempAnswersArrayList.add(answers.get(j).getAnswerID());
				}
				else{continue;}
			}			
		}
		this.trueAnswersArrayList = tempAnswersArrayList;
	}

	//GETTERS AND SETTERS
	public List<String> getKeyListOfQuestionText() {
		return keyListOfQuestionText;
	}
	public List<String> getKeyListOfQuestionIDs() {
		return keyListOfQuestionIDs;
	}
	public String[] getQuestionTextArrayForPart() {
		return questionTextArrayForPart;
	}
	public void setQuestionTextArrayForPart(String[] questionTextArrayForPart) {
		this.questionTextArrayForPart = questionTextArrayForPart;
	}
	public String[] getQuestionIDsArrayForPart() {
		return questionIDsArrayForPart;
	}
	public void setQuestionIDsArrayForPart(String[] questionIDsArrayForPart) {
		this.questionIDsArrayForPart = questionIDsArrayForPart;
	}
	public Map<String, String[]> getAnswersTextMapForPart() {
		return answersTextMapForPart;
	}
	public void setAnswersTextMapForPart(Map<String, String[]> answersTextMapForPart) {
		this.answersTextMapForPart = answersTextMapForPart;
	}
	public Map<String, String[]> getAnswersIDMapForPart() {
		return answersIDMapForPart;
	}
	public void setAnswersIDMapForPart(Map<String, String[]> answersIDMapForPart) {
		this.answersIDMapForPart = answersIDMapForPart;
	}
	public ArrayList<QuestionEnums> getQuestionEnumsInThisPart() {
		return questionEnumsInThisPart;
	}
	public void setQuestionEnumsInThisPart(
			ArrayList<QuestionEnums> questionEnumsInThisPart) {
		this.questionEnumsInThisPart = questionEnumsInThisPart;
	}
	public ArrayList<String> getTrueAnswersArrayList() {
		return trueAnswersArrayList;
	}
	public void setTrueAnswersArrayList(ArrayList<String> trueAnswersArrayList) {
		this.trueAnswersArrayList = trueAnswersArrayList;
	}
	
	@Override
	public String toString() {
		return "Part [questionTextArrayForPart="
				+ Arrays.toString(questionTextArrayForPart)
				+ ", questionIDsArrayForPart="
				+ Arrays.toString(questionIDsArrayForPart)
				+ ", answersTextMapForPart=" + answersTextMapForPart
				+ ", answersIDMapForPart=" + answersIDMapForPart
				+ ", keyListOfQuestionText=" + keyListOfQuestionText
				+ ", keyListOfQuestionIDs=" + keyListOfQuestionIDs
				+ ", questionEnumsInThisPart=" + questionEnumsInThisPart
				+ ", trueAnswersArrayList=" + trueAnswersArrayList + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((answersIDMapForPart == null) ? 0 : answersIDMapForPart
						.hashCode());
		result = prime
				* result
				+ ((answersTextMapForPart == null) ? 0 : answersTextMapForPart
						.hashCode());
		result = prime
				* result
				+ ((keyListOfQuestionIDs == null) ? 0 : keyListOfQuestionIDs
						.hashCode());
		result = prime
				* result
				+ ((keyListOfQuestionText == null) ? 0 : keyListOfQuestionText
						.hashCode());
		result = prime
				* result
				+ ((questionEnumsInThisPart == null) ? 0
						: questionEnumsInThisPart.hashCode());
		result = prime * result + Arrays.hashCode(questionIDsArrayForPart);
		result = prime * result + Arrays.hashCode(questionTextArrayForPart);
		result = prime
				* result
				+ ((trueAnswersArrayList == null) ? 0 : trueAnswersArrayList
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
		Part other = (Part) obj;
		if (answersIDMapForPart == null) {
			if (other.answersIDMapForPart != null)
				return false;
		} else if (!answersIDMapForPart.equals(other.answersIDMapForPart))
			return false;
		if (answersTextMapForPart == null) {
			if (other.answersTextMapForPart != null)
				return false;
		} else if (!answersTextMapForPart.equals(other.answersTextMapForPart))
			return false;
		if (keyListOfQuestionIDs == null) {
			if (other.keyListOfQuestionIDs != null)
				return false;
		} else if (!keyListOfQuestionIDs.equals(other.keyListOfQuestionIDs))
			return false;
		if (keyListOfQuestionText == null) {
			if (other.keyListOfQuestionText != null)
				return false;
		} else if (!keyListOfQuestionText.equals(other.keyListOfQuestionText))
			return false;
		if (questionEnumsInThisPart == null) {
			if (other.questionEnumsInThisPart != null)
				return false;
		} else if (!questionEnumsInThisPart
				.equals(other.questionEnumsInThisPart))
			return false;
		if (!Arrays.equals(questionIDsArrayForPart,
				other.questionIDsArrayForPart))
			return false;
		if (!Arrays.equals(questionTextArrayForPart,
				other.questionTextArrayForPart))
			return false;
		if (trueAnswersArrayList == null) {
			if (other.trueAnswersArrayList != null)
				return false;
		} else if (!trueAnswersArrayList.equals(other.trueAnswersArrayList))
			return false;
		return true;
	}
}
