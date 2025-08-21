package fcu.pbiecs.springdemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // 加入@Entity註解，表示這是一個資料庫的表格
@Table(name = "students") // 指定資料庫中的表格名稱
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    // 主鍵設定
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id") // 指定資料庫中的欄位名稱
    private int studentId;

    @Column(name = "first_name") // 指定資料庫中的欄位名稱
    private String firstName;

    @Column(name = "last_name") // 指定資料庫中的欄位名稱
    private String lastName;

    @Column(name="email") // 指定資料庫中的欄位名稱
    private String email;

    @Column(name="date_of_birth")
    private String birthday;
}
