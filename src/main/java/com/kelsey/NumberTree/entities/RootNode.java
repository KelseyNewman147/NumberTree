package com.kelsey.NumberTree.entities;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "root_node")
public class RootNode {
    @Id
    @GeneratedValue
    int id;

    @OneToMany(mappedBy = "rootNode")
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
