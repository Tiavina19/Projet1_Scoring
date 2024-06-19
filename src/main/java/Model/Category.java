package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Category {
    private String name;
    private int normalHoursPerWeek;
    private double weeklySalary;

    public double calculateHourlyRate() {
        return weeklySalary / normalHoursPerWeek;
    }
}
