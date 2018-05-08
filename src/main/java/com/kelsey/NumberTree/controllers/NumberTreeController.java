package com.kelsey.NumberTree.controllers;

import com.kelsey.NumberTree.entities.ChildNode;
import com.kelsey.NumberTree.entities.Factory;
import com.kelsey.NumberTree.entities.RootNode;
import com.kelsey.NumberTree.services.ChildNodeRepository;
import com.kelsey.NumberTree.services.CorsFilter;
import com.kelsey.NumberTree.services.FactoryRepository;
import com.kelsey.NumberTree.services.RootNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
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
            Factory factory = new Factory(rootNode, "Tommy", 15, 55);
            List<Factory> rootFactories = new ArrayList<>();
            rootFactories.add(factory);
            rootNode.setFactories(rootFactories);
            factories.save(factory);

        }
    }

    //retrieve all existing factories and their children from root node
    @RequestMapping(path = "/api", method = RequestMethod.GET)
    public RootNode tree() {
        RootNode rootNode = rootNodeRepo.findById(1);
        return rootNode;
    }

    //add new factory
    @RequestMapping(path = "/api/create-factory", method = RequestMethod.POST)
    public void createFactory(String name, int rangeLow, int rangeHigh) {
        RootNode rootNode = rootNodeRepo.findById(1);
        Factory factory = new Factory();
        factory.setRootNode(rootNode);
        if ((rangeHigh > rangeLow) && !name.isEmpty()) {
            factory.setName(name);
            factory.setRangeLow(rangeLow);
            factory.setRangeHigh(rangeHigh);
            factories.save(factory);
        } else if (rangeHigh < rangeLow){
            throw new java.lang.RuntimeException("Upper bound should be higher than the lower bound.");
        } else {
            throw new java.lang.RuntimeException("Name cannot be null.");
        }
    }

    //create new generation of children through user input
    @RequestMapping(path = "/api/create-children", method = RequestMethod.POST)
    public void createChildren(int factoryId, int numberOfChildren) {
        Factory factory = factories.findFactoryById(factoryId);
        int rangeHigh = factory.getRangeHigh();
        int rangeLow = factory.getRangeLow();
        if (numberOfChildren <= 15) {
            if (factory.getChildNodes() != null) {
                List<ChildNode> currentChildren = factory.getChildNodes();
                currentChildren.forEach(c -> childNodes.delete(c));
            }

            for (int i = 0; i < numberOfChildren; i++) {
                Random r = new Random();
                ChildNode childNode = new ChildNode();
                childNode.setNumber(r.nextInt(rangeHigh - rangeLow) + rangeLow);
                childNode.setFactory(factory);
                childNodes.save(childNode);
            }
        } else {
            throw new java.lang.RuntimeException("Sorry, no more than 15 kids. Calm down, please.");
        }
    }

    //remove factory and its children
    @RequestMapping(path = "/api/delete-factory/{id}", method = RequestMethod.DELETE)
    public void deleteFactory(@PathVariable("id") int factoryId) {
        Factory factory = factories.findFactoryById(factoryId);
        factory.getChildNodes().forEach(c -> childNodes.delete(c));
        factories.delete(factory);
    }

    //rename factory
    @RequestMapping(path = "/api/rename-factory/{id}", method = RequestMethod.POST)
    public void renameFactory(@PathVariable("id") int factoryId, String newName) {
        Factory factory = factories.findFactoryById(factoryId);
        if (!newName.isEmpty()) {
            factory.setName(newName);
            factories.save(factory);
        } else {
            throw new java.lang.RuntimeException("Name cannot be null.");
        }

    }


    //adjust factory's range
    @RequestMapping(path = "/api/adjust-range/{id}", method = RequestMethod.POST)
    public void adjustRange(@PathVariable("id") int factoryId, int newRangeHigh, int newRangeLow) {
        if (newRangeHigh > newRangeLow){
            Factory factory = factories.findFactoryById(factoryId);
            factory.setRangeHigh(newRangeHigh);
            factory.setRangeLow(newRangeLow);
            factories.save(factory);
        } else {
            throw new java.lang.RuntimeException("Upper bound should be higher than the lower bound.");
        }
    }

}
