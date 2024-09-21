package com.BRAINWAVE_JD_01.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String password; //Hashed password
    private String address;
    private int age;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Account account;
}
