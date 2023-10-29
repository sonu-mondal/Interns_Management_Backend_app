package com.InternsManagement.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.InternsManagement.Entity.Mentor;

public interface MentorRepository extends CrudRepository<Mentor, Integer>{
	
	List<Mentor> findByNumberOfProjectsMentored(Integer numberOfProjectsMentored);

}
