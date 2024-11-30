package Orion.com.Orion.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "StudentBenefits")
@Data
public class StudentBenefits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer benefitId;

    // Many-to-one relationship to StudentInfo
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentInfo studentInfo;

    // Fields for the remaining columns
    @Column(name = "skill_milestones")
    private Integer skillMilestones;

    @Column(name = "completed_tasks")
    private Integer completedTasks;

    @Column(name = "number_of_absences")
    private Integer numberOfAbsences;
}
