package ru.maliutin.storage.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Сущность товара.
 */
@Data
@Entity
@Table(name = "t_product")
@SecondaryTable(
        name = "t_product_balance",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "product_id"))
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    @Column(name = "c_title")
    private String title;
    @Column(name = "c_catalogue_number")
    private String catalogueNumber;
    @Column(name = "c_program_number")
    private int programNumber;
    @ManyToMany
    @JoinTable(
            name = "t_applicability",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "technic_id")
    )
    private Set<Technic> technics;
    @Column(table = "t_product_balance")
    private int balance;
    @Column(table = "t_product_balance")
    private BigDecimal price;
}
