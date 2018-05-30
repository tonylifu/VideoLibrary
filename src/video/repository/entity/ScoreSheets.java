package com.school.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@IdClass(ScoreSheetsPk.class)
@Entity
public class ScoreSheets {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE) 
	private long sId;
	private int stdClass;
	private int stdId;
	private int term;
	private int subjectCode;
	
	private double CA1, CA2, CA3, examScore, total;
	private String name, subjectName, classLetter, grade;
	
	//constructors
	public ScoreSheets(long sId, int stdId, int stdClass, int term, int subjectCode, 
			String subjectName, double CA1, double CA2, double CA3, double examScore, double total, 
			String name, String classLetter, String grade) {
		super();
		this.sId = sId;
		this.stdId = stdId;
		this.stdClass = stdClass;
		this.term = term;
		this.subjectCode = subjectCode;
		this.subjectName = subjectName;
		this.CA1 = CA1;
		this.CA2 = CA2;
		this.CA3 = CA3;
		this.examScore = examScore;
		this.total = total;
		this.name = name;
		this.classLetter = classLetter;
		this.grade = grade;
	}
	public ScoreSheets() {
		super();
	}
	
	public long getSId() {
		return sId;
	}
	
	public void setSId(long sId) {
		this.sId = sId;
	}
	
	public int getStdId() {
		return stdId;
	}
	public void setStdId(int stdId) {
		this.stdId = stdId;
	}
	public int getStdClass() {
		return stdClass;
	}
	public void setStdClass(int stdClass) {
		this.stdClass = stdClass;
	}
	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	public int getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(int subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public double getCA1() {
		return CA1;
	}
	public void setCA1(double CA1) {
		this.CA1 = CA1;
	}
	public double getCA2() {
		return CA2;
	}
	public void setCA2(double CA2) {
		this.CA2 = CA2;
	}
	public double getCA3() {
		return CA3;
	}
	public void setCA3(double CA3) {
		this.CA3 = CA3;
	}
	public double getExamScore() {
		return examScore;
	}
	public void setExamScore(double examScore) {
		this.examScore = examScore;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		total = CA1 + CA2 + CA3 + examScore;
		this.total = total;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassLetter() {
		return classLetter;
	}
	public void setClassLetter(String classLetter) {
		this.classLetter = classLetter;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
	//override toString
	@Override
	public String toString() {
		return "[Std ID: "+stdId + ", Class: "+ stdClass+ ", Term: "+ term+ ", Subject Code: "+subjectCode+", Subject: "+subjectName
				+"\nCA1: "+CA1+", CA2: "+CA2+", CA3: "+CA3+", Exam Score: "+examScore+", Total: "+ total 
				+"\nName: "+name+", Class: "+classLetter+", Grade: "+grade+"]";
	}
}
