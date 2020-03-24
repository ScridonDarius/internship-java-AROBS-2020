package com.arobs.internship.librarymanagement.repository.hibernate;

import com.arobs.internship.librarymanagement.model.RentRequest;
import com.arobs.internship.librarymanagement.repository.RentRequestRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RentRequestRepositoryHibernateImpl implements RentRequestRepository {

    final SessionFactory sessionFactory;

    public RentRequestRepositoryHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public RentRequest save(RentRequest bookRent) {
        getSessionFactory().getCurrentSession().save(bookRent);
        return getSessionFactory().getCurrentSession().get(RentRequest.class, bookRent.getId());
    }

    @Override
    public List<RentRequest> findByStatus(String rentRequestStatus) {
        return getSessionFactory().getCurrentSession().createQuery("FROM RentRequest WHERE Status = :rentRequestStatus").setParameter("rentRequestStatus", rentRequestStatus).getResultList();
    }

    @Override
    public List<RentRequest> findById(int rentId) {
        return getSessionFactory().getCurrentSession().createQuery("FROM RentRequest WHERE  rent_request_id = :id").setParameter("id", rentId).getResultList();
    }

    @Override
    public void delete(RentRequest rentRequest) {
        getSessionFactory().getCurrentSession().delete(rentRequest);
    }

    @Override
    public List<RentRequest> findAll() {
        return getSessionFactory().getCurrentSession().createQuery("FROM RentRequest", RentRequest.class).getResultList();
    }

    @Override
    public void update(String rentRequestStatus, int rentRequestId) {
        getSessionFactory().getCurrentSession().createQuery("UPDATE RentRequest SET Status = :rentRequestStatus WHERE rent_request_id = :rentRequestId ").setParameter("rentRequestStatus", rentRequestStatus).setParameter("rentRequestId", rentRequestId).executeUpdate();
    }

    @Override
    public List<RentRequest> orderByRentDate() {
        return getSessionFactory().getCurrentSession().createQuery("FROM RentRequest ORDER BY  request_date", RentRequest.class).getResultList();
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
