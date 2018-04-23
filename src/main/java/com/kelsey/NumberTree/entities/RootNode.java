package com.kelsey.NumberTree.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
//@Table(name = "root_node")
public class RootNode {
    int id;
    List<Factory> factories;

    public RootNode() {
    }

    public RootNode(int id, List<Factory> factories) {
        this.id = id;
        this.factories = factories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Factory> getFactories() {
        return factories;
    }

    public void setFactories(List<Factory> factories) {
        this.factories = factories;
    }
}
