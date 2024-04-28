package com.deepu.restapi.serviceImpl;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;
import javax.naming.directory.InvalidAttributesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.deepu.restapi.dto.PersonDto;
import com.deepu.restapi.model.Laptop;
import com.deepu.restapi.model.Person;
import com.deepu.restapi.repository.PersonRepo;
import com.deepu.restapi.service.PersonService;
import com.deepu.restapi.util.DtoMapper;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	private PersonRepo personRepo;
	@Autowired
	private DtoMapper dtoMapper;

	public ResponseEntity<PersonDto> addPerson(Person person) throws InvalidParameterException{
		if(person.getAge()==null || person.getName()==null) {
			throw new InvalidParameterException();
		}
		PersonDto personDto = dtoMapper.convertToDto(person);
		personRepo.save(person);
		return new ResponseEntity<PersonDto>(personDto, HttpStatus.CREATED);
	}

	public ResponseEntity<List<PersonDto>> getAllPerson(Integer age) {
		if (age != null) {
			List<Person> li = personRepo.findByAge(age);
			if (li.size() > 0) {
				List<PersonDto> pdList = personRepo.findByAge(age).stream().map(n -> dtoMapper.convertToDto(n)).collect(Collectors.toList());
				return new ResponseEntity<List<PersonDto>>(pdList, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} else {
			List<PersonDto> pdList = personRepo.findAll().stream().map(n -> dtoMapper.convertToDto(n)).collect(Collectors.toList());
			if(pdList.size()>0) {
				return new ResponseEntity<List<PersonDto>>(pdList, HttpStatus.OK);				
			}else {
				return new ResponseEntity<List<PersonDto>>(HttpStatus.NO_CONTENT);
			}
		}
	}

	public ResponseEntity<PersonDto> deleteAllPerson() {
		personRepo.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	public ResponseEntity<PersonDto> getPerson(String name) {
		Person person = personRepo.findByName(name);
		if (person!=null) {
			PersonDto personDto = dtoMapper.convertToDto(person);
			return new ResponseEntity<PersonDto>(personDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<PersonDto> updatePerson(String name, Person person) throws InvalidAttributesException {
		if(person.getAge()==null || person.getName()==null || name==null) {
			throw new InvalidAttributesException();
		}
		Person personTest = personRepo.findByName(name);
		if (personTest!=null) {
			personTest.setAge(person.getAge());
			personTest.setName(person.getName());
			personTest.setLaptops(person.getLaptops());
			personRepo.save(personTest);
			PersonDto personDto = dtoMapper.convertToDto(personTest);
			return new ResponseEntity<PersonDto>(personDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<PersonDto> deletePerson(String name) {
		Person person = personRepo.findByName(name);
		if (person!=null) {
			PersonDto personDto = dtoMapper.convertToDto(person);
			personRepo.delete(person);
			return new ResponseEntity<>(personDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<List<Laptop>> findAllLaptop(String name) {
		Person person = personRepo.findByName(name);
		if (person!=null) {
			List<Laptop> laptopNames = person.getLaptops();
			return new ResponseEntity<List<Laptop>>(laptopNames, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
