package com.kelsey.NumberTree.entities;

import javax.persistence.*;

@Entity
@Table(name = "child_nodes")
public class ChildNode {
    @Id
    @GeneratedValue
    int id;

    @Column
    int number;

    @ManyToOne
    Factory factory;

    public ChildNode() {
    }

    public ChildNode(int id, int number) {
        this.id = id;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
