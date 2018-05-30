package video.repository.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javafx.scene.control.DatePicker;
import video.repository.entity.UploadedVideos;

public class CreateUploadedVideos {
	public void createUploadedVideo(String vid, String fileName, String fileLocation, String fileTitle, 
			String fileClass, DatePicker date, String user) {
		EntityManagerFactory emfactory = 
				Persistence.createEntityManagerFactory("SchoolMgtSystem");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		UploadedVideos video = new UploadedVideos();
		
		video.setVid(vid);
		video.setFileName(fileName);
		video.setFileLocation(fileLocation);
		video.setFileTitle(fileTitle);
		video.setFileClass(fileClass);
		video.setDate(date);
		video.setUser(user);
		
		entitymanager.persist(video);
		entitymanager.getTransaction().commit();
		
		entitymanager.close();
		emfactory.close();
	}

}
