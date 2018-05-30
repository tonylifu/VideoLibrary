package com.school.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.school.entity.*;

import javafx.scene.control.TextField;

public class DeleteScores {
	public void deleteScores(TextField sId) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		long id = Long.parseLong(sId.getText());
		
		ScoreSheets scores = entitymanager.find(ScoreSheets.class, id);
		
		entitymanager.remove(scores);
		
		entitymanager.getTransaction().commit();
		
		entitymanager.close();
		emfactory.close();
	}
	
	public void deleteScores(long sId) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		ScoreSheets scores = entitymanager.find(ScoreSheets.class, sId);
		
		entitymanager.remove(scores);
		
		entitymanager.getTransaction().commit();
		
		entitymanager.close();
		emfactory.close();
	}
	
	//Delete Scores_SID from student record set
	public void deleteScores_SID(int stdID,long SID) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		Students std_scores = entitymanager.find(Students.class, stdID);
		
		std_scores.getScores_sId().remove(SID);
		
		entitymanager.getTransaction().commit();
		
		entitymanager.close();
		emfactory.close();
	}
}
