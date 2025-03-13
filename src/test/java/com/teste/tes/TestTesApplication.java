package com.teste.tes;

import org.springframework.boot.SpringApplication;

public class TestTesApplication {

	public static void main(String[] args) {
		SpringApplication.from(TesApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
