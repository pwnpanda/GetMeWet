package com.example.getmewet.repositories;

import com.example.getmewet.models.Day;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DayService extends CrudRepository<Day, Integer> {
    List<Day> getDaysInMonth(int year, int month);
    Day getToday();
    int getMonth();
    int getYear();
}
