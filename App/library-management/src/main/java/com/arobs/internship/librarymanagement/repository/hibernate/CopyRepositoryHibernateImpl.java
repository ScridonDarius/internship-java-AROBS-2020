package com.arobs.internship.librarymanagement.repository.hibernate;

import com.arobs.internship.librarymanagement.model.Copy;
import com.arobs.internship.librarymanagement.repository.CopyRepository;
import org.hibernate.Session;
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
        Session session = getSessionFactory().getCurrentSession();
        session.save(copy);
        return session.get(Copy.class, copy.getId());
    }

    @Override
    public Copy findById(int copyId) {
        return null;
    }

    @Override
    public Copy updateCopy(Copy copy) {
        return null;
    }

    @Override
    public boolean deleteCopy(Copy copy) {
        return false;
    }

    @Override
    public List<Copy> getAll() {
        return null;
    }


    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
