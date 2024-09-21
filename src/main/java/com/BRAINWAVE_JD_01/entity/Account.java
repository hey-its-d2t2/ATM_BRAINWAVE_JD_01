package com.BRAINWAVE_JD_01.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    private String accountNumber;//Auto Generated
    private String pin; // Hashed pin
    private double balance =100.0; //Default balance for new users


}
