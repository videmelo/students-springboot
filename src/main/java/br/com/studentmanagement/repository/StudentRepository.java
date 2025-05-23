package br.com.studentmanagement.repository;

import br.com.studentmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT a FROM Student a WHERE a.status = 'ACTIVE' ")
    public List<Student> findByStatusActive();

    @Query("SELECT i FROM Student i WHERE i.status = 'INACTIVE' ")
    public List<Student> findByStatusInactive();

    public List<Student> findByNameContainingIgnoreCase(String name);

}
