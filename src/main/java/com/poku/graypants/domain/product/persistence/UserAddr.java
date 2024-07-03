package com.poku.graypants.domain.product.persistence;
/**
 * @author HAYEON
 */
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAddr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userAddrId;

    @Column(name = "user_addr", nullable = false, length = 200)
    private String userAddr;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "user_addr_phone", nullable = false, length = 20)
    private String userAddrPhone;
}
