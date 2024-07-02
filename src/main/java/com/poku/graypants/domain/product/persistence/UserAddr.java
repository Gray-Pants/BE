package src.main.java.com.poku.graypants.domain.product.persistence;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class UserAddr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userAddrId;

    @Column(nullable = false, length = 200)
    private String userAddr;

    //@ManyToOne
    //@JoinColumn(name = "user_id")
    //private User user;

    @Column(nullable = false, length = 20)
    private String userAddrPhone;
}
