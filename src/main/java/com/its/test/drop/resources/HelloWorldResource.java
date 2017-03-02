package com.its.test.drop.resources;

import com.its.test.drop.api.Person;
import com.its.test.drop.api.Saying;
import com.its.test.drop.db.PersonDao;
import com.codahale.metrics.annotation.Timed;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
	private final String template;
	private final String defaultName;
	private final AtomicLong counter;

	private static final Logger logger = LoggerFactory.getLogger(HelloWorldResource.class);

	@Inject
	PersonDao personDao;
	
	public HelloWorldResource(String template, String defaultName) {
		this.template = template;
		this.defaultName = defaultName;
		this.counter = new AtomicLong();
	}

	@GET
	@Timed
	public Saying sayHello(@QueryParam("name") Optional<String> name) {
		final String value = String.format(template, name.orElse(defaultName));
		return new Saying(counter.incrementAndGet(), value);
	}

	@POST
	@Timed
	@Path("/save")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Person addPerson(Person person) {
		logger.info("Adding new person {}",person);
		return personDao.save(person);
	}
	
	@PUT
	@Timed
	@Path("/save/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Person updatePerson(@PathParam("id") String id,  Person person) {
		logger.info("Update  person {} {}",id, person);
		return personDao.save(person);
	}
	
	
	
	@DELETE
	@Timed
	@Path("/save/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Person deletePerson(@PathParam("id") String id, Person person) {
		logger.info("Delete  person {} {}", id, person);
		return personDao.save(person);
	}
	
	
}