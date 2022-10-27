package com.github.hels.stockmanagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter

@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "uuid", nullable = false, length = 36, unique = true)
    private String uuid;
    @Column(name = "name", nullable = false, length = 60)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at", nullable = false)
    private LocalDateTime deletedAt;
    @ManyToMany
    @JoinColumn(name = "parent_id")
    private Set<Category> entity;
}
