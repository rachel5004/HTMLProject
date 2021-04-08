<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
        System.setProperty("mail.smtp.starttls.enable", "true"); // gmail은 무조건 true 고정
        System.setProperty("mail.smtp.auth", "true"); // gmail은 무조건 true 고정
        System.setProperty("mail.smtp.host", "smtp.gmail.com"); // smtp 서버 주소
        System.setProperty("mail.smtp.port", "587"); // gmail 포트
        //구글 인증
        Authenticator auth = new MyAuthentication();
        Message msg = new MimeMessage(Session.getDefaultInstance(System.getProperties(), auth));
        //받는사람
        InternetAddress[] tos = InternetAddress.parse(to);
        msg.setRecipients(Message.RecipientType.TO, tos);
        //한글을 위한 인코딩
        msg.setHeader("Content-Type", "text/plain; charset=UTF-8");
        //제목
        msg.setSubject((String)mailData.get("subject"));
        msg.setSentDate(new Date());
 
        //첨부파일이 없으면 내용만 전송
        if(null == mailData.get("attachment")){
                 msg.setText((String)mailData.get("body"));
          } else {
            //첨부파일이 있으면
            BodyPart body = new MimeBodyPart();
              BodyPart attachment = (BodyPart)mailData.get("attachment");
              body.setText((String)mailData.get("body"));
              MimeMultipart multipart = new MimeMultipart();
              multipart.addBodyPart(body);
              multipart.addBodyPart(attachment);
              msg.setContent(multipart, "text/plain; charset=UTF-8");
           }
        //전송
        Transport.send(msg);
    }
   
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>