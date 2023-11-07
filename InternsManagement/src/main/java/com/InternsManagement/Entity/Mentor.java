package com.InternsManagement.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="mentor")
public class Mentor {
	
	@Id
	private Integer mentorId;
	private String mentorName;
	@Column(name="projects_mentored")
	private Integer numberOfProjectsMentored;
	
	public Mentor(Integer mentorId, String mentorName, Integer numberOfProjectsMentored) {
		super();
		this.mentorId = mentorId;
		this.mentorName = mentorName;
		this.numberOfProjectsMentored = numberOfProjectsMentored;
	}

	public Mentor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getMentorId() {
		return mentorId;
	}

	public void setMentorId(Integer mentorId) {
		this.mentorId = mentorId;
	}

	public String getMentorName() {
		return mentorName;
	}

	public void setMentorName(String mentorName) {
		this.mentorName = mentorName;
	}

	public Integer getNumberOfProjectsMentored() {
		return numberOfProjectsMentored;
	}

	public Integer setNumberOfProjectsMentored(Integer numberOfProjectsMentored) {
		return this.numberOfProjectsMentored = numberOfProjectsMentored;
	}

	@Override
	public String toString() {
		return "Mentor [mentorId=" + mentorId + ", mentorName=" + mentorName + ", numberOfProjectsMentored="
				+ numberOfProjectsMentored + "]";
	}
	
	
}
