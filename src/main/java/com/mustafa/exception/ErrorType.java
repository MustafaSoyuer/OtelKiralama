package com.mustafa.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorType {

    INTERNAL_ERROR(1000,"Sunucuda beklenmeyen hata oluştu, lütfen tekrar deneyiniz",HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(1001,"Girilen parametreler hatalıdır. Lütfen düzelterek tekrar deneyiniz",HttpStatus.BAD_REQUEST),
    ERROR_DUPLICATE_USERNAME(2000,"Bu Kullanıcı adı zaten kayıtlıdır. Lütfen değiştirerek tekrar deneyiniz.",HttpStatus.BAD_REQUEST),
    ERROR_CREATE_TOKEN(1003,"Token oluşturma hatası. Lütfen değiştirerek tekrar deneyiniz.",HttpStatus.INTERNAL_SERVER_ERROR),
    ERROR_INVALID_LOGIN_PARAMETER(2001,"Kullanıcı adı ya da şifre hatalıdır. Lütfen düzelterek tekrar deneyiniz.",HttpStatus.BAD_REQUEST),
    LOGIN_ERROR(4110,"Kullanici adi veya sifre hatalidir...",HttpStatus.BAD_REQUEST),
    TOKEN_NOT_CREATED(4115,"Token olusturulamadi..." , HttpStatus.BAD_REQUEST ),
    ACCOUNT_NOT_ACTIVE(4116,"Hesabiniz aktif degildir..." , HttpStatus.FORBIDDEN ),
    USER_NOT_FOUND(4112,"Böyle bir kullanici bulunamadi...",HttpStatus.BAD_REQUEST),
    ACTIVATION_CODE_ERROR(4113,"Aktivasyon kod hatasi..." ,HttpStatus.BAD_REQUEST ),
    USERNAME_DUPLICATE(4211,"Böyle bir kullanici adi sistemde mevcut...", HttpStatus.BAD_REQUEST),
    USER_NOT_CREATED(4213,"Kullanıcı oluşturulamadı..." ,HttpStatus.BAD_REQUEST ),
    INVALID_TOKEN(4214,"Geçersiz token" ,HttpStatus.BAD_REQUEST),
    OTEL_NOT_FOUND(4215,"Kullanıcı bulunamadı..." ,HttpStatus.BAD_REQUEST),




    ;




    int code;
    String message;
    HttpStatus httpStatus;
}
