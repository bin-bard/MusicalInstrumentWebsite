package com.instrumentwebsite.musicalinstruments.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {
    @Id // username là khóa chính và duy nhất
    private String username;

    private String password;

    private String resetToken;
    private LocalDateTime resetTokenExpiry;

    @OneToOne(mappedBy = "account") // Mối quan hệ một-một với Users
    private Users user; // Tham chiếu đến Users

    // Constructors
    public Account() {}

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Users getUser () {
        return user;
    }

    public void setUser (Users user) {
        this.user = user;
    }

    public String getResetToken() {
        return resetToken;
    }
    
    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }
    
    public LocalDateTime getResetTokenExpiry() {
        return resetTokenExpiry;
    }
    
    public void setResetTokenExpiry(LocalDateTime resetTokenExpiry) {
        this.resetTokenExpiry = resetTokenExpiry;
    }

    // Xác nhận mail
    private String verificationToken;
    private LocalDateTime verificationTokenExpiry;
    private boolean isVerified = false;

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

    public LocalDateTime getVerificationTokenExpiry() {
        return verificationTokenExpiry;
    }

    public void setVerificationTokenExpiry(LocalDateTime verificationTokenExpiry) {
        this.verificationTokenExpiry = verificationTokenExpiry;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }
}