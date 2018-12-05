package com.test.demo.database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="oauth_access_token")
public class OauthclientToken implements Serializable {
    String tokenid;
    char token;
    String authenticationid;
    String username;
    String clientid;
    String authentication;
    String refreshtoken;

    @Column(name="token_id")
    @Id
    public String getTokenid() {
        return tokenid;
    }

    public void setTokenid(String tokenid) {
        this.tokenid = tokenid;
    }

    @Column(name="token",columnDefinition = "longtext")
    public char getToken() {
        return token;
    }

    public void setToken(char token) {
        this.token = token;
    }

    @Column(name="authentication_id")
    public String getAuthenticationid() {
        return authenticationid;
    }

    public void setAuthenticationid(String authenticationid) {
        this.authenticationid = authenticationid;
    }

    @Column(name="user_name")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name="client_id")
    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    @Column(name="authentication",columnDefinition = "longtext")
    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    @Column(name="refresh_token",columnDefinition = "longtext")
    public String getRefreshtoken() {
        return refreshtoken;
    }

    public void setRefreshtoken(String refreshtoken) {
        this.refreshtoken = refreshtoken;
    }
}
