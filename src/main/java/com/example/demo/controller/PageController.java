package com.example.demo.controller;

import java.net.URL;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.InviteCodeService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/v1/invite")
public class PageController {
	  @Autowired
	    private InviteCodeService inviteCodeService;


	 @GetMapping ("/register")
	 
	    public String showRegisterPage(@RequestParam String invite) {
		 System.out.println("code is : "+invite);
//		 return ResponseEntity.ok(Map.of(
//		            "redirect", true,
//		            "targetUrl", "http://127.0.0.1:5500/invite/register.html ",
//		            "message", "You are being redirected"
//		        ));
		 return "redirect:http://127.0.0.1:5500/invite/register.html?massage="+invite; // refers to register.html in templates
	    }
	 
}
