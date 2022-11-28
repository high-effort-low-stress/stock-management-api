package com.github.hels.stockmanagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "uuid", nullable = false, length = 36, unique = true)
    private String uuid;
    @Column(name = "name", nullable = false, length = 40)
    private String name;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products;
    @OneToMany(mappedBy = "parent")
    private Set<Category> children;
}
