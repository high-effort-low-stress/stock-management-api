package com.github.hels.stockmanagement.model;

import com.github.hels.stockmanagement.enums.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product productId;
    @Column(name = "uuid", nullable = false, length = 36, unique = true)
    private String uuid;
    @Column(name = "purchase_price", nullable = false)
    private Double purchasePrice;
    @Column(name = "tax")
    private Double tax;
    @Column(name = "resale_price", nullable = false)
    private Double resalePrice;
    @Enumerated
    @Column(name = "status", nullable = false)
    private OrderStatus orderStatus;
    @Column(name = "sold_at", nullable = false)
    private LocalDateTime soldAt;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

}
