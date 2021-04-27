package com.gill.webservices.Versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

	//Following two are via URI 
	@GetMapping("/v1/person")
	public PersonV1 getPersonV1() {
		return new PersonV1("Ahmad");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getPersonV2() {
		return new PersonV2(new Name("Ahmad", "Gill"));
	}
	
	//Following 2 are via params
	@GetMapping(value="/person/param", params = "version=1")
	public PersonV1 getPersonV1ViaParam() {
		return new PersonV1("Ahmad");
	}
	
	@GetMapping(value="/person/param", params = "version=2")
	public PersonV2 getPersonV2ViaParam() {
		return new PersonV2(new Name("Ahmad", "Gill"));
	}
	
	//Following 2 are via Headers
	@GetMapping(value="/person/header", headers = "X-API-VERSION=1")
	public PersonV1 getPersonV1ViaHeader() {
		return new PersonV1("Ahmad");
	}
	
	@GetMapping(value="/person/header", headers = "X-API-VERSION=2")
	public PersonV2 getPersonV2ViaHeader() {
		return new PersonV2(new Name("Ahmad", "Gill"));
	}
	
	//Following 2 are via produces
	@GetMapping(value="/person/produces", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getPersonV1ViaProduces() {
		return new PersonV1("Ahmad");
	}
	
	@GetMapping(value="/person/produces", produces = "application/vnd.company.app-v2+json")
	public PersonV2 getPersonV2ViaProduces() {
		return new PersonV2(new Name("Ahmad", "Gill"));
	}
	
//	None oof these approches are perfect, you have to look into your solution and then choose which one is best for you
	
}
