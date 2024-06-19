package Service;

import Model.Calendar;
import Model.Employee;

import java.time.LocalDate;
import java.util.List;

public class SalaryService {

    public double calculateGrossSalary(Employee employee, List<LocalDate> workDays) {
        double grossSalary = 0.0;
        double hourlyRate = employee.getCategory().calculateHourlyRate();
        double normalHoursPerWeek = employee.getCategory().getNormalHoursPerWeek();

        double hoursWorked = 0.0;
        double extraHours = 0.0;
        double majoratedHours = 0.0;

        Calendar calendar = new Calendar();

        for (LocalDate date : workDays) {
            if (calendar.isPublicHoliday(date)) {
                majoratedHours += 8.0;
            } else if (calendar.isRegularWorkingDay(date)) {
                hoursWorked += 8.0;
            } else if (calendar.isGuardianWorkingDay(date)) {
                hoursWorked += 8.0;
            }
        }

        if (hoursWorked > normalHoursPerWeek) {
            extraHours = hoursWorked - normalHoursPerWeek;
            hoursWorked = normalHoursPerWeek;
        }

        grossSalary += hoursWorked * hourlyRate;
        grossSalary += extraHours * hourlyRate * 1.3;
        grossSalary += majoratedHours * hourlyRate * 1.3;
        return grossSalary;
    }

    public double calculateNetSalary(double grossSalary) {
        return grossSalary * 0.8;
    }

    public double calculateOvertimeHours(double workedHours, double normalHoursPerWeek) {
        if (workedHours > normalHoursPerWeek) {
            return workedHours - normalHoursPerWeek;
        }
        return 0.0;
    }
}
