package com.arobs.internship.librarymanagement.repository.hibernate;

import com.arobs.internship.librarymanagement.repository.StatisticsRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class StatisticsRepositoryHibernateImpl implements StatisticsRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Object[]> findBooksRented(LocalDateTime startDate, LocalDateTime endDate, int limit) {
        return sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT book_id, COUNT(*) as c FROM book_rent copy_id WHERE rental_date BETWEEN :startDate AND :endDate GROUP BY book_id LIMIT :limit")
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setParameter("limit", limit)
                .getResultList();
    }

    @Override
    public List<Object[]> findEmployeesByBooksRead(LocalDateTime startDate, LocalDateTime endDate) {
        return sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT employee_id, COUNT(*) as b FROM book_rent book_id WHERE (rental_date BETWEEN :startDate AND :endDate) AND (Status Like 'RETURNED') GROUP BY employee_id")
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
