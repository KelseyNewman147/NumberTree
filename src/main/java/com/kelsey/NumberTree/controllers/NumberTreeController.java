package com.kelsey.NumberTree.controllers;

import com.kelsey.NumberTree.entities.NumberTree;
import com.kelsey.NumberTree.services.ChildNodeRepository;
import com.kelsey.NumberTree.services.FactoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.swing.*;

import static com.kelsey.NumberTree.NumberTreeApplication.frame;

@Controller
public class NumberTreeController {
//    @Autowired
//    RootNode rootNode;
    @Autowired
    FactoryRepository factories;

    @Autowired
    ChildNodeRepository childNodes;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String tree(Model model) {
//        RootNode rootNode =
//        model.addAttribute()
        //if (useSystemLookAndFeel) {
            try {
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Couldn't use system look and feel.");
            }
        //}

        //Create and set up the window.
        JFrame frame = frame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new NumberTree());

        //Display the window.
        frame.pack();
        frame.setVisible(true);

        return "tree";
    }
}
