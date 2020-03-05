package com.arobs.internship.librarymanagement.repository.hibernate;

import com.arobs.internship.librarymanagement.model.BookRequest;
import com.arobs.internship.librarymanagement.repository.BookRequestRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

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

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
