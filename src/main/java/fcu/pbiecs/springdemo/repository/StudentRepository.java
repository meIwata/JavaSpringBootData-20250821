package fcu.pbiecs.springdemo.repository;

import fcu.pbiecs.springdemo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
