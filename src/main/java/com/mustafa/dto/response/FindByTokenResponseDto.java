package com.mustafa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindByTokenResponseDto {

    private String id;
    private String username;
    private String email;
    private String phone;
    private String avatar;
    private String address;
    private String about;

}
