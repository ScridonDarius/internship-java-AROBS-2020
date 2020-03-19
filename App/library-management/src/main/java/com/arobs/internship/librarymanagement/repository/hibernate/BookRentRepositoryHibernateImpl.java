package com.arobs.internship.librarymanagement.repository.hibernate;

import com.arobs.internship.librarymanagement.model.BookRent;
import com.arobs.internship.librarymanagement.repository.BookRentRepository;
import com.arobs.internship.librarymanagement.validation.ValidationService;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRentRepositoryHibernateImpl implements BookRentRepository {

    final SessionFactory sessionFactory;

    public BookRentRepositoryHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public BookRent save(BookRent bookRent) {
        getSessionFactory().getCurrentSession().save(bookRent);
        return getSessionFactory().getCurrentSession().get(BookRent.class, bookRent.getId());
    }

    @Override
    public boolean findByBookIdAndEmployeeId(int bookId, int employeeId) {
        List<BookRent> bookRents = getSessionFactory().getCurrentSession().createQuery("FROM BookRent WHERE book_id = :bookId AND employee_id = :employeeId").setParameter("bookId", bookId).setParameter("employeeId", employeeId).getResultList();
        if (bookRents.size() == 1 && bookRents.size() >= 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<BookRent> findByStatus(String bookRentStatus) {
        return getSessionFactory().getCurrentSession().createQuery("FROM BookRent WHERE Status = :bookRentStatus").setParameter("bookRentStatus", bookRentStatus).getResultList();
    }

    @Override
    public List<BookRent> findById(int id) {
        return getSessionFactory().getCurrentSession().createQuery("FROM BookRent WHERE  book_rent_id = :id").setParameter("id", id).getResultList();
    }

    @Override
    public void delete(BookRent book) {
        getSessionFactory().getCurrentSession().delete(book);
    }

    @Override
    public List<BookRent> findAll() {
        return getSessionFactory().getCurrentSession().createQuery("FROM BookRent", BookRent.class).getResultList();
    }

    @Override
    public List<BookRent> orderByRentDate() {
        return getSessionFactory().getCurrentSession().createQuery("FROM BookRent ORDER BY  rental_date", BookRent.class).getResultList();
    }

    @Override
    public void update(BookRent book) {
        getSessionFactory().getCurrentSession().update(book);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
