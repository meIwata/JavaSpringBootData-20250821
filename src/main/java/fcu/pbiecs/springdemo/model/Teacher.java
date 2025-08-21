package fcu.pbiecs.springdemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "teachers") // 指定資料庫中的表格名稱
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Teacher {
    // 主鍵設定
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id") // 指定資料庫中的欄位名稱
    private int teacherId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private int age;

}
