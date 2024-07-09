package com.poku.graypants.domain.item.persistence;

import com.poku.graypants.domain.item.application.dto.ItemUpdateRequestDto;
import com.poku.graypants.domain.order.persistence.OrderItem;
import com.poku.graypants.domain.store.persistence.Store;
import com.poku.graypants.global.entity.BaseTime;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", nullable = false, unique = true)
    private Long Id;

    @Column(name = "item_name", length = 200, nullable = false)
    private String itemName;

    @Column(name = "item_price", nullable = false)
    private int itemPrice;

    @Column(name = "item_desc_img", length = 100, nullable = false)
    private String itemDescImg;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "sales_quantity", nullable = false)
    private int salesQuantity;

    @Column(name = "view_count", nullable = false)
    private int viewCount;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    private Category category;

    @OneToMany(mappedBy = "item", fetch = LAZY)
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "item" ,fetch = LAZY)
    private List<ItemPhoto> itemPhotos;

    @Builder
    public Item(String itemName, int itemPrice, String itemDescImg, int stock, Store store, Category category, List<ItemPhoto> itemPhotos) {
        super();
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemDescImg = itemDescImg;
        this.stock = stock;
        this.store = store;
        this.category = category;
        this.itemPhotos = itemPhotos;
    }

    public Item updateItem(ItemUpdateRequestDto dto) {
        this.itemName = dto.getItemName();
        this.itemPrice = dto.getItemPrice();
        this.stock = dto.getStock();
        this.category = dto.getCategory();

        return this;
    }

    public void updateItemPhotos(List<ItemPhoto> itemPhotos) {
        this.itemPhotos = itemPhotos;
    }

    public void updateItemDescImg(String itemDescImg) {
        this.itemDescImg = itemDescImg;
    }

}