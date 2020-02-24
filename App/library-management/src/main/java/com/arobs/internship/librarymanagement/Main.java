package com.arobs.internship.librarymanagement;

import com.arobs.internship.librarymanagement.config.HibernateUtil;
import org.hibernate.Session;

public class Main {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Check database version
        String sql = "select version()";

        String result = (String) session.createNativeQuery(sql).getSingleResult();
        System.out.println(result);

        session.getTransaction().commit();
        session.close();

        HibernateUtil.shutdown();
    }

}
