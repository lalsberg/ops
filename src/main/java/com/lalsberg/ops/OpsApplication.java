package com.lalsberg.ops;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class OpsApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(OpsApplication.class, args);

		Ops ops = new Ops("bla", "agora vai entao ehn");
		List<Resolution> resolutions = run.getBean(ResolutionResolver.class).findPossibleResolutions(ops);

		for (Resolution resolution : resolutions) {
			System.out.println(resolution);
		}

	}

}
