package com.mydemoproject.payment.dto.request;

import com.mydemoproject.payment.entity.Card;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    Long userId;

    @NotBlank(message = "Ad boş ola bilməz ")
    String firstName;

    @NotBlank
    String username;

    @NotBlank(message = "Soyad boş ola bilməz ")
    String surname;

    @NotBlank(message = "Şifrə boş ola bilməz ")
    String password;

    @NotBlank
    String address;

    @Email(message = "Düzgün emaili daxil edin !")
    @NotBlank(message = "boş ola bilməz ")
    String email;

    @NotBlank(message = "Fin boş ola bilməz !")
    String docFin;

    @NotBlank(message = "Telefon nömrəsi düzgün daxil edilməyib !")
    @Size(min = 11, max = 12)
    String phoneNumber;

    List<Card> cards;

}
