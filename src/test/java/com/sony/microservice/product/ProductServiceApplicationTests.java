package com.sony.microservice.product;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

//Not using if I am not using TestContainer for mongodb
@Import(TestcontainersConfiguration.class)

//this Random port is mandatory, telling sporing to run in some port
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	//inject that random port number here
	@LocalServerPort
	private Integer port;

	//this is rest assured set up for initiating URI
	@BeforeEach
	void setUp(){
		RestAssured.baseURI="http://localhost";
		RestAssured.port = port;
	}

	@Test
	void shouldCreateProduct() {
		//this is latest java feature
		String requestBody = """
				{
				"name": "iPhone 15",
				"description": "iPhone 15 is a smartphone from Apple",
				"price": 1000
				}
				""";

		//REst Assured function to integrate test and verify
		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/product")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name", Matchers.equalTo("iPhone 15"))
				.body("description", Matchers.equalTo("iPhone 15 is a smartphone from Apple"))
				.body("price", Matchers.equalTo(1000));
	}


/*

This is used, if you want to use TestContainer for MongoDB with Docker
Static method is set up so that, the container is started before the test run
	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");


	static{
		mongoDBContainer.start();
	}
*/
}
