package src.main.java.com.poku.graypants.domain.product.persistence;
/**
 * @author HAYEON
 */

import jakarta.persistence.*;
import lombok.Cleanup;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int alertId;

    @Column(name = "alert_message", nullable = false, length = 1000)
    private String alertMessage;

    @Column(name = "alert_type", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private String alertType;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    //@ManyToOne
    //@JoinColumn(name = "store_id")
    //private Store store;
}
