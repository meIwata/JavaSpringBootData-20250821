package fcu.pbiecs.springdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course {
    private int courseId;
    private String courseName;
    private String courseDescription;
    private int credits;
    private int teacherId;
}
