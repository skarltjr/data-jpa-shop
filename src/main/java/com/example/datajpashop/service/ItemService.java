package com.example.datajpashop.service;

import com.example.datajpashop.domain.item.Item;
import com.example.datajpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item item = itemRepository.findById(itemId).get();
        item.setName(name);
        item.setStockQuantity(stockQuantity);
        item.setPrice(price);
    }

    public List<Item> findItems()
    {
        return itemRepository.findAll();
    }

    public Item findOne(Long id) {
       return itemRepository.findById(id).get();
    }
}
