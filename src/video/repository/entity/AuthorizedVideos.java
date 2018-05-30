package video.repository.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.persistence.Entity;
import javax.persistence.Id;

import javafx.scene.control.DatePicker;

@Entity
public class AuthorizedVideos {
	@Id
	private String vid;
	private String fileName, fileLocation, fileTitle, fileClass, date, authorizedDate, user;
	
	public AuthorizedVideos(String vid, String fileName, String fileLocation, String fileTitle, 
			String fileClass, String date, String authorizedDate, String user) {
		super();
		this.vid = vid;
		this.fileName = fileName;
		this.fileLocation = fileLocation;
		this.fileTitle = fileTitle;
		this.fileClass = fileClass;
		this.date = date;
		this.authorizedDate = authorizedDate;
		this.user = user;
	}
	public AuthorizedVideos() {
		super();
	}
	
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileLocation() {
		return fileLocation;
	}
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	public String getFileTitle() {
		return fileTitle;
	}
	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}
	public String getFileClass() {
		return fileClass;
	}
	public void setFileClass(String fileClass) {
		this.fileClass = fileClass;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String myDate) {
		this.date = myDate;
	}
	public String getAuthorizedDate() {
		return authorizedDate;
	}
	public void setAuthorizedDate(LocalDate authorizedDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
		String d2 = formatter.format(authorizedDate);
		this.authorizedDate = d2;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	
}
