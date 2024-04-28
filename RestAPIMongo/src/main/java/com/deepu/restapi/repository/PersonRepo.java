package com.deepu.restapi.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.deepu.restapi.model.Person;

@Repository
public interface PersonRepo extends MongoRepository<Person, String>{
	public List<Person> findByAge(int age);
	public Person findByName(String name);
}
