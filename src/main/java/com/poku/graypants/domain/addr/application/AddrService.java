package com.poku.graypants.domain.addr.application;

import com.poku.graypants.domain.addr.application.Dto.AddrRequestDto;
import com.poku.graypants.domain.addr.application.Dto.AddrResponseDto;
import com.poku.graypants.domain.addr.persistence.AddrRepository;
import com.poku.graypants.domain.addr.persistence.UserAddr;
import com.poku.graypants.domain.user.application.UserService;
import com.poku.graypants.global.exception.ExceptionStatus;
import com.poku.graypants.global.exception.GrayPantsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.poku.graypants.domain.user.persistence.User;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AddrService {

    private final AddrRepository addrRepository;
    private final UserService userService;

    // 배송지 추가
    public AddrResponseDto createAddr(AddrRequestDto addrRequestDto) {
        User user = userService.getVerifyUserByUserId(addrRequestDto.getUserId());

        UserAddr userAddr = UserAddr.builder()
                .userAddrName(addrRequestDto.getUserAddrName())
                .userAddr(addrRequestDto.getUserAddr())
                .user(user)
                .userAddrPhone(addrRequestDto.getUserAddrPhone())
                .build();

        UserAddr savedAddr = addrRepository.save(userAddr);
        return AddrResponseDto.toDto(savedAddr);
    }

    // 배송지 수정
    public AddrResponseDto updateAddr(Long userAddrId, AddrRequestDto addrRequestDto) {
        UserAddr existingUserAddr = addrRepository.findById(userAddrId)
                .orElseThrow(() -> new GrayPantsException(ExceptionStatus.ADDR_NOT_FOUND));


        existingUserAddr.update(addrRequestDto.getUserAddrName(), addrRequestDto.getUserAddr(), addrRequestDto.getUserAddrPhone());

        UserAddr savedAddr = addrRepository.save(existingUserAddr);
        return AddrResponseDto.toDto(savedAddr);
    }

    // 배송지 삭제
    public void deleteUserAddr(Long userAddrId) {
        if(!addrRepository.existsById(userAddrId)) {
            throw new GrayPantsException(ExceptionStatus.ADDR_NOT_FOUND);
        }
        addrRepository.deleteById(userAddrId);
    }

    // 배송지 이름에 따른 조회
    public AddrResponseDto getUserById(Long userAddrId) {
        UserAddr userAddr = addrRepository.findById(userAddrId)
                .orElseThrow(() -> new GrayPantsException(ExceptionStatus.ADDR_NOT_FOUND));
        return AddrResponseDto.toDto(userAddr);
    }

}