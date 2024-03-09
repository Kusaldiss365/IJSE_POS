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
public class Stock {
    @Id
    @Column(name = "stock_id")
    private String stock_id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private int quantity;

    @OneToMany(mappedBy="stock")
    private Set<Item> items;

}
