package Model;

import lombok.Getter;
import lombok.Setter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Calendar {
    private Set<LocalDate> publicHolidays;
    private Set<DayOfWeek> regularWorkingDays;
    private Set<DayOfWeek> guardianWorkingDays;

    public Calendar() {
        this.publicHolidays = new HashSet<>();
        this.regularWorkingDays = new HashSet<>();
        this.guardianWorkingDays = new HashSet<>();
        initDefaultValues();
    }

    private void initDefaultValues() {
        publicHolidays.add(LocalDate.of(2024, 6, 17));
        publicHolidays.add(LocalDate.of(2024, 6, 25));
        publicHolidays.add(LocalDate.of(2024, 6, 26));

        regularWorkingDays.add(DayOfWeek.MONDAY);
        regularWorkingDays.add(DayOfWeek.TUESDAY);
        regularWorkingDays.add(DayOfWeek.WEDNESDAY);
        regularWorkingDays.add(DayOfWeek.THURSDAY);
        regularWorkingDays.add(DayOfWeek.FRIDAY);

        for (DayOfWeek day : DayOfWeek.values()) {
            guardianWorkingDays.add(day);
        }
    }

    public boolean isPublicHoliday(LocalDate date) {
        return publicHolidays.contains(date);
    }

    public boolean isRegularWorkingDay(LocalDate date) {
        return regularWorkingDays.contains(date.getDayOfWeek()) && !isPublicHoliday(date);
    }

    public boolean isGuardianWorkingDay(LocalDate date) {
        return guardianWorkingDays.contains(date.getDayOfWeek()) && !isPublicHoliday(date);
    }
}
