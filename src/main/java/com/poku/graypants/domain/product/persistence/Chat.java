package src.main.java.com.poku.graypants.domain.product.persistence;
/**
 * @author HAYEON
 */

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chatId;

    @Column(nullable = false, length = 1000)
    private String chatMessage;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private int receverID;

    @Column(nullable = false)
    private int storeId;
}
