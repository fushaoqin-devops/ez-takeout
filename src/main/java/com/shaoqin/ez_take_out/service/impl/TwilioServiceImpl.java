package com.shaoqin.ez_take_out.service.impl;

import com.shaoqin.ez_take_out.service.TwilioService;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;

/**
 * ClassName: TwilioServiceImpl
 * Package: com.shaoqin.ez_take_out.service.impl
 * Description:
 * Author Shaoqin
 * Create 6/28/23 4:23 PM
 * Version 1.0
 */
enum VerificationStatus {
    PENDING,
    APPROVED,
    CANCELED
}

@org.springframework.stereotype.Service
public class TwilioServiceImpl implements TwilioService {

    @Value("${twilio.sid}")
    private String ACCOUNT_SID;

    @Value("${twilio.token}")
    private String AUTH_TOKEN;

    @Value("${twilio.service}")
    private String SERVICE_ID;

    private void init() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    @Override
    public void sendVerificationSMS(String phone, String verificationCode) {
        this.init();
        Verification verification = Verification.creator(
                SERVICE_ID,
                phone,
                "sms"
        ).create();
    }

    @Override
    public boolean checkVerificationCode(String code, String phone) {
        this.init();
        VerificationCheck verificationCheck = VerificationCheck
                .creator(SERVICE_ID)
                .setTo(phone)
                .setCode(code)
                .create();
        String status = verificationCheck.getStatus();
        return VerificationStatus.APPROVED.name().equalsIgnoreCase(status);
    }

}
