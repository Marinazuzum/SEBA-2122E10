package tum.seba.persistence.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tum.seba.persistence.entity.Student;
import tum.seba.persistence.service.AdminService;
import tum.seba.persistence.service.RequestValidationService;

@RestController
@RequestMapping("/api")
public class StudentController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private RequestValidationService requestValidationService;

	// CRUD

	@GetMapping("/students")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<Iterable<Student>>(adminService.findAllStudents(), HttpStatus.OK);
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<?> findOne(@PathVariable int id) {
		return new ResponseEntity<Student>(adminService.findStudentById(id), HttpStatus.OK);
	}

	@PostMapping("/students")
	public ResponseEntity<?> newStudent(@Valid @RequestBody Student newStudent, BindingResult result) {
		Map<String, String> errorMap = requestValidationService.handleValidationErrors(result);
		if (errorMap != null) return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<Student>(adminService.saveStudent(newStudent), HttpStatus.CREATED);
	}

	@PutMapping("/students/{id}")
	public ResponseEntity<?> replaceStudent(@PathVariable int id, @Valid @RequestBody Student newStudent, BindingResult result) {
		Map<String, String> errorMap = requestValidationService.handleValidationErrors(result);
		if (errorMap != null) return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		newStudent.setId(id);
		adminService.saveStudent(newStudent);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/students/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable int id) {
		adminService.deleteStudentById(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	// Queries

	@GetMapping("/students/jpql")
	public Student findStudentByUsername(@RequestParam(value="username") String username) {
		return adminService.findStudentByUsernameWithJPQL(username);
	}

	@GetMapping("/students/criteriaapi")
	public Iterable<Student> findStudentByAgeRange(@RequestParam(value="age1") int age1, @RequestParam(value="age2") int age2) {
		return adminService.findStudentsByAgeRangeWithCriteriaAPI(age1, age2);
	}

}
