package tum.seba.persistence.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.querydsl.jpa.impl.JPAQuery;

import tum.seba.persistence.entity.Device;
import tum.seba.persistence.entity.QDevice;
import tum.seba.persistence.entity.QStudent;

public class DeviceRepositoryCustomImpl implements DeviceRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Device> findDevicesByStudentUsernameWithCriteriaAPI(String username) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Device> cquery = cb.createQuery(Device.class);
		Root<Device> root = cquery.from(Device.class);
		cquery.select(root);
		Join student = root.join("owner");
		
		Predicate devicesByUsername = cb.equal(student.get("username"), username);
		cquery.where(devicesByUsername);
		
		Query query = em.createQuery(cquery);
		List<Device> results = query.getResultList();
		return results;
	}

	@Override
	public List<Device> findDevicesByStudentUsernameWithQuerydsl(String username) {
		JPAQuery<Device> query = new JPAQuery<Device>(em);
		QDevice device = QDevice.device;
		QStudent student = QStudent.student;
		return query.from(device).join(device.owner, student).where(student.username.eq(username)).fetch();
	}

}
