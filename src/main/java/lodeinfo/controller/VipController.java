package lodeinfo.controller;

import lodeinfo.repository.VipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/vip")
public class VipController {

    @Autowired
    VipRepository vipRepository;

    @RequestMapping(
            value = "/searchVip",
            method = RequestMethod.GET
    )
    public int searchVip(@RequestParam int spent, @RequestParam int payments) {
        return vipRepository.getVipCount(spent, payments);
    }

}
