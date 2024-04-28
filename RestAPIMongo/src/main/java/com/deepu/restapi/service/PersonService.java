package com.deepu.restapi.service;

import java.util.List;
import javax.naming.directory.InvalidAttributesException;
import org.springframework.http.ResponseEntity;
import com.deepu.restapi.dto.PersonDto;
import com.deepu.restapi.model.Laptop;
import com.deepu.restapi.model.Person;

public interface PersonService {
	public ResponseEntity<PersonDto> addPerson(Person person);
	public ResponseEntity<List<PersonDto>> getAllPerson(Integer age);
	public ResponseEntity<PersonDto> deleteAllPerson();
	public ResponseEntity<PersonDto> getPerson(String name);
	public ResponseEntity<PersonDto> updatePerson(String name , Person person) throws InvalidAttributesException;
	public ResponseEntity<PersonDto> deletePerson(String name);
	public ResponseEntity<List<Laptop>> findAllLaptop(String name);
}
