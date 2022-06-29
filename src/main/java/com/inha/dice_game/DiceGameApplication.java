package com.inha.dice_game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.websocket.server.ServerEndpoint;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = {"com.inha.dice_game.repository"})
//@EntityScan(basePackages = {"com.inha.dice_game.entity"})
public class DiceGameApplication {
	public static void main(String[] args) {
		SpringApplication.run(DiceGameApplication.class, args);
	}

}
