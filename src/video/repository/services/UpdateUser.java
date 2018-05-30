package video.repository.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import video.repository.entity.Users;

public class UpdateUser {
	public void updateUser(TextField userName, TextField userStaffID, TextField name, PasswordField password, 
			ComboBox<String> userClass, TextField dept) {
		EntityManagerFactory emfactory = 
				Persistence.createEntityManagerFactory("SchoolMgtSystem");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		String username = userName.getText();
		
		Users user = entitymanager.find(Users.class, username);
		
		user.setUserClass(userClass.getValue());
		user.setDept(dept.getText());
		
		entitymanager.persist(user);
		entitymanager.getTransaction().commit();
		
		entitymanager.close();
		emfactory.close();
	}

}
