package tum.seba.persistence.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FirstController {

	@GetMapping("/welcome")
	public ResponseEntity<?> sayHello() {
		return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
	}

}
