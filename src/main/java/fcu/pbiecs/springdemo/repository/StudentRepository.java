package fcu.pbiecs.springdemo.repository;

import fcu.pbiecs.springdemo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByEmail (String email);
    List<Student> findByLastName (String lastName);

}
