package com.InternsManagement.Entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="project")
public class Project {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer projectId;
	private String projectName;
	private Integer ideaOwner;
	private LocalDate releaseDate;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="mentor")
	private Mentor mentor;

	public Project(Integer projectId, String projectName, Integer ideaOwner, LocalDate releaseDate, Mentor mentor) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.ideaOwner = ideaOwner;
		this.releaseDate = releaseDate;
		this.mentor = mentor;
	}

	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getIdeaOwner() {
		return ideaOwner;
	}

	public void setIdeaOwner(Integer ideaOwner) {
		this.ideaOwner = ideaOwner;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Mentor getMentor() {
		return mentor;
	}

	public void setMentor(Mentor mentor) {
		this.mentor = mentor;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", ideaOwner=" + ideaOwner
				+ ", releaseDate=" + releaseDate + ", mentor=" + mentor + "]";
	}
	
	
	
	
	
}
