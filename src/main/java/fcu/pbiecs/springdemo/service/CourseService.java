package fcu.pbiecs.springdemo.service;

import fcu.pbiecs.springdemo.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
// 處理課程相關的業務邏輯
public class CourseService {
    @Autowired // 自動注入DatabaseService，這樣就可以使用資料庫服務
    private DatabaseService dbService;

    // 查詢所有課程資料
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();

        String sql = "SELECT * FROM Course";
        try (
                Connection conn = dbService.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                // 使用建構子來創建Course物件
                int courseId = rs.getInt("course_id");
                String courseName = rs.getString("course_name");
                String description = rs.getString("course_description");
                int credits = rs.getInt("credits");
                int teacherId = rs.getInt("teacher_id");
                Course course = new Course(courseId, courseName, description, credits, teacherId);

                courses.add(course);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return courses;
    }

    // 查詢特定指定id課程資料
    public Course getCourseById(int courseId) {
        String sql = "SELECT * FROM Course WHERE course_id = ?";
        try (Connection conn = dbService.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("course_id");
                String name = rs.getString("course_name");
                String description = rs.getString("course_description");
                int credits = rs.getInt("credits");
                int teacherId = rs.getInt("teacher_id");
                return new Course(id, name, description, credits, teacherId);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null; // 如果沒有找到指定課程資料，返回null
    }

    // 刪除課程資料
    public String deleteCourseById(int courseId) {
        String sql = "DELETE FROM Course WHERE course_id = ?";
        try (Connection conn = dbService.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, courseId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return "成功刪除課程 id=" + courseId + " 的資料";
            }
        } catch (SQLException exception){
                exception.printStackTrace();
            }
        return "刪除課程 id=" + courseId + " 的資料失敗";
    }

    // 新增課程資料
    public String createCourse(Course course) {

        String sql = "INSERT INTO Course (course_id, course_name, course_description, credits, teacher_id) VALUES (?, ?, ?, ?, ?)";
        try(Connection conn = dbService.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, course.getCourseId());
            pstmt.setString(2, course.getCourseName());
            pstmt.setString(3, course.getCourseDescription());
            pstmt.setInt(4, course.getCredits());
            pstmt.setInt(5, course.getTeacherId());
            pstmt.executeUpdate();
            return "成功新增課程資料";

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return "新增課程資料失敗";
    }

    // 更新課程資料
    public String updateCourse(int courseId, Course course) {
        String sql = "UPDATE Course SET course_name = ?, course_description = ?, credits = ?, teacher_id = ? WHERE course_id = ?";
        try (Connection conn = dbService.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, course.getCourseName());
            pstmt.setString(2, course.getCourseDescription());
            pstmt.setInt(3, course.getCredits());
            pstmt.setInt(4, course.getTeacherId());
            pstmt.setInt(5, courseId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return "成功更新課程資料";
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return "更新課程資料失敗";
    }
}
