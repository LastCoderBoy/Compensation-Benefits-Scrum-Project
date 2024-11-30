package Orion.com.Orion.repository;

import Orion.com.Orion.model.AssignedTasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<AssignedTasks, Integer> {
    List<AssignedTasks> findByStudentInfo_Id(Integer id);
}
