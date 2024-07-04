package com.poku.graypants.domain.store.persistence;

import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.order.persistence.OrderItem;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @Column(name = "store_name", length = 50, nullable = false, unique = true)
    private String storeName;

    @Column(name = "store_email", length = 200, nullable = false, unique = true)
    private String storeEmail;

    @Column(name = "store_password", length = 100, nullable = false)
    private String storePassword;

    @CreatedDate
    private LocalDateTime created_at;

    @LastModifiedDate
    private LocalDateTime updated_at;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = LAZY)
    private List<Item> itemList = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = LAZY)
    private List<OrderItem> orderItemList = new ArrayList<>();

    @Builder
    public Store(List<Item> itemList, String storeName, String storeEmail, String storePassword) {
        this.itemList = itemList;
        this.storeName = storeName;
        this.storeEmail = storeEmail;
        this.storePassword = storePassword;
    }
}