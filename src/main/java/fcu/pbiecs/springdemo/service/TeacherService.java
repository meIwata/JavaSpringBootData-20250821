package fcu.pbiecs.springdemo.service;

import fcu.pbiecs.springdemo.model.Teacher;
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
// 處理教師相關的業務邏輯
public class TeacherService {
//    @Autowired
//    private DatabaseService databaseService;

    @Autowired
    private TeacherRepository teacherRepository;

    // 查詢所有教師資料
    public List<Teacher> getAllTeachers() {
       return teacherRepository.findAll();
    }

    // 查詢特定指定id教師資料
    public Teacher getTeacherById(int teacherId) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);
        if (teacherOptional.isPresent()) {
            return teacherOptional.get(); // 如果存在，返回教師資料
        }
        return null; // 如果沒有找到教師資料，返回null
    }

    // 刪除教師資料
    public String deleteTeacherById(int teacherId) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);
        if (teacherOptional.isPresent()) {
            teacherRepository.deleteById(teacherId); // 刪除教師資料
            return "刪除成功，id=" + teacherId + " 的資料已被刪除";
        }
        return "刪除失敗或找不到 id=" + teacherId + " 的資料";
    }

    // 新增教師資料
    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    // 更新教師資料
    public Teacher updateTeacher(int id, Teacher teacher) {
        Teacher existingTeacher = getTeacherById(id);
        if (existingTeacher != null) {
            // 更新教師資料
            existingTeacher.setName(teacher.getName());
            existingTeacher.setEmail(teacher.getEmail());
            existingTeacher.setAge(teacher.getAge());

            // 保存更新後的教師資料
            return teacherRepository.save(existingTeacher);
        }

        return null; // 如果更新失敗，返回null
    }
}
