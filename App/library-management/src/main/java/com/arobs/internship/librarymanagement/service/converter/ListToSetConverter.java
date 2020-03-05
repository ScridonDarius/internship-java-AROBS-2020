package com.arobs.internship.librarymanagement.service.converter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListToSetConverter {

    public static <T> Set<T> convertListToSet(List<T> list) {
        return new HashSet<>(list);
    }
}
