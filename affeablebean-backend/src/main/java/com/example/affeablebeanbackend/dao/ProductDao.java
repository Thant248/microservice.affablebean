package com.example.affeablebeanbackend.dao;

import com.example.affeablebeanbackend.entity.Product;
import org.springframework.data.repository.CrudRepository;


public interface ProductDao extends CrudRepository<Product,Integer> {
}
