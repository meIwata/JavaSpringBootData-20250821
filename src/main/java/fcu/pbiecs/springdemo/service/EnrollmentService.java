package fcu.pbiecs.springdemo.service;

import fcu.pbiecs.springdemo.model.Enrollment;
import fcu.pbiecs.springdemo.model.EnrollmentId;
import fcu.pbiecs.springdemo.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    // 查詢所有選課
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    // 新增選課
    public Enrollment addEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    // 查詢單筆選課
    public Enrollment getEnrollmentById(EnrollmentId id) {
        return enrollmentRepository.findById(id).orElse(null);
    }

    // 更新選課
    public Enrollment updateEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    // 刪除選課
    public void deleteEnrollment(EnrollmentId id) {
        enrollmentRepository.deleteById(id);
    }

}
