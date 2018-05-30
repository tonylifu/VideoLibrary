package com.school.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.school.entity.ScoreSheets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class UpdateScores {
	public void updateScoreSheets(TextField sId, TextField stdClass, TextField term, TextField CA1, 
			TextField CA2, TextField CA3, TextField examScore, ComboBox<String> classLetter,
			ComboBox<String> grade) {
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		long id = Long.parseLong(sId.getText());
		
		ScoreSheets scores = entitymanager.find(ScoreSheets.class, id);
		
		scores.setStdClass(Integer.parseInt(stdClass.getText()));
		scores.setTerm(Integer.parseInt(term.getText()));
		scores.setCA1(Double.parseDouble(CA1.getText()));
		scores.setCA2(Double.parseDouble(CA2.getText()));
		scores.setCA3(Double.parseDouble(CA3.getText()));
		scores.setExamScore(Double.parseDouble(examScore.getText()));
		scores.setClassLetter(classLetter.getValue());
		scores.setTotal(Double.parseDouble(CA1.getText())+Double.parseDouble(CA2.getText())
				+Double.parseDouble(CA3.getText())+Double.parseDouble(examScore.getText()));
		double totalScore = Double.parseDouble(CA1.getText())+Double.parseDouble(CA2.getText())
			+Double.parseDouble(CA3.getText())+Double.parseDouble(examScore.getText());
		
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
		
		entitymanager.persist(scores);
		entitymanager.getTransaction().commit();
		
		entitymanager.close();
		emfactory.close();
		
	}

}
