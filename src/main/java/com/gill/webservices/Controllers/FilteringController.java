package com.gill.webservices.Controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.gill.webservices.model.SomeBean;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public SomeBean retrieveSomeBean() {
		return new SomeBean("value1", "value2", "value3");
	}
	
	@GetMapping("/dynamic-filtering")
	public MappingJacksonValue retrieveSomeBeanAfterDynamicFiltering() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");
		
		//1 Create property filter
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		
		//2 Create Filter Provider
		FilterProvider filters = new SimpleFilterProvider()
				.addFilter("SomeBeanFilter", filter);
		//3 Create Mapping JacksoneValue
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		//4 Set FilterValue
		mapping.setFilters(filters);
		return mapping;
	}
	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveListOfSomeBean() {
		List<SomeBean> list =Arrays.asList( new SomeBean("value1", "value2", "value3"),
				new SomeBean("value1", "value2", "value3"));
				
		//1 Create property filter
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		
		//2 Create Filter Provider
		FilterProvider filters = new SimpleFilterProvider()
				.addFilter("SomeBeanFilter", filter);
		//3 Create Mapping JacksoneValue
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		//4 Set FilterValue
		mapping.setFilters(filters);
		return mapping;
	}
//	public List<SomeBean> retrieveListOfSomeBean() {
//		return Arrays.asList( new SomeBean("value1", "value2", "value3"),
//				new SomeBean("value1", "value2", "value3"));
//	}
}
