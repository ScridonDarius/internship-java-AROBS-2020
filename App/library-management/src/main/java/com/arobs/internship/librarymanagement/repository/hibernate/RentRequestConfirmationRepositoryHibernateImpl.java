package com.arobs.internship.librarymanagement.repository.hibernate;

import com.arobs.internship.librarymanagement.model.RentRequestConfirmation;
import com.arobs.internship.librarymanagement.repository.RentRequestConfirmationRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public class RentRequestConfirmationRepositoryHibernateImpl implements RentRequestConfirmationRepository {

    private final SessionFactory sessionFactory;

    public RentRequestConfirmationRepositoryHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public RentRequestConfirmation save(RentRequestConfirmation rentRequestConfirmation) {
        getSessionFactory().getCurrentSession().save(rentRequestConfirmation);
        return getSessionFactory().getCurrentSession().get(RentRequestConfirmation.class, rentRequestConfirmation.getId());

    }

    @Override
    public List<RentRequestConfirmation> findById(int rentConfirmationId) {
        return getSessionFactory().getCurrentSession().createQuery("FROM RentRequestConfirmation WHERE confirmation_id = :rentConfirmationId").setParameter("rentConfirmationId", rentConfirmationId).getResultList();
    }

    @Override
    public void delete(RentRequestConfirmation rentRequestConfirmation) {
        getSessionFactory().getCurrentSession().delete(rentRequestConfirmation);
    }

    @Override
    public List<RentRequestConfirmation> findAll() {
        return getSessionFactory().getCurrentSession().createQuery("FROM RentRequestConfrimation").getResultList();
    }

    @Override
    public void update(RentRequestConfirmation rentRequestConfirmation) {
getSessionFactory().getCurrentSession().update(rentRequestConfirmation);
    }

    @Override
    public List<RentRequestConfirmation> orderByConfirmationDate() {
        return getSessionFactory().getCurrentSession().createQuery("FROM RentRequestConfirmation ORDER BY confirmation_date",RentRequestConfirmation.class).getResultList();
    }

    @Override
    public void updateStatus(String rentRequestConfirmationStatus, int rentConfirmationId) {
        getSessionFactory().getCurrentSession().createQuery("UPDATE RentRequestConfirmation SET Status = :rentRequestConfirmationStatus WHERE rent_request_id = :rentConfirmationId").setParameter("rentRequestConfirmationStatus", rentRequestConfirmationStatus).setParameter("rentConfirmationId", rentConfirmationId).executeUpdate();

    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
