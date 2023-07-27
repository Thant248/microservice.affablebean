package com.example.affableview.service;

import com.example.affableview.entity.Product;
import com.example.affableview.entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {


    public static final int DELIVERY_CHANGE = 3;

    record TransferData(String to_email,String from_email,double amount){}
    @Autowired
    private   CartService cartService;

   public ResponseEntity transfer(String to_email,String from_email,double amount){
       var data = new TransferData(to_email,from_email,amount+ DELIVERY_CHANGE);
       return restTemplate.postForEntity("http://localhost:8090/account/transfer",data,String.class);
   }


   public ResponseEntity saveCartItem(){
       return restTemplate.getForEntity("http://localhost:9000/transport/cart/save", String.class);

   }




    private List<Product> products;

    private RestTemplate restTemplate = new RestTemplate();
    public List<Product> findProductByCategory(int categoryId){
        return products.stream().filter(p->p.getCategory().getId()== categoryId).collect(Collectors.toList());
    }

    private  Product findProduct(int id){
        return products.stream().filter(p->p.getId() == id).findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public  Product Purchase(int id){
        Product product = findProduct(id);
        cartService.CartId(product);
        return product;
    }

    public ProductService(){
        var productResponEntity = restTemplate.getForEntity("http://localhost:8095/backend/products",Products.class);
       if (productResponEntity.getStatusCode().is2xxSuccessful()){
           products = productResponEntity.getBody().getProducts();
           return;
       }
       throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }


        public List<Product> showAllProduct(){
        return products;
    }



}
