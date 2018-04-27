package com.kelsey.NumberTree.controllers;

import com.kelsey.NumberTree.entities.ChildNode;
import com.kelsey.NumberTree.entities.Factory;
import com.kelsey.NumberTree.entities.RootNode;
import com.kelsey.NumberTree.services.ChildNodeRepository;
import com.kelsey.NumberTree.services.FactoryRepository;
import com.kelsey.NumberTree.services.RootNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@RestController
public class NumberTreeController {
    @Autowired
    RootNodeRepository rootNodeRepo;

    @Autowired
    FactoryRepository factories;

    @Autowired
    ChildNodeRepository childNodes;

    //create initial factory and child if null
    @PostConstruct
    public void init() {
        if (rootNodeRepo.count() == 0) {
            RootNode rootNode = new RootNode(1);
            rootNodeRepo.save(rootNode);
            Factory factory = new Factory(rootNode, "Tommy", 15, 55);
            List<Factory> rootFactories = new ArrayList<>();
            rootFactories.add(factory);
            rootNode.setFactories(rootFactories);
            factories.save(factory);

        }
    }

    //retrieve all existing factories and their children
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<Factory> tree() {
          RootNode rootNode = rootNodeRepo.findById(1);
          List<Factory> rootFactories = rootNode.getFactories();
        return rootFactories;
    }

    //add new factory
    @RequestMapping(path = "/factory", method = RequestMethod.POST)
    public void createFactory(String name, int rangeLow, int rangeHigh) {
        Factory factory = new Factory();
        factory.setName(name);
        factory.setRangleLow(rangeLow);
        factory.setRangeHigh(rangeHigh);
        factories.save(factory);
    }
    //create new generation of children through user input
    @RequestMapping(path = "/child", method = RequestMethod.POST)
    public void createChildren(int factoryId, int numberOfChildren, int rangeLow, int rangeHigh) {
        //"Factory" needs to be filled by factory selected by user
        Factory factory = factories.findFactoryById(factoryId);

        if (factory.getChildNodes() != null) {
            List<ChildNode> currentChildren = factory.getChildNodes();
            currentChildren.forEach(c -> childNodes.delete(c));
        }
        for (int i = 0; i < numberOfChildren; i ++) {
            Random r = new Random();
            ChildNode childNode = new ChildNode();
            childNode.setNumber(r.nextInt(rangeHigh - rangeLow) + rangeLow);
            childNode.setFactory(factory);
            childNodes.save(childNode);
        }
    }

    //remove factory and its children
    @RequestMapping(path = "/factory/{id}", method = RequestMethod.DELETE)
    public void deleteFactory(@PathVariable("id") int factoryId) {
        Factory factory = factories.findFactoryById(factoryId);
        factory.getChildNodes().forEach(c -> childNodes.delete(c));
        factories.delete(factory);
    }

    //ToDO: adjust name of factory
    //factory.setName(name)
    //ToDo: adjust range
//    factory.setRangeHigh(rangeHigh);
//    factory.setRangleLow(rangeLow);

}
