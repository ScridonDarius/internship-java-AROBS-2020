package com.arobs.internship.librarymanagement.validation;

import javax.persistence.PersistenceException;
import java.util.List;

public class ValidationService {
    public static <T> T safeGetUniqueResult(List resultList) throws PersistenceException {
        if (resultList == null || resultList.isEmpty()) {
            return null;
        }
        if (resultList.size() > 1) {
            throw new PersistenceException("Too many results :(");
        }

        return (T) resultList.get(0);
    }
}
