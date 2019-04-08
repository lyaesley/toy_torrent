package io.toy.torrent.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("toysystem/v1")
@RestController
public class ToySystemController {

	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello World";
	}
}
