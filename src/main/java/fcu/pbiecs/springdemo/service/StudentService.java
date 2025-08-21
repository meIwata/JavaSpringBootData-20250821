package fcu.pbiecs.springdemo.service;

import fcu.pbiecs.springdemo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
// 處理學生相關的業務邏輯
public class StudentService {
    @Autowired // 自動注入DatabaseService，這樣就可以使用資料庫服務
    private DatabaseService dbService;

    // 查詢所有學生資料
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM Student";
        try (Connection conn = dbService.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
//                Student student = new Student();
//                student.setStudentId(rs.getInt("student_id"));
//                student.setFirstName(rs.getString("first_name"));
//                student.setLastName(rs.getString("last_name"));
//                student.setBirthday(rs.getString("date_of_birth"));
//                student.setEmail(rs.getString("email"));

                // 也可以使用建構子來創建Student物件
                int studentId = rs.getInt("student_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String birthday = rs.getString("date_of_birth");
                String email = rs.getString("email");
                Student student = new Student(studentId, firstName, lastName, email, birthday);

                students.add(student);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return students;
    }

    // 查詢特定指定id學生資料
    public Student getStudentById(int studentId) {
        String sql = "SELECT * FROM Student WHERE student_id = ?";
        try (Connection conn = dbService.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("student_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String birthday = rs.getString("date_of_birth");
                String email = rs.getString("email");
                return new Student(studentId, firstName, lastName, email, birthday);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null; // 如果沒有找到學生，返回null
    }

    // 刪除特定學生資料
    public String deleteStudentById(int studentId) {
        String sql = "DELETE FROM Student WHERE student_id = ?";
        try (Connection conn = dbService.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId); // 有問號就要塞值
            int rowsAffected = pstmt.executeUpdate(); // 執行更新操作，返回受影響的行數
            if (rowsAffected > 0) {
                return "成功刪除 id=" + studentId + " 的資料";
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return "刪除失敗或找不到 id=" + studentId + " 的資料";
    }

    // 新增學生資料
    public Student createStudent(Student student) {
        String sql = "INSERT INTO Student (first_name, last_name, email, date_of_birth) VALUES (?, ?, ?, ?)";
        try (Connection conn = dbService.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, student.getFirstName());
            pstmt.setString(2, student.getLastName());
            pstmt.setString(3, student.getEmail());
            pstmt.setString(4, student.getBirthday());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int newId = generatedKeys.getInt(1);
                    student.setStudentId(newId); // 設定新學生的ID
                    return student;
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null; // 如果新增失敗，返回null
    }

    // 更新學生資料
    public Student updateStudent(int id, Student student) {
        String sql = "UPDATE Student SET first_name = ?, last_name = ?, email = ?, date_of_birth = ? WHERE student_id = ?";
        try (Connection conn = dbService.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getFirstName());
            pstmt.setString(2, student.getLastName());
            pstmt.setString(3, student.getEmail());
            pstmt.setString(4, student.getBirthday());
            pstmt.setInt(5, student.getStudentId());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return student; // 更新成功，返回更新後的學生資料
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null; // 如果更新失敗，返回null
    }
}
