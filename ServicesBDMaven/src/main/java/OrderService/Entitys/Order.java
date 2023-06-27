package OrderService.Entitys;

import OrderService.Enums.OrderState;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @ElementCollection
    @CollectionTable(name = "order_products", joinColumns = @JoinColumn(name = "order_id"))
    private List<Item> items;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "barcode")
    private String barCode;

    @Column(name = "delivery_code")
    private String delivery_code;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_state", nullable = false)
    private OrderState state;

    public Order() {
    }

    public Order(Long userId, String userName, String userEmail, List<Item> items, String deliveryAddress) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.items = items;
        this.deliveryAddress = deliveryAddress;
        this.state = OrderState.CREATED;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> orderItems) {
        this.items = orderItems;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getDelivery_code() {
        return delivery_code;
    }

    public void setDelivery_code(String delivery_code) {
        this.delivery_code = delivery_code;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }
}
