package tum.seba.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Device {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@NotBlank(message="Type field is required")
	private String type;

	@ManyToOne
	@NotNull(message="Owner cannot be null")
	private Student owner;

	public Device() {}

	public Device(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Student getOwner() {
		return owner;
	}

	public void setOwner(Student owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Device [id=" + id + ", type=" + type + "]";
	}

}
