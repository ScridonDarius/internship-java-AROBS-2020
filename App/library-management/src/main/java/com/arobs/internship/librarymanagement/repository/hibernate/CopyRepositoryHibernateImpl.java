package com.arobs.internship.librarymanagement.repository.hibernate;

import com.arobs.internship.librarymanagement.model.Copy;
import com.arobs.internship.librarymanagement.repository.CopyRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CopyRepositoryHibernateImpl implements CopyRepository {
    private final SessionFactory sessionFactory;

    public CopyRepositoryHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Copy save(Copy copy) {
        getSessionFactory().getCurrentSession().save(copy);
        return getSessionFactory().getCurrentSession().get(Copy.class, copy.getId());
    }

    @Override
    public Copy findById(int copyId) {
        return getSessionFactory().getCurrentSession().get(Copy.class, copyId);
    }

    @Override
    public Copy findByISBN(String isbn) {
        return getSessionFactory().getCurrentSession().createQuery("FROM Copy WHERE isbn = :isbn", Copy.class).setParameter("isbn", isbn).getSingleResult();
    }

    @Override
    public List<Copy> findByStatusAndByBookId(int id, String copyStatus) {
        return getSessionFactory().getCurrentSession()
                .createQuery("FROM Copy WHERE book_id = :id AND Status = :copyStatus", Copy.class)
                .setParameter("id", id)
                .setParameter("copyStatus", copyStatus)
                .getResultList();
    }

    @Override
    public List<Copy> findByBookId(int id) {
        return getSessionFactory().getCurrentSession()
                .createQuery("FROM Copy WHERE book_id = :id", Copy.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public Copy update(Copy copy) {
        getSessionFactory().getCurrentSession().update(copy);
        return findById(copy.getId());
    }

    @Override
    public void delete(Copy copy) {
        getSessionFactory().getCurrentSession().delete(copy);
    }

    @Override
    public List<Copy> findAll() {
        return getSessionFactory().getCurrentSession().createQuery("FROM Copy", Copy.class).getResultList();
    }

    @Override
    public List<Copy> findAllByStatus(String copyStatus) {
        return getSessionFactory().getCurrentSession()
                .createQuery("FROM Copy WHERE Status = :copyStatus")
                .setParameter("copyStatus", copyStatus)
                .getResultList();
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
