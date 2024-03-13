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
public class Comment implements Serializable {
    @Id
    private String id;
    private String userId;
    private String hotelId;
    private String comment;
    private Long date;
    private boolean isActive;
    private Long createAt;
    private Long updateAt;

}
