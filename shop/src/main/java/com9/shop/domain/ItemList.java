package com9.shop.domain;

import com9.shop.domain.entity.Item;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ItemList {
    private List<Item> items;

    public ItemList(){
        items = new ArrayList<>();
    }
}
