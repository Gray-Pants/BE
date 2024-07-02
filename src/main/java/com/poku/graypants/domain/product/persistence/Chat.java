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

    @Column(name = "chat_message", nullable = false, length = 1000)
    private String chatMessage;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    //받는 사람??

    //@ManyToOne
    //@JoinColumn(name = "store_id")
    //private Store store;
}
