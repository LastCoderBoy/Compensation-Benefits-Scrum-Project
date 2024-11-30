package Orion.com.Orion.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Assigned_Projects")
public class NewProjects {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String title;
    private String requirements;
}
