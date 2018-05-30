package video.repository.services;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javafx.scene.control.DatePicker;
import video.repository.entity.AuthorizedVideos;

public class CreateAuthorizedVideos {
	public void createAuthorizedVideo(String vid, String fileName, String fileLocation, String fileTitle, 
			String fileClass, String date, LocalDate currentDate, String user) {
		EntityManagerFactory emfactory = 
				Persistence.createEntityManagerFactory("SchoolMgtSystem");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		AuthorizedVideos video = new AuthorizedVideos();
		
		video.setVid(vid);
		video.setFileName(fileName);
		video.setFileLocation(fileLocation);
		video.setFileTitle(fileTitle);
		video.setFileClass(fileClass);
		video.setDate(date);
		video.setAuthorizedDate(currentDate);
		video.setUser(user);
		
		entitymanager.persist(video);
		entitymanager.getTransaction().commit();
		
		entitymanager.close();
		emfactory.close();
	}

}
