package com.BRAINWAVE_JD_01.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private double amount;
    private String type; // Withdrew/ Deposit / Transfer / Check balance
    private String details; // Mode of transaction


    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
