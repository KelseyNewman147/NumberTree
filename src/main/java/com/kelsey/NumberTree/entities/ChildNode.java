package com.kelsey.NumberTree.entities;

import javax.persistence.*;

@Entity
@Table(name = "child_nodes")
public class ChildNode {
    @Id
    @GeneratedValue
    @Column(name = "child_id")
    int id;

    @Column
    int number;

    @ManyToOne
    Factory factory;

    public ChildNode() {
    }

    public ChildNode( int number, Factory factory) {
        this.number = number;
        this.factory = factory;
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
