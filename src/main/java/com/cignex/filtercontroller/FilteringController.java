package com.cignex.filtercontroller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cignex.bean.Somebean;

@RestController
public class FilteringController {
	@GetMapping("/filter")
	public Somebean someBean() {
		return new Somebean("value1", "value2", "value3");
	}
	
	@GetMapping("/filter-list")
	public List<Somebean> listOfSomeBeans() {
		return Arrays.asList(new Somebean("value1", "value2", "value3"),
				new Somebean("value12", "value22", "value23"),
				new Somebean("value31", "value23", "value33"));
	}
}
