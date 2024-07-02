package com.poku.graypants.domain.product.persistence;
/**
 * @author HAYEON
 */
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class UserAddr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userAddrId;

    @Column(name = "user_addr", nullable = false, length = 200)
    private String userAddr;

    //@ManyToOne
    //@JoinColumn(name = "user_id")
    //private User user;

    @Column(name = "user_addr_phone", nullable = false, length = 20)
    private String userAddrPhone;
}
