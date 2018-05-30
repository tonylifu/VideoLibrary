package com.school.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.school.entity.ScoreSheets;
import com.school.entity.Students;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CreateScores {
	public void createScores(TextField stdId_TF, TextField stdClass_TF, TextField term_TF, 
			TextField subjectCode_TF, TextField CA1_TF, TextField CA2_TF, TextField CA3_TF, 
			TextField examScore_TF, TextField total_TF, ComboBox<String> classLetter_TF,
			ComboBox<String> grade_TF, TextField name_TF, TextField subjectName_TF, TextField sId_TF) {
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		ScoreSheets scores = new ScoreSheets();
		
		scores.setStdId(Integer.parseInt(stdId_TF.getText()));
		scores.setStdClass(Integer.parseInt(stdClass_TF.getText()));	
		scores.setTerm(Integer.parseInt(term_TF.getText()));
		scores.setSubjectCode(Integer.parseInt(subjectCode_TF.getText()));
		scores.setCA1(Double.parseDouble(CA1_TF.getText()));
		scores.setCA2(Double.parseDouble(CA2_TF.getText()));
		scores.setCA3(Double.parseDouble(CA3_TF.getText()));
		scores.setExamScore(Double.parseDouble(examScore_TF.getText()));
		scores.setTotal(Double.parseDouble(CA1_TF.getText())+Double.parseDouble(CA2_TF.getText())
		+Double.parseDouble(CA3_TF.getText())+Double.parseDouble(examScore_TF.getText()));
		scores.setClassLetter((String)classLetter_TF.getValue());
		
		double totalScore = Double.parseDouble(CA1_TF.getText())+Double.parseDouble(CA2_TF.getText())
		+Double.parseDouble(CA3_TF.getText())+Double.parseDouble(examScore_TF.getText());
	
		if(totalScore >= 70.0) {
			scores.setGrade("A");
		}
		else if(totalScore >= 65 && totalScore < 70) {
			scores.setGrade("B");
		}
		else if (totalScore >= 55 && totalScore < 65) {
			scores.setGrade("C");
		}
		else if (totalScore >= 40 && totalScore < 55) {
			scores.setGrade("D");
		}
		else {
			scores.setGrade("F");
		}
		
		//scores.setGrade((String)grade_TF.getValue());
		
		scores.setName(name_TF.getText());
		scores.setSubjectName(subjectName_TF.getText());
		scores.setSId(Long.parseLong(sId_TF.getText()));
		
		entitymanager.persist(scores);
		entitymanager.getTransaction().commit();
		
		entitymanager.close();
		emfactory.close();
	}
	
	public void createScoresSID(int sstd, long sId) {
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		Students std = entitymanager.find(Students.class, sstd);
		
		std.setScores_sId(sId);
		
		entitymanager.persist(std);
		entitymanager.getTransaction().commit();
		
		entitymanager.close();
		emfactory.close();
	}
}
