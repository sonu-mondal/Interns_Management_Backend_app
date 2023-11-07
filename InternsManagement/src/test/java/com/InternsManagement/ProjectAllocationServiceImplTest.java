package com.InternsManagement;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.InternsManagement.DTO.MentorDTO;
import com.InternsManagement.DTO.ProjectDTO;
import com.InternsManagement.Entity.Mentor;
import com.InternsManagement.Entity.Project;
import com.InternsManagement.Exception.InternsManagementException;
import com.InternsManagement.Repository.MentorRepository;
import com.InternsManagement.Repository.ProjectRepository;
import com.InternsManagement.Service.ProjectAllocationServiceImpl;

@SpringBootTest
public class ProjectAllocationServiceImplTest {
	
	@Mock
	private ProjectRepository projectRepository;
	@Mock
	private MentorRepository mentorRepository;
	@InjectMocks
	private ProjectAllocationServiceImpl  projectAllocationServiceImpl;
	
//	@Test
//	public void allocateProjectTestSuccess() throws InternsManagementException{
//		Mentor mentor1=new Mentor(5000, "sonu", 2);
//		Project project1=new Project(1,"Interns Management", 10000,LocalDate.of(2023, 06, 11), mentor1);
//		
//		MentorDTO mDTO=new MentorDTO(5000, "sonu", 2);
//		ProjectDTO pDTO=new ProjectDTO(1,"Interns Management", 10000,LocalDate.of(2023, 06, 11), mDTO);
//		
//		Mockito.<Optional<Mentor>>when(mentorRepository.findById(pDTO.getMentorDTO().getMentorId())).thenReturn(Optional.of(mentor1));
//		Mockito.when(projectRepository.save(project1)).thenReturn(project1);
//		Assertions.assertEquals(project1.getProjectId(), projectAllocationServiceImpl.allocateProject(pDTO));
//		
//	}
	
	@Test
	public void allocateProjectTestFailure() throws InternsManagementException{
		Mentor mentor1=new Mentor(5000, "sonu", 4);
		Project project1=new Project(1,"Interns Management", 10000,LocalDate.of(2023, 06, 11), mentor1);
		
		MentorDTO mDTO=new MentorDTO(5000, "sonu", 4);
		ProjectDTO pDTO=new ProjectDTO(1,"Interns Management", 10000,LocalDate.of(2023, 06, 11), mDTO);
		
		Mockito.<Optional<Mentor>>when(mentorRepository.findById(pDTO.getMentorDTO().getMentorId())).thenReturn(Optional.of(mentor1));
		//Mockito.when(projectRepository.save(project1)).thenReturn(project1);
		InternsManagementException exc=Assertions.assertThrows(InternsManagementException.class, ()->projectAllocationServiceImpl.allocateProject(pDTO));
		Assertions.assertEquals("Service.CANNOT_ALLOCATE_PROJECT",exc.getMessage());
		
	}
	
	@Test
	public void updateProjectMentorTestSuccess() throws InternsManagementException{
		Mentor mentor1=new Mentor(5000, "sonu", 2);
		Project project1=new Project(1,"Interns Management", 10000,LocalDate.of(2023, 06, 11), mentor1);
		
		Mockito.<Optional<Mentor>>when(mentorRepository.findById(mentor1.getMentorId())).thenReturn(Optional.of(mentor1));
		Mockito.<Optional<Project>>when(projectRepository.findById(project1.getProjectId())).thenReturn(Optional.of(project1));
		Assertions.assertEquals(mentor1.getNumberOfProjectsMentored()+1, projectAllocationServiceImpl.updateProjectMentor(project1.getProjectId(), project1.getMentor().getMentorId()));
		
	}
	
	@Test
	public void updateProjectMentorTestFailure1() throws InternsManagementException{
		Mentor mentor1=new Mentor(5000, "sonu", 4);
		Project project1=new Project(1,"Interns Management", 10000,LocalDate.of(2023, 06, 11), mentor1);
		
		Mockito.<Optional<Mentor>>when(mentorRepository.findById(mentor1.getMentorId())).thenReturn(Optional.of(mentor1));
		Mockito.<Optional<Project>>when(projectRepository.findById(project1.getProjectId())).thenReturn(Optional.of(project1));
		InternsManagementException exc=Assertions.assertThrows(InternsManagementException.class, ()->projectAllocationServiceImpl.updateProjectMentor(project1.getProjectId(), mentor1.getMentorId()));
		Assertions.assertEquals("Service.CANNOT_ALLOCATE_PROJECT", exc.getMessage());
		
		
	}
	
	@Test
	public void updateProjectMentorTestFailure2() throws InternsManagementException{
		Mentor mentor1=new Mentor(5000, "sonu", 2);
		Project project1=new Project(1,"Interns Management", 10000,LocalDate.of(2023, 06, 11), mentor1);
		
		Mockito.<Optional<Mentor>>when(mentorRepository.findById(mentor1.getMentorId())).thenReturn(Optional.of(mentor1));
		Mockito.<Optional<Project>>when(projectRepository.findById(project1.getProjectId())).thenReturn(Optional.of(project1));
		InternsManagementException exc=Assertions.assertThrows(InternsManagementException.class, ()->projectAllocationServiceImpl.updateProjectMentor(project1.getProjectId(), 1200));
		Assertions.assertEquals("Service.MENTOR_NOT_FOUND", exc.getMessage());
		
		
	}
	
	@Test
	public void updateProjectMentorTestFailure3() throws InternsManagementException{
		Mentor mentor1=new Mentor(5000, "sonu", 0);
		Project project1=new Project(1,"Interns Management", 10000,LocalDate.of(2023, 06, 11), mentor1);
		
		Mockito.<Optional<Mentor>>when(mentorRepository.findById(mentor1.getMentorId())).thenReturn(Optional.of(mentor1));
		Mockito.<Optional<Project>>when(projectRepository.findById(project1.getProjectId())).thenReturn(Optional.of(project1));
		InternsManagementException exc=Assertions.assertThrows(InternsManagementException.class, ()->projectAllocationServiceImpl.updateProjectMentor(2, mentor1.getMentorId()));
		Assertions.assertEquals("Service.PROJECT_NOT_FOUND", exc.getMessage());
		
		
	}
	

	@Test
	public void deleteProjectTestSuccess() throws InternsManagementException{
		Mentor mentor1=new Mentor(5000, "sonu", 2);
		Project project1=new Project(1,"Interns Management", 10000,LocalDate.of(2023, 06, 11), mentor1);
		
		Mockito.<Optional<Project>>when(projectRepository.findById(project1.getProjectId())).thenReturn(Optional.of(project1));
		Assertions.assertEquals(project1.getProjectId(), projectAllocationServiceImpl.deleteProject(project1.getProjectId()));
	}
	

	@Test
	public void deleteProjectTestFailure() throws InternsManagementException{
		Mentor mentor1=new Mentor(5000, "sonu", 2);
		Project project1=new Project(1,"Interns Management", 10000,LocalDate.of(2023, 06, 11), mentor1);
		
		Mockito.<Optional<Project>>when(projectRepository.findById(project1.getProjectId())).thenReturn(Optional.of(project1));
		InternsManagementException exc=Assertions.assertThrows(InternsManagementException.class, ()->projectAllocationServiceImpl.deleteProject(3));
		Assertions.assertEquals("Service.PROJECT_NOT_FOUND", exc.getMessage());
	}
	
	

}
