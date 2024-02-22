package com.jromeo.internshipbackend.controller;

import com.jromeo.internshipbackend.entity.Internship;
import com.jromeo.internshipbackend.repository.InternshipRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class InternshipControllerTest {

    @LocalServerPort
    private Integer port;


    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.3.0");

    @BeforeAll
    static void beforeAll() {
        mysql.start();
    }

    @AfterAll
    static void afterAll() {
        mysql.stop();
    }

    @DynamicPropertySource
    static void setDatasourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }

    @Autowired
    InternshipRepository internshipRepository;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        internshipRepository.deleteAll();
    }

    @Test
    void should_create_internship() {
        Internship internship = new Internship(
                1,
                "Johan AB",
                "Johan provar TestContainers",
                "testcontainers.com",
                false
        );

        given()
                .contentType(ContentType.JSON)
                .body(internship)
                .when()
                .post("/api/internships")
                .then()
                .statusCode(201);
    }
    @Test
    void should_get_all_internships() {
        List<Internship> internships = List.of(
                new Internship(
                        null,
                        "Johan AB",
                        "Johan provar TestContainers",
                        "testcontainers.com",
                        false
                ),
                new Internship(
                        null,
                        "Eiemi AB",
                        "Eiemi provar TestContainers",
                        "testcontainers.com",
                        true
                ));
        internshipRepository.saveAll(internships);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/internships")
                .then()
                .statusCode(200)
                .body(".", hasSize(2));
    }

}