package com.poku.graypants.domain.item.persistence;


import com.poku.graypants.domain.item.application.dto.ItemResponseDto;
import com.poku.graypants.domain.item.application.dto.QItemResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepositoryCustomImpl implements ItemRepositoryCustom{

    @Autowired
    EntityManager em;

    JPAQueryFactory query;

    QItem item = QItem.item;

    @Override
    public List<ItemResponseDto> searchItemList(String name) {
        query = new JPAQueryFactory(em);
        return query.
                select(new QItemResponseDto(item))
                .from(item)
                .where(item.itemName.contains(name)
                        .or(item.store.storeName.contains(name)))
                .fetch();
    }
}
