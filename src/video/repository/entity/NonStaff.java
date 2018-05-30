package video.repository.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.LinkedHashSet;

import javax.persistence.Entity;
import javax.persistence.Id;

import javafx.scene.control.DatePicker;

@Entity
public class NonStaff {
	@Id
	private String cardNo;
	private String surName, otherNames, dob, sex, maritalStatus, nationality, state, lga, village, address, email, phone1, phone2, 
		nextOfKinName, nextOfKinAdd, nextOfKinPhone, dor, user;
	
	//constructors
	public NonStaff(String cardNo, String surName, String otherNames, String dob, String sex, 
			String maritalStatus, String nationality, String state, String lga, String village, 
			String address, String email, String phone1, String phone2, String nextOfKinName, 
			String nextOfKinAdd, String nextOfKinPhone, String dor, String user, 
			LinkedHashSet<String> checkInTIDSet, LinkedHashSet<String> checkOutTIDSet) {
		super();
		this.cardNo = cardNo;
		this.surName = surName;
		this.otherNames = otherNames;
		this.dob = dob;
		this.sex = sex;
		this.maritalStatus = maritalStatus;
		this.nationality = nationality;
		this.state = state;
		this.lga = lga;
		this.village = village;
		this.address = address;
		this.email = email;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.nextOfKinName = nextOfKinName;
		this.nextOfKinAdd = nextOfKinAdd;
		this.nextOfKinPhone = nextOfKinPhone;
		this.dor = dor;
		this.user = user;
	}
	public NonStaff() {
		super();
	}
	
	//getters and setters
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
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
	public void setDob(DatePicker dobx) {
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
		LocalDate date = dobx.getValue();
		String dob = formatter.format(date);
		this.dob = dob;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getNextOfKinName() {
		return nextOfKinName;
	}
	public void setNextOfKinName(String nextOfKinName) {
		this.nextOfKinName = nextOfKinName;
	}
	public String getNextOfKinAdd() {
		return nextOfKinAdd;
	}
	public void setNextOfKinAdd(String nextOfKinAdd) {
		this.nextOfKinAdd = nextOfKinAdd;
	}
	public String getNextOfKinPhone() {
		return nextOfKinPhone;
	}
	public void setNextOfKinPhone(String nextOfKinPhone) {
		this.nextOfKinPhone = nextOfKinPhone;
	}
	public String getDor() {
		return dor;
	}
	public void setDor(DatePicker dorx) {
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
		LocalDate date = dorx.getValue();
		String dor = formatter.format(date);
		this.dor = dor;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
}
