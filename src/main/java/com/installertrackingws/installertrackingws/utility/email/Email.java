package com.installertrackingws.installertrackingws.utility.email;

import com.installertrackingws.installertrackingws.AppInfo;
import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.bean.user.UserBn;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.internet.MimeMessage;

public class Email {

    public Response sendRegistrationSuccessEmail(JavaMailSender javaMailSender,UserBn user, String ip) {

        Response response = new Response();
        response.setCode(200);
        response.setMsg("Mail send successful !");

        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(user.getUserEmail());
            helper.setText("<html><body><h3>Click the bellow link to complete to your registration.</h3><br><a href='http://"+AppInfo.getClientIp()+":"+AppInfo.getClientPort()+"/#/email-verification/"+user.getToken()+"' >http://"+AppInfo.getClientIp()+":"+AppInfo.getClientPort()+"/#/email-verification/"+user.getToken()+"</a><body></html>",true);
            helper.setSubject("registration confirmation mail");
            javaMailSender.send(message);

        }catch (Exception e){
            e.printStackTrace();
            response.setCode(400);
            response.setMsg("Mail send exception successful !");
        }

        return response;

    }

    public Response sendForgotPasswordLinkMail(JavaMailSender javaMailSender, Request request, String ip) {

        Response response = new Response();

        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(request.getUserBn().getUserEmail());
            helper.setText("<html><body><h3>Click the bellow link to change your password.</h3><br><a href='http://"+AppInfo.getClientIp() +":"+AppInfo.getClientPort()+"/#/forgot-password/"+request.getUserBn().getToken()+"' >http://"+AppInfo.getClientIp()+":"+AppInfo.getClientPort()+"/#/forgot-password/"+request.getUserBn().getToken()+"</a><body></html>",true);
            helper.setSubject("Password change mail");
            javaMailSender.send(message);

            response.setCode(200);
            response.setMsg("Link sent successfully !");

        }catch (Exception e){
            e.printStackTrace();
            response.setCode(400);
            response.setMsg("Exception occurred !");
        }

        return response;

    }

    public Response sendWoAssignMailToUser(JavaMailSender javaMailSender, Request request) {

        Response response = new Response();

        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(request.getWoAssignBn().getAssignUserMail());
            helper.setText("<html><body><h3>A new work order has been assign to you !</h3><body></html>",true);
            helper.setSubject("Work order assign mail");
            javaMailSender.send(message);

            response.setCode(200);
            response.setMsg("Link sent successfully !");

        }catch (Exception e){
            e.printStackTrace();
            response.setCode(400);
            response.setMsg("Exception occurred !");
        }

        return response;

    }
}
