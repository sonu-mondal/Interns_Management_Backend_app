package com.InternsManagement.Service;

import java.util.List;

import com.InternsManagement.DTO.MentorDTO;
import com.InternsManagement.DTO.ProjectDTO;
import com.InternsManagement.Exception.InternsManagementException;

public interface ProjectAllocationService {

	public Integer allocateProject(ProjectDTO projectAllocation) throws InternsManagementException;
	
	public List<MentorDTO> getMentors(Integer numberOfProjectsMentored) throws InternsManagementException;
	
	public Integer updateProjectMentor(Integer projectId, Integer mentorId) throws InternsManagementException;
	
	public Integer deleteProject(Integer projectId) throws InternsManagementException;
	
	
	
	
	
}
