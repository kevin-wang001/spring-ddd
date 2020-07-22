package com.sf.ddd.sample;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ServletComponentScan
@EnableTransactionManagement
//@MapperScan("com.sf.demo.base.mapper")
@ComponentScan(basePackages = {"com.sf.ddd"})
@SpringBootApplication
public class SampleApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SampleApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

}
