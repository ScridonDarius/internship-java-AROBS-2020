package com.arobs.internship.librarymanagement.repository.hibernate;

import com.arobs.internship.librarymanagement.model.Book;
import com.arobs.internship.librarymanagement.repository.BookRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepositoryHibernateImpl implements BookRepository {

    private final SessionFactory sessionFactory;

    public BookRepositoryHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Book save(Book book) {
        getSessionFactory().getCurrentSession().save(book);
        return getSessionFactory().getCurrentSession().get(Book.class, book.getId());
    }

    @Override
    public List<Book> findBook(String author, String title) {
       List<Book> books =  getSessionFactory().getCurrentSession().createQuery("FROM Book WHERE author = :author AND title = :title").setParameter("author", author).setParameter("title", title).getResultList();
   return books;
    }

    @Override
    public Book findBookById(int id) {
        return getSessionFactory().getCurrentSession().get(Book.class, id);
    }

    @Override
    public void delete(Book book) {
        getSessionFactory().getCurrentSession().delete(book);
    }

    @Override
    public List<Book> getAll() {
        return getSessionFactory().getCurrentSession().createQuery("FROM Book", Book.class).getResultList();
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}