package com.test.demo.database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="oauth_code")
public class OauthCode implements Serializable {
    String code;
    char authentication;

    @Id
    @Column(name="code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name="authentication")
    public char getAuthentication() {
        return authentication;
    }

    public void setAuthentication(char authentication) {
        this.authentication = authentication;
    }
}
