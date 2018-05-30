package video.repository.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.nio.file.CopyOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.Vector;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.TextFlow;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import javafx.stage.Stage;
import video.repository.entity.AuthorizedVideos;
import video.repository.entity.NonStaff;
import video.repository.entity.Staff;
import video.repository.entity.UploadedVideos;
import video.repository.entity.Users;
import video.repository.services.CreateAuthorizedVideos;
import video.repository.services.CreateNonStaff;
import video.repository.services.CreateStaff;
import video.repository.services.CreateUploadedVideos;
import video.repository.services.CreateUser;
import video.repository.services.DeleteNonStaff;
import video.repository.services.DeleteStaff;
import video.repository.services.DeleteUser;
import video.repository.services.PrintReport;
import video.repository.services.PrintReport2;
import video.repository.services.UpdateNonStaff;
import video.repository.services.UpdateStaff;
import video.repository.services.UpdateUser;

public class OmniLoginControl extends Application {
	@FXML TextField userNameLogin_TF;
	@FXML PasswordField passwordLogin_TF;
	@FXML private Button loginBtn;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
	
	@FXML 
	public void loginButton(ActionEvent event) {
		String userName = userNameLogin_TF.getText();
		String pwd = passwordLogin_TF.getText();
		try {
			Users user = new Users();
			
			EntityManagerFactory emfactory = 
					Persistence.createEntityManagerFactory("SchoolMgtSystem");
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
			
			user = entitymanager.find(Users.class, userName);
			dispName = user.getName();
			dispUserClass = user.getUserClass();
			dispUserName = user.getUserName();
			if(pwd.equals(user.getUserName()) && pwd.equals(user.getPassword())) {
					try {
						Stage primaryStage = (Stage)loginBtn.getScene().getWindow();
						BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("changePassword.fxml"));
						primaryStage.setTitle("You Must Change Your Password at Your First Login");
						Scene scene = new Scene(root,579,620);
						//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						primaryStage.setScene(scene);
						primaryStage.show();
					} catch(Exception e) {
						userNameLogin_TF.setText("Error(pwd==userName): "+ e.getMessage());
						
					}
				}
			else if(pwd.equals(user.getPassword())) {
				try {
					Stage primaryStage = (Stage)loginBtn.getScene().getWindow();
					GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("launchPad.fxml"));
					primaryStage.setTitle("Dajoptek Hospital Management System");
					Scene scene = new Scene(root,680,400);
					//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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
			
			entitymanager.getTransaction().commit();
			entitymanager.close();
			emfactory.close();
		}
		catch(Exception e) {
			userNameLogin_TF.setText("Error: "+ e.getMessage());
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	@FXML public void exitMenuItemClick(ActionEvent event) {
		Platform.exit();
	}
	
	//File and Help About login
	
	@FXML private Button returnAboutLogin, aboutMenuItemClick2;
	@FXML MenuItem aboutMenuItemClick;
	
	@FXML public void handleAboutMenuItemClick(ActionEvent event) throws IOException {
		if(event.getSource() == aboutMenuItemClick) {
			try {
				Stage primaryStage = (Stage)aboutMenuItemClick2.getScene().getWindow();
				
				BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("about.fxml"));
				primaryStage.setTitle("Dajoptek School Management System");
				Scene scene = new Scene(root,600,400);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			Stage primaryStage = (Stage)returnAboutLogin.getScene().getWindow();
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("login.fxml"));
			Scene scene = new Scene(root,600,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Login");
			
			primaryStage.show();
		}
	}
	
	//About File and Help for Home
	@FXML private Button aboutMenuItemHome, returnAboutLogin2;
	@FXML private MenuItem aboutMenuItemHomeClick, aboutMenuItemHomeLogout;
	
	@FXML public void handleAboutMenuItemHome(ActionEvent event) throws IOException {
		if(event.getSource() == aboutMenuItemHomeClick) {
			try {
				Stage primaryStage = (Stage)aboutMenuItemHome.getScene().getWindow();
				
				BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("about2.fxml"));
				primaryStage.setTitle("Dajoptek School Management System");
				Scene scene = new Scene(root,600,400);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			Stage primaryStage = (Stage)returnAboutLogin2.getScene().getWindow();
			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("launchPad.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}
	
	@FXML public void logOutMenuHome(ActionEvent event) {
		try{
			if(event.getSource() == aboutMenuItemHomeLogout) {
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
		}
		catch(Exception e) {
			msgLabel.setText("Error: "+ e.getMessage());
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("login.fxml"));
			Scene scene = new Scene(root,600,500);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Login");
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	// launchPad Experiment
	ObservableList<String> sex = FXCollections.observableArrayList("Male","Female","select option");
	ObservableList<String> disable = FXCollections.observableArrayList("Yes","No","select option");
	
	@FXML TextArea msgLabel;
	@FXML TextArea msgLabel2;
	@FXML private Button changePwdButton;
	@FXML private Button returnChangePwdButton;
	@FXML private Button returnChangePwdButton2;
	
	
	@FXML private Button logOffHomeButton;
	public void quitPlatformButton(ActionEvent event) {
		try{
			if(event.getSource() == logOffHomeButton) {
				try {
					Stage primaryStage = (Stage)logOffHomeButton.getScene().getWindow();
					BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("login.fxml"));
					primaryStage.setTitle("Login");
					Scene scene = new Scene(root,600,500);
					//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene);
					primaryStage.show();
				} catch(Exception e) {
					msgLabel.setText("Error: "+ e.getMessage());
				}
			}
		}
		catch(Exception e) {
			msgLabel.setText("Error: "+ e.getMessage());
		}
	}
	
	@FXML private Button adminButton;
	@FXML private Button logoutAdminButton;
	static String dispName;
	static String dispUserName;
	static String dispUserClass;
	
	@FXML private Button returnEnterVidLibBtn, enterVidLibBtn;
	@FXML
	private void handleButtonAction3(ActionEvent event) throws IOException{
	
		if(event.getSource() == enterVidLibBtn) {
			try {				
				Stage primaryStage = (Stage)enterVidLibBtn.getScene().getWindow();
				VBox root = (VBox)FXMLLoader.load(getClass().getResource("videoLibrary.fxml"));
				primaryStage.setTitle("Video Library...");
				Scene scene = new Scene(root,800,700);
				
				//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				msgLabel.setText("Error: "+ e.getMessage());
			}
		}
		else {
			Stage primaryStage = (Stage)returnEnterVidLibBtn.getScene().getWindow();
			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("launchPad.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}
	
	@FXML private Button enterAuthorizeBtn, returnEnterAuthorizeBtn;
	@FXML
	private void handleButtonAction4(ActionEvent event) throws IOException{
	
		if(event.getSource() == enterAuthorizeBtn) {
			try {				
				Stage primaryStage = (Stage)enterAuthorizeBtn.getScene().getWindow();
				VBox root = (VBox)FXMLLoader.load(getClass().getResource("authorizePane.fxml"));
				primaryStage.setTitle("Athorization...");
				Scene scene = new Scene(root,800,700);
				
				//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				msgLabel.setText("Error: "+ e.getMessage());
			}
		}
		else {
			Stage primaryStage = (Stage)returnEnterAuthorizeBtn.getScene().getWindow();
			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("launchPad.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}
	
	@FXML private Button browseVidBtn;
	@FXML
	private void handleButtonAction5(ActionEvent event) throws IOException{
	
		if(event.getSource() == enterUploadVideoBtn) {
			try {				
				Stage primaryStage = (Stage)enterUploadVideoBtn.getScene().getWindow();
				GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("videoUpload.fxml"));
				primaryStage.setTitle("Uploaded Videos are subject to authorization by Admin...");
				Scene scene = new Scene(root,600,300);
				
//				FileChooser fileChooser = new FileChooser();
//				//File file = fileChooser.showOpenDialog(primaryStage);
//				browseVidBtn.setOnAction(e -> {
//					fileChooser.showOpenDialog(primaryStage);
//				});
				
				//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				msgLabel.setText("Error: "+ e.getMessage());
			}
		}
		else {
			Stage primaryStage = (Stage)returnUploadVideoBtn.getScene().getWindow();
			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("launchPad.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}
	
	@FXML private Button newUserBtn, returnNewUserBtn;
	@FXML
	private void handleButtonAction6(ActionEvent event) throws IOException{
	
		if(event.getSource() == adminButton) {
			
			EntityManagerFactory emfactory = 
					Persistence.createEntityManagerFactory("SchoolMgtSystem");
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
		
			String userName1 = userNameLogin_TF.getText();
			dispUserName2 = userNameLogin_TF.getText();
			String pwd1 = passwordLogin_TF.getText();
			
			Users user1 = entitymanager.find(Users.class, userName1);
			
			if(pwd1.equals(user1.getUserName()) && pwd1.equals(user1.getPassword())) {
				if(user1.getUserClass().equalsIgnoreCase("admin")) {
				
					try {
						Stage primaryStage = (Stage)adminButton.getScene().getWindow();
						BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("changePassword2.fxml"));
						primaryStage.setTitle("You Must Change Your Password at Your First Login");
						Scene scene = new Scene(root,579,620);
						scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						primaryStage.setScene(scene);
						primaryStage.show();
					} catch(Exception e) {
						userNameLogin_TF.setText("Error(adminpwd==userName): "+ e.getMessage());
					}
				}
				else {
					userNameLogin_TF.setText("Only Admin users can login here...");
				}
			}
			else if (pwd1.equals(user1.getPassword())){
				try {
					if(user1.getUserClass().equalsIgnoreCase("admin")) {
			
						try {
							Stage primaryStage = (Stage)adminButton.getScene().getWindow();
							BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("users.fxml"));
							primaryStage.setTitle("Users Entry");
							Scene scene = new Scene(root,570,539);
							//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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
			else {
				userNameLogin_TF.setText("Password missmatch... Contact Administrator");
				passwordLogin_TF.clear();
			}
			entitymanager.getTransaction().commit();
			entitymanager.close();
			emfactory.close();
		}
		else if(event.getSource() == returnChangePwdButton2) {
			Stage primaryStage = (Stage)returnChangePwdButton2.getScene().getWindow();
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("login.fxml"));
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		//nonStaffUsers
		else if(event.getSource() == newUserBtn) {
			try {
				Stage primaryStage = (Stage)newUserBtn.getScene().getWindow();
				BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("nonStaffUsers.fxml"));
				primaryStage.setTitle("NonStaff Users Entry");
				Scene scene = new Scene(root,570,539);
				//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			} 
			catch(Exception e) {
				nonStaffUser.setText("Error: "+e.getLocalizedMessage());
			}
		}
		else if(event.getSource() == returnNewUserBtn) {
			Stage primaryStage = (Stage)returnNewUserBtn.getScene().getWindow();
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("login.fxml"));
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
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
				Scene scene = new Scene(root,579,620);
				//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				msgLabel.setText("Error: "+ e.getMessage());
			}
		}
		else {
			Stage primaryStage = (Stage)returnChangePwdButton.getScene().getWindow();
			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("launchPad.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}
	
	
	
	@FXML
	private void handleButtonAction9(ActionEvent event) throws IOException{
	
		if(event.getSource() == patientInfoBtn) {
			try {				
				Stage primaryStage = (Stage)patientInfoBtn.getScene().getWindow();
				BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("patientInfo.fxml"));
				primaryStage.setTitle("Patient Information");
				Scene scene = new Scene(root,800,600);
				//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				msgLabel.setText("Error: "+ e.getMessage());
			}
		}
		else {
			Stage primaryStage = (Stage)returnPatientInfoBtn.getScene().getWindow();
			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("launchPad.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}
	
	@FXML
	private void handleButtonAction13(ActionEvent event) throws IOException{
		
		if(event.getSource() == staffInfoButton) {
			try {
				Stage primaryStage = (Stage)staffInfoButton.getScene().getWindow();
				BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("staffInfo.fxml"));
				primaryStage.setTitle("Staff Information");
				Scene scene = new Scene(root,800,600);
				//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			} 
			catch(Exception e) {
				msgLabel.setText("Error: "+ e.getMessage());
			}
		}
		else {
			Stage primaryStage = (Stage)returnStaffInfoButton.getScene().getWindow();
			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("launchPad.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}

	
	@FXML private void handleButtonAction39(ActionEvent event) throws IOException{
		
		if(event.getSource() == enterListOfPatientBtn) {
			try {				
				Stage primaryStage = (Stage)enterListOfPatientBtn.getScene().getWindow();
				SplitPane pane = (SplitPane)FXMLLoader.load(getClass().getResource("listOfPatients.fxml"));
				primaryStage.setTitle("Hospital Mgt System");
				Scene scene = new Scene(pane,920,720);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			Stage primaryStage = (Stage)returnEnterListOfPatientBtn.getScene().getWindow();
			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("launchPad.fxml"));
		
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}
	
	
	@FXML
	public void quitPlatformButton2(ActionEvent event) {
		Platform.exit();
	}
	
	
	//CreateUser Controller
	@FXML TextField userName_TextField;
	@FXML TextField name_TextField;
	@FXML TextField userStaffID;
	@FXML PasswordField password_TextField;
	@FXML ComboBox<String> userClass_TextField;
	@FXML TextField dept_TextField;
	
	@FXML ObservableList<String> userClassification = FXCollections.observableArrayList("Admin",
			"Cashier","Nurse","Matron","Admin Officer","Doctor","CMD","Lab","Pharmacist");
	
	@FXML public void initialize9(MouseEvent event) {
		userClass_TextField.setItems(userClassification);
	}
	
	@FXML public void initializeUsersNameText(MouseEvent event) {
		String staffID = userStaffID.getText().toUpperCase();
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		Staff staff = entitymanager.find(Staff.class, staffID);
		
		name_TextField.setText(staff.getOtherNames()+" "+staff.getSurName());
		
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
	
	@FXML 
	public void createUserButton(ActionEvent event) {
		initializeUsersNameText(null);
		try {
			CreateUser user = new CreateUser();
			String userClass = userClass_TextField.getValue();
			user.createUser(userName_TextField, userStaffID, name_TextField, password_TextField, 
					userClass, dept_TextField);
		}
		catch(Exception e) {
			userName_TextField.setText("Error: "+ e.getMessage());
		}
		finally {
			name_TextField.clear();
			password_TextField.clear();
			userClass_TextField.setValue("select option");;
			dept_TextField.clear();
			userStaffID.clear();
		}
	}
	
	@FXML private TextField nonStaffUser, nonStaffId, nonStaffName, nonStaffDept;
	@FXML private PasswordField nonStaffPassword;
	@FXML private ComboBox<String> nonStaffUserClass;
	@FXML public void createNonStaffUserButton(ActionEvent event) {
		//initializeUsersNameText2(null);
		try {
			CreateUser user = new CreateUser();
			String userClass = "Non-Staff";
			user.createUser(nonStaffUser, nonStaffId, nonStaffName, nonStaffPassword, 
					userClass, nonStaffDept);
		}
		catch(Exception e) {
			nonStaffId.setText("Error: "+ e.getMessage());
		}
		finally {
			nonStaffName.clear();
			nonStaffPassword.clear();
			nonStaffUserClass.setValue("Non-Staff");
			nonStaffDept.clear();
			nonStaffId.clear();
		}
	}
	
	@FXML public void initializeUsersNameText2(MouseEvent event) {
		String nonStaffID = nonStaffId.getText().toUpperCase();
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		NonStaff patient = entitymanager.find(NonStaff.class, nonStaffID);
		
		nonStaffName.setText(patient.getOtherNames()+" "+patient.getSurName());
		nonStaffUserClass.setValue("Non-Staff");
		
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
	
	@FXML 
	public void updateUserButton(ActionEvent event) {
		initializeUsersNameText(null);
		try {
			UpdateUser user = new UpdateUser();
			user.updateUser(userName_TextField, userStaffID, name_TextField, password_TextField, 
					userClass_TextField, dept_TextField);
		}
		catch(Exception e) {
			userName_TextField.setText("Error: "+ e.getMessage());
		}
		finally {
			name_TextField.clear();
			password_TextField.clear();
			userClass_TextField.setValue("select option");;
			dept_TextField.clear();
			userStaffID.clear();
		}
	}
	
	@FXML public void verifyuserNameButton(ActionEvent event) {
		try {
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
			
			String userName = userName_TextField.getText().toUpperCase();
			Users user = entitymanager.find(Users.class, userName);
			try {
				userStaffID.setText(user.getUserStaffID());
				name_TextField.setText(user.getName());
				userClass_TextField.setValue(user.getUserClass());
				dept_TextField.setText(user.getDept());
			}
			catch(Exception e) {
				userName_TextField.setText("Error: "+e.getMessage());
				userStaffID.clear();
				name_TextField.clear();
				userClass_TextField.setValue("select option");
				dept_TextField.clear();
			}
			
			entitymanager.getTransaction().commit();
			
			entitymanager.close();
			emfactory.close();
		}
		catch(Exception e) {
			userName_TextField.setText("Error: "+e.getMessage());
			userStaffID.clear();
			name_TextField.clear();
			userClass_TextField.setValue("select option");
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
			userStaffID.clear();
			name_TextField.clear();
			password_TextField.clear();
			userClass_TextField.setValue("select option");
			dept_TextField.clear();
		}
	}
	
	@FXML public void resetUserButton(ActionEvent event) {
		try {
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
			
			String userName = userName_TextField.getText().toUpperCase();
			Users user = entitymanager.find(Users.class, userName);
			
			user.setPassword(user.getUserName());
			
			entitymanager.persist(user);
			entitymanager.getTransaction().commit();
			
			String userStr = user.getUserName();
			userName_TextField.setText("Password reset for "+userStr+ " was successful...");
			
			entitymanager.close();
			emfactory.close();
			
		}
		catch(Exception e) {
			userName_TextField.setText("Error: "+e.getMessage());
		}
		finally {
			userStaffID.clear();
			name_TextField.clear();
			userClass_TextField.setValue("select option");;
			dept_TextField.clear();
		}
	}
	
	//Password Change Controller
	@FXML TextField pwdUserName_TF;
	@FXML PasswordField pwdCurrent_TF;
	@FXML PasswordField pwdNew_TF;
	@FXML PasswordField pwdNewConfirm_TF;
	
	//admin password change
	@FXML TextField pwdUserName_TF2;
	@FXML PasswordField pwdCurrent_TF2;
	@FXML PasswordField pwdNew_TF2;
	@FXML PasswordField pwdNewConfirm_TF2;
	
	@FXML public void pwdChangeButton(ActionEvent event) {
		changePWD(pwdUserName_TF.getText(),pwdCurrent_TF.getText(),
				pwdNew_TF.getText(),pwdNewConfirm_TF.getText());
	}
	static String dispUserName2;
	
	@FXML public void pwdChangeButton2(ActionEvent event) {
		
		try {
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
			
			String userName = pwdUserName_TF2.getText().toUpperCase();
			
			Users user = entitymanager.find(Users.class, userName);
			
			if(pwdCurrent_TF2.getText().equals(user.getPassword())) {
				if(dispUserName2.equalsIgnoreCase(user.getUserName())) {
					if(pwdNew_TF2.getText().equals(pwdNewConfirm_TF2.getText())) {
						try {
							user.setPassword(pwdNew_TF2.getText());
							pwdUserName_TF2.setText("Password Change Successful!");
						}
						catch(Exception e) {
							pwdUserName_TF2.setText("Error: "+e.getLocalizedMessage());
						}
						finally {
							pwdCurrent_TF2.clear();
							pwdNew_TF2.clear();
							pwdNewConfirm_TF2.clear();
						}
					}
					else {
						pwdUserName_TF2.setText("New password and confirm password mismatch!");
					}
				}
				else {
					pwdUserName_TF2.setText("You can only change password for your username...");
				}
			}
			else {
				pwdUserName_TF2.setText("Wrong current password. Check that your capslock is not on...");
			}
			
			entitymanager.persist(user);
			entitymanager.getTransaction().commit();
			
			entitymanager.close();
			emfactory.close();
		}
		catch(Exception e) {
			pwdUserName_TF2.setText("Error: "+e.getMessage());
		}
	}
	
	public void changePWD(String userName, String currentPassword,
			String newPassword,String confirmPassword) {
		try {
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
			
			Users user = entitymanager.find(Users.class, userName);
			
			if(currentPassword.equals(user.getPassword())) {
				if(dispUserName.equalsIgnoreCase(user.getUserName())) {
					if(newPassword.equals(confirmPassword)) {
						try {
							user.setPassword(newPassword);
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
			pwdUserName_TF.setText("Error: "+e.getMessage());
		}
	}
	
	@FXML
	public void displayLoginUser(ActionEvent event) {
		msgLabel.setText("Login User: ("+dispUserClass+"): "+ dispName);
	}
	
	@FXML private Button enterStaffListBtn;
	@FXML public void defineAccessLevel(MouseEvent event) {
		msgLabel.setText("Login User: "+ dispName);
		
		if(!(dispUserClass.equals("Admin"))) {
			enterAuthorizeBtn.setDisable(true);
			staffInfoButton.setDisable(true);
			patientInfoBtn.setDisable(true);
			enterListOfPatientBtn.setDisable(true);
			enterStaffListBtn.setDisable(true);
		}
	}
	
	
	//Staff Information Controller
	@FXML private Button staffInfoButton, returnStaffInfoButton;
	
	@FXML TextField staffEmpNo, surNameStaff, otherNamesStaff, placeOfBirthStaff, nationalityStaff,
		emailStaff, stateStaff, lgaStaff, villageStaff, phone1Staff, phone2Staff,
		staffQualification;
	@FXML private TextField schoolAttendedStaff, yearOfGraduationStaff;
	
	@FXML ComboBox<String> staffSex, disabilityStaff, maritalStatusStaff, designationStaff, cadreStaff; 
		// formMasterOf1, formMasterOf2, 
		//designationStaff;
	
	@FXML DatePicker staffDOB, staffDOE;
	
	@FXML TextArea addressStaff, ifDisabledStaff, nextOfKinStaff;
	
	ObservableList<String> maritalStatus = FXCollections.observableArrayList("select option","Married", "Single", 
			"Divorced", "Separated","Rather not say");
	ObservableList<String> designation = FXCollections.observableArrayList("select option","CMD", "Proprietor", 
			"Administrator", "Matron","Surgical Nurse","Surgeon","Doctor","Lab Technician","HOD","Pharmacist",
			"Admin Officer","ICT Officer","Security","Physiologist","Psychologist","Accountant","Cashier");
	ObservableList<String> cadre = FXCollections.observableArrayList("Management","Medical Team","Administrative",
			"Operations","Logistics");
	
	@FXML public void initializeStaffCombo(MouseEvent event) {
		staffSex.setItems(sex);
		disabilityStaff.setItems(disable);
		maritalStatusStaff.setItems(maritalStatus);
		designationStaff.setItems(designation);
		cadreStaff.setItems(cadre);
	}
	
	@FXML public void findStaff(ActionEvent event) {
		String empNo = staffEmpNo.getText().toUpperCase();
		try {
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
			
			Staff staff = entitymanager.find(Staff.class, empNo);
			
			surNameStaff.setText(staff.getSurName());
			otherNamesStaff.setText(staff.getOtherNames());
			placeOfBirthStaff.setText(staff.getPlaceOfBirth());
			nationalityStaff.setText(staff.getNationality());
			emailStaff.setText(staff.getEmail()); 
			nextOfKinStaff.setText(staff.getNextOfKin());
			stateStaff.setText(staff.getState());
			lgaStaff.setText(staff.getLga());
			villageStaff.setText(staff.getVillage());
			phone1Staff.setText(staff.getPhone1());
			phone2Staff.setText(staff.getPhone2());
			schoolAttendedStaff.setText(staff.getSchoolAttended());
			staffQualification.setText(staff.getQualification());
			addressStaff.setText(staff.getAddress()); 
			ifDisabledStaff.setText(staff.getIfDisabled());
			staffSex.setValue(staff.getSex()); 
			disabilityStaff.setValue(staff.getDisabled());
			maritalStatusStaff.setValue(staff.getMaritalStatus());
			cadreStaff.setValue(staff.getCadre()); 
			designationStaff.setValue(staff.getDesignation());
			String dob = staff.getDob();
			String doe = staff.getDoe();
			LocalDate dob2 = LocalDate.parse(dob, formatter);
			LocalDate doe2 = LocalDate.parse(doe, formatter);
			staffDOB.setValue(dob2);
			staffDOE.setValue(doe2);
			yearOfGraduationStaff.setText(staff.getYearOfQualification());
			
			entitymanager.getTransaction().commit();
			entitymanager.close();
			emfactory.close();
		}
		catch(Exception e) {
			staffEmpNo.setText("Staff Search Error: "+ e.getMessage());
			addressStaff.clear();
			ifDisabledStaff.clear();
			surNameStaff.clear();
			otherNamesStaff.clear(); 
			placeOfBirthStaff.clear(); 
			nationalityStaff.clear();
			emailStaff.clear(); 
			nextOfKinStaff.clear(); 
			stateStaff.clear(); 
			lgaStaff.clear(); 
			villageStaff.clear(); 
			phone1Staff.clear();
			phone2Staff.clear();
			schoolAttendedStaff.clear();
			staffQualification.clear();
			staffSex.setValue("select option");
			disabilityStaff.setValue("select option");
			maritalStatusStaff.setValue("select option");
			designationStaff.setValue("select option");
			cadreStaff.setValue("select option");
			staffDOB.setValue(LocalDate.now());
			staffDOE.setValue(LocalDate.now());
			yearOfGraduationStaff.clear();
		}
	}
	
	@FXML public void updateStaffButton(ActionEvent event) {
		String empNo = staffEmpNo.getText().toUpperCase();
		String surName = surNameStaff.getText();
		String otherNames = otherNamesStaff.getText();
		String placeOfBirth = placeOfBirthStaff.getText();
		String nationality = nationalityStaff.getText();
		String email = emailStaff.getText();
		String nextOfKin = nextOfKinStaff.getText();
		String state = stateStaff.getText();
		String lga = lgaStaff.getText();
		String village = villageStaff.getText();
		String phone1 = phone1Staff.getText();
		String phone2 = phone2Staff.getText();
		String cadre = cadreStaff.getValue();
		String schoolAttended = schoolAttendedStaff.getText();
		String qualification = staffQualification.getText();
		String yearOfGraduation = yearOfGraduationStaff.getText();
		String sex = staffSex.getValue();
		String disability = disabilityStaff.getValue();
		String maritalStatus = maritalStatusStaff.getValue();
		String designation = designationStaff.getValue();
		String address = addressStaff.getText();
		String ifDisabled = ifDisabledStaff.getText();
		try {
			UpdateStaff staff = new UpdateStaff();
			staff.updateStaff(empNo, surName, otherNames, staffDOB, sex, cadre, placeOfBirth, 
					nationality, maritalStatus, designation, address, state, lga, village, email,
					phone1, phone2, disability, ifDisabled, nextOfKin, staffDOE, schoolAttended, 
					qualification, yearOfGraduation, dispName);
			
		}
		catch(Exception e) {
			staffEmpNo.setText("UpdateStaff Error: "+e.getMessage());
		}
		finally {
			addressStaff.clear();
			ifDisabledStaff.clear();
			surNameStaff.clear();
			otherNamesStaff.clear(); 
			placeOfBirthStaff.clear(); 
			nationalityStaff.clear();
			emailStaff.clear(); 
			nextOfKinStaff.clear(); 
			stateStaff.clear(); 
			lgaStaff.clear(); 
			villageStaff.clear(); 
			phone1Staff.clear();
			phone2Staff.clear();
			schoolAttendedStaff.clear();
			staffQualification.clear();
			staffSex.setValue("select option");
			disabilityStaff.setValue("select option");
			maritalStatusStaff.setValue("select option");
			designationStaff.setValue("select option");
			cadreStaff.setValue("select option");
			staffDOB.setValue(LocalDate.now());
			staffDOE.setValue(LocalDate.now());
			yearOfGraduationStaff.clear();
		}
	}
	
	@FXML public void saveStaffButton(ActionEvent event) {
		String empNo = staffEmpNo.getText().toUpperCase();
		String surName = surNameStaff.getText();
		String otherNames = otherNamesStaff.getText();
		String placeOfBirth = placeOfBirthStaff.getText();
		String nationality = nationalityStaff.getText();
		String email = emailStaff.getText();
		String nextOfKin = nextOfKinStaff.getText();
		String state = stateStaff.getText();
		String lga = lgaStaff.getText();
		String village = villageStaff.getText();
		String phone1 = phone1Staff.getText();
		String phone2 = phone2Staff.getText();
		String cadre = cadreStaff.getValue();
		String schoolAttended = schoolAttendedStaff.getText();
		String qualification = staffQualification.getText();
		String yearOfGraduation = yearOfGraduationStaff.getText();
		String sex = staffSex.getValue();
		String disability = disabilityStaff.getValue();
		String maritalStatus = maritalStatusStaff.getValue();
		String designation = designationStaff.getValue();
		String address = addressStaff.getText();
		String ifDisabled = ifDisabledStaff.getText();
		try {
			CreateStaff staff = new CreateStaff();
			staff.createStaff(empNo, surName, otherNames, staffDOB, sex, cadre, placeOfBirth, nationality, 
					maritalStatus, designation, address, state, lga, village, email, phone1, phone2, 
					disability, ifDisabled, nextOfKin, staffDOE, schoolAttended, qualification, 
					yearOfGraduation, dispName);
			
		}
		catch(Exception e) {
			staffEmpNo.setText("CreateStaff Error: "+e.getMessage());
		}
		finally {
			addressStaff.clear();
			ifDisabledStaff.clear();
			surNameStaff.clear();
			otherNamesStaff.clear(); 
			placeOfBirthStaff.clear(); 
			nationalityStaff.clear();
			emailStaff.clear(); 
			nextOfKinStaff.clear(); 
			stateStaff.clear(); 
			lgaStaff.clear(); 
			villageStaff.clear(); 
			phone1Staff.clear();
			phone2Staff.clear();
			schoolAttendedStaff.clear();
			staffQualification.clear();
			staffSex.setValue("select option");
			disabilityStaff.setValue("select option");
			maritalStatusStaff.setValue("select option");
			designationStaff.setValue("select option");
			cadreStaff.setValue("select option");
			staffDOB.setValue(LocalDate.now());
			staffDOE.setValue(LocalDate.now());
			yearOfGraduationStaff.clear();
		}
	}
	
	//Delete Staff
	@SuppressWarnings("unchecked")
	@FXML public void deleteStaff(ActionEvent event) {
		
		String empNo = staffEmpNo.getText().toUpperCase();
		
		if(empNo.equals(staffEmpNo.getText().toUpperCase())) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setContentText("You are about to delete this user... "
					+ "Note that this action will delete the respective staff's "
					+ "user login profile and this process is irreversible!");
			Optional<ButtonType> result = alert.showAndWait();
			if(result.get() == ButtonType.OK) {
				Vector<Users> UserAcct = new Vector<>();
				try{
					EntityManagerFactory emfactory = Persistence.createEntityManagerFactory
							("SchoolMgtSystem");
					EntityManager entitymanager = emfactory.createEntityManager();
					entitymanager.getTransaction().begin();
					
					Query query =
							entitymanager.createQuery("select e from Users e");
					UserAcct = (Vector<Users>) query.getResultList();
					
					entitymanager.close();
					emfactory.close();
					
					for (Users str : UserAcct) {
						if(str.getUserStaffID().equals(empNo)) {
							String userName = str.getUserName();
							DeleteUser user = new DeleteUser();
							user.deleteUser(userName);
						}
					}
				}
				catch(Exception ex) {
					staffEmpNo.setText("Delete User Error: "+ ex.getLocalizedMessage());
				}
				
				try {
					DeleteStaff staff = new DeleteStaff();
					staff.deleteStaff(empNo);
					
				}
				catch(Exception e) {
					staffEmpNo.setText("DeleteStaff Error: "+e.getMessage());
				}
				finally {
					addressStaff.clear();
					ifDisabledStaff.clear();
					surNameStaff.clear();
					otherNamesStaff.clear(); 
					placeOfBirthStaff.clear(); 
					nationalityStaff.clear();
					emailStaff.clear(); 
					nextOfKinStaff.clear(); 
					stateStaff.clear(); 
					lgaStaff.clear(); 
					villageStaff.clear(); 
					phone1Staff.clear();
					phone2Staff.clear();
					schoolAttendedStaff.clear();
					staffQualification.clear();
					staffSex.setValue("select option");
					disabilityStaff.setValue("select option");
					maritalStatusStaff.setValue("select option");
					designationStaff.setValue("select option");
					cadreStaff.setValue("select option");
					staffDOB.setValue(LocalDate.now());
					staffDOE.setValue(LocalDate.now());
					yearOfGraduationStaff.clear();
				}
			}
			else {
				alert.close();
			}
		}
	}
	
	//Non-Staff Information
	@FXML private Button patientInfoBtn, returnPatientInfoBtn;
	@FXML private TextField patientCardNo, patientSurName, patientOtherNames, patientNationality, 
	patientState, patientLga, patientVillage, patientEmail, patientPhone1, patientPhone2, 
	patientNextOfKinName, patientNextOfKinAdd, patientNextOfKinPhone;
	@FXML TextArea patientAddress;
	@FXML ComboBox<String> patientSex, patientMaritalStatus;
	@FXML DatePicker patientDob, patientDor;
	
	@FXML public void patientCombo(MouseEvent event) {
		patientSex.setItems(sex);
		patientMaritalStatus.setItems(maritalStatus);
	}
	
	@FXML public void savePatientsButton(ActionEvent event) {
		String cardNo = patientCardNo.getText().toUpperCase();
		String surName = patientSurName.getText();
		String otherNames = patientOtherNames.getText();
		//patientDob, 
		int yob = patientDob.getValue().getYear();
		int tod = LocalDate.now().getYear();
		String age = String.valueOf(tod - yob);
		String text = "";
		String nationality = patientNationality.getText(); 
		String state = patientState.getText();
		String lga = patientLga.getText();
		String village = patientVillage.getText();
		String address = patientAddress.getText();
		String email = patientEmail.getText();
		String phone1 = patientPhone1.getText(); 
		String phone2 = patientPhone2.getText();
		String nokName = patientNextOfKinName.getText();
		String nokAddress = patientNextOfKinAdd.getText();
		String nokPhone = patientNextOfKinPhone.getText();
		//patientDor
		String sex = patientSex.getValue();
		String maritalStatus = patientMaritalStatus.getValue();
		
		try {
			CreateNonStaff patient = new CreateNonStaff();
			patient.createPatient(cardNo, surName, otherNames, patientDob, sex, maritalStatus, nationality, state, lga, 
					village, address, email, phone1, phone2, nokName, nokAddress, nokPhone, patientDor, dispName);
			
		}
		catch(Exception e) {
			patientCardNo.setText("Error: " + e.getMessage());
		}
		finally {
			patientSurName.clear();
			patientOtherNames.clear();
			patientNationality.clear();
			patientState.clear();
			patientLga.clear();
			patientVillage.clear();
			patientAddress.clear();
			patientEmail.clear();
			patientPhone1.clear();
			patientPhone2.clear();
			patientNextOfKinName.clear();
			patientNextOfKinAdd.clear();
			patientNextOfKinPhone.clear();
			patientSex.setValue("select option");
			patientMaritalStatus.setValue("select option");
			patientDob.setValue(LocalDate.now());
			patientDor.setValue(LocalDate.now());
		}
	}
	
	//Find Patient
	@FXML public void findPatientButton(ActionEvent event) {
		String cardNo = patientCardNo.getText().toUpperCase();
		try {
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
			
			NonStaff patient = entitymanager.find(NonStaff.class, cardNo);
			
			patientSurName.setText(patient.getSurName());
			patientOtherNames.setText(patient.getOtherNames());
			patientNationality.setText(patient.getNationality());
			patientState.setText(patient.getState());
			patientLga.setText(patient.getLga());
			patientVillage.setText(patient.getVillage());
			patientAddress.setText(patient.getAddress());
			patientEmail.setText(patient.getEmail());
			patientPhone1.setText(patient.getPhone1());
			patientPhone2.setText(patient.getPhone2());
			patientNextOfKinName.setText(patient.getNextOfKinName());
			patientNextOfKinAdd.setText(patient.getNextOfKinAdd());
			patientNextOfKinPhone.setText(patient.getNextOfKinPhone());
			patientSex.setValue(patient.getSex());
			patientMaritalStatus.setValue(patient.getMaritalStatus());
			String dob1 = patient.getDob();
			String dor1 = patient.getDor();
			LocalDate dob = LocalDate.parse(dob1, formatter);
			LocalDate dor = LocalDate.parse(dor1, formatter);
			patientDob.setValue(dob);
			patientDor.setValue(dor);
			
			entitymanager.persist(patient);
		
			entitymanager.getTransaction().commit();
			
			entitymanager.close();
			emfactory.close();
		}
		catch(Exception e) {
			patientCardNo.setText("Find Error: "+ e.getMessage());
			patientSurName.clear();
			patientOtherNames.clear();
			patientNationality.clear();
			patientState.clear();
			patientLga.clear();
			patientVillage.clear();
			patientAddress.clear();
			patientEmail.clear();
			patientPhone1.clear();
			patientPhone2.clear();
			patientNextOfKinName.clear();
			patientNextOfKinAdd.clear();
			patientNextOfKinPhone.clear();
			patientSex.setValue("select option");
			patientMaritalStatus.setValue("select option");
			patientDob.setValue(LocalDate.now());
			patientDor.setValue(LocalDate.now());
		}
	}
	
	//Update Patient
	@FXML public void updatePatientButton(ActionEvent event) {
		String cardNo = patientCardNo.getText().toUpperCase();
		String surName = patientSurName.getText();
		String otherNames = patientOtherNames.getText();
		//patientDob, 
		int yob = patientDob.getValue().getYear();
		int tod = LocalDate.now().getYear();
		String age = String.valueOf(tod - yob);
		String text = "";
		String nationality = patientNationality.getText(); 
		String state = patientState.getText();
		String lga = patientLga.getText();
		String village = patientVillage.getText();
		String address = patientAddress.getText();
		String email = patientEmail.getText();
		String phone1 = patientPhone1.getText(); 
		String phone2 = patientPhone2.getText();
		String nokName = patientNextOfKinName.getText();
		String nokAddress = patientNextOfKinAdd.getText();
		String nokPhone = patientNextOfKinPhone.getText();
		//patientDor
		String sex = patientSex.getValue();
		String maritalStatus = patientMaritalStatus.getValue();
		
		try {
			UpdateNonStaff patient = new UpdateNonStaff();
			patient.updatePatient(cardNo, surName, otherNames, patientDob, sex, maritalStatus, nationality, state, lga, village, 
					address, email, phone1, phone2, nokName, nokAddress, nokPhone, patientDor, dispName);
			
		}
		catch(Exception e) {
			patientCardNo.setText("Error: " + e.getMessage());
		}
		finally {
			patientSurName.clear();
			patientOtherNames.clear();
			patientNationality.clear();
			patientState.clear();
			patientLga.clear();
			patientVillage.clear();
			patientAddress.clear();
			patientEmail.clear();
			patientPhone1.clear();
			patientPhone2.clear();
			patientNextOfKinName.clear();
			patientNextOfKinAdd.clear();
			patientNextOfKinPhone.clear();
			patientSex.setValue("select option");
			patientMaritalStatus.setValue("select option");
			patientDob.setValue(LocalDate.now());
			patientDor.setValue(LocalDate.now());
		}
	}
	
	//Delete Patient
	@FXML public void deletePatientButton(ActionEvent event) {
		if(true) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setContentText("You are about to delete this patient record... "
					+ "Note that this action will delete the patient's "
					+ "medical history and financial records and this process is irreversible!");
			Optional<ButtonType> result = alert.showAndWait();
			if(result.get() == ButtonType.OK) {
				String cardNo = patientCardNo.getText().toUpperCase();
				try {
					LinkedList<String> vitalList, diagList, labList, pharmList;
					double balanceS = 0.0;
					
					EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchoolMgtSystem");
					EntityManager entitymanager = emfactory.createEntityManager();
					entitymanager.getTransaction().begin();
					
					entitymanager.getTransaction().commit();
					
					entitymanager.close();
					emfactory.close();
					
					if(balanceS == 0) {
						
						
						DeleteNonStaff patient = new DeleteNonStaff();
						patient.deletePatient(cardNo);
					}
					else {
						Alert alert2 = new Alert(Alert.AlertType.WARNING);
						alert2.setContentText("You cannot delete a patient that already "
								+ "has medical records!");
						alert2.showAndWait();
					}
				}
				catch(Exception e) {
					patientCardNo.setText("Delete Error: "+ e.getMessage());
				}
				finally {
					patientSurName.clear();
					patientOtherNames.clear();
					patientNationality.clear();
					patientState.clear();
					patientLga.clear();
					patientVillage.clear();
					patientAddress.clear();
					patientEmail.clear();
					patientPhone1.clear();
					patientPhone2.clear();
					patientNextOfKinName.clear();
					patientNextOfKinAdd.clear();
					patientNextOfKinPhone.clear();
					patientSex.setValue("select option");
					patientMaritalStatus.setValue("select option");
					patientDob.setValue(LocalDate.now());
					patientDor.setValue(LocalDate.now());
				}
			}
			else {
				alert.close();
			}
		}
	}
	
	
	public static void printNode(final SplitPane printPane) throws NoSuchMethodException, 
	InstantiationException, IllegalAccessException, InvocationTargetException {
	
    	Printer printer = Printer.getDefaultPrinter();
    	PageLayout pageLayout
        	= printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
    //PrinterAttributes attr = printer.getPrinterAttributes();
	    PrinterJob job = PrinterJob.createPrinterJob();
	    double scaleX
	        = pageLayout.getPrintableWidth() / printPane.getBoundsInParent().getWidth();
	    double scaleY
	        = pageLayout.getPrintableHeight() / printPane.getBoundsInParent().getHeight();
	    Scale scale = new Scale(scaleX, scaleY);
	    printPane.getTransforms().add(scale);

	    if (job != null && job.showPrintDialog(printPane.getScene().getWindow())) {
	    	boolean success = job.printPage(pageLayout, printPane);
	    	if (success) {
	    		job.endJob();

	    	}
	    }
	    printPane.getTransforms().remove(scale);
	}
	
	
	
	
	//List of NonStaff
	@FXML private Button enterListOfPatientBtn, returnEnterListOfPatientBtn, printPatListBtn, spoolPatListBtn,
		genExcelPatListBtn;
	@FXML private SplitPane panePatientList;
	@FXML private TableView<PatientsListDataTable> patientsListDataTable;
	@FXML private TableColumn<PatientsListDataTable,String> patListSNoCol, patListCardNoCol, patListNameCol, 
		patListSexCol, patListAgeCol, patListAddressCol, patListPhoneCol, patListNokCol, patListNokPhoneCol, 
		patListABCol;
	@FXML private Label patListNo, patListOverdrawn, patListNetOverdrawn, patListNetAcctBal, patListDate;
	@FXML private TextField patListTextError;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@FXML public void spoolPatListButton(ActionEvent event) {
		
		try {
			
			ObservableList<PatientsListDataTable> data = FXCollections.observableArrayList();
			Vector<NonStaff> patData = new Vector<>();
//			Vector<AccountBalance> balData = new Vector<>();
			
			try {
				EntityManagerFactory emfactory = Persistence.createEntityManagerFactory
						("SchoolMgtSystem");
				EntityManager entitymanager = emfactory.createEntityManager();
				entitymanager.getTransaction().begin();
						
				Query query =
						entitymanager.createQuery("select e from NonStaff e ");
				patData = (Vector<NonStaff>) query.getResultList();
				
				Query query2 =
						entitymanager.createQuery("select e from AccountBalance e ");
//				balData = (Vector<AccountBalance>) query2.getResultList();
				
				entitymanager.getTransaction().commit();
				entitymanager.close();
				emfactory.close();
			}
			catch(Exception e) {
				patListTextError.setVisible(true);
				patListTextError.setText("Spool Error: "+e.getLocalizedMessage());
			}
			
			int i = 0;
			int overdrawnCount = 0;
			double overdrawnSum = 0.0, netSum = 0.0;
			
			for(NonStaff pat : patData) {
				i = i + 1;
				String sNo = String.valueOf(i);
				String cardNo = pat.getCardNo();
				String name = pat.getOtherNames() + " " + pat.getSurName();
				String sex = pat.getSex();
				
				String dob = pat.getDob();
				LocalDate dob2 = LocalDate.parse(dob, formatter);
				int age = LocalDate.now().getYear() - dob2.getYear();
				
				String ageX = String.valueOf(age);
				String address = pat.getAddress();
				String phone = pat.getPhone1()+ ", "+pat.getPhone2();
				String nok = pat.getNextOfKinName();
				String nokPhone = pat.getNextOfKinPhone();
				
				double acctBal = 0.0;
				EntityManagerFactory emfactory = Persistence.createEntityManagerFactory
						("SchoolMgtSystem");
				EntityManager entitymanager = emfactory.createEntityManager();
				entitymanager.getTransaction().begin();
						
//				AccountBalance balance = entitymanager.find(AccountBalance.class, cardNo);
//				
//				acctBal = balance.getBalance();
//				
//				netSum = netSum + balance.getBalance();
//				
//				if(balance.getBalance() < 0.0 ) {
//					overdrawnCount = overdrawnCount + 1;
//					overdrawnSum = overdrawnSum + balance.getBalance();
//				}
//				
//				entitymanager.getTransaction().commit();
//				entitymanager.close();
//				emfactory.close();
				
				String acctBalance = String.valueOf(acctBal);
				
				data.add(new PatientsListDataTable(
						sNo, cardNo, name, sex, ageX, address, phone, nok, nokPhone, acctBalance));
				
			}
			
			patListSNoCol.setCellValueFactory(new PropertyValueFactory("sNo_Data"));
			patListCardNoCol.setCellValueFactory(new PropertyValueFactory("cardNo_Data"));
			patListNameCol.setCellValueFactory(new PropertyValueFactory("name_Data"));
			patListSexCol.setCellValueFactory(new PropertyValueFactory("sex_Data"));
			patListAgeCol.setCellValueFactory(new PropertyValueFactory("age_Data"));
			patListAddressCol.setCellValueFactory(new PropertyValueFactory("address_Data"));
			patListPhoneCol.setCellValueFactory(new PropertyValueFactory("phone_Data"));
			patListNokCol.setCellValueFactory(new PropertyValueFactory("nokDetails_Data"));
			patListNokPhoneCol.setCellValueFactory(new PropertyValueFactory("nokPhone_Data"));
			patListABCol.setCellValueFactory(new PropertyValueFactory("acctBal_Data"));
			
			
			patientsListDataTable.setItems(data);
			
			patListNo.setText(String.valueOf(data.size()));
		
			LocalDate d3 = LocalDate.now();
			DateTimeFormatter d4 = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
			String d5 = d4.format(d3);
			patListDate.setText(d5);
			patListOverdrawn.setText(String.valueOf(overdrawnCount));
//			patListNetOverdrawn.setText(fm2.format(overdrawnSum));
//			patListNetAcctBal.setText(fm2.format(netSum));
		}
		catch(Exception ex) {
			patListTextError.setVisible(true);
			patListTextError.setText(ex.getLocalizedMessage());
			patientsListDataTable.setItems(null);
		}
	}
	
	
	@FXML public void printPatListButton(ActionEvent event) {
		returnEnterListOfPatientBtn.setVisible(false); 
		printPatListBtn.setVisible(false); 
		spoolPatListBtn.setVisible(false);
		genExcelPatListBtn.setVisible(false);
		patListTextError.setVisible(false);
		try {
			printNode(panePatientList);
		}
		catch(Exception exception) {
			patListTextError.setVisible(true);
			patListTextError.setText("Print Error: "+ exception.getLocalizedMessage());
		}
		finally {
			returnEnterListOfPatientBtn.setVisible(true); 
			printPatListBtn.setVisible(true); 
			spoolPatListBtn.setVisible(true);
			genExcelPatListBtn.setVisible(true);
			patListTextError.setVisible(false);
		}
	}
	
	@FXML public void genExcelPatListButton(ActionEvent event) {
		Workbook workbook = new HSSFWorkbook();
		Sheet spreadSheet = workbook.createSheet("sheet");
		Row row = spreadSheet.createRow(0);
		
		for(int j = 0; j < patientsListDataTable.getColumns().size(); j++) {
			row.createCell(j).setCellValue(patientsListDataTable.getColumns().get(j).getText());
		}
		
		for(int i = 0; i < patientsListDataTable.getItems().size(); i++) {
			row = spreadSheet.createRow(i + 1);
			for(int j = 0; j < patientsListDataTable.getColumns().size(); j++) {
				if(patientsListDataTable.getColumns().get(j).getCellData(i) != null) {
					row.createCell(j).setCellValue(patientsListDataTable.getColumns().get(j).getCellData(i).toString());
				}
				else {
					row.createCell(j).setCellValue("");
				}
			}
		}
		
		String localDate = LocalDate.now().toString();
		String txt = "patientsListDataTable"+localDate+".xls";
		String filePath = "C:\\workbook\\"+txt;
		
		try {
			FileOutputStream fileOut = new FileOutputStream(filePath);
			try {
				workbook.write(fileOut);
				fileOut.close();
				patListTextError.setVisible(true);
				patListTextError.setText(filePath);
			} catch (IOException e2) {
				patListTextError.setVisible(true);
				patListTextError.setText("Write Error: "+ e2.getMessage());
			}
		} catch (FileNotFoundException e) {
			patListTextError.setVisible(true);
			patListTextError.setText("File Error: "+ e.getMessage());
		}
	}
	
	//Upload Video Controller
	@FXML private Button enterUploadVideoBtn, returnUploadVideoBtn;
	@FXML private TextField uploadVidTitle, videoUploadFP, videoUploadDF, videoUploadFN;
	@FXML private ComboBox<String> uploadVidClass;
	@FXML private DatePicker uploadVidDate;
	@FXML private Label uploadedVidErrorLable;
	
	ObservableList<String> videoCat = FXCollections.observableArrayList("Academics","Lecture","Movie",
			"Music","Violence","Adult","Family","Others");
	
	@FXML public void initVideoCategory(MouseEvent event) {
		uploadVidClass.setItems(videoCat);
	}
	
	@FXML Stage stage;
	@FXML public void vidUploadBrowseButton(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("select video for upload...");
		File file = fileChooser.showOpenDialog(stage);
//		if(file != null) {
//			openFile(file);
//		}
		
		videoUploadFP.setText(file.getAbsolutePath());
		videoUploadFN.setText(file.getName());
	}
	
	private void openFile(File file) {
		try {
			Desktop desktop = Desktop.getDesktop();
			desktop.open(file);
		}
		catch(Exception e) {
			videoUploadDF.setText(e.getMessage());
		}
	}
	
	@FXML public void videoUploadButton(ActionEvent event) {
		String fileName = videoUploadFN.getText();
		
		final int BUFFERSIZE = 8 * 1024;
		String srcPath = videoUploadFP.getText();
		String outPath = "C:\\UploadsDirectory\\"+fileName;
		
		try (
			FileInputStream fin = new FileInputStream(new File(srcPath));
			FileOutputStream fout = new FileOutputStream(new File(outPath));
				)
		{
			byte[] buffer = new byte[BUFFERSIZE];
			int bytesRead;
			
			while(fin.available() != 0) {
				bytesRead = fin.read(buffer);
				fout.write(buffer, 0, bytesRead);
			}
			
			videoUploadDF.setText("C:\\UploadsDirectory\\"+fileName);
			
			try {
				String vid = fileName + LocalDateTime.now();
				String fileNameX = fileName;
				String fileLocation = outPath; 
				String fileTitle = uploadVidTitle.getText(); 
				String fileClass = uploadVidClass.getValue();
				String user = dispName;
				
				CreateUploadedVideos video = new CreateUploadedVideos();
				
				video.createUploadedVideo(vid, fileNameX, fileLocation, fileTitle, fileClass, uploadVidDate, user);
				
			}
			catch(Exception ex) {
				uploadedVidErrorLable.setText(ex.getMessage());
			}
		}
		catch(Exception e) {
			videoUploadDF.setText(e.getMessage());
		}
	}
	
	
	//Authorization Controller
	@FXML private TableView<UploadsDataTable> uploadsDataTable;
	@FXML private TableColumn<UploadsDataTable,String> vidCol, titleCol, genreCol, fileCol, locationCol, userCol;
	@FXML private TextArea uploadedVidLocation;
	@FXML private ComboBox<String> uploadVidCombo;
	
	ObservableList<String> uploads = FXCollections.observableArrayList();
	
	@SuppressWarnings("unchecked")
	@FXML public void initVidUploadsCombo(MouseEvent event) {
		ObservableList<String> data = FXCollections.observableArrayList();
		Vector<UploadedVideos> videoData = new Vector<>();
		
		try {
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory
					("SchoolMgtSystem");
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
					
			Query query =
					entitymanager.createQuery("select e from UploadedVideos e ");
			videoData = (Vector<UploadedVideos>) query.getResultList();
			
			entitymanager.getTransaction().commit();
			entitymanager.close();
			emfactory.close();
		}
		catch(Exception e) {
			uploadedVidLocation.setText("Refresh Error: "+e.getLocalizedMessage());
		}
		
		for(UploadedVideos video : videoData) {
			
			String vid = video.getVid();
			
			data.add(vid);
		}
		
		uploadVidCombo.setItems(data);
	}
	
	
	@FXML public void RefrestUploadsTableButton(ActionEvent event) {
		refreshUploads();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void refreshUploads() {
		try {
			
			ObservableList<UploadsDataTable> data = FXCollections.observableArrayList();
			Vector<UploadedVideos> videoData = new Vector<>();
			
			try {
				EntityManagerFactory emfactory = Persistence.createEntityManagerFactory
						("SchoolMgtSystem");
				EntityManager entitymanager = emfactory.createEntityManager();
				entitymanager.getTransaction().begin();
						
				Query query =
						entitymanager.createQuery("select e from UploadedVideos e ");
				videoData = (Vector<UploadedVideos>) query.getResultList();
				
				entitymanager.getTransaction().commit();
				entitymanager.close();
				emfactory.close();
			}
			catch(Exception e) {
				uploadedVidLocation.setText("Refresh Error: "+e.getLocalizedMessage());
			}
			
			for(UploadedVideos video : videoData) {
				
				String vid = video.getVid();
				String title = video.getFileTitle();
				String genre = video.getFileClass();
				String file = video.getFileName();
				String location = video.getFileLocation();
				String user = video.getUser();
				
				data.add(new UploadsDataTable(vid, title, genre, file, location, user));
			}
			
			vidCol.setCellValueFactory(new PropertyValueFactory("vid_Data"));
			titleCol.setCellValueFactory(new PropertyValueFactory("title_Data"));
			genreCol.setCellValueFactory(new PropertyValueFactory("genre_Data"));
			fileCol.setCellValueFactory(new PropertyValueFactory("file_Data"));
			locationCol.setCellValueFactory(new PropertyValueFactory("location_Data"));
			userCol.setCellValueFactory(new PropertyValueFactory("user_Data"));
			
			
			uploadsDataTable.setItems(data);
		}
		catch(Exception ex) {
			uploadedVidLocation.setText(ex.getLocalizedMessage());
			uploadsDataTable.setItems(null);
		}
	}
	
	//Media
	@FXML private MediaView mediaView;
	@FXML private Button uploadVidPauseBtn, uploadVidStopBtn;
	
	@FXML public void playMediaButton(ActionEvent event) {
		String vid = uploadVidCombo.getValue();
		
		try {
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory
					("SchoolMgtSystem");
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
					
			UploadedVideos video = entitymanager.find(UploadedVideos.class, vid);
			
			uploadedVidLocation.setText(video.getFileLocation());
			
			entitymanager.getTransaction().commit();
			entitymanager.close();
			emfactory.close();
		}
		catch(Exception e) {
			uploadedVidLocation.setText("Refresh Error: "+e.getLocalizedMessage());
		}
		
		String url = uploadedVidLocation.getText();
		
		playMedia(url, uploadVidPauseBtn, uploadVidStopBtn);
		
	}
	
	
	//Authorized Video
	DateTimeFormatter formatterX = DateTimeFormatter.ofPattern("d-MMM-yyyy");
	@FXML DatePicker myDatePicker;
	
	@FXML public void authorizeVideoButton(ActionEvent event) {
		String vid = uploadVidCombo.getValue();
		String fileName = null;
		String fileLocation = uploadedVidLocation.getText();
		String fileTitle = null;
		String fileClass = null;
		String date = null;
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory
				("SchoolMgtSystem");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
				
		UploadedVideos video = entitymanager.find(UploadedVideos.class, vid);
		
		fileName = video.getFileName();
		//fileLocation = video.getFileLocation();
		fileTitle = video.getFileTitle();
		fileClass = video.getFileClass();
		date = video.getDate();
		
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
		
		
		final int BUFFERSIZE = 8 * 1024;
		String srcPath = uploadedVidLocation.getText();
		String outPath = "C:\\AuthorizedVideosDirectory\\"+fileName;
		
		try (
			FileInputStream fin = new FileInputStream(new File(srcPath));
			FileOutputStream fout = new FileOutputStream(new File(outPath));
				)
		{
			byte[] buffer = new byte[BUFFERSIZE];
			int bytesRead;
			
			while(fin.available() != 0) {
				bytesRead = fin.read(buffer);
				fout.write(buffer, 0, bytesRead);
			}
			
		}
		catch(Exception e) {
			uploadedVidLocation.setText(e.getMessage());
		}
		
//		LocalDate uploadDate = LocalDate.parse(date, formatterX);
		LocalDate currDate = LocalDate.now();
		String user = dispName;
		
		CreateAuthorizedVideos authorizedVid = new CreateAuthorizedVideos();
		
		authorizedVid.createAuthorizedVideo(vid, fileName, outPath, fileTitle, fileClass, date, currDate, user);
		
		File file = new File(fileLocation);
		
		if(file.isFile()) {
			
			EntityManagerFactory emfactory2 = Persistence.createEntityManagerFactory
					("SchoolMgtSystem");
			EntityManager entitymanager2 = emfactory2.createEntityManager();
			entitymanager2.getTransaction().begin();
					
			UploadedVideos videoX = entitymanager2.find(UploadedVideos.class, vid);
			entitymanager2.remove(videoX);
			
			entitymanager2.getTransaction().commit();
			entitymanager2.close();
			emfactory2.close();
			
			file.delete();
			
		}

		uploadedVidLocation.setText("Authorization Successful");
		refreshUploads();
	}
	
	//Video Library Controller
	@FXML private TableView<VideoLibDataTable> videoLibDataTable;
	@FXML private TableColumn<VideoLibDataTable,String> vidCol2, titleCol2, genreCol2, fileCol2, locationCol2, userCol2;
	@FXML private TextArea uploadedVidLocation2;
	@FXML private ComboBox<String> uploadVidCombo2;
	
	ObservableList<String> uploads2 = FXCollections.observableArrayList();
	
	@SuppressWarnings("unchecked")
	@FXML public void initVidUploadsCombo2(MouseEvent event) {
		ObservableList<String> data = FXCollections.observableArrayList();
		Vector<AuthorizedVideos> videoData = new Vector<>();
		
		try {
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory
					("SchoolMgtSystem");
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
					
			Query query =
					entitymanager.createQuery("select e from AuthorizedVideos e ");
			videoData = (Vector<AuthorizedVideos>) query.getResultList();
			
			entitymanager.getTransaction().commit();
			entitymanager.close();
			emfactory.close();
		}
		catch(Exception e) {
			uploadedVidLocation2.setText("Refresh Error: "+e.getLocalizedMessage());
		}
		
		for(AuthorizedVideos video : videoData) {
			
			String vid = video.getVid();
			
			data.add(vid);
		}
		
		uploadVidCombo2.setItems(data);
	}
	
	@FXML public void RefrestAuthorizedTableButton(ActionEvent evet) {
		refreshVideoLib();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void refreshVideoLib() {
		try {
			
			ObservableList<VideoLibDataTable> data = FXCollections.observableArrayList();
			Vector<AuthorizedVideos> videoData = new Vector<>();
			
			try {
				EntityManagerFactory emfactory = Persistence.createEntityManagerFactory
						("SchoolMgtSystem");
				EntityManager entitymanager = emfactory.createEntityManager();
				entitymanager.getTransaction().begin();
						
				Query query =
						entitymanager.createQuery("select e from AuthorizedVideos e ");
				videoData = (Vector<AuthorizedVideos>) query.getResultList();
				
				entitymanager.getTransaction().commit();
				entitymanager.close();
				emfactory.close();
			}
			catch(Exception e) {
				uploadedVidLocation2.setText("Refresh Error: "+e.getLocalizedMessage());
			}
			
			for(AuthorizedVideos video : videoData) {
				
				String vid = video.getVid();
				String title = video.getFileTitle();
				String genre = video.getFileClass();
				String file = video.getFileName();
				String location = video.getFileLocation();
				String user = video.getUser();
				
				data.add(new VideoLibDataTable(vid, title, genre, file, location, user));
				
			}
			
			vidCol2.setCellValueFactory(new PropertyValueFactory("vid_Data"));
			titleCol2.setCellValueFactory(new PropertyValueFactory("title_Data"));
			genreCol2.setCellValueFactory(new PropertyValueFactory("genre_Data"));
			fileCol2.setCellValueFactory(new PropertyValueFactory("file_Data"));
			locationCol2.setCellValueFactory(new PropertyValueFactory("location_Data"));
			userCol2.setCellValueFactory(new PropertyValueFactory("user_Data"));
			
			
			videoLibDataTable.setItems(data);
		}
		catch(Exception ex) {
			uploadedVidLocation2.setText(ex.getLocalizedMessage());
			videoLibDataTable.setItems(null);
		}
	}
	
	@FXML private Button uploadVidPauseBtn2, uploadVidStopBtn2;
	@FXML public void playMediaButton2(ActionEvent event) {
		String vid = uploadVidCombo2.getValue();
		
		try {
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory
					("SchoolMgtSystem");
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
					
			AuthorizedVideos video = entitymanager.find(AuthorizedVideos.class, vid);
			
			uploadedVidLocation2.setText(video.getFileLocation());
			
			entitymanager.getTransaction().commit();
			entitymanager.close();
			emfactory.close();
		}
		catch(Exception e) {
			uploadedVidLocation2.setText("Refresh Error: "+e.getLocalizedMessage());
		}
		
		String url = uploadedVidLocation2.getText();
		
		playMedia(url, uploadVidPauseBtn2, uploadVidStopBtn2);
		
	}
	
	//Play media control
	public void playMedia(String url, Button pause, Button stop) {
		
		File file = new File(url);
		try {
			String filePath = file.toURI().toURL().toString();
			
			Media media = new Media(filePath);
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			
//			mediaPlayer.currentTimeProperty()
//			.addListener(progressListener);
			
			mediaPlayer.currentTimeProperty().addListener((ObservableValue<? extends Duration> observable, 
					Duration oldValue, Duration newValue) -> {
						progressSlider.setValue(newValue.toSeconds());
					});
			
			progressSlider.valueProperty()
			.addListener((observable) -> {
				if (progressSlider.isValueChanging()) {
					// must check if media is paused before seeking
					if (mediaPlayer != null &&
					mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED) {
						// convert seconds to millis
						double dur = progressSlider.getValue() * 1000;
						mediaPlayer.seek(Duration.millis(dur));
					}
				}
			}); //addListener()
			
			mediaPlayer.setOnReady(() -> {
				progressSlider.setValue(0);
				progressSlider.setMax(mediaPlayer.getMedia()
				.getDuration()
				.toSeconds());
				mediaPlayer.play();
			}); // setOnReady()
			// back to the beginning
			mediaPlayer.setOnEndOfMedia( ()-> {
				// change buttons to play and rewind
				mediaPlayer.stop();
			}); // setOnEndOfMedia()
			
			
			mediaView.setMediaPlayer(mediaPlayer);
			mediaPlayer.play();
			
			pause.setOnMouseClicked(e -> {
				mediaPlayer.pause();
			});
			
			stop.setOnMouseClicked(e -> {
				mediaPlayer.stop();
			});
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	//Slider
	@FXML Slider progressSlider;
//	private ChangeListener<Duration> progressListener;
	
//	progressListener = (observable, oldValue, newValue) ->
//	progressSlider.setValue(newValue.toSeconds());
	
	
	
	//Jasper Report
	@FXML private ComboBox<String> jasperGenCombo2;
	String admittedPat = "C:\\repwiz\\admittedPatients.jrxml";
	String patientsList = "C:\\repwiz\\listOfPatients.jrxml";
	String NOKList = "C:\\repwiz\\patientsNOKList.jrxml";
	String staffList = "C:\\repwiz\\listOfStaff.jrxml";
	String users = "C:\\repwiz\\usersAccount.jrxml";
	String debtors = "C:\\repwiz\\debtorsList.jrxml";
	ObservableList<String> jrml = FXCollections.observableArrayList(admittedPat,patientsList,NOKList,staffList,users,debtors);
	
	@FXML public void initJasperCombo(MouseEvent event) {
		jasperGenCombo2.setItems(jrml);
	}
	
	@FXML public void reportGenWiz(ActionEvent event) {
		String report = jasperGenCombo2.getValue();
		PrintReport viewReport = new PrintReport();
		viewReport.showReport(report);
	}
	
	
	//Parametized Jasper Report
	@FXML private ComboBox<String> jasperGenCombo;
	@FXML private ComboBox<String> jasperGenCardNo;
	String patSTM = "C:\\repwiz\\patientSTMT.jrxml";
	String medHist = "C:\\repwiz\\medicalHistory.jrxml";
	ObservableList<String> jrml2 = FXCollections.observableArrayList(patSTM,medHist);
	ObservableList<String> jCardNo = FXCollections.observableArrayList();
	
	@FXML public void initJasperCombo2(MouseEvent event) {
		jasperGenCardNo.setItems(jCardNo);
		jasperGenCombo.setItems(jrml2);
	}
	
	@FXML public void reportGenWiz2(ActionEvent event) {
		String cardNo = jasperGenCardNo.getValue();
		String report = jasperGenCombo.getValue();
		
		HashMap<String, Object> param = new HashMap<>();
		param.put("cardNo2", cardNo);
		
		PrintReport2 viewReport = new PrintReport2();
		viewReport.showReport(param,report);
	}
	
}
