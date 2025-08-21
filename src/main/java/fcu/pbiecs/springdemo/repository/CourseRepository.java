package fcu.pbiecs.springdemo.repository;

import fcu.pbiecs.springdemo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
