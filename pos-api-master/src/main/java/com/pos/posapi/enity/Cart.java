package com.pos.posapi.enity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @Column(name = "cart_id")
    private String cartId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.MERGE)
    Set<CartItem> cartItems;

}
