package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class Employee {
    private String lastName;
    private String firstName;
    private LocalDate dateOfBirth;
    private Instant hireDate;
    private Instant contractEndDate;
    private double grossSalary;
    private Category category;

    public double calculateNetSalary() {
        return this.grossSalary * 0.8;
    }
}
