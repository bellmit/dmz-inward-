package com.dmz.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by dmz on 2016/3/29.
 */
@Controller
public class UploadFile {
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public String handleFormUpload(@RequestParam("file") MultipartFile file, ModelMap model) {
        System.out.println(file.getSize());
        model.addAttribute("success","yes");
        return "success";
    }
}
