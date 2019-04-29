package com.lalsberg.ops;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeRedirector {

	@RequestMapping(value = "/{path:[^\\.]*}")
	public String redirect() {
	  return "forward:/";
	}

}
