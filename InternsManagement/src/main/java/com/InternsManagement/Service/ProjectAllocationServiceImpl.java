package com.InternsManagement.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.InternsManagement.InternsManagementApplication;
import com.InternsManagement.DTO.MentorDTO;
import com.InternsManagement.DTO.ProjectDTO;
import com.InternsManagement.Entity.Mentor;
import com.InternsManagement.Entity.Project;
import com.InternsManagement.Exception.InternsManagementException;
import com.InternsManagement.Repository.MentorRepository;
import com.InternsManagement.Repository.ProjectRepository;


@Service//(value="projectService")
@Transactional//
public class ProjectAllocationServiceImpl implements ProjectAllocationService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private MentorRepository mentorRepository;

	@Override
	public Integer allocateProject(ProjectDTO projectAllocation) throws InternsManagementException {
		Optional<Mentor> optional=mentorRepository.findById(projectAllocation.getMentorDTO().getMentorId());
		Mentor mentor=optional.orElseThrow(()->new InternsManagementException("Service.MENTOR_NOT_FOUND"));
		if(mentor.getNumberOfProjectsMentored()>=3) {
			throw new InternsManagementException("Service.CANNOT_ALLOCATE_PROJECT");
		}
		Project project1=new Project();
		project1.setIdeaOwner(projectAllocation.getIdeaOwner());
		project1.setProjectId(projectAllocation.getProjectId());
		project1.setMentor(mentor);
		mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored()+1);
		project1.setProjectName(projectAllocation.getProjectName());
		project1.setReleaseDate(projectAllocation.getReleaseDate());
		
		Project proj=projectRepository.save(project1);
		return proj.getProjectId();
	}

	@Override
	public List<MentorDTO> getMentors(Integer numberOfProjectsMentored) throws InternsManagementException {
		List<MentorDTO> listToReturn=new ArrayList<>();
		List<Mentor> list=mentorRepository.findByNumberOfProjectsMentored(numberOfProjectsMentored);
		
		if(list.isEmpty()) {
			throw new InternsManagementException("Service.MENTOR_NOT_FOUND");
		}
		for(Mentor mentor:list) {
			MentorDTO mDTO=new MentorDTO();
			mDTO.setMentorId(mentor.getMentorId());
			mDTO.setMentorName(mentor.getMentorName());
			mDTO.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored());
			listToReturn.add(mDTO);
		}
		return listToReturn;
	}

	@Override
	public Integer updateProjectMentor(Integer projectId, Integer mentorId) throws InternsManagementException {
		Optional<Mentor> optional=mentorRepository.findById(mentorId);
		Mentor mentor=optional.orElseThrow(()-> new InternsManagementException("Service.MENTOR_NOT_FOUND"));
		if(mentor.getNumberOfProjectsMentored()>=3) {
			throw new InternsManagementException("Service.CANNOT_ALLOCATE_PROJECT");
		}
		Optional<Project> optionalProject=projectRepository.findById(projectId);
		Project proj=optionalProject.orElseThrow(()->new InternsManagementException("Service.PROJECT_NOT_FOUND"));
		
		proj.setMentor(mentor);
		return mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored()+1);		
		
	}

	@Override
	public Integer deleteProject(Integer projectId) throws InternsManagementException {
		Optional<Project> optionalProject=projectRepository.findById(projectId);
		Project proj=optionalProject.orElseThrow(()->new InternsManagementException("Service.PROJECT_NOT_FOUND"));
		if(proj.getMentor()==null) {
			projectRepository.deleteById(projectId);
		}
		else {
			Mentor mentor=proj.getMentor();
			mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored()-1);
			proj.setMentor(null);
			projectRepository.delete(proj);
			
		}
		return proj.getProjectId();
		
		
	}

}
