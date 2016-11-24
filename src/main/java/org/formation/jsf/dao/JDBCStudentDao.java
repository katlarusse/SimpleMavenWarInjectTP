package org.formation.jsf.dao;

import java.util.ArrayList;
import java.util.List;

//import!!!
import javax.enterprise.context.ApplicationScoped;


import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.formation.jsf.model.Student;


@Named
@ApplicationScoped
public class JDBCStudentDao implements IStudentDao {

	@Override
	public List<Student> getStudents() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-pu");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		List<Student> retList = new ArrayList<Student>();

		try {

			trans.begin();

			TypedQuery<Student> query = em.createQuery("from Student", Student.class);
			retList = query.getResultList();

			trans.commit();

		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		System.exit(0);

		return retList;
	}

	@Override
	public void addStudent(Student theStudent) throws Exception {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-pu");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {

			trans.begin();

			em.persist(theStudent);

			trans.commit();

		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		System.exit(0);

	}

	@Override
	public Student getStudent(int studentId) throws Exception {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-pu");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {

			trans.begin();
			Student s = em.find(Student.class, studentId);
			trans.commit();

			return s;

		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		System.exit(0);
		return null;
	}

	@Override
	public void updateStudent(Student theStudent) throws Exception {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-pu");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {

			trans.begin();

			em.merge(theStudent);

			trans.commit();

		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		System.exit(0);
	}

	@Override
	public void deleteStudent(int studentId) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-pu");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {

			trans.begin();

			Student s = (Student) em.find(Student.class, studentId);
			em.remove(s);

			trans.commit();

		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		System.exit(0);
	}

}
