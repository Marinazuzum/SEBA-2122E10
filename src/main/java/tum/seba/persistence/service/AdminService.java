package tum.seba.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tum.seba.persistence.entity.Backpack;
import tum.seba.persistence.entity.Device;
import tum.seba.persistence.entity.Lecture;
import tum.seba.persistence.entity.Student;
import tum.seba.persistence.exception.EntityNotFoundException;
import tum.seba.persistence.exception.UniqueConstraintViolationException;
import tum.seba.persistence.repository.BackpackRepository;
import tum.seba.persistence.repository.DeviceRepository;
import tum.seba.persistence.repository.LectureRepository;
import tum.seba.persistence.repository.StudentRepository;

@Service
public class AdminService {
	
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private BackpackRepository backpackRepository;

	@Autowired
	private DeviceRepository deviceRepository;

	@Autowired
	private LectureRepository lectureRepository;

	public Student saveStudent(Student newStudent) {
		try {
			return studentRepository.save(newStudent);
		} catch (Exception e) {
			throw new UniqueConstraintViolationException("Username '" + newStudent.getUsername() + "' is already taken!");
		}
	}

	public Iterable<Student> findAllStudents() {
		return studentRepository.findAll();
	}

	public Student findStudentById(int studentId) {
		return studentRepository.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Student with ID '" + studentId + "' does not exist!"));
	}

	public void deleteStudentById(int studentId) {
		try {
			studentRepository.deleteById(studentId);
		} catch (Exception e) {
			System.err.println("Unable to delete Student with ID: " + studentId);
		}
	}

	public Backpack saveBackpack(Backpack newBackpack) {
		return backpackRepository.save(newBackpack);
	}

	public Iterable<Backpack> findAllBackpacks() {
		return backpackRepository.findAll();
	}

	public Backpack findBackpackById(int backpackId) {
		return backpackRepository.findById(backpackId).orElse(null);
	}

	public void deleteBackpackById(int backpackId) {
		try {
			backpackRepository.deleteById(backpackId);
		} catch (Exception e) {
			System.err.println("Unable to delete Backpack with ID: " + backpackId);
		}
	}

	public Device saveDevice(Device newDevice) {
		return deviceRepository.save(newDevice);
	}

	public Iterable<Device> findAllDevices() {
		return deviceRepository.findAll();
	}

	public Device findDeviceById(int deviceId) {
		return deviceRepository.findById(deviceId).orElse(null);
	}

	public void deleteDeviceById(int deviceId) {
		try {
			deviceRepository.deleteById(deviceId);
		} catch (Exception e) {
			System.err.println("Unable to delete Device with ID: " + deviceId);
		}
	}

	public Lecture saveLecture(Lecture newLecture) {
		return lectureRepository.save(newLecture);
	}

	public Iterable<Lecture> findAllLectures() {
		return lectureRepository.findAll();
	}

	public Lecture findLectureById(int lectureId) {
		return lectureRepository.findById(lectureId).orElse(null);
	}

	public void deleteLectureById(int lectureId) {
		try {
			lectureRepository.deleteById(lectureId);
		} catch (Exception e) {
			System.err.println("Unable to delete Lecture with ID: " + lectureId);
		}
	}


	// JPQL Example
	public Iterable<Student> findAllStudentsWithJPQL() {
		return studentRepository.findAllStudentsWithJPQL();
	}

	// Criteria API Example
	public Iterable<Student> findAllStudentsWithCriteriaAPI() {
		return studentRepository.findAllStudentsWithCriteriaAPI();
	}

	// Querydsl Example
	public Iterable<Student> findAllStudentsWithQuerydsl() {
		return studentRepository.findAllStudentsWithQuerydsl();
	}

	// Query 1 JPQL
	public Student findStudentByUsernameWithJPQL(String username) {
		return studentRepository.findStudentByUsernameWithJPQL(username);
	}

	// Query 1 CriteriaAPI
	public Student findStudentByUsernameWithCriteriaAPI(String username) {
		return studentRepository.findStudentByUsernameWithCriteriaAPI(username);
	}

	// Query 1 Querydsl
	public Student findStudentByUsernameWithQuerydsl(String username) {
		return studentRepository.findStudentByUsernameWithQuerydsl(username);
	}
	
	// Query 2 JPQL
	public Iterable<Student> findStudentsbyAgeRangeWithJPQL(int age1, int age2) {
		return studentRepository.findStudentsByAgeRangeWithJPQL(age1, age2);
	}

	// Query 2 CriteriaAPI
	public Iterable<Student> findStudentsByAgeRangeWithCriteriaAPI(int age1, int age2) {
		return studentRepository.findStudentsByAgeRangeWithCriteriaAPI(age1, age2);
	}

	// Query 2 Querydsl
	public Iterable<Student> findStudentsByAgeRangeWithQuerydsl(int age1, int age2) {
		return studentRepository.findStudentsByAgeRangeWithQuerydsl(age1, age2);
	}

	// Query 3 JPQL
	public Iterable<Device> findDevicesByStudentUsernameWithJPQL(String username) {
		return deviceRepository.findDevicesByStudentUsernameWithJPQL(username);
	}

	// Query 3 CriteriaAPI
	public Iterable<Device> findDevicesByStudentUsernameWithCriteriaAPI(String username) {
		return deviceRepository.findDevicesByStudentUsernameWithCriteriaAPI(username);
	}

	// Query 3 Querydsl
	public Iterable<Device> findDevicesByStudentUsernameWithQuerydsl(String username) {
		return deviceRepository.findDevicesByStudentUsernameWithQuerydsl(username);
	}

}
