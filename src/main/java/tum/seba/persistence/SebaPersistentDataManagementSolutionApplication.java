package tum.seba.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import tum.seba.persistence.entity.Backpack;
import tum.seba.persistence.entity.Device;
import tum.seba.persistence.entity.Lecture;
import tum.seba.persistence.entity.Student;
import tum.seba.persistence.service.AdminService;

@SpringBootApplication
public class SebaPersistentDataManagementSolutionApplication {
	
	@Autowired
	AdminService adminService;

	public static void main(String[] args) {
		SpringApplication.run(SebaPersistentDataManagementSolutionApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void execCodeAfterStartup() {
	
		// create test data
		Student testStudent1 = new Student("Max", 22, "max123", "max@tum.de");
		Student testStudent2 = new Student("Anna", 20, "anna123", "anna@tum.de");
		Student testStudent3 = new Student("Chris", 23, "chris123", "chris@tum.de");
		Backpack testBackpack = new Backpack("red");
		Device testDevice1 = new Device("Laptop");
		Device testDevice2 = new Device("Phone");
		Device testDevice3 = new Device("Tablet");
		Lecture testLecture1 = new Lecture("SEBA Bachelor");
		Lecture testLecture2 = new Lecture("SEBA Master");
		Lecture testLecture3 = new Lecture("SEBA Lab");
		
		// One-To-One
		testStudent1.setBackpack(testBackpack);
		adminService.saveStudent(testStudent1);
		
		// One-To-Many / Many-To-One
		testDevice1.setOwner(testStudent2);
		testDevice2.setOwner(testStudent2);
		testDevice3.setOwner(testStudent2);
		List<Device> testDevices = new ArrayList<>(List.of(testDevice1, testDevice2, testDevice3));
		testStudent2.setDevices(testDevices);
		adminService.saveStudent(testStudent2);
		
		// Many-To-Many
		List<Student> testStudents = new ArrayList<>(List.of(testStudent1, testStudent2, testStudent3));
		testLecture1.setStudents(testStudents);
		testLecture2.setStudents(testStudents);
		testLecture3.setStudents(testStudents);
		testStudent3.setLectures(new ArrayList<>(List.of(testLecture1, testLecture2, testLecture3)));
		adminService.saveStudent(testStudent3);
		
		// test queries
		System.out.println("Examples");
		System.out.println(adminService.findAllStudentsWithJPQL());
		System.out.println(adminService.findAllStudentsWithCriteriaAPI());
		System.out.println(adminService.findAllStudentsWithQuerydsl());
		
		System.out.println("Query 1");
		
		System.out.println(adminService.findStudentByUsernameWithJPQL("max123"));
		System.out.println(adminService.findStudentByUsernameWithCriteriaAPI("max123"));
		System.out.println(adminService.findStudentByUsernameWithQuerydsl("max123"));
		
		System.out.println("Query 2");
		
		System.out.println(adminService.findStudentsbyAgeRangeWithJPQL(21, 25));
		System.out.println(adminService.findStudentsByAgeRangeWithCriteriaAPI(21, 25));
		System.out.println(adminService.findStudentsByAgeRangeWithQuerydsl(21, 25));
		
		System.out.println("Query 3");
		
		System.out.println(adminService.findDevicesByStudentUsernameWithJPQL("anna123"));
		System.out.println(adminService.findDevicesByStudentUsernameWithCriteriaAPI("anna123"));
		System.out.println(adminService.findDevicesByStudentUsernameWithQuerydsl("anna123"));
		
	}

}
