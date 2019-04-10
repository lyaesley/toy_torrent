package io.toy.system.status;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("toy/v1/system")
@RestController
public class SystemStatusController {
	
	@GetMapping("/health")
	public String healthCheck() {
		return "OK";
	}

}
