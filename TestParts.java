package gov.texas.tceq.BOEAccessTest;

import java.io.Serializable;
import java.util.ArrayList;

public class TestParts implements Serializable {
	/**
	 * This class creates the test that is instantiated from the TestBackingBean and loaded in the index.xhtml
	 * @author CSchaefe
	 * @since 1.8
	 */
	
	//STATE
	private static final long serialVersionUID = 1L;	
	private static final int NUMBER_OF_TEST_PARTS = 4;
	private static final int NUMBER_OF_QUESTIONS_PART_1 = 4;
	private static final int NUMBER_OF_QUESTIONS_PART_2 = 4;
	private static final int NUMBER_OF_QUESTIONS_PART_3 = 2;
	private static final int NUMBER_OF_QUESTIONS_PART_4 = 1;
	private static final int[] PART_QUESTION_COUNT = {NUMBER_OF_QUESTIONS_PART_1, NUMBER_OF_QUESTIONS_PART_2, NUMBER_OF_QUESTIONS_PART_3, NUMBER_OF_QUESTIONS_PART_4};
	private ArrayList<Part> examPartsArrayList = new ArrayList<>();
	
	//NO ARG CONSTRUCTOR FOR BEAN
	/**
	 * default no-arg constructor. builds an arrayList of exam Parts that together make up the whole exam
	 */
	public TestParts() {
		for (int i = 1; i <= NUMBER_OF_TEST_PARTS; i++) {
			Part part = new Part(i, PART_QUESTION_COUNT[i-1]);
			this.examPartsArrayList.add(part);
		}
	}
	
	//METHODS
	
	//GETTERS & SETTERS
	public static int getNumberOfTestParts() {
		return NUMBER_OF_TEST_PARTS;
	}

	public static int getNumberOfQuestionsPart1() {
		return NUMBER_OF_QUESTIONS_PART_1;
	}

	public static int getNumberOfQuestionsPart2() {
		return NUMBER_OF_QUESTIONS_PART_2;
	}

	public static int getNumberOfQuestionsPart3() {
		return NUMBER_OF_QUESTIONS_PART_3;
	}

	public static int getNumberOfQuestionsPart4() {
		return NUMBER_OF_QUESTIONS_PART_4;
	}

	public ArrayList<Part> getExamPartsArrayList() {
		return examPartsArrayList;
	}

	public void setExamPartsArrayList(ArrayList<Part> examPartsArrayList) {
		this.examPartsArrayList = examPartsArrayList;
	}

	public static int[] getPartQuestionCount() {
		return PART_QUESTION_COUNT;
	}
	
	//TOSTRING, EQUALS, HASHCODE

	@Override
	public String toString() {
		return "TestParts [examPartsArrayList=" + examPartsArrayList + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((examPartsArrayList == null) ? 0 : examPartsArrayList
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
		TestParts other = (TestParts) obj;
		if (examPartsArrayList == null) {
			if (other.examPartsArrayList != null)
				return false;
		} else if (!examPartsArrayList.equals(other.examPartsArrayList))
			return false;
		return true;
	}
	
}
