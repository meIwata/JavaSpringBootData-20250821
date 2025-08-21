package fcu.pbiecs.springdemo.service;

import fcu.pbiecs.springdemo.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
// 處理教師相關的業務邏輯
public class TeacherService {
    @Autowired
    private DatabaseService databaseService;

    // 查詢所有教師資料
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT * FROM Teacher";
        try (Connection conn = databaseService.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int teacherId = rs.getInt("teacher_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int age = rs.getInt("age");
                Teacher teacher = new Teacher(teacherId, name, email, age);
                teachers.add(teacher);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return teachers;
    }

    // 查詢特定指定id教師資料
    public Teacher getTeacherById(int teacherId) {
        String sql = "SELECT * FROM Teacher WHERE teacher_id = ?";
        try (Connection conn = databaseService.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, teacherId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                int age = rs.getInt("age");
                return new Teacher(teacherId, name, email, age);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null; // 如果沒有找到教師資料，返回null
    }

    // 刪除教師資料
    public String deleteTeacherById(int teacherId) {
        String sql = "DELETE FROM Teacher WHERE teacher_id = ?";
        try (Connection conn = databaseService.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, teacherId);
            int rowsAffected = pstmt.executeUpdate(); // 執行更新操作，返回受影響的行數
            if (rowsAffected > 0) { // 如果rowsAffected大於0，表示刪除成功
                return "成功刪除 id=" + teacherId + " 的資料";
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return "刪除失敗或找不到 id=" + teacherId + " 的資料";
    }

    // 新增教師資料
    public Teacher createTeacher(Teacher teacher) {
        String sql = "INSERT INTO Teacher (name, email, age) VALUES (?, ?, ?)";
        try (Connection conn = databaseService.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, teacher.getName());
            pstmt.setString(2, teacher.getEmail());
            pstmt.setInt(3, teacher.getAge());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    teacher.setTeacherId(generatedId);
                    return teacher; // 返回新增的教師資料
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null; // 如果新增失敗，返回null
    }

    // 更新教師資料
    public Teacher updateTeacher(int id, Teacher teacher) {
        String sql = "UPDATE Teacher SET name = ?, email = ?, age = ? WHERE teacher_id = ?";
        try (Connection conn = databaseService.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, teacher.getName());
            pstmt.setString(2, teacher.getEmail());
            pstmt.setInt(3, teacher.getAge());
            pstmt.setInt(4, teacher.getTeacherId());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return teacher; // 返回更新後的教師資料
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null; // 如果更新失敗，返回null
    }
}
