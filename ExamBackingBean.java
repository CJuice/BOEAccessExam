package gov.texas.tceq.BOEAccessTest;

import java.io.Serializable;
import java.time.LocalDate;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="examBean")
@ViewScoped
public class ExamBackingBean implements Serializable {
	/**
	 * This class is the backing bean to the exam webform, upon initial load.
	 * 
	 * @author CSchaefe
	 * @since 1.8
	 */
	
	//STATE
//	boolean doLogging = true;
	private static final long serialVersionUID = 1L;
	private String dateLabel = "Today: ";
	private String userName = "";
	private String userNameLabel = "Name: ";
	private String userEmail = "@tceq.texas.gov";
	private String userEmailLabel = "Email: ";
	private LocalDate today;
	private ExamParts examParts;
	
	//NO ARG CONSTRUCTOR FOR BEAN
	/**
	 * Instantiates ExamParts and builds the collection of exam Parts
	 */
	public ExamBackingBean() {
		this.today = LocalDate.now();
		this.examParts = new ExamParts();
	}
	
	//METHODS

	//GETTERS & SETTERS
	public String getDateLabel() {
		return dateLabel;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNameLabel() {
		return userNameLabel;
	}
	
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserEmailLabel() {
		return userEmailLabel;
	}
	
	public LocalDate getToday() {
		return today;
	}

	public void setToday(LocalDate today) {
		this.today = today;
	}
		
	public ExamParts getExamParts() {
		return examParts;
	}

	public void setExamParts(ExamParts examParts) {
		this.examParts = examParts;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setDateLabel(String dateLabel) {
		this.dateLabel = dateLabel;
	}

	public void setUserNameLabel(String userNameLabel) {
		this.userNameLabel = userNameLabel;
	}

	public void setUserEmailLabel(String userEmailLabel) {
		this.userEmailLabel = userEmailLabel;
	}

	@Override
	public String toString() {
		return "ExamBackingBean [dateLabel=" + dateLabel + ", userName="
				+ userName + ", userNameLabel=" + userNameLabel
				+ ", userEmail=" + userEmail + ", userEmailLabel="
				+ userEmailLabel + ", today=" + today + ", examParts="
				+ examParts + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateLabel == null) ? 0 : dateLabel.hashCode());
		result = prime * result
				+ ((examParts == null) ? 0 : examParts.hashCode());
		result = prime * result + ((today == null) ? 0 : today.hashCode());
		result = prime * result
				+ ((userEmail == null) ? 0 : userEmail.hashCode());
		result = prime * result
				+ ((userEmailLabel == null) ? 0 : userEmailLabel.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		result = prime * result
				+ ((userNameLabel == null) ? 0 : userNameLabel.hashCode());
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
		ExamBackingBean other = (ExamBackingBean) obj;
		if (dateLabel == null) {
			if (other.dateLabel != null)
				return false;
		} else if (!dateLabel.equals(other.dateLabel))
			return false;
		if (examParts == null) {
			if (other.examParts != null)
				return false;
		} else if (!examParts.equals(other.examParts))
			return false;
		if (today == null) {
			if (other.today != null)
				return false;
		} else if (!today.equals(other.today))
			return false;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		if (userEmailLabel == null) {
			if (other.userEmailLabel != null)
				return false;
		} else if (!userEmailLabel.equals(other.userEmailLabel))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userNameLabel == null) {
			if (other.userNameLabel != null)
				return false;
		} else if (!userNameLabel.equals(other.userNameLabel))
			return false;
		return true;
	}
	
		
}