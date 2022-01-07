package tum.seba.persistence.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@NotBlank(message="Name field is required")
	private String name;

	@Min(value=16, message="Students need to be at least 16 years old")
	private int age;

	@Size(min=3, max=8, message="Username must be at least 3 and up to 8 characters")
	@Column(unique=true)
	private String username;

	@Email(message="Email address must be well-formed")
	private String email;

	@OneToOne(cascade = CascadeType.ALL)
	private Backpack backpack;

	@OneToMany(mappedBy="owner", cascade=CascadeType.ALL)
	private List<Device> devices;

	@ManyToMany(mappedBy="students", cascade=CascadeType.ALL)
	private List<Lecture> lectures;

	public Student() {}

	public Student(String name, int age, String username, String email) {
		this.name = name;
		this.age = age;
		this.username = username;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Backpack getBackpack() {
		return backpack;
	}

	public void setBackpack(Backpack backpack) {
		this.backpack = backpack;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public List<Lecture> getLectures() {
		return lectures;
	}

	public void setLectures(List<Lecture> lectures) {
		this.lectures = lectures;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", username=" + username + ", email=" + email
				+ "]";
	}

}
