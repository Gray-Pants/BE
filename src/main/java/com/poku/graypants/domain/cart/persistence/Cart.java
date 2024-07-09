package com.poku.graypants.domain.cart.persistence;

import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.global.entity.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "CARTS")
@Getter
@NoArgsConstructor
public class Cart extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id",unique = true, nullable = false)
    private Long cartId;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;

    @OneToOne
    @JoinTable(name = "user_id")
    private User user;

    @Builder
    public Cart(User user) {
        this.user = user;
    }
}
