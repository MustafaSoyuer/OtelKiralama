package com.mustafa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OtelFindByIdResponseDto implements Serializable {

    private String id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private Double lon;
    private Double lat;
    private String totalPoint;
    private String girisSaati;
    private String cikisSaati;
    private String genelAciklama;

}
