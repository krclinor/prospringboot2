package com.linor.spring;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("message", "안녕하세요");
		return "welcome";
	}
}
