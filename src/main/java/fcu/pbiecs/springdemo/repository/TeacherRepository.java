package fcu.pbiecs.springdemo.repository;

import fcu.pbiecs.springdemo.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

}
