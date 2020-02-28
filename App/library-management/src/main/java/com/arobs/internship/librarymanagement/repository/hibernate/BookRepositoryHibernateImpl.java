package com.arobs.internship.librarymanagement.repository.hibernate;

import com.arobs.internship.librarymanagement.model.Book;
import com.arobs.internship.librarymanagement.repository.BookRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryHibernateImpl implements BookRepository {

    private final SessionFactory sessionFactory;

    public BookRepositoryHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Book save(Book book) {
       int id = (int) this.getSessionFactory().getCurrentSession().save(book);
        return this.getSessionFactory().getCurrentSession().get(Book.class,id);
    }

    @Override
    public Book findBook(String author, String title) {
            return (Book) this.getSessionFactory().getCurrentSession().createQuery("FROM Book WHERE author = :author AND title = :title").setParameter("author", author).setParameter("title", title).getSingleResult();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
