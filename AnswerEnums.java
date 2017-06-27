package gov.texas.tceq.BOEAccessTest;

import java.io.Serializable;


public enum AnswerEnums implements Serializable {
	/**
	 * This class of enums serves to store and create the answer objects for use in the exam.
	 * @author CSchaefe
	 * @since 1.8
	 */
	//ENUM OBJECTS FOR ALL ANSWERS: Part I
	P1_QA_ANSWER1("P1_QA_ANSWER1", "P1_QA", "A BOE password that I created and manage.<span class=\"hidden\">P1_QA_ANSWER1</span>", false),
	P1_QA_ANSWER2("P1_QA_ANSWER2", "P1_QA", "Oracle password that I use to connect to my program area database.<span class=\"hidden\">P1_QA_ANSWER2</span>", false),
	P1_QA_ANSWER3("P1_QA_ANSWER3", "P1_QA", "Novell, the password I use to access my computer first thing in the morning.<span class=\"hidden\">P1_QA_ANSWER3</span>", true),
	P1_QA_ANSWER4("P1_QA_ANSWER4", "P1_QA", "My Outlook password.<span class=\"hidden\">P1_QA_ANSWER4</span>", false),
	P1_QA_ANSWER5("P1_QA_ANSWER5", "P1_QA", "My CEEDS password.<span class=\"hidden\">P1_QA_ANSWER5</span>", false),
	P1_QA_ANSWER6("P1_QA_ANSWER6", "P1_QA", "My firewall password.<span class=\"hidden\">P1_QA_ANSWER6</span>", false),
	P1_QB_ANSWER1("P1_QB_ANSWER1", "P1_QB", "It magically appears to the right of the screen.<span class=\"hidden\">P1_QB_ANSWER1</span>", false),
	P1_QB_ANSWER2("P1_QB_ANSWER2", "P1_QB", "Under the schedule menu bar normally located on the left, labeled ‘Database Logon’.<span class=\"hidden\">P1_QB_ANSWER2</span>", true),
	P1_QB_ANSWER3("P1_QB_ANSWER3", "P1_QB", "My computer automatically logons me into the database that I am using.<span class=\"hidden\">P1_QB_ANSWER3</span>", false),
	P1_QB_ANSWER4("P1_QB_ANSWER4", "P1_QB", "When logon onto Outlook.<span class=\"hidden\">P1_QB_ANSWER4</span>", false),
	P1_QB_ANSWER5("P1_QB_ANSWER5", "P1_QB", "When updating my computer software.<span class=\"hidden\">P1_QB_ANSWER5</span>", false),
	P1_QB_ANSWER6("P1_QB_ANSWER6", "P1_QB", "When accessing the regional viewer workspace.<span class=\"hidden\">P1_QB_ANSWER6</span>", false),
	P1_QC_ANSWER1("P1_QC_ANSWER1", "P1_QC", "Novell, the password I use to access my computer first thing in the morning.<span class=\"hidden\">P1_QC_ANSWER1</span>", false),
	P1_QC_ANSWER2("P1_QC_ANSWER2", "P1_QC", "Oracle password that I use to connect to my program area database.<span class=\"hidden\">P1_QC_ANSWER2</span>", true),
	P1_QC_ANSWER3("P1_QC_ANSWER3", "P1_QC", "A BOE password that I created and manage.<span class=\"hidden\">P1_QC_ANSWER3</span>", false),
	P1_QC_ANSWER4("P1_QC_ANSWER4", "P1_QC", "My Outlook password.<span class=\"hidden\">P1_QC_ANSWER4</span>", false),
	P1_QC_ANSWER5("P1_QC_ANSWER5", "P1_QC", "My CCEDS password.<span class=\"hidden\">P1_QC_ANSWER5</span>", false),
	P1_QC_ANSWER6("P1_QC_ANSWER6", "P1_QC", "My firewall password.<span class=\"hidden\">P1_QC_ANSWER6</span>", false),	
	P1_QD_ANSWER1("P1_QD_ANSWER1", "P1_QD", "No. To change my password I report to the Security Information Officer.<span class=\"hidden\">P1_QD_ANSWER1</span>", false),
	P1_QD_ANSWER2("P1_QD_ANSWER2", "P1_QD", "No. To change my WindowsAD/Novell password I need to contact the Help Desk.<span class=\"hidden\">P1_QD_ANSWER2</span>", true),
	P1_QD_ANSWER3("P1_QD_ANSWER3", "P1_QD", "No. I tell my supervisor, and he or she will reset.<span class=\"hidden\">P1_QD_ANSWER3</span>", false),
	P1_QD_ANSWER4("P1_QD_ANSWER4", "P1_QD", "No. It magically updates once it fails 5 times.<span class=\"hidden\">P1_QD_ANSWER4</span>", false),
	P1_QD_ANSWER5("P1_QD_ANSWER5", "P1_QD", "No. I have to reinstall BOE.<span class=\"hidden\">P1_QD_ANSWER5</span>", false),
	P1_QD_ANSWER6("P1_QD_ANSWER6", "P1_QD", "No. Once blocked out I can never use BOE again.<span class=\"hidden\">P1_QD_ANSWER6</span>", false),	
//	P1_QE_ANSWER1("P1_QE_ANSWER1", "P1_QE", "No. They are separate systems and would require the user to sync them.<span class=\"hidden\">P1_QE_ANSWER1</span>", true),
//	P1_QE_ANSWER2("P1_QE_ANSWER2", "P1_QE", "Yes. BOE can change all my passwords.<span class=\"hidden\">P1_QE_ANSWER2</span>", false),
//	P1_QE_ANSWER3("P1_QE_ANSWER3", "P1_QE", "Yes. BOE can autofill all my reports to use one logon/password.<span class=\"hidden\">P1_QE_ANSWER3</span>", false),
//	P1_QE_ANSWER4("P1_QE_ANSWER4", "P1_QE", "Yes. BOE can set up all my reports to have a generic logon/password.<span class=\"hidden\">P1_QE_ANSWER4</span>", false),
//	P1_QE_ANSWER5("P1_QE_ANSWER5", "P1_QE", "Yes. BOE will update my account and I have to reinstall BOE.<span class=\"hidden\">P1_QE_ANSWER5</span>", false),
//	P1_QE_ANSWER6("P1_QE_ANSWER6", "P1_QE", "Yes. BOE can remove the need for any password requirement.<span class=\"hidden\">P1_QE_ANSWER6</span>", false),

	//ENUM OBJECTS FOR ALL ANSWERS: Part II
	P2_QA_ANSWER1("P2_QA_ANSWER1", "P2_QA", "Call the Help Desk.<span class=\"hidden\">P2_QA_ANSWER1</span>", false),
	P2_QA_ANSWER2("P2_QA_ANSWER2", "P2_QA", "Ask my co-worker.<span class=\"hidden\">P2_QA_ANSWER2</span>", false),
	P2_QA_ANSWER3("P2_QA_ANSWER3", "P2_QA", "Locate the desired report in the folder right click on it and select the Schedule option.<span class=\"hidden\">P2_QA_ANSWER3</span>", true),
	P2_QA_ANSWER4("P2_QA_ANSWER4", "P2_QA", "Locate the desired report in the folder, click on More Actions in the top menu bar and select the Schedule option.<span class=\"hidden\">P2_QA_ANSWER4</span>", true),
	P2_QA_ANSWER5("P2_QA_ANSWER5", "P2_QA", "Call ERT and ask them to generate the report.<span class=\"hidden\">P2_QA_ANSWER5</span>", false),
	P2_QA_ANSWER6("P2_QA_ANSWER6", "P2_QA", "Report are in BOE only for viewing.<span class=\"hidden\">P2_QA_ANSWER6</span>", false),
	P2_QB_ANSWER1("P2_QB_ANSWER1", "P2_QB", "There is no history available to view.<span class=\"hidden\">P2_QB_ANSWER1</span>", false),
	P2_QB_ANSWER2("P2_QB_ANSWER2", "P2_QB", "Call the Help Desk.<span class=\"hidden\">P2_QB_ANSWER2</span>", false),
	P2_QB_ANSWER3("P2_QB_ANSWER3", "P2_QB", "Locate the desired report in the folder, right click on it, and select the History.<span class=\"hidden\">P2_QB_ANSWER3</span>", true),
	P2_QB_ANSWER4("P2_QB_ANSWER4", "P2_QB", "Locate the desired report in the folder, click on More Actions in the top menu bar, and select the History.<span class=\"hidden\">P2_QB_ANSWER4</span>", true),
	P2_QB_ANSWER5("P2_QB_ANSWER5", "P2_QB", "Call ERT and ask them to provide you with a history.<span class=\"hidden\">P2_QB_ANSWER5</span>", false),
	P2_QB_ANSWER6("P2_QB_ANSWER6", "P2_QB", "Only management can view history.<span class=\"hidden\">P2_QB_ANSWER6</span>", false),
	P2_QC_ANSWER1("P2_QC_ANSWER1", "P2_QC", "Questions the Help Desk ask to complete a ticket request.<span class=\"hidden\">P2_QC_ANSWER1</span>", false),
	P2_QC_ANSWER2("P2_QC_ANSWER2", "P2_QC", "Requirements in the original specification of a new report.<span class=\"hidden\">P2_QC_ANSWER2</span>", false),
	P2_QC_ANSWER3("P2_QC_ANSWER3", "P2_QC", "Not all reports have prompts; sometimes they are required and other reports they may be optional, which are data selections picked before you can schedule the report.<span class=\"hidden\">P2_QC_ANSWER3</span>", true),
	P2_QC_ANSWER4("P2_QC_ANSWER4", "P2_QC", "Parameters of data selections made prior to running the report, which filters the results the user will see in the final report.<span class=\"hidden\">P2_QC_ANSWER4</span>", true),
	P2_QC_ANSWER5("P2_QC_ANSWER5", "P2_QC", "Informational questions used by Customer Support to bug you.<span class=\"hidden\">P2_QC_ANSWER5</span>", false),
	P2_QC_ANSWER6("P2_QC_ANSWER6", "P2_QC", "Selection made when you are on hold for Customer Support.<span class=\"hidden\">P2_QC_ANSWER6</span>", false),
	P2_QD_ANSWER1("P2_QD_ANSWER1", "P2_QD", "This is not an option available in BOE.<span class=\"hidden\">P2_QD_ANSWER1</span>", false),
	P2_QD_ANSWER2("P2_QD_ANSWER2", "P2_QD", "Put a notice reminder in your personal calendar.<span class=\"hidden\">P2_QD_ANSWER2</span>", false),
	P2_QD_ANSWER3("P2_QD_ANSWER3", "P2_QD", "Under Schedule select Recurrence. Using the drop down arrow, select the best recurrence option for your desired results. Remember to update all recurring reports when your password changes, you must also complete the database logon to schedule recurring reports.<span class=\"hidden\">P2_QD_ANSWER3</span>", true),
	P2_QD_ANSWER4("P2_QD_ANSWER4", "P2_QD", "Call ERT and ask them to run the report for you each month.<span class=\"hidden\">P2_QD_ANSWER4</span>", false),
	P2_QD_ANSWER5("P2_QD_ANSWER5", "P2_QD", "Set a reminder on you cell phone to alert you to schedule the report.<span class=\"hidden\">P2_QD_ANSWER5</span>", false),
	P2_QD_ANSWER6("P2_QD_ANSWER6", "P2_QD", "Ask you co-worker to remind you.<span class=\"hidden\">P2_QD_ANSWER6</span>", false),
	P2_QE_ANSWER1("P2_QE_ANSWER1", "P2_QE", "Instance Title changes could be helpful in generating a report for individuals prompt selection. Adding the person name in front of the original report title provides clear understanding of the data in the report.<span class=\"hidden\">P2_QE_ANSWER1</span>", true),
	P2_QE_ANSWER2("P2_QE_ANSWER2", "P2_QE", "Removing the assigned title in the Instance Title block makes ittn hard to locate the report when it fails.<span class=\"hidden\">P2_QE_ANSWER2</span>", false),
	P2_QE_ANSWER3("P2_QE_ANSWER3", "P2_QE", "You would never want to change the Instance Title name.<span class=\"hidden\">P2_QE_ANSWER3</span>", false),
	P2_QE_ANSWER4("P2_QE_ANSWER4", "P2_QE", "In BOE there is not selection process to change the Instance Title.<span class=\"hidden\">P2_QE_ANSWER4</span>", false),
	P2_QE_ANSWER5("P2_QE_ANSWER5", "P2_QE", "It makes the report look better with clever Title names.<span class=\"hidden\">P2_QE_ANSWER5</span>", false),
	P2_QE_ANSWER6("P2_QE_ANSWER6", "P2_QE", "The Instance Title changes the data in the report.<span class=\"hidden\">P2_QE_ANSWER6</span>", false),

	//ENUM OBJECTS FOR ALL ANSWERS: Part III
	P3_QA_ANSWER1("P3_QA_ANSWER1", "P3_QA", "Yes. Under Schedule select formats; use the drop down to select best format choice.<span class=\"hidden\">P3_QA_ANSWER1</span>", true),
	P3_QA_ANSWER2("P3_QA_ANSWER2", "P3_QA", "No. Each report format is set up to deliver the best results based on the initial design.<span class=\"hidden\">P3_QA_ANSWER2</span>", false),
	P3_QA_ANSWER3("P3_QA_ANSWER3", "P3_QA", "No. The report stops working if you change the format option.<span class=\"hidden\">P3_QA_ANSWER3</span>", false),
	P3_QA_ANSWER4("P3_QA_ANSWER4", "P3_QA", "No. It magically know what is the best format for your needs.<span class=\"hidden\">P3_QA_ANSWER4</span>", false),
	P3_QA_ANSWER5("P3_QA_ANSWER5", "P3_QA", "No. BOE does not allow for format options to be changed.<span class=\"hidden\">P3_QA_ANSWER5</span>", false),
	P3_QA_ANSWER6("P3_QA_ANSWER6", "P3_QA", "Yes. You have to ask your supervisor to submit a request to change the format.<span class=\"hidden\">P3_QA_ANSWER6</span>", false),
	P3_QB_ANSWER1("P3_QB_ANSWER1", "P3_QB", "Under the Schedule select destination; use the drop down to select Email. You must always have a From: and it should be BOEXI@tceq.texas.gov, To: is your intended recipient(s).<span class=\"hidden\">P3_QB_ANSWER1</span>", true),
	P3_QB_ANSWER2("P3_QB_ANSWER2", "P3_QB", "Under the Schedule select destination; use the drop down to select email, and check “use default settings” to have the report email to you.<span class=\"hidden\">P3_QB_ANSWER2</span>", true),
	P3_QB_ANSWER3("P3_QB_ANSWER3", "P3_QB", "Under the Schedule select destination, use the drop down to select Email. Complete From: BOEXI@tceq.texas.gov, To: your email address, Cc: other email addresses you are sending the report to.<span class=\"hidden\">P3_QB_ANSWER3</span>", true),
	P3_QB_ANSWER4("P3_QB_ANSWER4", "P3_QB", "BOE does not allow reports to be emailed.<span class=\"hidden\">P3_QB_ANSWER4</span>", false),
	P3_QB_ANSWER5("P3_QB_ANSWER5", "P3_QB", "Outlook does not connect with BOE to accept reports.<span class=\"hidden\">P3_QB_ANSWER5</span>", false),
	P3_QB_ANSWER6("P3_QB_ANSWER6", "P3_QB", "Security concerning the firewall does not allow reports to be emailed.<span class=\"hidden\">P3_QB_ANSWER6</span>", false),	
	P3_QC_ANSWER1("P3_QC_ANSWER1", "P3_QC", "PDF format.<span class=\"hidden\">P3_QC_ANSWER1</span>", true),
	P3_QC_ANSWER2("P3_QC_ANSWER2", "P3_QC", "Crystal Reports format.<span class=\"hidden\">P3_QC_ANSWER2</span>", false),
	P3_QC_ANSWER3("P3_QC_ANSWER3", "P3_QC", "XML format.<span class=\"hidden\">P3_QC_ANSWER3</span>", false),
	P3_QC_ANSWER4("P3_QC_ANSWER4", "P3_QC", "RTF format.<span class=\"hidden\">P3_QC_ANSWER4</span>", false),
	P3_QC_ANSWER5("P3_QC_ANSWER5", "P3_QC", "JPG format.<span class=\"hidden\">P3_QC_ANSWER5</span>", false),
	P3_QC_ANSWER6("P3_QC_ANSWER6", "P3_QC", "QPT format.<span class=\"hidden\">P3_QC_ANSWER6</span>", false),	
	P3_QD_ANSWER1("P3_QD_ANSWER1", "P3_QD", "Crystal Reports format.<span class=\"hidden\">P3_QD_ANSWER1</span>", true),
	P3_QD_ANSWER2("P3_QD_ANSWER2", "P3_QD", "XML format.<span class=\"hidden\">P3_QD_ANSWER2</span>", false),
	P3_QD_ANSWER3("P3_QD_ANSWER3", "P3_QD", "Microsoft Word format.<span class=\"hidden\">P3_QD_ANSWER3</span>", false),
	P3_QD_ANSWER4("P3_QD_ANSWER4", "P3_QD", "Microsoft Excel format.<span class=\"hidden\">P3_QD_ANSWER4</span>", false),
	P3_QD_ANSWER5("P3_QD_ANSWER5", "P3_QD", "Microsoft Access format.<span class=\"hidden\">P3_QD_ANSWER5</span>", false),
	P3_QD_ANSWER6("P3_QD_ANSWER6", "P3_QD", "Adobe Structured Report format.<span class=\"hidden\">P3_QD_ANSWER6</span>", false),	
	P3_QE_ANSWER1("P3_QE_ANSWER1", "P3_QE", "In the My Favorites folder in the left pane of BOE.<span class=\"hidden\">P3_QE_ANSWER1</span>", true),
	P3_QE_ANSWER2("P3_QE_ANSWER2", "P3_QE", "On My Desktop.<span class=\"hidden\">P3_QE_ANSWER2</span>", false),
	P3_QE_ANSWER3("P3_QE_ANSWER3", "P3_QE", "In the Internet Explorer Favorites Bar.<span class=\"hidden\">P3_QE_ANSWER3</span>", false),
	P3_QE_ANSWER4("P3_QE_ANSWER4", "P3_QE", "In any folder in BOE; I am able to save shortcuts in any folder I choose.<span class=\"hidden\">P3_QE_ANSWER4</span>", false),
	P3_QE_ANSWER5("P3_QE_ANSWER5", "P3_QE", "In the shortcuts folder on my local server.<span class=\"hidden\">P3_QE_ANSWER5</span>", false),
	P3_QE_ANSWER6("P3_QE_ANSWER6", "P3_QE", "In the report summary folder in BOX-T.<span class=\"hidden\">P3_QE_ANSWER6</span>", false),

	//ENUM OBJECTS FOR ALL ANSWERS: Part IV
	P4_QA_ANSWER1("P4_QA_ANSWER1", "P4_QA", "Right click on the Failed report, and select instance detail. Review the error message. Once you have the error message, look it up on the Failed messages under ‘Common Business Objects Enterprise Errors.’<span class=\"hidden\">P4_QA_ANSWER1</span>", true),
	P4_QA_ANSWER2("P4_QA_ANSWER2", "P4_QA", "Right click the report; select History. Under status, find the failed report, and then right click on the failed report. Select View > Instance Details and review the error message. Once you have the error message, look it up on the Failed messages under ‘Common Business Objects Enterprise Errors’<span class=\"hidden\">P4_QA_ANSWER2</span>", true),
	P4_QA_ANSWER3("P4_QA_ANSWER3", "P4_QA", "Tell my supervisor my fail status code.<span class=\"hidden\">P4_QA_ANSWER3</span>", false),
	P4_QA_ANSWER4("P4_QA_ANSWER4", "P4_QA", "Google my fail status code.<span class=\"hidden\">P4_QA_ANSWER4</span>", false),
	P4_QA_ANSWER5("P4_QA_ANSWER5", "P4_QA", "Just attempt to run the report again and don’t worry about it failing.<span class=\"hidden\">P4_QA_ANSWER5</span>", false),
	P4_QA_ANSWER6("P4_QA_ANSWER6", "P4_QA", "Call ERT with the fail status code.<span class=\"hidden\">P4_QA_ANSWER6</span>", false),
	P4_QB_ANSWER1("P4_QB_ANSWER1", "P4_QB", "The user that scheduled the recurring report is responsible for updating the recurring report by either deleting the old version or replacing it with the update password when a password expires.<span class=\"hidden\">P4_QB_ANSWER1</span>", true),
	P4_QB_ANSWER2("P4_QB_ANSWER2", "P4_QB", "Nothing needs to be done no one cares how many recurring reports are running and failing daily.<span class=\"hidden\">P4_QB_ANSWER2</span>", false),
	P4_QB_ANSWER3("P4_QB_ANSWER3", "P4_QB", "My supervisor gets a report showing all my failed recurring reports.<span class=\"hidden\">P4_QB_ANSWER3</span>", false),
	P4_QB_ANSWER4("P4_QB_ANSWER4", "P4_QB", "Once my password expires all my recurring report disappears.<span class=\"hidden\">P4_QB_ANSWER4</span>", false),
	P4_QB_ANSWER5("P4_QB_ANSWER5", "P4_QB", "Just schedule a new recurring report and don’t worry about it all the failing reports.<span class=\"hidden\">P4_QB_ANSWER5</span>", false),
	P4_QB_ANSWER6("P4_QB_ANSWER6", "P4_QB", "ERT will clean out failed reports.<span class=\"hidden\">P4_QB_ANSWER6</span>", false),
	;
	
	//STATE
	private String answerID;
	private String questionID;
	private String answerText;
	private Boolean validAnswer;
	
	//CONSTRUCTOR
	private AnswerEnums(String answerID, String questionID, String answerText, Boolean validAnswer){
		this.answerID = answerID;
		this.questionID = "p" + questionID.substring(1);
		this.answerText = answerText;
		this.validAnswer = validAnswer;
	}
	//METHODS

	//GETTERS AND SETTERS
	public String getAnswerID() {
		return answerID;
	}

	public void setAnswerID(String answerID) {
		this.answerID = answerID;
	}

	public String getQuestionID() {
		return questionID;
	}

	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public Boolean getValidAnswer() {
		return validAnswer;
	}

	public void setValidAnswer(Boolean validAnswer) {
		this.validAnswer = validAnswer;
	}
}
