package lodeinfo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @RequestMapping(
            value = "/test",
            method = RequestMethod.POST
    )
    public HttpStatus showRequest(@RequestBody List<Object> obj) {
        System.out.println(obj);
        return HttpStatus.OK;
    }
}
