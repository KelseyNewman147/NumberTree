package com.kelsey.NumberTree.controllers;

import com.kelsey.NumberTree.entities.ChildNode;
import com.kelsey.NumberTree.entities.Factory;
import com.kelsey.NumberTree.entities.RootNode;
import com.kelsey.NumberTree.services.ChildNodeRepository;
import com.kelsey.NumberTree.services.FactoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class NumberTreeController {

    @Autowired
    FactoryRepository factories;

    @Autowired
    ChildNodeRepository childNodes;

    @PostConstruct
    public void init() {

        if (factories.count() == 0) {
            Factory factory = new Factory("Tommy");
            factories.save(factory);
            ChildNode child = new ChildNode( 42, factory);
            childNodes.save(child);
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public HashMap<Factory, List<ChildNode>> tree() {
        RootNode rootNode = new RootNode();
        List<Factory> factoriesList = new ArrayList<>();
        Iterable<Factory> factoriesIt = factories.findAll();
        factoriesIt.forEach(f -> factoriesList.add(f));
        rootNode.setFactories(factoriesList);
        HashMap<Factory, List<ChildNode>> tree = new HashMap<>();
        rootNode.getFactories().forEach(f -> tree.put(f, f.getChildNodes()));

        return tree;
    }
}
