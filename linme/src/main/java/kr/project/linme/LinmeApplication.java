package kr.project.linme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

// 스케쥴러 활성화
// --> import org.springframework.scheduling.annotation.EnableScheduling; 
@EnableScheduling
@SpringBootApplication
public class LinmeApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(LinmeApplication.class, args);
	}

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LinmeApplication.class);
    }
}
