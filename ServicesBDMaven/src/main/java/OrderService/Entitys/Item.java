package OrderService.Entitys;

import jakarta.persistence.*;

@Embeddable
public class Item {
    @Column(name = "item_id", nullable = false)
    private Long id;

    @Column(name = "item_name", nullable = false)
    private String name;


    @Column(name = "item_price", nullable = false)
    private int price;

    @Column(name = "item_amount", nullable = false)
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public Item() {
    }

    public Item(Long id, String name, int price, int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String itemName) {
        this.name = itemName;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int itemPrice) {
        this.price = itemPrice;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
