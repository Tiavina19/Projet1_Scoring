package com.example.pointage;

import Model.Category;
import Model.Calendar;
import Model.Employee;
import Service.SalaryService;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SalaryTest {

    @Test
    public void testCalendar() {
        Calendar calendar = new Calendar();

        LocalDate holidayDate = LocalDate.of(2024, 6, 17);
        assertTrue(calendar.isPublicHoliday(holidayDate));

        LocalDate nonHolidayDate = LocalDate.of(2024, 6, 18);
        assertFalse(calendar.isPublicHoliday(nonHolidayDate));

        LocalDate normalWorkDate = LocalDate.of(2024, 6, 20);
        assertTrue(calendar.isRegularWorkingDay(normalWorkDate));

        LocalDate nonWorkDate = LocalDate.of(2024, 6, 22);
        assertFalse(calendar.isRegularWorkingDay(nonWorkDate));

        LocalDate guardianWorkDate = LocalDate.of(2024, 6, 23);
        assertTrue(calendar.isGuardianWorkingDay(guardianWorkDate));

        LocalDate nonGuardianWorkDate = LocalDate.of(2024, 6, 26);
        assertFalse(calendar.isGuardianWorkingDay(nonGuardianWorkDate));
    }

    @Test
    public void testCategory() {
        Category normalCategory = new Category("normal", 40, 100000);
        assertEquals(2500.0, normalCategory.calculateHourlyRate());

        Category guardianCategory = new Category("gardien", 56, 110000);
        assertEquals(1964.29, guardianCategory.calculateHourlyRate(), 0.01);
    }

    @Test
    public void testEmployeeNetSalary() {
        Category normalCategory = new Category("normal", 40, 100000);
        Employee employee = new Employee("Rakoto", "Jean", LocalDate.of(1990, 5, 15),
                Instant.now(), Instant.now().plusSeconds(3600), 100000, normalCategory);

        assertEquals(80000.0, employee.calculateNetSalary()); // 100000 * 0.8
    }

    @Test
    public void testSalaryService() {
        Category guardianCategory = new Category("gardien", 56, 110000);
        Employee rakoto = new Employee("Rakoto", "Jean", LocalDate.of(1990, 5, 15),
                Instant.now(), Instant.now().plusSeconds(3600), 110000, guardianCategory);

        SalaryService salaryService = new SalaryService();
        LocalDate[] workDays = {LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 2), LocalDate.of(2024, 6, 3)};
        List<LocalDate> workDaysList = Arrays.asList(workDays);

        double grossSalary = salaryService.calculateGrossSalary(rakoto, workDaysList);
        assertEquals(126000.0, grossSalary);


    }
}
