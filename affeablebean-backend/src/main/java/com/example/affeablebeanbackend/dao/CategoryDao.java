package com.example.affeablebeanbackend.dao;

import com.example.affeablebeanbackend.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryDao extends CrudRepository<Category,Integer> {
}
