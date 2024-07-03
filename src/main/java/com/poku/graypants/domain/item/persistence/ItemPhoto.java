package com.poku.graypants.domain.item.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_phote_id", nullable = false, unique = true)
    private Long itemPhoteId;

    @Column(name = "item_photo_link",length = 200)
    private String itemPhotoLink;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Builder
    public ItemPhoto(String itemPhotoLink, Item item) {
        this.itemPhotoLink = itemPhotoLink;
        this.item = item;
    }
}
