package com.school.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.school.entity.*;

import javafx.scene.control.TextField;

public class DeleteStudent {
	public void deleteStudent(TextField id) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		String id2 = id.getText();
		int id3 = Integer.parseInt(id2);
		
		Students std = entitymanager.find(Students.class, id3);
		entitymanager.remove(std);
		
		entitymanager.getTransaction().commit();
		
		entitymanager.close();
		emfactory.close();
	}
}
