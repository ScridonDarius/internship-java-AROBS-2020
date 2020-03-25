package com.arobs.internship.librarymanagement.repository.hibernate;


import com.arobs.internship.librarymanagement.model.BookRequest;
import com.arobs.internship.librarymanagement.repository.BookRequestRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRequestRepositoryHibernateImpl implements BookRequestRepository {

    private final SessionFactory sessionFactory;

    public BookRequestRepositoryHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public BookRequest save(BookRequest bookRequest) {
        getSessionFactory().getCurrentSession().save(bookRequest);
        return getSessionFactory().getCurrentSession().get(BookRequest.class, bookRequest.getId());
    }

    @Override
    public List<BookRequest> findByAuthorAndTitle(String author, String title) {
        return getSessionFactory().getCurrentSession()
                .createQuery("FROM BookRequest WHERE author = :author AND title = :title", BookRequest.class)
                .setParameter("author", author)
                .setParameter("title", title)
                .getResultList();
    }

    @Override
    public List<BookRequest> findByAuthorTitleAndEmployeeId(String author, String title, int employeeId) {
        return getSessionFactory().getCurrentSession()
                .createQuery("FROM BookRequest WHERE author = :author AND title = :title AND employee_id = :employeeId", BookRequest.class)
                .setParameter("author", author)
                .setParameter("title", title)
                .setParameter("employeeId", employeeId)
                .getResultList();
    }

    @Override
    public void delete(BookRequest bookRequest) {
        getSessionFactory().getCurrentSession().delete(bookRequest);
    }

    @Override
    public List<BookRequest> findById(int bookRequestId) {
        return getSessionFactory().getCurrentSession()
                .createQuery("FROM BookRequest WHERE book_request_id = :bookRequestId")
                .setParameter("bookRequestId", bookRequestId)
                .getResultList();
    }

    @Override
    public List<BookRequest> getAll() {
        return getSessionFactory().getCurrentSession().createQuery("FROM BookRequest", BookRequest.class).getResultList();
    }

    @Override
    public void update(BookRequest bookRequest) {
        getSessionFactory().getCurrentSession().update(bookRequest);
    }

    @Override
    public List<BookRequest> findByStatus(String bookRequestStatus) {
        return getSessionFactory().getCurrentSession()
                .createQuery("FROM BookRequest WHERE Status = :bookRequestStatus", BookRequest.class)
                .setParameter("bookRequestStatus", bookRequestStatus)
                .getResultList();
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
