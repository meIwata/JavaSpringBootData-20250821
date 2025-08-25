package fcu.pbiecs.springdemo.controller;

import fcu.pbiecs.springdemo.model.Enrollment;
import fcu.pbiecs.springdemo.model.EnrollmentId;
import fcu.pbiecs.springdemo.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "選課管理", description = "提供選課 CRUD API") // Swagger標籤，用於API文檔生成
@CrossOrigin("*") // 允許所有來源的跨域請求
@RestController // 處理HTTP請求的控制器
@RequestMapping("/api/enrollments") // 定義路徑前綴
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    // 查詢所有選課
    @Operation(summary = "取得所有選課資料", description = "取得系統中所有的選課資料")
    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    // 查詢單筆選課
    @Operation(summary = "取得單筆選課資料", description = "根據學生ID和課程ID取得單筆選課資料")
    @GetMapping("/{studentId}/{courseId}")
    public Enrollment getEnrollmentById(@PathVariable int studentId, @PathVariable int courseId) {
        EnrollmentId enrollmentId = new EnrollmentId(studentId, courseId);
        return enrollmentService.getEnrollmentById(enrollmentId);
    }

    // 新增選課
    @Operation(summary = "新增選課資料", description = "新增一筆選課資料到系統中")
    @PostMapping
    public Enrollment addEnrollment(@RequestBody Enrollment enrollment) {
        return enrollmentService.addEnrollment(enrollment);
    }

    // 更新選課
    @Operation(summary = "更新選課資料", description = "更新系統中已存在的選課資料")
    @PutMapping
    public Enrollment updateEnrollment(@RequestBody Enrollment enrollment) {
        return enrollmentService.updateEnrollment(enrollment);
    }

    // 刪除選課
    @Operation(summary = "刪除選課資料", description = "根據學生ID和課程ID刪除單筆選課資料")
    @DeleteMapping("/{studentId}/{courseId}")
    public void deleteEnrollment(@PathVariable int studentId, @PathVariable int courseId) {
        EnrollmentId enrollmentId = new EnrollmentId(studentId, courseId);
        enrollmentService.deleteEnrollment(enrollmentId);
    }

}