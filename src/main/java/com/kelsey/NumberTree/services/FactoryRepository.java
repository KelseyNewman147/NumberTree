package com.kelsey.NumberTree.services;


import com.kelsey.NumberTree.entities.Factory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FactoryRepository extends CrudRepository<Factory, Integer> {
    //update?
    Factory findFactoryById(int id);
}
