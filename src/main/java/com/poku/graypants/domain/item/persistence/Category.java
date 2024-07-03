package com.poku.graypants.domain.item.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id",unique = true, nullable = false)
    private Long categoryId;

    @OneToMany(mappedBy = "category", fetch = LAZY)
    private List<Item> item;

    @Column(name = "category_name", length = 50, nullable = false, unique = true)
    private String categoryName;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(fetch = LAZY, mappedBy = "parent")
    private List<Category> children;

    @Builder
    public Category(List<Item> item, String categoryName, Category parent, List<Category> children) {
        this.item = item;
        this.categoryName = categoryName;
        this.parent = parent;
        this.children = children;
    }
}
