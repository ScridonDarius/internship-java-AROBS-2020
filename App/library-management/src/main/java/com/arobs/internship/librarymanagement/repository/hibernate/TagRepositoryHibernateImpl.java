package com.arobs.internship.librarymanagement.repository.hibernate;

import com.arobs.internship.librarymanagement.model.Tag;
import com.arobs.internship.librarymanagement.repository.TagRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

public class TagRepositoryHibernateImpl implements TagRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public int createTag(Tag tag) {
        Session session = this.entityManager.unwrap(Session.class);

        return (int) session.save(tag);
    }

    @Override
    public Tag findByTagName(String tagName) {
        Session session = this.entityManager.unwrap(Session.class);
        TypedQuery<Tag> query = session.createQuery("FROM Tag WHERE tag_name = :tagName");

        return query.setParameter("tagName", tagName).getSingleResult();
    }

    @Override
    @Transactional
    public boolean updateTag(String tagName, String newTag) {
        Session session = this.entityManager.unwrap(Session.class);
        session.update(newTag);

        return !findByTagName(tagName).getTagName().equals(newTag);
    }

    @Override
    @Transactional
    public boolean deleteTag(String tagName) {
        Session session = this.entityManager.unwrap(Session.class);
        session.delete(findByTagName(tagName).getTagName());

        return findByTagName(tagName).getTagName().isEmpty();
    }

    @Override
    public List<Tag> findAll() {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createQuery("FROM Tag");

        return query.getResultList();
    }
}
