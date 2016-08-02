package com.mk.projects.reportGenerator;

import java.util.List;

public class Subject {

	private String name;
	private double grade;
	private List<SubjectObjectives> subjectObjectives;
	private String summaryReport;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public List<SubjectObjectives> getSubjectObjectives() {
		return subjectObjectives;
	}
	public void setSubjectObjectives(List<SubjectObjectives> subjectObjectives) {
		this.subjectObjectives = subjectObjectives;
	}
	public String getSummaryReport() {
		return summaryReport;
	}
	public void setSummaryReport(String summaryReport) {
		this.summaryReport = summaryReport;
	}
}
