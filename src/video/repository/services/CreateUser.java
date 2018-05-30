package video.repository.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import video.repository.entity.Users;

public class CreateUser {
	public void createUser(TextField userName, TextField userStaffID, TextField name, PasswordField password, 
			String userClass, TextField dept) {
		EntityManagerFactory emfactory = 
				Persistence.createEntityManagerFactory("SchoolMgtSystem");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		Users user = new Users();
		
		user.setUserName(userName.getText());
		user.setUserStaffID(userStaffID.getText().toUpperCase());
		user.setName(name.getText());
		user.setPassword(userName.getText());
		user.setUserClass(userClass);
		user.setDept(dept.getText());
		
		entitymanager.persist(user);
		entitymanager.getTransaction().commit();
		
		entitymanager.close();
		emfactory.close();
	}

}
