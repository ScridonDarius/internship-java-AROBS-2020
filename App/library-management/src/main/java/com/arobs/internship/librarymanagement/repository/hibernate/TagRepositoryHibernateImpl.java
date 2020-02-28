package com.arobs.internship.librarymanagement.repository.hibernate;

import com.arobs.internship.librarymanagement.model.Tag;
import com.arobs.internship.librarymanagement.repository.TagRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagRepositoryHibernateImpl implements TagRepository {

    private final SessionFactory sessionFactory;

    public TagRepositoryHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int createTag(Tag tag) {
        return (int) this.getSessionFactory().getCurrentSession().save(tag);
    }

    @Override
    public void deleteTag(final Tag tag) {
        getSessionFactory().getCurrentSession().delete(tag);
    }

    @Override
    public Tag findByTagName(String tagName) {
        return (Tag) this.getSessionFactory().getCurrentSession().createQuery("FROM Tag WHERE tag_name = :tagName").setParameter("tagName", tagName).getSingleResult();
    }

    public Tag findById(int tagId){
        return this.getSessionFactory().getCurrentSession().get(Tag.class,tagId);
    }

    @Override
    public void updateTag(final Tag tag) {
        getSessionFactory().getCurrentSession().update(tag);
    }

    @Override
    public List<Tag> findAll() {
        return this.getSessionFactory().getCurrentSession().createQuery("FROM Tag").getResultList();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
