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
    @Column(name = "item_photo_id")
    private Long itemPhotoId;

    @Column(name = "item_photo_link",length = 200)
    private String itemPhotoLink;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Builder
    public ItemPhoto(String itemPhotoLink) {
        this.itemPhotoLink = itemPhotoLink;

    }
}
