package fcu.pbiecs.springdemo.service;

import fcu.pbiecs.springdemo.model.Course;
import fcu.pbiecs.springdemo.repository.CourseRepository;
import fcu.pbiecs.springdemo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
// 處理課程相關的業務邏輯
public class CourseService {
//    @Autowired // 自動注入DatabaseService，這樣就可以使用資料庫服務
//    private DatabaseService dbService;

    @Autowired
    private CourseRepository courseRepository;


    @Autowired
    private TeacherRepository teacherRepository;
    // 查詢所有課程資料
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // 查詢特定指定id課程資料
    public Course getCourseById(int courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (courseOptional.isPresent()) {
            return courseOptional.get(); // 如果存在，返回課程資料
        }
        return null;
    }

    // 刪除課程資料
    public String deleteCourseById(int courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (courseOptional.isPresent()) {
            courseRepository.deleteById(courseId); // 刪除課程資料
            return "刪除成功，id=" + courseId + " 的資料已被刪除";
        }
        return "刪除失敗或找不到 id=" + courseId + " 的資料";
    }

    // 新增課程資料
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    // 更新課程資料
    public Course updateCourse(int courseId, Course course) {
        Course existingCourse = getCourseById(courseId);
        if (existingCourse != null) {
            // 更新課程資料
            existingCourse.setCourseName(course.getCourseName());
            existingCourse.setCourseDescription(course.getCourseDescription());
            existingCourse.setCredits(course.getCredits());
            existingCourse.setTeacher(course.getTeacher()); // 假設課程有一個教師屬性

            // 保存更新後的課程資料
            return courseRepository.save(existingCourse);
        }
        return null;
    }
}
