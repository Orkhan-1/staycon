package com.staycon.service;

public interface EmailService {

    void sendVerificationEmail (String EmailAdress, String token);
}
