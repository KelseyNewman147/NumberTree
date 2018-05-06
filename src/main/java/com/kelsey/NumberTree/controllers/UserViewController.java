package com.kelsey.NumberTree.controllers;

import com.kelsey.NumberTree.entities.Factory;
import com.kelsey.NumberTree.entities.RootNode;
import com.kelsey.NumberTree.services.CorsFilter;
import com.kelsey.NumberTree.services.FactoryRepository;
import com.kelsey.NumberTree.services.RootNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserViewController {
    @Autowired
    RootNodeRepository rootNodeRepo;

    @Autowired
    FactoryRepository factories;

    @Bean
    CorsFilter corsFilter() {
        CorsFilter filter = new CorsFilter();
        return filter;
    }


    @PostConstruct
    public void init() {
        if (rootNodeRepo.count() == 0) {
            RootNode rootNode = new RootNode(1);
            rootNodeRepo.save(rootNode);
            //ToDo: remove once root node can be viewed in UI
            Factory factory = new Factory(rootNode, "Tommy", 15, 55);
            List<Factory> rootFactories = new ArrayList<>();
            rootFactories.add(factory);
            rootNode.setFactories(rootFactories);
            factories.save(factory);

        }
    }

    //retrieve view
    @RequestMapping(path = "/")
    public String tree() {
//        RootNode rootNode = rootNodeRepo.findById(1);
//        //model.addAttribute("rootNode", rootNode);
//        List<Factory> rootFactories = rootNode.getFactories();
//        model.addAttribute("factories", rootFactories);

        return "index.html";
    }
}
