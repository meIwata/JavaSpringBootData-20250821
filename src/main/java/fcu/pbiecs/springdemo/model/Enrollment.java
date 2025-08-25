package fcu.pbiecs.springdemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Enrollment") // !!!指定資料庫中的表格名稱!!!
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Enrollment {
    @EmbeddedId
    private EnrollmentId id;

    @ManyToOne
    @MapsId("student")
    @JoinColumn(name="student_id", nullable = false) // 把外鍵定義出來
    private Student student;

    @ManyToOne
    @MapsId("course")
    @JoinColumn(name="course_id", nullable = false)
    private Course course;

    @Column(name = "enrollment_date")
    private Date enrollDate;
}