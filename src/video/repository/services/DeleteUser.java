package com.school.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.school.entity.Users;

import javafx.scene.control.TextField;

public class DeleteUser {
	public void deleteUser(TextField userName) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		String id = userName.getText();
		Users user = entitymanager.find(Users.class, id);
		entitymanager.remove(user);
		
		entitymanager.getTransaction().commit();
		
		entitymanager.close();
		emfactory.close();
	}

}
