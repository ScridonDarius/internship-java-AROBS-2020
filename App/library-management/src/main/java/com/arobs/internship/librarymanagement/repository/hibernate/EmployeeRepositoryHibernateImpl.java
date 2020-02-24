package com.arobs.internship.librarymanagement.repository.hibernate;

import com.arobs.internship.librarymanagement.model.Employee;
import com.arobs.internship.librarymanagement.repository.EmployeeRepository;

import org.hibernate.Session;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeeRepositoryHibernateImpl implements EmployeeRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public int createEmployee(Employee employee) {
        Session session = this.entityManager.unwrap(Session.class);

        return (int) session.save(employee);
    }

    @Override
    @Transactional
    public Employee findEmployee(String userName) {
        Session session = this.entityManager.unwrap(Session.class);
        TypedQuery<Employee> query = session.createQuery("FROM Employee WHERE user_name = :userName");
        return query.setParameter("userName", userName).getSingleResult();
    }

    @Override
    public boolean updateEmployee(String userName, Employee employee) {
        return false;
    }

    @Override
    public boolean deleteEmployee(String userName) {
        return false;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }
}
