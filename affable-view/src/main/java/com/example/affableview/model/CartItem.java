package com.example.affableview.model;

import java.time.LocalDateTime;

public record CartItem(

        Integer id,
        String name,
        double price,
        String description,
        int quantity,
        LocalDateTime lastUpdate

) {
}
