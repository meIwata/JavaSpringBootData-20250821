package fcu.pbiecs.springdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Teacher {
    private int teacherId;
    private String name;
    private String email;
    private int age;
}
