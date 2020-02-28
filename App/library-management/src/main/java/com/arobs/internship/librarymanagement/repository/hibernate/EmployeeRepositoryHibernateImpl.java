package com.arobs.internship.librarymanagement.repository.hibernate;

import com.arobs.internship.librarymanagement.model.Employee;
import com.arobs.internship.librarymanagement.repository.EmployeeRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepositoryHibernateImpl implements EmployeeRepository {

    private final SessionFactory sessionFactory;

    public EmployeeRepositoryHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int createEmployee(Employee employee) {
        return (int) this.getSessionFactory().getCurrentSession().save(employee);
    }

    @Override
    public Employee findEmployee(String userName) {
        return (Employee) this.getSessionFactory().getCurrentSession().createQuery("FROM Employee WHERE user_name = :userName").setParameter("userName", userName).getSingleResult();
    }

    @Override
    public void updateEmployee(String userName, Employee employee) {
        this.getSessionFactory().getCurrentSession().update(employee);
    }

    @Override
    public void deleteEmployee(String userName) {
        this.getSessionFactory().getCurrentSession().createQuery("DELETE FROM Employee WHERE user_name = :userName").setParameter("userName", userName);
    }

    @Override
    public List<Employee> findAll() {
        return this.getSessionFactory().getCurrentSession().createQuery("FROM Employee", Employee.class).getResultList();
    }

    @Override
    public Employee findEmployeeByEmail(String email) {
        return (Employee) this.getSessionFactory().getCurrentSession().createQuery("FROM Employee WHERE email = :email").setParameter("email", email).getSingleResult();
    }

    @Override
    public Employee updatePassword(String userName, String password) {
        this.getSessionFactory().getCurrentSession().createQuery("UPDATE Employee SET password = :password WHERE user_name = :userName").setParameter("password", password).setParameter("userName", userName).executeUpdate();

        return findEmployee(userName);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}