package Orion.com.Orion.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "Achievements")
@Data
public class Achievements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "duration", nullable = false)
    private String duration;

    @Column(name = "badge_image_url")
    private String badgeImageUrl;

    @Column(name = "description")
    private String description;

    @Column(name = "date_earned")
    private LocalDate dateEarned;

    // Many-to-one relationship back to StudentBenefits
    @ManyToOne
    @JoinColumn(name = "benefit_id", nullable = false)
    private StudentBenefits studentBenefits;

}
