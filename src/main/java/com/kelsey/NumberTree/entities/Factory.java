package com.kelsey.NumberTree.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="factories")
public class Factory {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false, unique = true)
    String name;

    @Column
    List<ChildNode> childNodes;

    public Factory() {
    }

    public Factory(int id, String name, List<ChildNode> childNodes) {
        this.id = id;
        this.name = name;
        this.childNodes = childNodes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChildNode> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<ChildNode> childNodes) {
        this.childNodes = childNodes;
    }
}
