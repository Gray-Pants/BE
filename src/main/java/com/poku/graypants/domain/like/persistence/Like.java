package com.poku.graypants.domain.like.persistence;

import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.like.application.dto.LikeUpdateRequestDto;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.global.entity.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "likes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Like extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long likeId;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name =  "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY) //관계가 실제로 사용될 때까지 DB로부터 관련 엔티티를 로드하지 않는다는 것을 의미한다. 즉 customer 엔티티에 접근하기 전까지는 관련 데이터가 로드되지 않는다.(성능 최적화를 위해 사용)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @Builder
    public Like(User user, Item item) {
        this.user = user;
        this.item = item;
    }

    public Like updateLike(LikeUpdateRequestDto dto) {
        this.user = dto.getUser();
        this.item = dto.getItem();

        return this;
    }
}
