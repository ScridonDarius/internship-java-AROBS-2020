package com.arobs.internship.librarymanagement.repository.hibernate;

import com.arobs.internship.librarymanagement.model.Employee;
import com.arobs.internship.librarymanagement.repository.EmployeeRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
    public Employee findEmployee(String userName) {
        Session session = this.entityManager.unwrap(Session.class);
        TypedQuery<Employee> query = session.createQuery("FROM Employee WHERE user_name = :userName");

        return query.setParameter("userName", userName).getSingleResult();
    }

    @Override
    public boolean updateEmployee(String userName, Employee employee) {
        Employee oldEmployee = findEmployee(userName);
        Session session = this.entityManager.unwrap(Session.class);
        session.update(employee);
        Employee newEmployee = findEmployee(userName);

        return !oldEmployee.equals(newEmployee);
    }

    @Override
    @Modifying
    public boolean deleteEmployee(String userName) {
        Session session = this.entityManager.unwrap(Session.class);
        TypedQuery<Boolean> query = session.createQuery("DELETE FROM Employee WHERE user_name = :userName");

        return query.setParameter("userName", userName).getSingleResult();
    }

    @Override
    public List<Employee> findAll() {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createQuery("FROM Employee");

        return query.getResultList();
    }
}
