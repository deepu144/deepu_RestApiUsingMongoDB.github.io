package com.deepu.restapi.util;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.deepu.restapi.dto.LaptopDto;
import com.deepu.restapi.dto.PersonDto;
import com.deepu.restapi.model.Laptop;
import com.deepu.restapi.model.Person;

@Component
public class DtoMapper {
    public PersonDto convertToDto(Person person) {
    	PersonDto personDto = new PersonDto();
    	personDto.setName(person.getName());
    	personDto.setAge(person.getAge());
    	List<Laptop> li = person.getLaptops();
    	if(li.size()==0) {
    		personDto.setLaptops(new ArrayList<>());
    	}else {
    		personDto.setLaptops(person.getLaptops());
    	}
        return personDto;
    }

    public LaptopDto convertToDto(Laptop laptop) {
        LaptopDto laptopDto = new LaptopDto();
        laptopDto.setName(laptop.getName());
        laptopDto.setProcessor(laptop.getProcessor());
        return laptopDto;
    }
}
