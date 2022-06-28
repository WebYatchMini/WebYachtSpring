package com.inha.dice_game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.websocket.server.ServerEndpoint;

@SpringBootApplication
public class DiceGameApplication {
	public static void main(String[] args) {
		SpringApplication.run(DiceGameApplication.class, args);
	}

}
