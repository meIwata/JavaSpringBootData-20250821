package fcu.pbiecs.springdemo.controller;

import fcu.pbiecs.springdemo.model.Course;
import fcu.pbiecs.springdemo.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// http://localhost:8080/swagger-ui/index.html，可以利用Swagger標籤來生成API文檔，來查詢Restful API的使用方式
// http://localhost:8080/v3/api-docs，可以查看API的JSON格式描述
@Tag(name="課程管理", description = "提供課程  CRUD API")
@CrossOrigin("*") // 允許所有來源的跨域請求
@RestController // 處理HTTP請求的控制器
@RequestMapping("/api/courses") // 定義路徑前綴
public class CourseController {
    @Autowired // 自動注入CourseService，這樣就可以使用課程服務
    private CourseService courseService;

    // 查詢所有課程資料
    @Operation(summary = "查詢學生", description = "查詢所有學生")
    @GetMapping
    public List<Course> getCourses() {
     return courseService.getAllCourses();
    }

    // 查詢特定指定id課程資料
    @Operation(summary = "查詢特定課程", description = "根據ID查詢特定課程")
    @GetMapping("/{id}") // 完整路徑會是 /api/courses
    public Course getCourse(@PathVariable int id) {
        return courseService.getCourseById(id);
    }

    // 刪除特定課程資料
    @Operation(summary = "刪除特定課程", description = "根據ID刪除特定課程")
    @DeleteMapping("/{id}") // 完整路徑會是 /api/courses
    public String deleteCourse(@PathVariable int id) {
        return courseService.deleteCourseById(id);
    }

    // 新增課程資料
    @Operation(summary = "新增課程", description = "新增一個新的課程")
    @PostMapping
    public String createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    // 更新課程資料
    @Operation(summary = "更新課程", description = "根據ID更新特定課程")
    @PutMapping("/{id}")
    public String updateCourse(@PathVariable int id, @RequestBody Course updatedCourse) {
        return courseService.updateCourse(id, updatedCourse);
    }
}
