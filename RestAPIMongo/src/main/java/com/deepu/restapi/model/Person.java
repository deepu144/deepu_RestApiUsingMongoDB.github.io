package com.deepu.restapi.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "person")
public class Person {
	@Id
	private String id;
	@Indexed(unique = true)
	private String name;
	private Integer age;
	private List<Laptop> laptops = new ArrayList<>();
}
