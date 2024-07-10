package com.poku.graypants.domain.store.application;

import com.poku.graypants.domain.auth.persistence.EmailAuthenticateAble;
import com.poku.graypants.domain.store.persistence.Store;
import com.poku.graypants.domain.store.persistence.StoreRepository;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.global.exception.ExceptionStatus;
import com.poku.graypants.global.exception.GrayPantsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public Store getStoreByEmail(String email) {
        return getVerifyStore(email);
    }

    public Store getVerifyStore(String email) {
        return storeRepository.findByStoreEmail(email)
                .orElseThrow(() -> new GrayPantsException(ExceptionStatus.USER_NOT_FOUND));
    }

    public EmailAuthenticateAble saveStore(String email, String name, String password) {
        if(storeRepository.findByStoreEmail(email).isPresent()) {
            throw new GrayPantsException(ExceptionStatus.DUPLICATED_EMAIL);
        }
        return storeRepository.save(Store.builder()
                .storeEmail(email)
                .storeName(name)
                .storePassword(password)
                .build());
    }
}
