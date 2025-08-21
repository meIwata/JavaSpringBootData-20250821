package fcu.pbiecs.springdemo.controller;

import fcu.pbiecs.springdemo.model.Enrollment;
import fcu.pbiecs.springdemo.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="選課碗裡", description="提供選課 CRUD API") // Swagger標籤，用於API文檔生成
@CrossOrigin("*") // 允許所有來源的跨域請求
@RestController // 處理HTTP請求的控制器
@RequestMapping("/api/enrollments") // 定義路徑前綴
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;
    // 查詢所有選課
    @Operation(summary = "查詢所有選課", description = "取得所有學生的選課資訊")
    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    // 查詢某學生的所有選課
    @Operation(summary = "查詢某學生的所有選課", description = "根據學生ID取得該學生的選課資訊")
    @GetMapping("/{studentId}")
    public List<Enrollment> getEnrollmentsByStudentId(@PathVariable int studentId) {
        return enrollmentService.getEnrollmentsByStudentId(studentId);
    }

    // 新增選課
    @Operation(summary = "新增選課", description = "根據學生ID和課程ID新增選課資訊")
    @PostMapping("/{studentId}/{courseId}")
    public String addEnrollment(@PathVariable int studentId, @PathVariable int courseId) {
        return enrollmentService.addEnrollment(studentId, courseId);
    }

    // 刪除選課
    @Operation(summary = "刪除選課", description = "根據學生ID和課程ID刪除選課資訊")
    @DeleteMapping("/{studentId}/{courseId}")
    public String deleteEnrollment(@PathVariable int studentId, @PathVariable int courseId) {
        return enrollmentService.deleteEnrollment(studentId, courseId);
    }
}
