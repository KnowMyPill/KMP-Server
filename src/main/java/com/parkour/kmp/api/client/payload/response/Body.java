package com.parkour.kmp.api.client.payload.response;

import jakarta.xml.bind.annotation.XmlElement;


public class Body {

    private ItemsWrapper itemsWrapper;

    @XmlElement(name = "items")
    public ItemsWrapper getItemsWrapper() {
        return itemsWrapper;
    }

    public void setItemsWrapper(ItemsWrapper itemsWrapper) {
        this.itemsWrapper = itemsWrapper;
    }
}

