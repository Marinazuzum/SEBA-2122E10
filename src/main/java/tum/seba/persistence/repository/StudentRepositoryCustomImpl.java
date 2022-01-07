package tum.seba.persistence.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.querydsl.jpa.impl.JPAQuery;

import tum.seba.persistence.entity.QStudent;
import tum.seba.persistence.entity.Student;

public class StudentRepositoryCustomImpl implements StudentRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Student> findAllStudentsWithCriteriaAPI() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Student> cquery = cb.createQuery(Student.class);
		Root<Student> root = cquery.from(Student.class);
		cquery.select(root);
		
		Query query = em.createQuery(cquery);
		List<Student> results = query.getResultList();
		return results;
	}

	@Override
	public Student findStudentByUsernameWithCriteriaAPI(String username) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Student> cquery = cb.createQuery(Student.class);
		Root<Student> root = cquery.from(Student.class);
		cquery.select(root);
		Predicate eqUsername = cb.equal(root.get("username"), username);
		cquery.where(eqUsername);
		
		Query query = em.createQuery(cquery);
		Student result = (Student) query.getSingleResult();
		return result;
	}

	@Override
	public List<Student> findStudentsByAgeRangeWithCriteriaAPI(int age1, int age2) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Student> cquery = cb.createQuery(Student.class);
		Root<Student> root = cquery.from(Student.class);
		cquery.select(root);
		Predicate ageRange = cb.between(root.get("age"), age1, age2);
		cquery.where(ageRange);
		
		Query query = em.createQuery(cquery);
		List<Student> results = query.getResultList();
		return results;
	}

	@Override
	public List<Student> findAllStudentsWithQuerydsl() {
		JPAQuery<Student> query = new JPAQuery<Student>(em);
		QStudent student = QStudent.student;
		return query.from(student).fetch();
	}

	@Override
	public Student findStudentByUsernameWithQuerydsl(String username) {
		JPAQuery<Student> query = new JPAQuery<Student>(em);
		QStudent student = QStudent.student;
		return query.from(student).where(student.username.eq(username)).fetchFirst();
	}

	@Override
	public List<Student> findStudentsByAgeRangeWithQuerydsl(int age1, int age2) {
		JPAQuery<Student> query = new JPAQuery<Student>(em);
		QStudent student = QStudent.student;
		return query.from(student).where(student.age.between(age1, age2)).fetch();
	}

}
