package com.example.jwt;

		import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.SpringBootApplication;
		import org.springframework.cache.annotation.EnableCaching;
		import org.springframework.context.annotation.ComponentScan;
		import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
//@ComponentScan({"com.example.jwt.rest.system"})
@EnableWebMvc
@EnableCaching// 开启缓存，需要显示的指定

public class JwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtApplication.class, args);
	}
}
