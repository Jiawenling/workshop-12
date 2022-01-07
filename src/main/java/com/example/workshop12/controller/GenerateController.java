package com.example.workshop12.controller;

import main.java.com.example.workshop12.model.Generate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class GenerateController {

    private static final Logger logger = LoggerFactory.getLogger(GenerateController.class);
    @GetMapping("/generate")
    public String showGenerateForm(Model model){
        Generate generate = new Generate();
//        generate.setNumberVal(20);
        model.addAttribute("generate", generate);
        return "generate";
    }

    @PostMapping("/generate")
    public String generateNumbers(@ModelAttribute Generate generate, Model model){
        try{

        logger.info("From the form"+ generate.getNumberVal());
        int numberRandomNumbers = generate.getNumberVal();
        if (numberRandomNumbers > 10){
            throw new main.java.com.example.workshop12.error.RandomNumberException();
        }
        List<Integer> listToShuffle = new ArrayList<>();
        for (int i = 0; i <= 10; i++){
            listToShuffle.add(i);
        }
        Collections.shuffle(listToShuffle);
        List<String> selectedImg = new ArrayList<>();
        for(int i = 0; i < generate.getNumberVal(); i++) {
            int imgNum = listToShuffle.get(i);
            String imgPic = "number" + imgNum + ".jpg";
            selectedImg.add(imgPic);
        }
        model.addAttribute("randNumsResult", selectedImg.toArray());}

        catch(main.java.com.example.workshop12.error.RandomNumberException e){
            model.addAttribute("errorMessage", "OMG exceed 10 already !");
            return "error";
        }
        return "result";
    }
}
