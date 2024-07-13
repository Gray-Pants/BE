package com.poku.graypants.domain.item.application;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.poku.graypants.domain.item.application.dto.ItemCreateRequestDto;
import com.poku.graypants.domain.item.application.dto.ItemResponseDto;
import com.poku.graypants.domain.item.application.dto.ItemUpdateRequestDto;
import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.item.persistence.ItemRepository;
import com.poku.graypants.domain.item.persistence.ItemRepositoryCustom;
import com.poku.graypants.domain.store.persistence.Store;
import com.poku.graypants.global.exception.ExceptionStatus;
import com.poku.graypants.global.exception.GrayPantsException;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemRepositoryCustom itemRepositoryCustom;
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public ItemResponseDto createItem(ItemCreateRequestDto itemCreateRequestDto) {
        // USER email 을 통한 STORE 받아오기
        // authenticator.

        List<MultipartFile> multipartItemPhotos = itemCreateRequestDto.getItemPhotos();
        log.info("Creating new item: {}", multipartItemPhotos);
        MultipartFile multipartItemDesc = itemCreateRequestDto.getItemDescImg();

        Item item = itemCreateRequestDto.toEntity();
        item.updateItemPhotos(uploadItemPhotos(multipartItemPhotos));
        item.updateItemDescImg(uploadDescPhoto(multipartItemDesc));

        Item savedItem = itemRepository.save(item);

        return new ItemResponseDto(savedItem);
    }

    public ItemResponseDto updateItem(Long id, ItemUpdateRequestDto itemUpdateRequestDto) {
<<<<<<< Updated upstream
        Item item = getVerifyItemById(id);
=======
        Item item = getItemById(id);
>>>>>>> Stashed changes

        return new ItemResponseDto(item.updateItem(itemUpdateRequestDto,
                uploadItemPhotos(itemUpdateRequestDto.getItemPhotos()),
                uploadDescPhoto(itemUpdateRequestDto.getItemDescImg())));
    }


    public List<ItemResponseDto> findByNameAll(String name) {
        return itemRepositoryCustom.searchItemList(name);
    }

    public List<ItemResponseDto> findAll() {
        List<Item> items = itemRepository.findAll();
        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
        for (Item item : items) {
            itemResponseDtos.add(new ItemResponseDto(item));
        }
        return itemResponseDtos;
    }

    public ItemResponseDto findById(Long id) {
        Item item = getVerifyItemById(id);
        return new ItemResponseDto(item);
    }


    public void deleteItem(Long id) {

        Item findItem = getVerifyItemById(id);
        itemRepository.delete(findItem);
    }

    public Item getVerifyItemById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new GrayPantsException(ExceptionStatus.ITEM_NOT_FOUND));
    }

    public void verifyItemAndStoreMatch(Item item, Store store) {
        if (!item.getStoreId().equals(store.getStoreId())) {
            throw new GrayPantsException(ExceptionStatus.ORDER_AND_USER_MISMATCH);
        }
    }

    private String putS3(MultipartFile multipartItemPhoto, String fileName) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartItemPhoto.getContentType());
        objectMetadata.setContentLength(multipartItemPhoto.getSize());
        try {
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, multipartItemPhoto.getInputStream(),
                    objectMetadata));
        } catch (IOException e) {
            throw new GrayPantsException(ExceptionStatus.IO_EXCEPTION);
        }
        return amazonS3.getUrl(bucket, fileName).toString();
    }

    private String makeUUID() {

        return UUID.randomUUID().toString().substring(0, 7);
    }

    private String uploadDescPhoto(MultipartFile multipartItemDesc) {
        String descImgName = multipartItemDesc.getOriginalFilename();
        descImgName = LocalDateTime.now() + "-" + descImgName + makeUUID();

        return putS3(multipartItemDesc, descImgName);
    }

    private String uploadItemPhotos(List<MultipartFile> multipartItemPhotos) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < multipartItemPhotos.size(); i++) {
            String fileName = multipartItemPhotos.get(i).getOriginalFilename();
            fileName = LocalDateTime.now() + "-" + fileName + "-" + makeUUID();

            String uploadFileUrl = putS3(multipartItemPhotos.get(i), fileName);

            sb.append(uploadFileUrl);

            if (i != multipartItemPhotos.size() - 1) {
                sb.append(",");
            }

        }

        return sb.toString();
    }
}
