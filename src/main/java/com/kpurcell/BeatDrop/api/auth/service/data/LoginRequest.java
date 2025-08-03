package com.kpurcell.BeatDrop.api.auth.service.data;

public class LoginRequest
{

    public LoginRequest(String emailAddress, String password)
    {
        this.emailAddress = emailAddress;
        this.password = password;
    }

    private String emailAddress;

    private String password;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
