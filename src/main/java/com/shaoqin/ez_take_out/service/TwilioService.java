package com.shaoqin.ez_take_out.service;

import jakarta.servlet.http.HttpSession;

/**
 * ClassName: TwilioService
 * Package: com.shaoqin.ez_take_out.service
 * Description:
 * Author Shaoqin
 * Create 6/28/23 4:22 PM
 * Version 1.0
 */
public interface TwilioService {

    public void sendVerificationSMS(String phone, String verificationCode, HttpSession session);

    public boolean checkVerificationCode(String code, String phone, HttpSession session);

}
