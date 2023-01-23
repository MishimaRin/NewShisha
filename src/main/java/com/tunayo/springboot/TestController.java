package com.tunayo.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/index2",method = RequestMethod.GET)
public class TestController {
	@GetMapping
	public String index2() {
		return "index2";
	}

	@PostMapping("/test")
	@ResponseBody
	public String note(@RequestParam String note) {
	    return note + note;
	}

}
