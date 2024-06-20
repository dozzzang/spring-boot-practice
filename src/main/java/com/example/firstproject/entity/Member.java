package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long Id;

    @Column
    private String email;

    @Column
    private String password;

    public Member(Long Id,String email,String password) {
        this.Id = Id;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Member{" +
                "Id=" + Id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
