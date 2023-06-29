package com.shaoqin.ez_take_out.service.impl;

import com.shaoqin.ez_take_out.service.TwilioService;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.Service;
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
    public String ACCOUNT_SID;

    @Value("${twilio.token}")
    public String AUTH_TOKEN;

    private Service getService(HttpSession session) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Service service = (Service) session.getAttribute("twilioService");
        if (service == null) {
            service = Service.creator("Ez Takeout Login").create();
            session.setAttribute("twilioService", service);
        }
        return service;
    }

    @Override
    public void sendVerificationSMS(String phone, String verificationCode, HttpSession session) {
        Service service = this.getService(session);
        String sid = service.getSid();
        Verification verification = Verification.creator(
                sid,
                phone,
                "sms"
        ).create();
    }

    @Override
    public boolean checkVerificationCode(String code, String phone, HttpSession session) {
        Service service = this.getService(session);
        String sid = service.getSid();
        VerificationCheck verificationCheck = VerificationCheck
                .creator(sid)
                .setTo(phone)
                .setCode(code)
                .create();
        String status = verificationCheck.getStatus();
        return VerificationStatus.APPROVED.name().equalsIgnoreCase(status);
    }

}
