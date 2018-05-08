package com.kelsey.NumberTree.services;

import com.kelsey.NumberTree.entities.ChildNode;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChildNodeRepository extends CrudRepository<ChildNode, Integer> {
}
