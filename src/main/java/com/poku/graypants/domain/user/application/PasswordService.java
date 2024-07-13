package com.poku.graypants.domain.user.application;

import com.poku.graypants.domain.user.application.dto.PasswordEditRequestDto;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.domain.user.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PasswordService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Transactional
    public boolean changePassword(PasswordEditRequestDto passwordDto) {
        User user = userRepository.findById(passwordDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(passwordDto.getPassword(), user.getPassword())) {
            user.changePassword(passwordEncoder.encode(passwordDto.getNewPassword()));
            userRepository.save(user);
            return true;
        }
        return false;
    }
}