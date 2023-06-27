package ItemService.Entitys;

import ItemService.Enums.CartState;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "price")
    private int price;

    @Column(name = "barcode")
    private String barCode;

    @Column(name = "delivery_code")
    private String delivery_code;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private CartState state;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();

    public Cart() {
    }

    public Cart(Long userId, int price, String barCode, String delivery_code, CartState state, List<CartItem> cartItems) {
        this.userId = userId;
        this.price = price;
        this.barCode = barCode;
        this.delivery_code = delivery_code;
        this.state = state;
        this.cartItems = cartItems;
    }

    public void addCartItem(CartItem cartItem){
        cartItems.add(cartItem);
    }

    public void deleteCartItem(CartItem cartItem){
        cartItems.remove(cartItem);
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public CartState getState() {
        return state;
    }

    public void setState(CartState state) {
        this.state = state;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

}
