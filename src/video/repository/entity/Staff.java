package video.repository.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import javax.persistence.Entity;
import javax.persistence.Id;
import javafx.scene.control.DatePicker;

@Entity
public class Staff {
	@Id
	private String empNo;
	private String surName, otherNames, dob, sex, placeOfBirth, nationality, maritalStatus, designation, 
		address, state, lga, village, email, phone1, phone2, disabled, ifDisabled, nextOfKin, doe, schoolAttended, 
		qualification, yearOfQualification, cadre, user;
	//constructors
	public Staff(String empNo, String surName, String otherNames, String dob, String sex, String cadre,
			String placeOfBirth, String nationality, String maritalStatus, String designation, String address,
			String state, String lga, String village, String email, String phone1, String phone2, 
			String disabled, String ifDisabled, String nextOfKin, String doe, String schoolAttended, 
			String qualification, String yearOfQualification, String user) {
		super();
		this.empNo = empNo;
		this.surName = surName;
		this.otherNames = otherNames;
		this.dob = dob;
		this.sex = sex;
		this.cadre = cadre;
		this.placeOfBirth = placeOfBirth;
		this.nationality = nationality;
		this.maritalStatus = maritalStatus;
		this.designation = designation;
		this.address = address;
		this.state = state;
		this.lga = lga;
		this.village = village;
		this.email = email;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.disabled = disabled;
		this.ifDisabled = ifDisabled;
		this.nextOfKin = nextOfKin;
		this.doe = doe;
		this.schoolAttended = schoolAttended;
		this.qualification = qualification;
		this.yearOfQualification = yearOfQualification;
		this.user = user;
	}
	public Staff() {
		super();
	}
	
	//getters and setters
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	public String getOtherNames() {
		return otherNames;
	}
	public void setOtherNames(String otherNames) {
		this.otherNames = otherNames;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(DatePicker dob) {
		LocalDate dobx = dob.getValue();
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
		String dob2 = formatter.format(dobx);
		this.dob = dob2;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLga() {
		return lga;
	}
	public void setLga(String lga) {
		this.lga = lga;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	public String getIfDisabled() {
		return ifDisabled;
	}
	public void setIfDisabled(String ifDisabled) {
		this.ifDisabled = ifDisabled;
	}
	public String getNextOfKin() {
		return nextOfKin;
	}
	public void setNextOfKin(String nextOfKin) {
		this.nextOfKin = nextOfKin;
	}
	public String getSchoolAttended() {
		return schoolAttended;
	}
	public void setSchoolAttended(String schoolAttended) {
		this.schoolAttended = schoolAttended;
	}
	public String getDoe() {
		return doe;
	}
	public void setDoe(DatePicker doe) {
		LocalDate doex = doe.getValue();
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
		String doe2 = formatter.format(doex);
		this.doe = doe2;
	}
	
	public String getCadre() {
		return cadre;
	}
	public void setCadre(String cadre) {
		this.cadre = cadre;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	
	public String getYearOfQualification() {
		return yearOfQualification;
	}
	public void setYearOfQualification(String yearOfQualification) {
		this.yearOfQualification = yearOfQualification;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}
