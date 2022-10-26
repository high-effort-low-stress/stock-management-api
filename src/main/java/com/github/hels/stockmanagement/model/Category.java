package com.github.hels.stockmanagement.model;

import lombok.*;

import javax.persistence.*;


@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "uuid", nullable = false, length = 36, unique = true)
    private Long uuid;
    @Column(name = "name", nullable = false, length = 40)
    private String name;
    @Column(name = "category_id")
    private Category categoryId;
}
