package lodeinfo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(
        value = "/security"
)
public class SecurityController {

// This is a useful trick in a Spring Security application.
// If the "/user" resource is reachable then it will return the currently authenticated user (an Authentication),
// and otherwise Spring Security will intercept the request and send a 401 response through an AuthenticationEntryPoint.
    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
