package fcu.pbiecs.springdemo.repository;

import fcu.pbiecs.springdemo.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

}
