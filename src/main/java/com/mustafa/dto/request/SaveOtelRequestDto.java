package com.mustafa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveOtelRequestDto {
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
