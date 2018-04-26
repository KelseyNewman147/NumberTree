package com.kelsey.NumberTree.entities;

import javax.persistence.*;
import javax.persistence.criteria.Root;
import java.util.List;

@Entity
@Table(name="factories")
public class Factory {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false, unique = true)
    String name;

    @ManyToOne
    RootNode rootNode;

    @OneToMany(mappedBy = "factory")
    List<ChildNode> childNodes;

    public Factory() {
    }

    public Factory(String name) {
        this.name = name;
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
