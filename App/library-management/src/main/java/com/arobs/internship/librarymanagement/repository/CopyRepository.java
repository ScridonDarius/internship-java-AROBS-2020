package com.arobs.internship.librarymanagement.repository;

import com.arobs.internship.librarymanagement.model.Copy;
import com.arobs.internship.librarymanagement.model.enums.CopyStatus;

import java.util.List;

public interface CopyRepository {

    Copy save(Copy copy);

    Copy findById(int copyId);

    Copy findByISBN(String isbn);

    Copy updateCopy(Copy copy);

    void deleteCopy(Copy copy);

    List<Copy> getAll();

    List<Copy> findCopysByStatusAndByBookId(int id, String copyStatus);

    List<Copy> findByBookId(int id);
}
