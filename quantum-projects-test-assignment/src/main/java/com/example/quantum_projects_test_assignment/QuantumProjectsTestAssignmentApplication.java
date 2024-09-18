package com.example.quantum_projects_test_assignment;

import com.example.quantum_projects_test_assignment.controller.AppController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuantumProjectsTestAssignmentApplication implements CommandLineRunner {
	@Autowired
	AppController appController;

	public static void main(String[] args) {
		SpringApplication.run(QuantumProjectsTestAssignmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		appController.runApp();
	}
}
