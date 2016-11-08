package lodeinfo.controller;

import lodeinfo.model.VipEntity;
import lodeinfo.repository.VipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/vip")
public class VipController {

    @Autowired
    VipRepository vipRepository;

//    @RequestMapping(
//            value = "/searchVipCount",
//            method = RequestMethod.GET
//    )
//    public int searchVipCount(@RequestParam int spent, @RequestParam int payments) {
//        return vipRepository.getVipCount(spent, payments);
//    }

    @RequestMapping(
            value = "/searchVipList",
            method = RequestMethod.GET
    )
    public List<VipEntity> searchVipList(@RequestParam int spent, @RequestParam int payments) {
        return vipRepository.getVipList(spent, payments);
    }

}
