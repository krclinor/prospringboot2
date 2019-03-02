package com.linor.spring;

import java.io.File;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class SpringBootSimpleApplication {

	public static void main(String[] args) {
		//SpringApplication.run(Ch0301SpringbootSimpleApplication.class, args);
//		SpringApplication app = 
//				new SpringApplication(SpringBootSimpleApplication.class);
//		app.setBanner(new Banner() {
//			@Override
//			public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
//				out.print("\n\n\t나만의 배너입니다!\n\n");
//			}
//		});
//		app.setBannerMode(Banner.Mode.OFF);
//		app.run(args);
		new SpringApplicationBuilder()
			.bannerMode(Banner.Mode.OFF)
			.logStartupInfo(false)
			.sources(SpringBootSimpleApplication.class)
			.listeners(new ApplicationListener<ApplicationEvent>(){
				@Override
				public void onApplicationEvent(ApplicationEvent event) {
					log.info("#### > " + event.getClass().getCanonicalName());
				}
			})
			.run(args);
	}

}

@Component
@Slf4j
class MyComponent{
	public MyComponent(ApplicationArguments args) {
		boolean enable = args.containsOption("enable");
		if(enable)
			log.info("## > You are enabled!");
		
		List<String> _argsList = args.getNonOptionArgs();
		log.info("## > extra args ...");
		
		if(!_argsList.isEmpty())
			_argsList.forEach(file -> log.info(file));
	}
}
