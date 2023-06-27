package ItemService.Entitys;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    public Item(String name, String description, String image, int price, int amount) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.amount = amount;
    }

    @Column(name = "price")
    private int price;

    @Column(name = "amount")
    private int amount;

    @Column(name = "discount")
    private int discount;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Set<Field> fields = new HashSet<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Set<CartItem> cartItems = new HashSet<>();

    public Item() {

    }

    public void addCartItem(CartItem cartItem){
        cartItems.add(cartItem);
    }

    public void deleteCartItem(CartItem cartItem){
        cartItems.add(cartItem);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Field> getFields() {
        return fields;
    }

    public void setFields(Set<Field> fields) {
        this.fields = fields;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
