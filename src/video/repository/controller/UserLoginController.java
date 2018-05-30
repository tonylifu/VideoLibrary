package com.school.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.school.entity.ScoreSheets;
import com.school.entity.Students;
import com.school.entity.SubjectNames;
import com.school.entity.Users;
import com.school.services.CreateScores;
import com.school.services.CreateStudent;
import com.school.services.CreateSubjects;
import com.school.services.CreateUser;
import com.school.services.DeleteScores;
import com.school.services.DeleteStudent;
import com.school.services.DeleteSubjects;
import com.school.services.DeleteUser;
import com.school.services.UpdateScores;
import com.school.services.UpdateStudent;
import com.school.services.UpdateSubjects;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UserLoginController extends Application {
	@FXML TextField userNameLogin_TF;
	@FXML PasswordField passwordLogin_TF;
	@FXML private Button loginBtn;
	
	@FXML 
	public void loginButton(ActionEvent event) {
		try {
			Users user = new Users();
			
			EntityManagerFactory emfactory = 
					Persistence.createEntityManagerFactory("SchoolMgtSystem");
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
			
			String userName = userNameLogin_TF.getText();
			String pwd = passwordLogin_TF.getText();
			
			user = entitymanager.find(Users.class, userName);
			if(pwd.equals(user.getPassword())) {
				dispName = user.getName();
				dispUserName = user.getUserName();
			
				try {
					Stage primaryStage = (Stage)loginBtn.getScene().getWindow();
					BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("homeLaunch.fxml"));
					primaryStage.setTitle("Home Page");
					Scene scene = new Scene(root,520,520);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene);
					primaryStage.show();
				} 
				catch(Exception e) {
					userNameLogin_TF.setText("ERROR: "+e.getLocalizedMessage());
				}
			}
				
			else {
				userNameLogin_TF.setText("Invalid login credentials!");
			}
		}
		catch(Exception e) {
			userNameLogin_TF.setText("Error: "+ e.getMessage());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("login.fxml"));
			Scene scene = new Scene(root,600,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Login");
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	// homeLaunch Experiment
@FXML TextField msgLabel;
	
	@FXML
	public void initialize(ActionEvent event) {
		// TODO Auto-generated method stub
		sexTF.setValue("Female");
		sexTF.setItems(sex);
		disabilityTF.setValue("No");
		disabilityTF.setItems(disable);
		sexTF2.setValue("Female");
		sexTF2.setItems(sex);
		disabilityTF2.setValue("No");
		disabilityTF2.setItems(disable);
		
		/*sexTF.getItems().removeAll(sexTF.getItems());
		//sexTF.getItems().setAll(sex);
		sexTF.getItems().addAll(sex);
		sexTF.getSelectionModel().select("Female");
		disabilityTF.getItems().removeAll(disabilityTF.getItems());
		disabilityTF.getItems().addAll(disable);
		disabilityTF.getSelectionModel().select("No");
		
		sexTF2.getItems().removeAll(sexTF2.getItems());
		sexTF2.getItems().addAll(sex);
		sexTF2.getSelectionModel().select("Female");
		disabilityTF2.getItems().removeAll(disabilityTF2.getItems());
		disabilityTF2.getItems().addAll(disable);
		disabilityTF2.getSelectionModel().select("No");*/
	}
	
	/*@FXML
	public void initialize() {
		sexTF.setValue("Female");
		sexTF.setItems(sex);
		disabilityTF.setValue("No");
		disabilityTF.setItems(disable);
		sexTF2.setValue("Female");
		sexTF2.setItems(sex);
		disabilityTF2.setValue("No");
		disabilityTF2.setItems(disable);
		
		sexTF.getItems().removeAll(sexTF.getItems());
		//sexTF.getItems().setAll(sex);
		//sexTF.setItems(sex);
		sexTF.getItems().addAll(sex);
		sexTF.getSelectionModel().select("Female");
		disabilityTF.getItems().removeAll(disabilityTF.getItems());
		disabilityTF.getItems().addAll(disable);
		disabilityTF.getSelectionModel().select("No");
		
		//sexTF2.getItems().removeAll(sexTF2.getItems());
		sexTF2.getItems().addAll(sex);
		sexTF2.getSelectionModel().select("Female");
		disabilityTF2.getItems().removeAll(disabilityTF2.getItems());
		disabilityTF2.getItems().addAll(disable);
		disabilityTF2.getSelectionModel().select("No");
	}*/

	
	@FXML private Button createNewStudentButton;
	@FXML private Button returnCreateButton;
	@FXML private Button findStudentButton;
	@FXML private Button updateStudentButton;
	@FXML private Button returnUpdateButton;
	@FXML private Button deleteStudentButton;
	@FXML private Button returnDeleteButton;
	@FXML private Button enterScoreSheetsButton;
	@FXML private Button returnScoreSheetsButton;
	@FXML private Button addDeleteSubjectsButton;
	@FXML private Button returnAddDeleteSubjectsButton;
	@FXML private Button changePwdButton;
	@FXML private Button returnChangePwdButton;
	
	@FXML
	private void handleButtonAction(ActionEvent event) throws IOException{
	
		if(event.getSource() == createNewStudentButton) {
			try {
				Stage primaryStage = (Stage)createNewStudentButton.getScene().getWindow();
				BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("studentEntryEntity.fxml"));
				primaryStage.setTitle("Student Details Entry");
				Scene scene = new Scene(root,600,400);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				msgLabel.setText("Error: "+ e.getMessage());
			}
		}
		else {
			Stage primaryStage = (Stage)returnCreateButton.getScene().getWindow();
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("homeLaunch.fxml"));
		
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}
	
	@FXML
	private void handleButtonAction2(ActionEvent event) throws IOException{
		
		if(event.getSource() == findStudentButton) {
			try {
				Stage primaryStage = (Stage)findStudentButton.getScene().getWindow();
				BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("updateStudentEntity.fxml"));
				primaryStage.setTitle("Find Student");
				Scene scene = new Scene(root,600,400);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			} 
			catch(Exception e) {
				msgLabel.setText("Error: "+ e.getMessage());
			}
		}
		else if(event.getSource() == updateStudentButton) {
			try {
				Stage primaryStage = (Stage)updateStudentButton.getScene().getWindow();
				BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("updateStudentEntity.fxml"));
				primaryStage.setTitle("Update Student Details");
				Scene scene = new Scene(root,600,400);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			} 
			catch(Exception e) {
				msgLabel.setText("Error: "+ e.getMessage());
			}
		}
		else {
			Stage primaryStage = (Stage)returnUpdateButton.getScene().getWindow();
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("homeLaunch.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			}
		}
	
	@FXML
	private void handleButtonAction3(ActionEvent event) throws IOException{
	
		if(event.getSource() == deleteStudentButton) {
			try {
				Stage primaryStage = (Stage)deleteStudentButton.getScene().getWindow();
				BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("deleteStudentEntity.fxml"));
				primaryStage.setTitle("Delete Student");
				Scene scene = new Scene(root,600,400);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				msgLabel.setText("Error: "+ e.getMessage());
			}
		}
		else {
			Stage primaryStage = (Stage)returnDeleteButton.getScene().getWindow();
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("homeLaunch.fxml"));
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}
	
	
	@FXML
	private void handleButtonAction4(ActionEvent event) throws IOException{
	
		if(event.getSource() == enterScoreSheetsButton) {
			try {
				Stage primaryStage = (Stage)enterScoreSheetsButton.getScene().getWindow();
				BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("scoreSheetsEntity.fxml"));
				Scene scene = new Scene(root,620,550);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("Student Scores Manager");
				primaryStage.show();
			} catch(Exception e) {
				msgLabel.setText("Error: "+ e.getMessage());
			}
		}
		else {
			Stage primaryStage = (Stage)returnScoreSheetsButton.getScene().getWindow();
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("homeLaunch.fxml"));
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}
	
	@FXML
	private void handleButtonAction5(ActionEvent event) throws IOException{
	
		if(event.getSource() == addDeleteSubjectsButton) {
			try {
				Stage primaryStage = (Stage)addDeleteSubjectsButton.getScene().getWindow();
				BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("subjectsEntity.fxml"));
				Scene scene = new Scene(root,530,500);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("Subjects Entry");
				primaryStage.show();
			} catch(Exception e) {
				msgLabel.setText("Error: "+ e.getMessage());
			}
		}
		else {
			Stage primaryStage = (Stage)returnAddDeleteSubjectsButton.getScene().getWindow();
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("homeLaunch.fxml"));
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}
	
	@FXML private Button logOffHomeButton;
	public void quitPlatformButton(ActionEvent event) {
		try{
			if(event.getSource() == logOffHomeButton) {
				try {
					Stage primaryStage = (Stage)logOffHomeButton.getScene().getWindow();
					BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("login.fxml"));
					primaryStage.setTitle("Login");
					Scene scene = new Scene(root,600,500);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene);
					primaryStage.show();
				} catch(Exception e) {
					msgLabel.setText("Error: "+ e.getMessage());
				}
			}
			//else{
				//}
			}
		catch(Exception e) {
			msgLabel.setText("Error: "+ e.getMessage());
		}
	}
	
	@FXML private Button adminButton;
	@FXML private Button logoutAdminButton;
	static String dispName;
	static String dispUserName;
	
	@FXML
	private void handleButtonAction6(ActionEvent event) throws IOException{
	
		if(event.getSource() == adminButton) {
			
			EntityManagerFactory emfactory = 
					Persistence.createEntityManagerFactory("SchoolMgtSystem");
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
		
			String userName1 = userNameLogin_TF.getText();
			String pwd1 = passwordLogin_TF.getText();
			
			Users user1 = entitymanager.find(Users.class, userName1);
			
			try {
				if(user1.getPassword().equals(pwd1)){
					try {
						if(user1.getUserClass().equalsIgnoreCase("admin")) {
				
							try {
								Stage primaryStage = (Stage)adminButton.getScene().getWindow();
								BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("users.fxml"));
								primaryStage.setTitle("Users Entry");
								Scene scene = new Scene(root,600,400);
								scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
								primaryStage.setScene(scene);
								primaryStage.show();
							} 
							catch(Exception e) {
								userNameLogin_TF.setText("Error: "+e.getLocalizedMessage());
							}
						}
						userNameLogin_TF.setText("Only admin can login here...");
					}
					catch(Exception e) {
						userNameLogin_TF.setText("Only admin can login here..."+ e.getLocalizedMessage());
					}
				}
			}
			catch (Exception e) {
				userNameLogin_TF.setText("Invalid Admin User: "+ e.getMessage());
			}
			entitymanager.getTransaction().commit();
			entitymanager.close();
			emfactory.close();
		}
		else {
			Stage primaryStage = (Stage)logoutAdminButton.getScene().getWindow();
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("login.fxml"));
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}
	
	@FXML
	private void handleButtonAction7(ActionEvent event) throws IOException{
	
		if(event.getSource() == changePwdButton) {
			try {				
				Stage primaryStage = (Stage)changePwdButton.getScene().getWindow();
				BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("changePassword.fxml"));
				primaryStage.setTitle("Change Your Password");
				Scene scene = new Scene(root,600,400);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				msgLabel.setText("Error: "+ e.getMessage());
			}
		}
		else {
			Stage primaryStage = (Stage)returnChangePwdButton.getScene().getWindow();
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("homeLaunch.fxml"));
		
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}
	
	@FXML
	public void displayLoginUser(ActionEvent event) {
		msgLabel.setText("Login User: "+ dispName);
	}
	
	@FXML
	public void quitPlatformButton2(ActionEvent event) {
		Platform.exit();
	}
	
	
	ObservableList<String> sex = FXCollections.observableArrayList("Male","Female");
	ObservableList<String> disable = FXCollections.observableArrayList("Yes","No");
	
	@FXML private TextField idTF;
	@FXML
	private TextField surNameTF, otherNamesTF, placeOfBirthTF, nationalityTF, 
	stateTF, lgaTF, villageTF, fatherNameTF, fatherPhoneTF, 
	fatherOccupationTF, fatherReligionTF, motherNameTF, motherPhoneTF, motherOccupationTF,
	motherReligionTF, guardianNameTF, guardianPhoneTF, guardianOccupationTF, guardianReligionTF, 
	previousSchoolTF, lastClassTF, classSeekingTF, classOfferedTF, ICETF;
	@FXML private DatePicker dateOfBirthTF, dateOfRegTF;
	@FXML private ComboBox<String> sexTF, disabilityTF;
	@FXML private TextArea addressTF, ifDisabledTF;
	
	//ObservableList<String> sex2 = FXCollections.observableArrayList("Male","Female");
	//ObservableList<String> disable2 = FXCollections.observableArrayList("Yes","No");
	
	@FXML private TextField idTF2;
	@FXML private TextField surNameTF2, otherNamesTF2, placeOfBirthTF2, nationalityTF2, 
	stateTF2, lgaTF2, villageTF2, fatherNameTF2, fatherPhoneTF2, 
	fatherOccupationTF2, fatherReligionTF2, motherNameTF2, motherPhoneTF2, motherOccupationTF2,
	motherReligionTF2, guardianNameTF2, guardianPhoneTF2, guardianOccupationTF2, guardianReligionTF2, 
	previousSchoolTF2, lastClassTF2, classSeekingTF2, classOfferedTF2, ICETF2;
	@FXML private DatePicker dateOfBirthTF2, dateOfRegTF2;
	@FXML private ComboBox<String> sexTF2, disabilityTF2;
	@FXML private TextArea addressTF2, ifDisabledTF2;

//StudentController
		
	@FXML
	public void SaveButton(ActionEvent event) {
		CreateStudent student = new CreateStudent();
		try {
			student.createStudent(idTF, disabilityTF, surNameTF, otherNamesTF, dateOfBirthTF, 
					placeOfBirthTF, sexTF,nationalityTF, stateTF, lgaTF, villageTF, addressTF, fatherNameTF, 
					fatherPhoneTF, fatherOccupationTF, fatherReligionTF, motherNameTF, motherPhoneTF, 
					motherOccupationTF, motherReligionTF, guardianNameTF, guardianPhoneTF, 
					guardianOccupationTF, guardianReligionTF, previousSchoolTF, lastClassTF, 
					classSeekingTF, classOfferedTF, ifDisabledTF, ICETF, dateOfRegTF);
		}
		catch(Exception e) {
			String str = e.getMessage();
			idTF.setText("Error: "+ str);
		}
		//clear form
		//idTF.clear(); 
		disabilityTF.setValue("No"); 
		surNameTF.clear();
		otherNamesTF.clear();
		dateOfBirthTF.setValue(LocalDate.now());
		placeOfBirthTF.clear();
		sexTF.setValue("Female");
		nationalityTF.clear();
		stateTF.clear();
		lgaTF.clear();
		villageTF.clear(); 
		addressTF.clear();
		fatherNameTF.clear(); 
		fatherPhoneTF.clear(); 
		fatherOccupationTF.clear(); 
		fatherReligionTF.clear();
		motherNameTF.clear(); 
		motherPhoneTF.clear();
		motherOccupationTF.clear();
		motherReligionTF.clear();
		guardianNameTF.clear();
		guardianPhoneTF.clear();
		guardianOccupationTF.clear(); 
		guardianReligionTF.clear();
		previousSchoolTF.clear(); 
		lastClassTF.clear();
		classSeekingTF.clear();
		classOfferedTF.clear(); 
		ifDisabledTF.clear();
		ICETF.clear();
		dateOfRegTF.setValue(LocalDate.now());
	}
	

//UpdateStudentController 
	
	@FXML
	public void updateStdButton(ActionEvent event) {
		
		UpdateStudent student = new UpdateStudent();
		try {
			student.updateStudent(idTF2, disabilityTF2, surNameTF2, otherNamesTF2, dateOfBirthTF2, 
					placeOfBirthTF2, sexTF2,nationalityTF2, stateTF2, lgaTF2, villageTF2, addressTF2, fatherNameTF2, 
					fatherPhoneTF2, fatherOccupationTF2, fatherReligionTF2, motherNameTF2, motherPhoneTF2, 
					motherOccupationTF2, motherReligionTF2, guardianNameTF2, guardianPhoneTF2, 
					guardianOccupationTF2, guardianReligionTF2, previousSchoolTF2, lastClassTF2, 
					classSeekingTF2, classOfferedTF2, ifDisabledTF2, ICETF2, dateOfRegTF2);
		}
		catch(Exception e) {
			String str = e.getMessage();
			idTF2.setText("Error: "+ str);
		}
		//clear form
				//idTF2.clear(); 
				disabilityTF2.setValue("No"); 
				surNameTF2.clear();
				otherNamesTF2.clear();
				dateOfBirthTF2.setValue(LocalDate.now());
				placeOfBirthTF2.clear();
				sexTF2.setValue("Female");
				nationalityTF2.clear();
				stateTF2.clear();
				lgaTF2.clear();
				villageTF2.clear(); 
				addressTF2.clear();
				fatherNameTF2.clear(); 
				fatherPhoneTF2.clear(); 
				fatherOccupationTF2.clear(); 
				fatherReligionTF2.clear();
				motherNameTF2.clear(); 
				motherPhoneTF2.clear();
				motherOccupationTF2.clear();
				motherReligionTF2.clear();
				guardianNameTF2.clear();
				guardianPhoneTF2.clear();
				guardianOccupationTF2.clear(); 
				guardianReligionTF2.clear();
				previousSchoolTF2.clear(); 
				lastClassTF2.clear();
				classSeekingTF2.clear();
				classOfferedTF2.clear(); 
				ifDisabledTF2.clear();
				ICETF2.clear();
				dateOfRegTF2.setValue(LocalDate.now());
	}
	
	
	@FXML
	public void searchStudent(ActionEvent even) {
		try {
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
			
			String id2 = idTF2.getText();
			int id3 = Integer.parseInt(id2);
			
			Students std = entitymanager.find(Students.class, id3);
			
			String dateSearch = std.getDateOfBirth();
			String date2 = std.getDateOfReg();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
			LocalDate localDate = LocalDate.parse(dateSearch, formatter);
			LocalDate localDate2 = LocalDate.parse(date2, formatter);
			
			surNameTF2.setText(std.getSurName());
			otherNamesTF2.setText(std.getOtherNames());
			dateOfBirthTF2.setValue(localDate); 
			placeOfBirthTF2.setText(std.getPlaceOfBirth());
			sexTF2.setValue(std.getSex());
			nationalityTF2.setText(std.getNationality()); 
			stateTF2.setText(std.getState());
			lgaTF2.setText(std.getLga()); 
			villageTF2.setText(std.getVillage());
			addressTF2.setText(std.getAddress());
			fatherNameTF2.setText(std.getFatherName()); 
			fatherPhoneTF2.setText(std.getFatherPhone());
			fatherOccupationTF2.setText(std.getFatherOccupation());
			fatherReligionTF2.setText(std.getFatherReligion());
			motherNameTF2.setText(std.getMotherName());
			motherPhoneTF2.setText(std.getMotherPhone());
			motherOccupationTF2.setText(std.getMotherOccupation()); 
			motherReligionTF2.setText(std.getMotherReligion()); 
			guardianNameTF2.setText(std.getGuardianName()); 
			guardianPhoneTF2.setText(std.getGuardianPhone()); 
			guardianOccupationTF2.setText(std.getGuardianOccupation());
			guardianReligionTF2.setText(std.getGuardianReligion());
			previousSchoolTF2.setText(std.getPreviousSchool());
			lastClassTF2.setText(std.getLastClass());
			classSeekingTF2.setText(std.getClassSeeking());
			classOfferedTF2.setText(std.getClassOffered());
			ifDisabledTF2.setText(std.getIfDisabled());
			ICETF2.setText(std.getICE());
			dateOfRegTF2.setValue(localDate2);
			disabilityTF2.setValue(std.getDisability());
			
			entitymanager.getTransaction().commit();
			
			entitymanager.close();
			emfactory.close();
		}
		catch(Exception e) {
			String str = e.getMessage();
			idTF2.setText("Error: "+ str);
		}
	}


//DeleteStudentController 
	
	@FXML protected TextField studentId;
	@FXML
	protected TextField studentSurNameTF, studentOtherNamesTF;
		
	@FXML
	public void DeleteButton(ActionEvent event) {
		DeleteScores scores = new DeleteScores();
		DeleteStudent student = new DeleteStudent();
		
		//Delete scores associated with student
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		int newId = Integer.parseInt(studentId.getText());
		
		Students std = entitymanager.find(Students.class, newId);
		Set<Long> alist = std.getScores_sId();
		
		if(!(alist.size() == 0)) {
			for(long element: alist) {
				if(!(alist.contains(null))){
					try {
						scores.deleteScores(element);
					}
					catch(Exception e) {
						studentId.setText("Error Deleting Scores: "+ e.getLocalizedMessage());
					}
				}
			}
		}
		
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
		
		try {
			student.deleteStudent(studentId);
		}
		catch(Exception e) {
			String str = e.getMessage();
			studentId.setText("Error Deleting student record: "+ str);
			studentSurNameTF.clear();
			studentOtherNamesTF.clear();
		}
		studentSurNameTF.clear();
		studentOtherNamesTF.clear();
	}
	
	@FXML
	public void ValidateStudent(ActionEvent event) {
		try {
			findStudent(studentId);
		}
		catch(Exception e) {
			String str = e.getMessage();
			studentId.setText("Error: "+ str);
			studentSurNameTF.clear();
			studentOtherNamesTF.clear();
		}
	}
	
	public void findStudent(TextField id) {
		String sName, oNames;
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		String id2 = id.getText();
		int id3 = Integer.parseInt(id2);
		
		Students std = entitymanager.find(Students.class, id3);
		sName = std.getSurName();
		oNames = std.getOtherNames();
		
		studentSurNameTF.setText(sName);
		studentOtherNamesTF.setText(oNames);
		
		entitymanager.getTransaction().commit();
		
		entitymanager.close();
		emfactory.close();
	}
	
	//ScoreSheets Controller
	
	@FXML protected TextField stdId_TF, stdClass_TF, term_TF, subjectCode_TF;
	@FXML private TextField CA1_TF, CA2_TF, CA3_TF, examScore_TF, total_TF, name_TF;
	@FXML private ComboBox<String> classLetter_TF, grade_TF;
	@FXML private TextField subjectName_TF;
	@FXML private TextField sId_TF;
	
		
	public void verifyIdButton(ActionEvent event) {

		verifyId(Integer.parseInt(stdId_TF.getText()));
	}
	
	public void computeTotalButton(ActionEvent event) {
		computeTotalAndGrade(Double.parseDouble(CA1_TF.getText()),Double.parseDouble(CA2_TF.getText()),
				Double.parseDouble(CA3_TF.getText()),Double.parseDouble(examScore_TF.getText()),
				stdId_TF.getText(),stdClass_TF.getText(),term_TF.getText(),
				subjectCode_TF.getText());
		
	}
	
	public void computeTotalAndGrade(double CA1,double CA2,double CA3,double exam,
			String stdId, String stdClass, String term, String subjectCode) {
		Double total = CA1 + CA2 + CA3 + exam;
		String str = String.valueOf(total);
		total_TF.setText(str);
		if(total >= 70.0) {
			grade_TF.setValue("A");
		}
		else if(total >= 65 && total < 70) {
			grade_TF.setValue("B");
		}
		else if (total >= 55 && total < 65) {
			grade_TF.setValue("C");
		}
		else if (total >= 40 && total < 55) {
			grade_TF.setValue("D");
		}
		else {
			grade_TF.setValue("F");
		}
		
	}
	
	public void verifySubjectButton(ActionEvent event) {
		verifySubjectCode(Integer.parseInt(subjectCode_TF.getText()));
	}
	
	public void saveScoresButton(ActionEvent event) {
		try {
			verifyId(Integer.parseInt(stdId_TF.getText()));
			verifySubjectCode(Integer.parseInt(subjectCode_TF.getText()));
			
			CreateScores scores = new CreateScores();
			
			String str2 = stdId_TF.getText();
			
			String str = stdId_TF.getText() + stdClass_TF.getText() + term_TF.getText()
					+ subjectCode_TF.getText();
			
			sId_TF.setText(str);
			
			scores.createScores(stdId_TF, stdClass_TF, term_TF, subjectCode_TF, CA1_TF, CA2_TF,
					CA3_TF, examScore_TF, total_TF, classLetter_TF, grade_TF,name_TF,
					subjectName_TF, sId_TF);
			
			int sstdId = Integer.parseInt(str2);
			long ssId = Long.parseLong(str);
			
			//System.out.println("TEST PRINT: "+sstdId +", "+ssId);
			
			CreateScores std = new CreateScores();
			std.createScoresSID(sstdId, ssId);
			
			//stdId_TF.clear();
			stdClass_TF.clear();
			term_TF.clear(); 
			subjectCode_TF.clear(); 
			CA1_TF.clear(); 
			CA2_TF.clear();
			CA3_TF.clear(); 
			examScore_TF.clear(); 
			total_TF.clear(); 
			name_TF.clear();
			subjectName_TF.clear();
			//sId_TF.clear();
		}
		catch(Exception e) {
			stdId_TF.setText("Error: "+e.getLocalizedMessage());
		}
		
	}
	
	public void verifyId(int stdId) {
		try {
			String sName, oNames;
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
			
			Students std = entitymanager.find(Students.class, stdId);
			sName = std.getSurName();
			oNames = std.getOtherNames();
			
			name_TF.setText(oNames+" "+sName);
			
			entitymanager.getTransaction().commit();
			
			entitymanager.close();
			emfactory.close();
		}
		catch(Exception e) {
			stdId_TF.setText("Error: "+ e.getLocalizedMessage());;
		}
	}
	
	public void verifySubjectCode(int subCode) {
		try {
			String sbj;
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
			
			SubjectNames subj = entitymanager.find(SubjectNames.class, subCode);
			sbj = subj.getSubjectName();
			
			subjectName_TF.setText(sbj);
			
			entitymanager.getTransaction().commit();
			
			entitymanager.close();
			emfactory.close();
		}
		catch(Exception e) {
			stdId_TF.setText("Error: "+ e.getMessage());
		}
	}
	
	public void validateScoresButton(ActionEvent event) {
		try {
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
			
			long id = Long.parseLong(sId_TF.getText());
			ScoreSheets scores = entitymanager.find(ScoreSheets.class, id);
			
			stdId_TF.setText(String.valueOf(scores.getStdId())); 
			stdClass_TF.setText(String.valueOf(scores.getStdClass()));
			term_TF.setText(String.valueOf(scores.getTerm()));
			subjectCode_TF.setText(String.valueOf(scores.getSubjectCode()));
			CA1_TF.setText(String.valueOf(scores.getCA1()));
			CA2_TF.setText(String.valueOf(scores.getCA2()));
			CA3_TF.setText(String.valueOf(scores.getCA3()));
			examScore_TF.setText(String.valueOf(scores.getExamScore()));
			total_TF.setText(String.valueOf(scores.getTotal()));
			classLetter_TF.setValue(scores.getClassLetter());
			grade_TF.setValue(scores.getGrade());
			name_TF.setText(scores.getName());
			subjectName_TF.setText(scores.getSubjectName());
			//sId_TF.setText(String.valueOf(scores.getSId()));
			
			entitymanager.getTransaction().commit();
			
			entitymanager.close();
			emfactory.close();
		}
		catch(Exception e) {
			stdId_TF.setText("Error: "+ e.getMessage());
			stdClass_TF.clear(); 
			term_TF.clear();
			subjectCode_TF.clear();
			CA1_TF.clear();
			CA2_TF.clear();
			CA3_TF.clear();
			examScore_TF.clear();
			total_TF.clear(); 
			classLetter_TF.setValue("A");
			grade_TF.setValue("A");
			name_TF.clear();
			subjectName_TF.clear();
		}
		
	}
	
	public void updateScoresButton(ActionEvent event) {
		try {
			UpdateScores scores = new UpdateScores();
			scores.updateScoreSheets(sId_TF, stdClass_TF, term_TF, CA1_TF, CA2_TF, CA3_TF, 
					examScore_TF, classLetter_TF, grade_TF);
		}
		catch(Exception e) {
			sId_TF.setText("Error: "+ e.getMessage());
		}
		finally {
		
			stdId_TF.clear(); 
			stdClass_TF.clear(); 
			term_TF.clear();
			subjectCode_TF.clear();
			CA1_TF.clear();
			CA2_TF.clear();
			CA3_TF.clear();
			examScore_TF.clear();
			total_TF.clear(); 
			classLetter_TF.setValue("A");
			grade_TF.setValue("A");
			name_TF.clear();
			subjectName_TF.clear();
			//sId_TF
		}
	}
	
	public void deleteScoresButton(ActionEvent event) {
		try {
			DeleteScores scores = new DeleteScores();
			scores.deleteScores(sId_TF);
			try {
				DeleteScores scores2 = new DeleteScores();
				scores2.deleteScores_SID(Integer.parseInt(stdId_TF.getText()), 
						Long.parseLong(sId_TF.getText()));
			}
			catch(Exception e) {
				sId_TF.setText("Error Deleting scores from student set: "+ e.getMessage());
			}
		}
		catch(Exception e) {
			sId_TF.setText("Error: "+ e.getMessage());
		}
		finally {
			stdId_TF.clear(); 
			stdClass_TF.clear(); 
			term_TF.clear();
			subjectCode_TF.clear();
			CA1_TF.clear();
			CA2_TF.clear();
			CA3_TF.clear();
			examScore_TF.clear();
			total_TF.clear(); 
			classLetter_TF.setValue("A");
			grade_TF.setValue("A");
			name_TF.clear();
			subjectName_TF.clear();
		}
	}
	
	//Subjects Controller
	@FXML protected TextField subjectIdTF;
	@FXML protected TextField subjectNameTF;
	@FXML protected TextField teacherNameTF;
	
	public void saveButton(ActionEvent event) {
		CreateSubjects subj = new CreateSubjects();
		try {
			subj.createSubject(subjectIdTF, subjectNameTF, teacherNameTF);
		}
		catch(Exception e) {
			subjectIdTF.setText(e.getMessage());
		}
		
		subjectNameTF.clear();
		teacherNameTF.clear();
	}
	
	public void updateSubjectButton(ActionEvent event) {
		UpdateSubjects update = new UpdateSubjects();
		update.updateSubjects(subjectIdTF, subjectNameTF, teacherNameTF);
		subjectNameTF.clear();
		teacherNameTF.clear();}
	
	public void validateSubjectButton(ActionEvent event) {
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		int id = Integer.parseInt(subjectIdTF.getText());
		
		try {
			SubjectNames validate = entitymanager.find(SubjectNames.class, id);
			
			subjectNameTF.setText(validate.getSubjectName());
			teacherNameTF.setText(validate.getTeacher());
		}
		catch(Exception e) {
			subjectIdTF.setText("Error: "+ e.getMessage());			
		}

		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
	
	public void deleteSubjectButton(ActionEvent event) {
		DeleteSubjects del = new DeleteSubjects();
		del.deleteSubject(subjectIdTF);
		subjectNameTF.clear();
		teacherNameTF.clear();
	}
	
	//CreateUser Controller
	@FXML TextField userName_TextField;
	@FXML TextField name_TextField;
	@FXML PasswordField password_TextField;
	@FXML TextField userClass_TextField;
	@FXML TextField dept_TextField;
	
	@FXML 
	public void createUserButton(ActionEvent event) {
		try {
			CreateUser user = new CreateUser();
			user.createUser(userName_TextField, name_TextField, password_TextField, 
					userClass_TextField, dept_TextField);
		}
		catch(Exception e) {
			userName_TextField.setText("Error: "+ e.getMessage());
		}
		finally {
			name_TextField.clear();
			password_TextField.clear();
			userClass_TextField.clear();
			dept_TextField.clear();
		}
	}
	
	@FXML public void verifyuserNameButton(ActionEvent event) {
		try {
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
			
			String userName = userName_TextField.getText();
			Users user = entitymanager.find(Users.class, userName);
			try {
				//userName_TextField.setText(user.getUserName());
				name_TextField.setText(user.getName());
				userClass_TextField.setText(user.getUserClass());
				dept_TextField.setText(user.getDept());
			}
			catch(Exception e) {
				userName_TextField.setText("Error: "+e.getMessage());
			}
			
			entitymanager.getTransaction().commit();
			
			entitymanager.close();
			emfactory.close();
		}
		catch(Exception e) {
			userName_TextField.setText("Error: "+e.getMessage());
			name_TextField.clear();
			userClass_TextField.clear();
			dept_TextField.clear();
		}
	}
	
	@FXML public void deleteUserNameButton(ActionEvent event) {
		DeleteUser user = new DeleteUser();
		try {
			user.deleteUser(userName_TextField);
		}
		catch(Exception e) {
			userName_TextField.setText("Error: "+e.getLocalizedMessage());
		}
		finally {
			name_TextField.clear();
			password_TextField.clear();
			userClass_TextField.clear();
			dept_TextField.clear();
		}
	}
	
	@FXML public void resetUserButton(ActionEvent event) {
		try {
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
			
			String userName = userName_TextField.getText();
			Users user = entitymanager.find(Users.class, userName);
			
			user.setPassword(user.getUserName());
			
			entitymanager.persist(user);
			entitymanager.getTransaction().commit();
			
			entitymanager.close();
			emfactory.close();
		}
		catch(Exception e) {
			userName_TextField.setText("Error: "+e.getMessage());
		}
		finally {
			name_TextField.clear();
			userClass_TextField.clear();
			dept_TextField.clear();
		}
	}
	
	//Password Change Controller
	@FXML TextField pwdUserName_TF;
	@FXML PasswordField pwdCurrent_TF;
	@FXML PasswordField pwdNew_TF;
	@FXML PasswordField pwdNewConfirm_TF;
	
	@FXML public void pwdChangeButton(ActionEvent event) {
		try {
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
			
			String userName = pwdUserName_TF.getText();
			Users user = entitymanager.find(Users.class, userName);
			
			if(pwdCurrent_TF.getText().equals(user.getPassword())) {
				if(user.getUserName().equalsIgnoreCase(dispUserName)) {
					if(pwdNew_TF.getText().equals(pwdNewConfirm_TF.getText())) {
						try {
							user.setPassword(pwdNew_TF.getText());
							pwdUserName_TF.setText("Password Change Successful!");
						}
						catch(Exception e) {
							pwdUserName_TF.setText("Error: "+e.getLocalizedMessage());
						}
						finally {
							pwdCurrent_TF.clear();
							pwdNew_TF.clear();
							pwdNewConfirm_TF.clear();
						}
					}
					else {
						pwdUserName_TF.setText("New password and confirm password mismatch!");
					}
				}
				else {
					pwdUserName_TF.setText("You can only change password for your username...");
				}
			}
			else {
				pwdUserName_TF.setText("Wrong current password. Check that your capslock is not on...");
			}
			
			entitymanager.persist(user);
			entitymanager.getTransaction().commit();
			
			entitymanager.close();
			emfactory.close();
		}
		catch(Exception e) {
			userName_TextField.setText("Error: "+e.getMessage());
		}
	}
}

