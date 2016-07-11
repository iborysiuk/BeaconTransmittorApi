package com.beacon.transmitter.api.repository.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Yuriy on 2016-07-07.
 */

@Entity
@Table(name = "users")
public class Users implements Serializable {

    @SerializedName(value = "username")
    @Expose
    @Column(name = "username")
    private String username;

    @SerializedName(value = "email")
    @Expose
    @Column(name = "email")
    private String email;

    @SerializedName(value = "password")
    @Expose(serialize = false)
    @Column(name = "password")
    private String password;

    @SerializedName(value = "isActivated")
    @Expose
    @Column(name = "activated")
    private boolean isActivated;

    @Id
    @GeneratedValue
    private Long id;

    public Users() {
    }

    private Users(String userName, String email, String password, boolean isActivated) {
        this.username = userName;
        this.email = email;
        this.password = password;
        this.isActivated = isActivated;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public final static class Builder {

        private String userName;
        private String email;
        private String password;
        private boolean isActivated;

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setIsActivated(boolean isActivated) {
            this.isActivated = isActivated;
            return this;
        }

        public Users createUsers() {
            return new Users(userName, email, password, isActivated);
        }
    }

    @Override
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
