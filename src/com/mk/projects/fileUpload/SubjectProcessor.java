package com.mk.projects.fileUpload;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mk.projects.reportGenerator.Student;
import com.mk.projects.reportGenerator.Subject;

public class SubjectProcessor {

	private static Logger logger = LogManager.getLogger(SubjectProcessor.class);
	
	//pass in: excel sheet, column reference to jump to and cell for objective from file processor 
	public static Subject process (Student student, String subjectName) {
		
		logger.trace("Entering SubjectProcessor.process()");
		
		Subject subject = new Subject();
		subject.setName(subjectName);
		
		logger.trace("Exiting SubjectProcessor.process()");
		
		return subject;
	}
}
