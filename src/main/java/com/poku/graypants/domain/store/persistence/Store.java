package com.poku.graypants.domain.store.persistence;

import com.poku.graypants.domain.auth.persistence.EmailAuthenticateAble;
import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.orderItem.persistence.OrderItem;
import com.poku.graypants.global.entity.BaseTime;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Table(name = "STORES")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends BaseTime implements EmailAuthenticateAble  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @Column(name = "store_name", length = 50, nullable = false, unique = true)
    private String storeName;

    @Column(name = "store_email", length = 200, nullable = false, unique = true)
    private String storeEmail;

    @Column(name = "store_password", length = 100, nullable = false)
    private String storePassword;

    @Column(name="refresh_token")
    private String refreshToken;
    
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = LAZY)
    private List<Item> itemList = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = LAZY)
    private List<OrderItem> orderItemList = new ArrayList<>();

    @Builder
    public Store(List<Item> itemList, String storeName, String storeEmail, String storePassword) {
        super();
        this.itemList = itemList;
        this.storeName = storeName;
        this.storeEmail = storeEmail;
        this.storePassword = storePassword;
    }

    @Override
    public String getEmail() {
        return this.storeEmail;
    }

    @Override
    public String getPassword() {
        return this.storePassword;
    }

    @Override
    public String getRole() {
        return "ROLE_STORE";
    }

    @Override
    public Long getId() {
        return this.storeId;
    }

    @Override
    public void updateRefreshToken(String newRefreshToken) {
        this.refreshToken = newRefreshToken;
    }
}
