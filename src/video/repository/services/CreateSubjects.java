package com.school.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.school.entity.*;
import javafx.scene.control.TextField;

public class CreateSubjects {
	public void createSubject(TextField subjectId, TextField subjectName, TextField teacher) {
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		SubjectNames std = new SubjectNames();
		std.setSubjectCode(Integer.parseInt(subjectId.getText()));
		std.setSubjectName(subjectName.getText());
		std.setTeacher(teacher.getText());		
		
		entitymanager.persist(std);
		entitymanager.getTransaction().commit();
		
		entitymanager.close();
		emfactory.close();
	}
	

}
