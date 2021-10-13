package com.example.restaurant.wishlist.repository;

import com.example.restaurant.db.MemoryDbRepositoryAbstract;
import com.example.restaurant.wishlist.entity.WishListEntity;
import org.springframework.stereotype.Repository;

// 레포지토리로 동작시키기 위한 어노테이션(데이터 저장)
@Repository
public class WishListRepository extends MemoryDbRepositoryAbstract<WishListEntity> {
}
