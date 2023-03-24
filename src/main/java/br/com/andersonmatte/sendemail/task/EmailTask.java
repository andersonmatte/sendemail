package br.com.andersonmatte.sendemail.task;

import br.com.andersonmatte.sendemail.dto.EntradaDto;
import br.com.andersonmatte.sendemail.dto.RetornoDto;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.TimerTask;

public class EmailTask extends TimerTask {

    public EntradaDto entradaDto;

    public RetornoDto retornoDto;

    public EmailTask(EntradaDto entradaDto) {
        this.entradaDto = entradaDto;
    }

    public void run() {
        retornoDto = new RetornoDto();
        // Envie o email aqui usando o código do exemplo anterior
        setRetornoDto(sendEmail(entradaDto));
    }

    public RetornoDto getRetornoDto() {
        return retornoDto;
    }

    public void setRetornoDto(RetornoDto retornoDto) {
        this.retornoDto = retornoDto;
    }

    public RetornoDto sendEmail(EntradaDto entradaDto) {
        RetornoDto retornoDto = new RetornoDto();
        // Informações da conta do Outlook
        String username = "Coloque aqui o seu email";
        String password = "Coloque aqui a sua senha";
        // Configurações para o servidor SMTP do Outlook
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");
        // Cria uma sessão de email
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            // Cria uma mensagem de email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(entradaDto.to));
            message.setSubject(entradaDto.subject);
            message.setText(entradaDto.body);
            // Envia o email
            Transport.send(message);
            retornoDto.setRetorno("Email enviado com sucesso para: " + entradaDto.to);
        } catch (MessagingException e) {
            retornoDto.setRetorno("Erro ao enviar email para: " + entradaDto.to);
            throw new RuntimeException(e);
        }
        return retornoDto;
    }

}

