package com.parkour.kmp.api.client.payload.response;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;

@Getter
@XmlRootElement(name = "response")
@XmlType(propOrder = {"body"})
public class ApiResponse {


    @XmlElement(name = "body")
    private Body body;
}