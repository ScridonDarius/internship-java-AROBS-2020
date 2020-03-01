package com.arobs.internship.librarymanagement.repository.hibernate;

import com.arobs.internship.librarymanagement.model.Employee;
import com.arobs.internship.librarymanagement.repository.EmployeeRepository;
import org.hibernate.SessionFactory;
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
        return (int) getSessionFactory().getCurrentSession().save(employee);
    }

    @Override
    public Employee findEmployee(String userName) {
        return (Employee) getSessionFactory().getCurrentSession().createQuery("FROM Employee WHERE user_name = :userName").setParameter("userName", userName).getSingleResult();
    }

    @Override
    public void updateEmployee(String userName, Employee employee) {
        getSessionFactory().getCurrentSession().update(employee);
    }

    @Override
    public void deleteEmployee(String userName) {
        getSessionFactory().getCurrentSession().createQuery("DELETE FROM Employee WHERE user_name = :userName").setParameter("userName", userName);
    }

    @Override
    public List<Employee> findAll() {
        return getSessionFactory().getCurrentSession().createQuery("FROM Employee", Employee.class).getResultList();
    }

    @Override
    public Employee findEmployeeByEmail(String email) {
        return (Employee) getSessionFactory().getCurrentSession().createQuery("FROM Employee WHERE email = :email").setParameter("email", email).getSingleResult();
    }

    @Override
    public Employee updatePassword(String userName, String password) {
        getSessionFactory().getCurrentSession().createQuery("UPDATE Employee SET password = :password WHERE user_name = :userName").setParameter("password", password).setParameter("userName", userName).executeUpdate();

        return findEmployee(userName);
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}