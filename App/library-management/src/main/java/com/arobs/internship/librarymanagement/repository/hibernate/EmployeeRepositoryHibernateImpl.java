package com.arobs.internship.librarymanagement.repository.hibernate;

import com.arobs.internship.librarymanagement.model.Employee;
import com.arobs.internship.librarymanagement.repository.EmployeeRepository;
import com.arobs.internship.librarymanagement.validation.ValidationService;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class EmployeeRepositoryHibernateImpl implements EmployeeRepository {

    private final SessionFactory sessionFactory;

    public EmployeeRepositoryHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int save(Employee employee) {
        return (int) getSessionFactory().getCurrentSession().save(employee);
    }

    @Override
    public List<Employee> findByUserName(String userName) {
        return (List<Employee>) getSessionFactory().getCurrentSession()
                .createQuery("FROM Employee WHERE user_name = :userName")
                .setParameter("userName", userName)
                .list();
    }

    @Override
    public void update(String userName, Employee employee) {
        getSessionFactory().getCurrentSession().update(employee);
    }

    @Override
    public void delete(int id) {
        getSessionFactory().getCurrentSession()
                .createQuery("DELETE FROM Employee WHERE employee_id = :id")
                .setParameter("id", id);
    }

    @Override
    public List<Employee> getAll() {
        return getSessionFactory().getCurrentSession().createQuery("FROM Employee", Employee.class).list();
    }

    @Override
    public List<Employee> findByEmail(String email) {
        return (List<Employee>) getSessionFactory().getCurrentSession()
                .createQuery("FROM Employee WHERE email = :email")
                .setParameter("email", email).list();
    }

    @Override
    public Employee updatePassword(String userName, String password) {
        getSessionFactory().getCurrentSession().createQuery("UPDATE Employee SET password = :password WHERE user_name = :userName").setParameter("password", password).setParameter("userName", userName).executeUpdate();

        return ValidationService.safeGetUniqueResult(findByUserName(userName));
    }

    @Override
    public void updateStatus(String employeeStatus, int employeeId) {
        getSessionFactory().getCurrentSession().createQuery("UPDATE Employee SET Status = :employeeStatus WHERE employee_id = :employeeId").setParameter("employeeStatus", employeeStatus).setParameter("employeeId", employeeId).executeUpdate();
    }

    @Override
    public void updateRemovalSuspended(LocalDateTime removalSuspended, int employeeId) {
        getSessionFactory().getCurrentSession().createQuery("UPDATE Employee SET removal_Suspended = :removalSuspended WHERE employee_id = :employeeId").setParameter("removalSuspended", removalSuspended).setParameter("employeeId", employeeId).executeUpdate();
    }

    @Override
    public List<Employee> findById(int id) {
        return getSessionFactory().getCurrentSession().createQuery("FROM Employee WHERE employee_id = :id").setParameter("id", id).getResultList();
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}