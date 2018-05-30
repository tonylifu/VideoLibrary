package com.school.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.school.entity.Users;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateUser {
	public void createUser(TextField userName, TextField name, PasswordField password, 
			TextField userClass, TextField dept) {
		EntityManagerFactory emfactory = 
				Persistence.createEntityManagerFactory("SchoolMgtSystem");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		Users user = new Users();
		user.setUserName(userName.getText());
		user.setName(name.getText());
		user.setPassword(password.getText());
		user.setUserClass(userClass.getText());
		user.setDept(dept.getText());
		
		entitymanager.persist(user);
		entitymanager.getTransaction().commit();
		
		entitymanager.close();
		emfactory.close();
	}

}
