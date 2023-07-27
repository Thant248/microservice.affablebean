package com.example.affableview.controller;

import com.example.affableview.entity.Product;
import com.example.affableview.model.CartItem;
import com.example.affableview.service.CartService;
import com.example.affableview.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ui")
public class ProductController {
    private final ProductService productService;
    private final CartService cartService;




    @GetMapping("/transfer")
    public String checkoutTransfer(@ModelAttribute("total") double total, RedirectAttributes redirectAttributes){
        ResponseEntity responseEntity = productService.transfer("john@gmail.com","mary@gmail.com",total);
        if (responseEntity.getStatusCode().is2xxSuccessful()){
            redirectAttributes.addFlashAttribute("tran",true);
            cartService.cartRemove();
            return "redirect:/ui/home";
        }

        redirectAttributes.addFlashAttribute("transfererror",true);
        return "redirect:/ui/checkoutView";


    }

    @GetMapping("/products/{id}")
    @ResponseBody
    public List<Product> showAllProduct(@PathVariable int id){
        return productService.findProductByCategory(id);
    }

    @GetMapping(value = {"/","/home"})
    public String home(Model model){
       model.addAttribute("tran",model.containsAttribute("tran"));
        return "home";
    }


    @GetMapping("/product/category")
    public String showProducts(@RequestParam("id")int id, Model model){
        model.addAttribute("products",productService.findProductByCategory(id));

        return "products";
    }

    @ModelAttribute("cartSize")
    public int cartSize(){
        return cartService.cartSize();
    }


    @GetMapping("/cart/clear")
    public String removeCart(){
        cartService.cartRemove();
        return "redirect:/ui/home";
    }

//     @ModelAttribute("Productname")
//     public Product ProductName(){
//
//        Product product = new Product();
//        productService.
//        return product;
//     }


    @GetMapping("/product/purchase")
    public String addToCart(@RequestParam("id")int id){
      Product product = productService.Purchase(id);
        return "redirect:/ui/product/category?id="+product.getCategory().getId();
    }

    @GetMapping("/product/cartView")
    public String viewCart(Model model){
        model.addAttribute("cartItem",cartService.findAllCartItem());
        model.addAttribute("products",new Product());
        return "cartView";
    }

    @PostMapping("/product/checkout")
    public String checkout(Product product, BindingResult result){
//        System.out.println("=============="+product.getQuantityList());
        for (Product cartItem: cartService.findAllCartItem()){
            int i =0;
            cartItem.setQuantity(product.getQuantityList().get(i));
            i++;
        }
//        cartService.findAllCartItem().forEach(System.out::println);
        return "redirect:/ui/checkoutView";
    }

    @GetMapping("/checkoutView")
    public String checkoutView(Model model){
        model.addAttribute("transfererror",model.containsAttribute("transfererror"));
        return "checkout";
    }
    @ModelAttribute("total")
    public double totalAmount(){
       return cartService.findAllCartItem().stream().map(p->p.getQuantity()*p.getPrice()).mapToDouble(i->i).sum();

    }


    @QueryMapping
    public List<CartItem> cartItems(){
        return cartService.findAllCartItem().stream().map(p->new CartItem(p.getId(),
                p.getName(),
                p.getPrice(),p.getDescription(),p.getQuantity(),p.getLastUpdate())).collect(Collectors.toList());

    }

    @GetMapping("/transport")
    public String transport(){
        ResponseEntity responseEntity = productService.saveCartItem();
        if (responseEntity.getStatusCode().is2xxSuccessful()){

            return "redirect:/ui/home";
        }
        return "redirect:/ui/checkoutView";
    }



}
