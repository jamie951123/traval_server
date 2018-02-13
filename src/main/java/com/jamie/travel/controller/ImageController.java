package com.jamie.travel.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jamie.travel.image.service.ImageService;
import com.jamie.travel.jwt.security.TokenObject;

@RequestMapping(value = TokenObject.MAINAPI + "/authentication/images")
@Controller
public class ImageController {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ImageService imageService;
	
	@Transactional(rollbackFor = Exception.class)
	@GetMapping(value = "/test", produces="text/plain;charset=UTF-8")
	public @ResponseBody String test(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "ImageController";
	}
	
}
