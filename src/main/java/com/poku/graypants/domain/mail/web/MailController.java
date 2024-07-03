package com.poku.graypants.domain.mail.web;

import com.poku.graypants.domain.mail.application.MailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mails")
@AllArgsConstructor
public class MailController {

    MailService mailService;

    @GetMapping("/new-mail/{to}")
    public String sendMail(@PathVariable String to) {
        mailService.sendMail(to, "test", "test");
        return "send mail to " + to;
    }
}
