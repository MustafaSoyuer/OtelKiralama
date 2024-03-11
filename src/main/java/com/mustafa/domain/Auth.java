package com.mustafa.domain;

import com.mustafa.utility.enums.State;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Auth implements Serializable {
    @Id
    UUID id;
    String username;
    String password;
    String email;
    Long createAt;
    Long updateAt;
    State state;



}
