package com.arobs.internship.librarymanagement.repository;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticsRepository {

    List<Object[]> findBooksRented(LocalDateTime startDate, LocalDateTime endDate, int limit);

    List<Object[]> findEmployeesByBooksRead(LocalDateTime startDate, LocalDateTime endDate);
}
