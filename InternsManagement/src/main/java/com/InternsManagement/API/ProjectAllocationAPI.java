package com.InternsManagement.API;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.InternsManagement.DTO.MentorDTO;
import com.InternsManagement.DTO.ProjectDTO;
import com.InternsManagement.Exception.InternsManagementException;
import com.InternsManagement.Service.ProjectAllocationService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@RestController
@Validated
@RequestMapping(value="/interns")
public class ProjectAllocationAPI {
	
	@Autowired
	private ProjectAllocationService projectAllocationService;
	
	@Autowired
	Environment environment;
	
	@PostMapping(value="/project")
	public ResponseEntity<String> allocateProject(@RequestBody @Valid ProjectDTO project) throws InternsManagementException{
		Integer projectAllocate=projectAllocationService.allocateProject(project);
		String successMessage=environment.getProperty("API.ALLOCATION_SUCCESS")+": "+projectAllocate;
		return new ResponseEntity<String>(successMessage, HttpStatus.CREATED);
		
	}
	
	@GetMapping(value="/mentor/{numberOfProjectsMentored}")
	public ResponseEntity<List<MentorDTO>> getMentors(@PathVariable("numberOfProjectsMentored") Integer numberOfProjectsMentored) throws InternsManagementException{
		List<MentorDTO> list=projectAllocationService.getMentors(numberOfProjectsMentored);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
	@PutMapping(value="/project/{projectId}/{mentorId}")
	public ResponseEntity<String> updateProjectMentor(@PathVariable("projectId") Integer projectId, @PathVariable("mentorId") 
	@Min(value=1000, message="{mentor.mentorId.invalid}") @Max(value=9999, message="{mentor.mentorId.invalid}") Integer mentorId) throws InternsManagementException{
		
		projectAllocationService.updateProjectMentor(projectId, mentorId);
		return new ResponseEntity<String>(environment.getProperty("API.PROJECT_UPDATE_SUCCESS"), HttpStatus.OK);
	}
	
	
	
	@DeleteMapping(value="/project/{projectId}")
	public ResponseEntity<String> deleteProject(@PathVariable Integer projectId) throws InternsManagementException{
		projectAllocationService.deleteProject(projectId);
		return new ResponseEntity<String>(environment.getProperty("API.PROJECT_DELETE_SUCCESS"), HttpStatus.OK);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
