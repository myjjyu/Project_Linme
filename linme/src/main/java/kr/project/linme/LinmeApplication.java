package kr.project.linme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// 스케쥴러 활성화
// --> import org.springframework.scheduling.annotation.EnableScheduling; 
@EnableScheduling
@SpringBootApplication
public class LinmeApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinmeApplication.class, args);
	}

}
