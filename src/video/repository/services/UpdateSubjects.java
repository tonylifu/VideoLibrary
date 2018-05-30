package com.school.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.school.entity.SubjectNames;
import javafx.scene.control.TextField;

public class UpdateSubjects {
	public void updateSubjects(TextField subjectIdTF, TextField subjectNameTF, TextField teacherNameTF) {
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		String id = subjectIdTF.getText();
		int id2 = Integer.parseInt(id);
		
		SubjectNames subj = entitymanager.find(SubjectNames.class, id2);
		subj.setSubjectName(subjectNameTF.getText());
		subj.setTeacher(teacherNameTF.getText());
		
		entitymanager.persist(subj);
		entitymanager.getTransaction().commit();
		
		entitymanager.close();
		emfactory.close();
		
	}

}
