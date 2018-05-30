package video.repository.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.persistence.Entity;
import javax.persistence.Id;

import javafx.scene.control.DatePicker;

@Entity
public class UploadedVideos {
	@Id
	private String vid;
	private String fileName, fileLocation, fileTitle, fileClass, date, user;
	
	public UploadedVideos(String vid, String fileName, String fileLocation, String fileTitle, 
			String fileClass, String date, String user) {
		super();
		this.vid = vid;
		this.fileName = fileName;
		this.fileLocation = fileLocation;
		this.fileTitle = fileTitle;
		this.fileClass = fileClass;
		this.date = date;
		this.user = user;
	}
	public UploadedVideos() {
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
	public void setDate(DatePicker date) {
		LocalDate d1 = date.getValue();
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
		String d2 = formatter.format(d1);
		this.date = d2;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	
}
