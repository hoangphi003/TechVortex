package com.techvortex.vortex.service;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class MailService {
    private @Autowired JavaMailSender sender;

    private final Queue<MimeMessage> queue = new LinkedList<>();

    public void add(MimeMessage message) {
        queue.add(message);
    }

    public MimeMessage create() {
        return sender.createMimeMessage();
    }
}
