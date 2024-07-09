package com.poku.graypants.domain.item.application;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.poku.graypants.domain.item.application.dto.*;
import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.item.persistence.ItemPhoto;
import com.poku.graypants.domain.item.persistence.ItemRepositoryCustom;
import com.poku.graypants.domain.item.persistence.ItemRepository;
import com.poku.graypants.global.exception.ExceptionStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private ItemRepository itemRepository;
    private ItemRepositoryCustom itemRepositoryCustom;
    private final AmazonS3 amazonS3;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    public ItemResponseDto createItem(ItemCreateRequestDto itemCreateRequestDto) {
        // USER email 을 통한 STORE 받아오기
        // authenticator.

        List<MultipartFile> multipartItemPhotos = itemCreateRequestDto.getItemPhotos();
        MultipartFile multipartItemDesc = itemCreateRequestDto.getItemDescImg();

//        Item savedItem = itemRepository.save(ItemCreateRequestDto.toEntity());
//
//        savedItem.updateItemPhotos(uploadItemPhotos(multipartItemPhotos, savedItem));
//        savedItem.updateItemDescImg(uploadDescPhoto(multipartItemDesc));
//
//        return new ItemResponseDto(savedItem);
        return null;
    }

    public ItemResponseDto updateItem(Long id, ItemUpdateRequestDto itemUpdateRequestDto) {
        Item item = getItemByID(id);

        return new ItemResponseDto(item.updateItem(itemUpdateRequestDto));
    }


    public List<ItemResponseDto> findByNameAll(String name) {
        return itemRepositoryCustom.searchItemList(name);
    }

    public ItemResponseDto findById(Long id) {
        Item item = getItemByID(id);
        return new ItemResponseDto(item);
    }


    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    private Item getItemByID(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() ->
            {throw new RuntimeException(ExceptionStatus.ITEM_NOT_FOUND.getMessage());
            });
        return item;
    }

    private String putS3(File uploadFile, String fileName){
        amazonS3.putObject(
                new PutObjectRequest(bucket, fileName, uploadFile)
                        .withCannedAcl(CannedAccessControlList.PublicRead)	// PublicRead 권한으로 업로드 됨
        );
        return amazonS3.getUrl(bucket, fileName).toString();
    }

    private void removeLocalFile(File file) {
        if (file.delete()){
            log.info("파일이 삭제 되었습니다.");
        }else{
            log.info("파일이 삭제 되지 않았습니다.");
        }
    }

    private File multipartFileToFile(MultipartFile multipartFile) {
        File file = new File(multipartFile.getOriginalFilename());
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            throw new RuntimeException(ExceptionStatus.FILE_NOT_FOUND.getMessage());
        }
        return file;
    }

    private String makeUUID() {
        StringBuilder sb = new StringBuilder();
        sb.append(UUID.randomUUID().toString().substring(0, 7));

        return sb.toString();
    }

    private String uploadDescPhoto(MultipartFile multipartItemDesc) {
        String descImgName = multipartItemDesc.getOriginalFilename();
        descImgName = LocalDateTime.now() + "-" + descImgName + makeUUID();

        File uploadDescPhoto = multipartFileToFile(multipartItemDesc);
        String uploadDescPhotoUrl = putS3(uploadDescPhoto, descImgName);

        removeLocalFile(uploadDescPhoto);

        return uploadDescPhotoUrl;
    }

    private List<ItemPhoto> uploadItemPhotos(List<MultipartFile> multipartItemPhotos, Item savedItem) {
        List<ItemPhoto> itemPhotos = new ArrayList<>();

        for (MultipartFile multipartItemPhoto : multipartItemPhotos){
            String fileName = multipartItemPhoto.getOriginalFilename();
            fileName = LocalDateTime.now() + "-" + fileName + "-" + makeUUID();

            File uploadFile = multipartFileToFile(multipartItemPhoto);
            String uploadFileUrl = putS3(uploadFile, fileName);
            itemPhotos.add(ItemPhoto.builder()
                    .itemPhotoLink(uploadFileUrl)
                    .item(savedItem).build());

            removeLocalFile(uploadFile);
        }

        return itemPhotos;
    }
}
