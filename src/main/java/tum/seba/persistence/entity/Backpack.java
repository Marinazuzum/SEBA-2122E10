package tum.seba.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Backpack {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@NotBlank(message="Color field is required")
	private String color;

	@OneToOne(mappedBy="backpack", cascade=CascadeType.ALL)
	private Student owner;

	public Backpack() {}

	public Backpack(String color) {
		this.color = color;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Student getOwner() {
		return owner;
	}

	public void setOwner(Student owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Backpack [id=" + id + ", color=" + color + "]";
	}

}
