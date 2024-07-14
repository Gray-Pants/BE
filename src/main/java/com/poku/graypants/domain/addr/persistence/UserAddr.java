package com.poku.graypants.domain.addr.persistence;

import com.poku.graypants.domain.user.persistence.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "USER_ADDRS")
@Getter
@NoArgsConstructor
public class UserAddr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_addr_id",unique = true, nullable = false)
    private Long userAddrId;

    @Column(name = "user_addr_name", length = 255)
    private String userAddrName;

    @Column(name = "user_addr", length = 200)
    private String userAddr;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_Id")
    private User user;

    @Column(name = "user_addr_phone", length = 20)
    private String userAddrPhone;

    @Builder
    public UserAddr(Long userAddrId, String userAddrName, User user, String userAddr,  String userAddrPhone) {
        this.userAddrId = userAddrId;
        this.userAddrName = userAddrName;
        this.userAddr = userAddr;
        this.user = user;
        this.userAddrPhone = userAddrPhone;
    }

    public void update(String userAddrName, String userAddr, String userAddrPhone) {
        this.userAddrName = userAddrName;
        this.userAddr = userAddr;
        this.userAddrPhone = userAddrPhone;
    }
}
