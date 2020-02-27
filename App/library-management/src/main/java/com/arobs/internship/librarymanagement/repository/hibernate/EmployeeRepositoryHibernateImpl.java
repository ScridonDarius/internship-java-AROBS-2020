package com.arobs.internship.librarymanagement.repository.hibernate;

import com.arobs.internship.librarymanagement.config.HibernateUtil;
import com.arobs.internship.librarymanagement.model.Employee;
import com.arobs.internship.librarymanagement.repository.EmployeeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class EmployeeRepositoryHibernateImpl implements EmployeeRepository {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public int createEmployee(Employee employee) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            int id = (int) session.save(employee);
            transaction.commit();

            return id;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Employee findEmployee(String userName) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            TypedQuery<Employee> query = session.createQuery("FROM Employee WHERE user_name = :userName").setParameter("userName", userName);
            transaction.commit();

            return query.getSingleResult();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateEmployee(String userName, Employee employee) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();

            return true;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteEmployee(String userName) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
         session.createQuery("DELETE FROM Employee WHERE user_name = :userName").setParameter("userName", userName);
            transaction.commit();

            return true;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Employee> findAll() {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Employee");
            transaction.commit();

            return query.getResultList();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee findEmployeeByEmail(String email) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            transaction = session.beginTransaction();
            TypedQuery<Employee> query = session.createQuery("FROM Employee WHERE email = :email").setParameter("email", email);
            transaction.commit();

            return query.getSingleResult();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee updatePassword(String userName, String password) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.createQuery("UPDATE Employee SET password = :password WHERE user_name = :userName").setParameter("password", password).setParameter("userName", userName).executeUpdate();
            transaction.commit();

            return findEmployee(userName);

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }
}