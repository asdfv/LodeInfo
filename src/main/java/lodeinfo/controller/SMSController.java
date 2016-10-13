package lodeinfo.controller;

import lodeinfo.model.SMSEntity;
import lodeinfo.repository.SMSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/sms")
public class SMSController {

    @Autowired
    SMSRepository smsRepository;

    @RequestMapping(
            value = "/getSMS",
            method = RequestMethod.GET
    )
    List<SMSEntity> getSMS() {
        return smsRepository.getSMS();
    }
}
