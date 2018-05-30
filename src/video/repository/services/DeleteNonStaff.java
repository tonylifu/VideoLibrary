package video.repository.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import video.repository.entity.NonStaff;

public class DeleteNonStaff {
	public void deletePatient(String cardNo) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		NonStaff patient = entitymanager.find(NonStaff.class, cardNo);
	
		entitymanager.remove(patient);
		
		entitymanager.getTransaction().commit();
		
		entitymanager.close();
		emfactory.close();
	}
}
