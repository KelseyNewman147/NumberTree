package com.kelsey.NumberTree.services;

import com.kelsey.NumberTree.entities.ChildNode;
import com.kelsey.NumberTree.entities.RootNode;
import org.springframework.data.repository.CrudRepository;

public interface RootNodeRepository extends CrudRepository<RootNode, Integer> {
    RootNode findById(int id);
}
