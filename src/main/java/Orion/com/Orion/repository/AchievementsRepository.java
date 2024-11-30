package Orion.com.Orion.repository;

import Orion.com.Orion.model.Achievements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AchievementsRepository extends JpaRepository<Achievements, Integer> {
    List<Achievements> findAchievementsByStudentBenefits_BenefitId(Integer benefitId);
}
