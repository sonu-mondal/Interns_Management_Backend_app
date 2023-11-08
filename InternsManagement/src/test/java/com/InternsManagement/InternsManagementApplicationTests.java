package com.InternsManagement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.InternsManagement.API.ProjectAllocationAPI;
import com.InternsManagement.DTO.MentorDTO;
import com.InternsManagement.Exception.InternsManagementException;
import com.InternsManagement.Service.ProjectAllocationService;
import com.InternsManagement.Service.ProjectAllocationServiceImpl;

@SpringBootTest
class InternsManagementApplicationTests {
	@InjectMocks
	private ProjectAllocationAPI projectAllocationAPI;
	
	@Mock
	private ProjectAllocationServiceImpl projectAllocationServiceImpl;
	

	@Test
	void contextLoads() {
	}

	@SuppressWarnings("deprecation")
	@Test
	@DisplayName("Test success scenario for fetching mentor details")
	public void testGetMentors() throws InternsManagementException {
		List<MentorDTO> mdto=new ArrayList<>();
		MentorDTO dto=new MentorDTO();
		dto.setMentorId(1009);
		dto.setMentorName("Sonu");
		dto.setNumberOfProjectsMentored(1);
		
		mdto.add(dto);
		
		Mockito.when(projectAllocationServiceImpl.getMentors(1)).thenReturn(mdto);
		ResponseEntity<List<MentorDTO>> list=projectAllocationAPI.getMentors(1);
		assertEquals(1, list.getBody().size());
		assertEquals(HttpStatus.OK.value(), list.getStatusCodeValue());
	}
}
