package com.deepu.restapi.dto;

import java.util.List;
import com.deepu.restapi.model.Laptop;
import lombok.Data;

@Data
public class PersonDto {
	private String id;
	private String name;
	private Integer age;
	private List<Laptop> laptops;
}
