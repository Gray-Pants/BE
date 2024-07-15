package com.poku.graypants.domain.item.persistence;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.poku.graypants.global.exception.ExceptionStatus;
import com.poku.graypants.global.exception.GrayPantsException;

import java.util.*;
import java.util.stream.Collectors;


public enum Category {

    ROOT("카테고리", null),
        TOP("상의", ROOT),
            OUTER("상의", TOP),
            LONG_SLEEVE("긴팔", TOP),
            SHORT_SLEEVE("상의", TOP),
        BOTTOM("하의", ROOT),
            SHORTS("반바지", BOTTOM),
            PANTS("긴바지", BOTTOM),
            SKIRT("치마", BOTTOM),
        SHOES("신발", ROOT),
            SNIKERS("운동화", SHOES),
            BOOTS("부츠", SHOES),
            SLIPPER("슬리퍼", SHOES),
        CAP("모자", ROOT),
            BALL_CAP("볼캡", CAP),
            BUCKET_HAT("버킷햇", CAP),
            BEANIE("비니", CAP),
        BAG("가방", ROOT),
            BACKPACK("백팩", BAG),
            CROSS_BAG("크로스백", BAG),
            SHOULDER_BAG("숄더백", BAG),
        ACC("소품", ROOT),
            BRACELET("팔찌", ACC),
            RING("반지", ACC),
            NECKLACE("목걸이", ACC),
        UNDERWEAR("속옷", ROOT),
            WOMAN_UNDERWEAR("여성속옷", UNDERWEAR),
            MAN_UNDERWEAR("남성속옷", UNDERWEAR);


    // 카테고리 이름
    private final String title;

    // 부모 카테고리
    private final Category parentCategory;

    // 자식카테고리
    private final List<Category> childCategories;

    Category(String title, Category parentCategory) {
        this.childCategories = new ArrayList<>();
        this.title = title;
        this.parentCategory = parentCategory;
        if(Objects.nonNull(parentCategory)) {
            parentCategory.childCategories.add(this);
        }
    }

    @JsonValue
    public String getTitle() {
        return title;
    }

    // 부모카테고리 Getter
    public Category getParentCategory() {
        if (parentCategory == null) {
            throw new GrayPantsException(ExceptionStatus.CATEGORY_NOT_FOUND);
        }
        return parentCategory;
    }

    // 자식카테고리 Getter
    public List<Category> getChildCategories() {
        return Collections.unmodifiableList(childCategories);
    }

    // 마지막 카테고리(상품추가 가능)인지 반환
    public boolean isLeafCategory() {
        return childCategories.isEmpty();
    }

    public List<Category> getAllLeafCategories() {
        List<Category> leafCategories = new ArrayList<>();
        getAllLeafCategoriesRecursive(this, leafCategories);
        return leafCategories;
    }
    private void getAllLeafCategoriesRecursive(Category category, List<Category> leafCategories) {
        if (category.isLeafCategory()) {
            leafCategories.add(category);
        } else {
            for (Category childCategory : category.getChildCategories()) {
                getAllLeafCategoriesRecursive(childCategory, leafCategories);
            }
        }
    }


    // 마지막 카테고리(상품추가 가능)들 반환
    public List<Category> getLeafCategories() {
        return Arrays.stream(Category.values())
                .filter(category -> category.isLeafCategoryOf(this))
                .collect(Collectors.toList());
    }

    private boolean isLeafCategoryOf(Category category) {
        return this.isLeafCategory() && category.contains(this);
    }

    private boolean contains(Category category) {
        if(this.equals(category)) return true;

        return Objects.nonNull(category.parentCategory) &&
                this.contains(category.parentCategory);
    }

}
