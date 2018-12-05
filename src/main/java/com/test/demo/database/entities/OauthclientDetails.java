package com.test.demo.database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name="oauth_client_details")
@Entity
public class OauthclientDetails implements Serializable {
    String clientid;
    String resourceids;
    String clientsecret;
    String scope;
    String authorizedgranttypes;
    String webserverredirecturi;
    String authorities;
    int accesstokenvalidity;
    int refreshtokenvalidity;
    String additionalinformation;
    String autoapprove;

    @Column(name="client_id")
    @Id
    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    @Column(name="resource_ids")
    public String getResourceids() {
        return resourceids;
    }

    public void setResourceids(String resourceids) {
        this.resourceids = resourceids;
    }

    @Column(name="client_secret")
    public String getClientsecret() {
        return clientsecret;
    }

    public void setClientsecret(String clientsecret) {
        this.clientsecret = clientsecret;
    }

    @Column(name="scope")
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Column(name="authorized_grant_types")
    public String getAuthorizedgranttypes() {
        return authorizedgranttypes;
    }

    public void setAuthorizedgranttypes(String authorizedgranttypes) {
        this.authorizedgranttypes = authorizedgranttypes;
    }

    @Column(name="web_server_redirect_uri")
    public String getWebserverredirecturi() {
        return webserverredirecturi;
    }

    public void setWebserverredirecturi(String webserverredirecturi) {
        this.webserverredirecturi = webserverredirecturi;
    }

    @Column(name="authorities")
    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    @Column(name="access_token_validity")
    public int getAccesstokenvalidity() {
        return accesstokenvalidity;
    }

    public void setAccesstokenvalidity(int accesstokenvalidity) {
        this.accesstokenvalidity = accesstokenvalidity;
    }

    @Column(name="refresh_token_validity")
    public int getRefreshtokenvalidity() {
        return refreshtokenvalidity;
    }

    public void setRefreshtokenvalidity(int refreshtokenvalidity) {
        this.refreshtokenvalidity = refreshtokenvalidity;
    }

    @Column(name="additional_information")
    public String getAdditionalinformation() {
        return additionalinformation;
    }

    public void setAdditionalinformation(String additionalinformation) {
        this.additionalinformation = additionalinformation;
    }

    @Column(name="autoapprove")
    public String getAutoapprove() {
        return autoapprove;
    }

    public void setAutoapprove(String autoapprove) {
        this.autoapprove = autoapprove;
    }
}
