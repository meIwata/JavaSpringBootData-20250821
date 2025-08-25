package fcu.pbiecs.springdemo.repository;

import fcu.pbiecs.springdemo.model.Enrollment;
import fcu.pbiecs.springdemo.model.EnrollmentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {

}
