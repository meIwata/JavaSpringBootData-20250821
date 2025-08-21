package fcu.pbiecs.springdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Enrollment {
    private int studentId;
    private int courseId;
    private Date enrollDate;
}
