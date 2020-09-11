package com.example.datajpashop.domain.item;

import com.example.datajpashop.domain.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    public void removeStock(int count)
    {
       int restStock=stockQuantity-count;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        stockQuantity=restStock;
    }

    public void addStock(int count) {
        this.stockQuantity+=count;
    }
}
