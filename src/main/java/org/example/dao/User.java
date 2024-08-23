package org.example.dao;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User implements Serializable{

    private  static final long serialVersionId = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer userId;

    @Column(columnDefinition = "varchar(255) NOT NULL COMMENT 'The email of the user'")
    String email;

    @Column(columnDefinition = "varchar(255) NOT NULL COMMENT 'The name of the user'")
    String name;

    @Column(columnDefinition = "varchar(255) NOT NULL COMMENT 'The password of the user'")
    String pwd;

    public Integer getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}