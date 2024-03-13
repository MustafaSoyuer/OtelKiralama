package com.mustafa.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Ozellik implements Serializable {
    @Id
    private String id;
    private String parentId;
    private String parentName;
    private String name;
    private Long count;
    private Boolean active;
}
