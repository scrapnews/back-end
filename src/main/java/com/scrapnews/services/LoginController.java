package com.scrapnews.services;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	
	@RequestMapping(value="/zizi", method = RequestMethod.GET)
	@ResponseBody
    public void zizi() {
        System.out.println( "zizi");
    }
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String showIndex() {
        return "Hello world";
    }
	
	@RequestMapping(value="/user", method = RequestMethod.GET, produces = "application/json", headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public Principal user(Principal user) {
		return user;
	}

}
