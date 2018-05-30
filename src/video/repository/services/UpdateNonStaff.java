package video.repository.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javafx.scene.control.DatePicker;
import video.repository.entity.NonStaff;

public class UpdateNonStaff {
	public void updatePatient(String cardNo, String surName, String otherNames, DatePicker dob, String sex, 
			String maritalStatus, String nationality, String state, String lga, String village, 
			String address, String email, String phone1, String phone2, String nextOfKinName, 
			String nextOfKinAdd, String nextOfKinPhone, DatePicker dor, String user) {
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		NonStaff patient = entitymanager.find(NonStaff.class, cardNo);
		
		//patient.setCardNo(cardNo);
		patient.setSurName(surName);
		patient.setOtherNames(otherNames);
		patient.setDob(dob);
		patient.setSex(sex);
		patient.setMaritalStatus(maritalStatus);
		patient.setNationality(nationality);
		patient.setState(state);
		patient.setLga(lga);
		patient.setVillage(village);
		patient.setAddress(address);
		patient.setEmail(email);
		patient.setPhone1(phone1);
		patient.setPhone2(phone2);
		patient.setNextOfKinName(nextOfKinName);
		patient.setNextOfKinAdd(nextOfKinAdd);
		patient.setNextOfKinPhone(nextOfKinPhone);
		patient.setDor(dor);
		patient.setUser(user);
		
		entitymanager.persist(patient);
	
		entitymanager.getTransaction().commit();
		
		entitymanager.close();
		emfactory.close();
		
	}
	
}
