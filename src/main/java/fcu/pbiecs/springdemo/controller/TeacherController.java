package fcu.pbiecs.springdemo.controller;

import fcu.pbiecs.springdemo.model.Teacher;
import fcu.pbiecs.springdemo.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="教師管理", description = "提供教師 CRUD API") // Swagger註解，描述這個控制器的功能
@CrossOrigin("*")
@RestController
@RequestMapping("/api/teachers") // 定義路徑前綴
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    
    // 查詢所有教師資料
    @Operation(summary = "查詢所有教師", description = "查詢所有教師資料")
    @GetMapping
    public List<Teacher> getTeachers() {
        return teacherService.getAllTeachers();
    }
    // 查詢特定指定id教師資料
    @Operation(summary = "查詢特定教師", description = "根據ID查詢特定教師")
    @GetMapping("/{id}") // 完整路徑會是 "/api/teachers/{id}"
    public Teacher getTeacher(int id) {
        return teacherService.getTeacherById(id);
    }
    
    // 刪除特定教師資料
    @Operation(summary = "刪除特定教師", description = "根據ID刪除特定教師")
    @DeleteMapping("/delete/{id}") // 完整路徑會是 "/api/teachers/delete/{id}"
    public String deleteTeacher(int id) {
        return teacherService.deleteTeacherById(id);
    }
    
    // 新增教師資料
    @Operation(summary = "新增教師", description = "新增一個新的教師")
    @PostMapping("/create") // 完整路徑會是 "/api/teachers
    public  Teacher createTeacher(Teacher teacher) {
        return teacherService.createTeacher(teacher);
    }

    // 更新教師資料
    @Operation(summary = "更新教師", description = "根據ID更新教師資料")
    @PutMapping("/update/{id}") // 完整路徑會是 "/api
    public Teacher updateTeacher(@RequestBody int id, @RequestBody Teacher teacher) {
        return teacherService.updateTeacher(id, teacher);
    }
    
}
