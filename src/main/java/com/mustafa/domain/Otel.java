package com.mustafa.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Otel implements Serializable {
    @Id
    private String id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private Double lon; // longitude-boylam
    private Double lat; // latitude-enlem
    private String totalPoint;
    private String girisSaati;
    private String cikisSaati;
    private String genelAciklama;
    private Long createAt;
    private Long updateAt;

}
