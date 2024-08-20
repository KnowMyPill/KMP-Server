package com.parkour.kmp.api.client.payload.response;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name = "items")
@XmlType(propOrder = {"item"})
@Getter
@Setter
public class ItemsWrapper {

    private List<MedicationApiResponse> item;

    @XmlElement(name = "item")
    public List<MedicationApiResponse> getItem() {
        return item;
    }

    public void setItem(List<MedicationApiResponse> item) {
        this.item = item;
    }
}
