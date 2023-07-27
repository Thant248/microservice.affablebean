package com.example.affableview.service;

import com.example.affableview.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.form.SelectTag;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service

public class CartService {
    private Set<Product> cart = new HashSet<>();

    public int cartSize(){
        return cart.size();
    }

    public void CartId(Product product){
        cart.add(product);

    }

    public Set<Product> findAllCartItem(){
        return cart;
    }
    public void  cartRemove(){
        cart.clear();
    }



}
