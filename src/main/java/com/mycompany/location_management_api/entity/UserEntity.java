package com.mycompany.location_management_api.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "USER_TABLE")
@Data // getter // setter
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "FULL_NAME")
    private String fullName;

    private String email;

    private String password;

    private String mobileNumber;

}
