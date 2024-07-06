
package com.poku.graypants.domain.like.application;

import com.poku.graypants.domain.item.persistence.ItemRepository;
import com.poku.graypants.domain.like.application.dto.LikeResponseDto;
import com.poku.graypants.domain.like.persistence.Like;
import com.poku.graypants.domain.like.persistence.LikeRepository;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.domain.user.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor //@Autowired 역할도 수행
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;


    @Transactional(readOnly = true)
    public List<LikeResponseDto> getAllLikesByUser(User userId) {
        List<Like> likes = likeRepository.findByUserId(userId);
        return likes.stream()
                .map(LikeResponseDto::new)
                .collect(Collectors.toList());
    }
}

