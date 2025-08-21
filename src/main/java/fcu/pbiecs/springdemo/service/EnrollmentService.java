package fcu.pbiecs.springdemo.service;

import fcu.pbiecs.springdemo.model.Enrollment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EnrollmentService {
    @Autowired
    private DatabaseService databaseService;

    // 新增選課
    public String addEnrollment(int studentId, int courseId) {
        String sql = "INSERT INTO Enrollment (student_id, course_id, enrollment_date) VALUES (?, ?, ?)";
        try (Connection conn = databaseService.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, courseId);
            pstmt.setDate(3, new java.sql.Date(new Date().getTime()));
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                return "選課成功";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "選課失敗";
    }

    // 查詢所有選課
    public List<Enrollment> getAllEnrollments() {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT * FROM Enrollment";
        try (Connection conn = databaseService.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Enrollment enrollment = new Enrollment();
                enrollment.setStudentId(rs.getInt("student_id"));
                enrollment.setCourseId(rs.getInt("course_id"));
                enrollment.setEnrollDate(rs.getDate("enrollment_date"));
                enrollments.add(enrollment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }

    // 查詢某學生的所有選課
    public List<Enrollment> getEnrollmentsByStudentId(int studentId) {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT * FROM Enrollment WHERE student_id = ?";
        try (Connection conn = databaseService.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Enrollment enrollment = new Enrollment();
                enrollment.setStudentId(rs.getInt("student_id"));
                enrollment.setCourseId(rs.getInt("course_id"));
                enrollment.setEnrollDate(rs.getDate("enrollment_date"));
                enrollments.add(enrollment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }

    // 刪除選課
    public String deleteEnrollment(int studentId, int courseId) {
        String sql = "DELETE FROM Enrollment WHERE student_id = ? AND course_id = ?";
        try (Connection conn = databaseService.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, courseId);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                return "刪除選課成功";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "刪除選課失敗";
    }
}
