package com.nttdata.api.bootcoin.account.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Unwrapped.Nullable;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class BootCoinAccount {

    @Id
    private String id;
    private String clientId;
    private String phone;
    @Email
    private String email;
    private Boolean seller;
    private String imei;
    @Nullable
    private LocalDateTime membershipDate;
    private double balance;
    @Nullable
    private String cardNumber;

}
