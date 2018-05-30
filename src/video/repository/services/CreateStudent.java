package com.school.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.school.entity.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateStudent {
	public void createStudent(TextField id, ComboBox<String> disability, TextField surName, TextField otherNames, DatePicker dateOfBirth, 
			TextField placeOfBirth,ComboBox<String> sex,TextField nationality, TextField state, TextField lga, TextField village, 
			TextArea address, TextField fatherName, TextField fatherPhone, TextField fatherOccupation, 
			TextField fatherReligion, TextField motherName, TextField motherPhone, TextField motherOccupation,
			TextField motherReligion, TextField guardianName, TextField guardianPhone, 
			TextField guardianOccupation, TextField guardianReligion, TextField previousSchool,
			TextField lastClass, TextField classSeeking, TextField classOffered, TextArea ifDisabled, 
			TextField ICE, DatePicker dateOfReg) {
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		Students std = new Students();
		std.setId(id);
		std.setDisability(disability);
		std.setSurName(surName);
		std.setOtherNames(otherNames);
		std.setDateOfBirth(dateOfBirth);
		std.setPlaceOfBirth(placeOfBirth);
		std.setSex(sex);
		std.setNationality(nationality);
		std.setState(state);
		std.setLga(lga);
		std.setVillage(village);
		std.setAddress(address);
		std.setFatherName(fatherName);
		std.setFatherOccupation(fatherOccupation);
		std.setFatherPhone(fatherPhone);
		std.setFatherReligion(fatherReligion);
		std.setMotherName(motherName);
		std.setMotherOccupation(motherOccupation);
		std.setMotherPhone(motherPhone);
		std.setMotherReligion(motherReligion);
		std.setGuardianName(guardianName);
		std.setGuardianOccupation(guardianOccupation);
		std.setGuardianPhone(guardianPhone);
		std.setGuardianReligion(guardianReligion);
		std.setPreviousSchool(previousSchool);
		std.setLastClass(lastClass);
		std.setClassSeeking(classSeeking);
		std.setClassOffered(classOffered);
		std.setIfDisabled(ifDisabled);
		std.setICE(ICE);
		std.setDateOfReg(dateOfReg);
		
		entitymanager.persist(std);
		entitymanager.getTransaction().commit();
		
		entitymanager.close();
		emfactory.close();
	}
	
}
