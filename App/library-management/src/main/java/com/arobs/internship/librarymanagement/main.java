package com.arobs.internship.librarymanagement;

import com.arobs.internship.librarymanagement.config.AppConfig;
import com.arobs.internship.librarymanagement.model.Book;
import com.arobs.internship.librarymanagement.model.Tag;
import com.arobs.internship.librarymanagement.repository.TagRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Book book = new Book();
        Tag tag = new Tag("java");

        TagRepository tagRepository = context.getBean(TagRepository.class);


    }
}
