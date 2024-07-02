package com.poku.graypants.domain.product.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id",unique = true, nullable = false)
    private long categoryId;

    @OneToOne(mappedBy = "item", fetch = LAZY)
    private Item item;

    @Column(name = "category_name", length = 50, nullable = false, unique = true)
    private String categoryName;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category parent;

    @OneToMany(fetch = LAZY, mappedBy = "parent")
    @JoinColumn(name = "upper_category_id2")
    private List<Category> category;
}
