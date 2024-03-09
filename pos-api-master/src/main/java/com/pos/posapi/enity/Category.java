package com.pos.posapi.enity;


import com.pos.posapi.enity.enums.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class Category {

    @Id
    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private CategoryType category;

    @Column(name = "category_description")
    private String description;

    @Column(name = "activeState", columnDefinition = "TINYINT")
    private boolean activeState;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Item> items;
}
