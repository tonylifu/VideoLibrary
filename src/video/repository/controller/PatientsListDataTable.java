package video.repository.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;

public class PatientsListDataTable {
	@FXML private SimpleStringProperty sNo_Data;
	@FXML private SimpleStringProperty cardNo_Data, name_Data, sex_Data, age_Data, address_Data, phone_Data, 
	 	nokDetails_Data, nokPhone_Data, acctBal_Data;
	
	public PatientsListDataTable(String sNo, String cardNo, String name, String sex, String age, String address, 
			String phone, String nokDetails, String nokPhone, String acctBal) {
		this.sNo_Data = new SimpleStringProperty(sNo);
		this.cardNo_Data = new SimpleStringProperty(cardNo);
		this.name_Data = new SimpleStringProperty(name);
		this.sex_Data = new SimpleStringProperty(sex);
		this.age_Data = new SimpleStringProperty(age);
		this.address_Data = new SimpleStringProperty(address);
		this.phone_Data = new SimpleStringProperty(phone);
		this.nokDetails_Data = new SimpleStringProperty(nokDetails);
		this.nokPhone_Data = new SimpleStringProperty(nokPhone);
		this.acctBal_Data = new SimpleStringProperty(acctBal);
		
	}

	public String getSNo_Data() {
		return sNo_Data.get();
	}
	public void setSNo_Data(String sNo) {
		sNo_Data.set(sNo);
	}
	
	public String getCardNo_Data() {
		return cardNo_Data.get();
	}
	public void setCardNo_Data(String cardNo) {
		cardNo_Data.set(cardNo);
	}
	
	public String getName_Data() {
		return name_Data.get();
	}
	public void setName_Data(String name) {
		name_Data.set(name);
	}
	public String getSex_Data() {
		return sex_Data.get();
	}
	public void setSex_Data(String sex) {
		sex_Data.set(sex);
	}
	
	public String getAge_Data() {
		return age_Data.get();
	}
	
	public void setAge_Data(String age) {
		age_Data.set(age);
	}
	
	public String getAddress_Data() {
		return address_Data.get();
	}
	public void setAddress_Data(String address) {
		address_Data.set(address);
	}
	
	public String getPhone_Data() {
		return phone_Data.get();
	}
	public void setPhone_Data(String phone) {
		phone_Data.set(phone);
	}
	
	public String getNokDetails_Data() {
		return nokDetails_Data.get();
	}
	public void setNokDetails_Data(String nokDetails) {
		nokDetails_Data.set(nokDetails);
	}
	
	public String getNokPhone_Data() {
		return nokPhone_Data.get();
	}
	public void setNokPhone_Data(String nokPhone) {
		nokPhone_Data.set(nokPhone);
	}
	
	public String getAcctBal_Data() {
		return acctBal_Data.get();
	}
	public void setAcctBal_Data(String acctBal) {
		acctBal_Data.set(acctBal);
	}
}
