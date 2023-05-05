package com.titzko.freemarkertodo.repository;

import com.titzko.freemarkertodo.model.ShoppingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ShoppingItemRepository extends JpaRepository<ShoppingItem, Long> {


    List<ShoppingItem> findShoppingItemsByUserId(Long userId);
}
