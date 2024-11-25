package Orion.com.Orion.repository;

import Orion.com.Orion.model.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentInfo, Integer> {
    StudentInfo findByUsername(String username);
}
