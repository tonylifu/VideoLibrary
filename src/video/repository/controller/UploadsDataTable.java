package video.repository.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;

public class UploadsDataTable {
	@FXML private SimpleStringProperty vid_Data;
	@FXML private SimpleStringProperty title_Data, genre_Data, file_Data, location_Data, user_Data;
	
	public UploadsDataTable(String vid, String title, String genre, String file, String location, String user) {
		this.vid_Data = new SimpleStringProperty(vid);
		this.title_Data = new SimpleStringProperty(title);
		this.genre_Data = new SimpleStringProperty(genre);
		this.file_Data = new SimpleStringProperty(file);
		this.location_Data = new SimpleStringProperty(location);
		this.user_Data = new SimpleStringProperty(user);
	}

	public String getVid_Data() {
		return vid_Data.get();
	}
	public void setVid_Data(String vid) {
		vid_Data.set(vid);
	}
	
	public String getTitle_Data() {
		return title_Data.get();
	}
	public void setTitle_Data(String title) {
		title_Data.set(title);
	}
	
	public String getGenre_Data() {
		return genre_Data.get();
	}
	public void setGenre_Data(String genre) {
		genre_Data.set(genre);
	}
	public String getFile_Data() {
		return file_Data.get();
	}
	public void setFile_Data(String file) {
		file_Data.set(file);
	}
	
	public String getLocation_Data() {
		return location_Data.get();
	}
	
	public void setLocation_Data(String location) {
		location_Data.set(location);
	}
	
	public String getUser_Data() {
		return user_Data.get();
	}
	public void setUser_Data(String user) {
		user_Data.set(user);
	}
	
}
