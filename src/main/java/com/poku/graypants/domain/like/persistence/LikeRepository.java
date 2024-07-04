package com.poku.graypants.domain.like.persistence;

import com.poku.graypants.domain.user.persistence.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {
//    // 회원만 찜을 클릭할 수 있게
//    Optional<User> findByUsername(String username);
//
//    // 특정 사용자에 대한 모든 찜 목록 가져오기(찜조회)
//    List<Like> findByUserId(Long userId);
//
//    // 특정 사용자의 특정 상품에 대한 찜 여부 확인
//    boolean existsByUserIdAndProductId(Long userId, Long productId);
//
//    // 특정 사용자의 특정 상품에 대한 찜 삭제
//    void deleteByUserIdAndProductId(Long userId, Long productId);

}
