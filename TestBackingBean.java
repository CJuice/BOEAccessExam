package gov.texas.tceq.BOEAccessTest;

import java.io.Serializable;
import java.time.LocalDate;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="testBean")
@ViewScoped
public class TestBackingBean implements Serializable {
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
	private TestParts testParts;
	
	//NO ARG CONSTRUCTOR FOR BEAN
	/**
	 * Instantiates TestParts and builds the collection of exam Parts
	 */
	public TestBackingBean() {
		this.today = LocalDate.now();
		this.testParts = new TestParts();
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
		
	public TestParts getTestParts() {
		return testParts;
	}

	public void setTestParts(TestParts testParts) {
		this.testParts = testParts;
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
		return "TestBackingBean [dateLabel=" + dateLabel + ", userName="
				+ userName + ", userNameLabel=" + userNameLabel
				+ ", userEmail=" + userEmail + ", userEmailLabel="
				+ userEmailLabel + ", today=" + today + ", testParts="
				+ testParts + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateLabel == null) ? 0 : dateLabel.hashCode());
		result = prime * result
				+ ((testParts == null) ? 0 : testParts.hashCode());
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
		TestBackingBean other = (TestBackingBean) obj;
		if (dateLabel == null) {
			if (other.dateLabel != null)
				return false;
		} else if (!dateLabel.equals(other.dateLabel))
			return false;
		if (testParts == null) {
			if (other.testParts != null)
				return false;
		} else if (!testParts.equals(other.testParts))
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