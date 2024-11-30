package Orion.com.Orion.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Student_Tasks")
public class AssignedTasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentInfo studentInfo;

    @Column(name = "task_title")
    private String task_title;

    @Column(name = "task_id")
    private Integer taskID;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_status")
    private TaskStatus taskStatus;
}
