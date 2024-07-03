package com.poku.graypants.domain.product.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = LAZY)
    private List<Item> itemList = new ArrayList<>();

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
}
