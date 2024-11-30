package Orion.com.Orion.repository;

import Orion.com.Orion.model.StudentBenefits;
import Orion.com.Orion.model.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentBenefitsRepository extends JpaRepository<StudentBenefits, Integer> {
    Optional<StudentBenefits> findByStudentInfo_Id(Integer id);
}