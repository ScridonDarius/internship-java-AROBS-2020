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
    public Tag createTag(Tag tag) {
        return findById((Integer) getSessionFactory().getCurrentSession().save(tag));
    }

    @Override
    public void deleteTag(final Tag tag) {
        getSessionFactory().getCurrentSession().delete(tag);
    }

    @Override
    public List<Tag> findByTagName(String tagName) {
        return (List<Tag>) getSessionFactory().getCurrentSession().createQuery("FROM Tag WHERE tag_name = :tagName").setParameter("tagName", tagName).list();
    }

    public Tag findById(int tagId) {
        return getSessionFactory().getCurrentSession().get(Tag.class, tagId);
    }

    @Override
    public void updateTag(final Tag tag) {
        getSessionFactory().getCurrentSession().update(tag);
    }

    @Override
    public List<Tag> findAll() {
        return getSessionFactory().getCurrentSession().createQuery("FROM Tag", Tag.class).list();
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
