package com.community.community_board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommunityBoardApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(CommunityBoardApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

//mysql 구동 명령어(터미널)
//brew services start mysql