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
    public List<Book> findByAuthorAndTitle(String author, String title) {
        return getSessionFactory().getCurrentSession().createQuery("FROM Book b LEFT JOIN FETCH b.tags WHERE b.author = :author AND b.title = :title", Book.class)
                .setParameter("author", author).setParameter("title", title).getResultList();
    }

    @Override
    public List<Book> findById(int id) {
        return getSessionFactory().getCurrentSession().createQuery("FROM Book b LEFT JOIN FETCH b.tags WHERE b.id = :id", Book.class).setParameter("id", id).getResultList();
    }

    @Override
    public void delete(Book book) {
        getSessionFactory().getCurrentSession().createQuery("DELETE FROM Book WHERE id = :id").setParameter("id", book.getId()).executeUpdate();
    }

    @Override
    public List<Book> getAll() {
        return getSessionFactory().getCurrentSession().createQuery("FROM Book b LEFT JOIN FETCH b.tags", Book.class).getResultList();
    }

    @Override
    public void update(Book book) {
        getSessionFactory().getCurrentSession().update(book);
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}