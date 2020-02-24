package com.arobs.internship.librarymanagement.model;

import com.arobs.internship.librarymanagement.model.enums.CopyCondition;
import com.arobs.internship.librarymanagement.model.enums.CopyStatus;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table(name = "copy")
public class Copy {

    private Long id;
    private String isbn;
    private CopyCondition copyCondition;
    private CopyStatus copyStatus;
    private Book book;

}
