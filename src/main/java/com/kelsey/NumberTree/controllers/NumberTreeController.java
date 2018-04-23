package com.kelsey.NumberTree.controllers;

import com.kelsey.NumberTree.services.ChildNodeRepository;
import com.kelsey.NumberTree.services.FactoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class NumberTreeController {
    @Autowired
    FactoryRepository factories;

    @Autowired
    ChildNodeRepository childNodes;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String tree(Model model) {


        return "tree";
    }
}
