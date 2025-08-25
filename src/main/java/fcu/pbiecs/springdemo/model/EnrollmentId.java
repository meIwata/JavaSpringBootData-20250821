package fcu.pbiecs.springdemo.model;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Data
public class EnrollmentId implements Serializable {
    private int student;
    private int course;
}
