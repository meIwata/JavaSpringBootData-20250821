package fcu.pbiecs.springdemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Course") // !!!指定資料庫中的表格名稱!!!
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="course_id") // 指定資料庫中的欄位名稱
    private int courseId;

    @Column(name = "course_name")
    private String courseName;

    @Column(name="course_description")
    private String courseDescription;

    @Column(name = "credits")
    private int credits;

    @ManyToOne
    @Column(name="teacher_id", nullable = false) // 把外鍵定義出來
//    private int teacherId;
    private Teacher teacher;
}
