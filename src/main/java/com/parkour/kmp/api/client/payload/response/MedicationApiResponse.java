package com.parkour.kmp.api.client.payload.response;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;

@XmlRootElement(name = "item")
@Getter
@XmlType(propOrder = {"entpName", "itemName", "itemSeq", "efcyQesitm", "useMethodQesitm", "atpnWarnQesitm", "atpnQesitm", "intrcQesitm", "seQesitm", "depositMethodQesitm"})
public class MedicationApiResponse {

    @XmlElement(name = "entpName")
    private String entpName;

    @XmlElement(name = "itemName")
    private String itemName;

    @XmlElement(name = "itemSeq")
    private String itemSeq;

    @XmlElement(name = "efcyQesitm")
    private String efcyQesitm;

    @XmlElement(name = "useMethodQesitm")
    private String useMethodQesitm;

    @XmlElement(name = "atpnWarnQesitm")
    private String atpnWarnQesitm;

    @XmlElement(name = "atpnQesitm")
    private String atpnQesitm;

    @XmlElement(name = "intrcQesitm")
    private String intrcQesitm;

    @XmlElement(name = "seQesitm")
    private String seQesitm;

    @XmlElement(name = "depositMethodQesitm")
    private String depositMethodQesitm;

    public MedicationApiResponse() {}

    public MedicationApiResponse(String entpName, String itemName, String itemSeq, String efcyQesitm, String useMethodQesitm, String atpnWarnQesitm, String atpnQesitm, String intrcQesitm, String seQesitm, String depositMethodQesitm) {
        this.entpName = entpName;
        this.itemName = itemName;
        this.itemSeq = itemSeq;
        this.efcyQesitm = efcyQesitm;
        this.useMethodQesitm = useMethodQesitm;
        this.atpnWarnQesitm = atpnWarnQesitm;
        this.atpnQesitm = atpnQesitm;
        this.intrcQesitm = intrcQesitm;
        this.seQesitm = seQesitm;
        this.depositMethodQesitm = depositMethodQesitm;
    }

    @Override
    public String toString() {
        return "MedicationApiResponse{" +
                "itemName='" + itemName + '\'' +
                ", otherField='" + entpName + '\'' +
                '}';
    }
}
