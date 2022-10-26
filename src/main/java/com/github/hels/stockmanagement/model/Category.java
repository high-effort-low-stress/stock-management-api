package com.github.hels.stockmanagement.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
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
    @Column(name = "category_id")
    private Category categoryId;
    @OneToMany
    @JoinColumn(name = "product_id")
    private Set<Product> products;
}
