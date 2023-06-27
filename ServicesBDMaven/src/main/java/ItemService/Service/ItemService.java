package ItemService.Service;

import ItemService.Entitys.Cart;
import ItemService.Entitys.CartItem;
import ItemService.Entitys.Item;
import ItemService.Repositories.CartRepository;
import ItemService.Repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;


    public ItemService(ItemRepository itemRepository, CartRepository cartRepository) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) throws NoSuchElementException {
        return itemRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Item not found with id: " + id));
    }

    public Item addItem(String name, String description, String image, int amount, int price) {
        Item item = new Item(name, description, image, amount, price);
        return itemRepository.save(item);
    }

    public Item updateItem(Long id, String name, String description, int price) throws NoSuchElementException {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Item not found with id: " + id));

        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);

        return itemRepository.save(item);
    }

    public void deleteItem(Long id) throws NoSuchElementException {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Item not found with id: "+ id));

        for (CartItem cartItem: item.getCartItems()) {
            Cart cart = cartItem.getCart();
            cart.deleteCartItem(cartItem);
            cartRepository.save(cart);
        }

        itemRepository.deleteById(id);
    }

    public List<CartItem> getCartItems(Long userId) {
        List<Item> cartItems = new ArrayList<>();

        Cart cart = cartRepository.findByUserId(userId);

        return cart.getCartItems();
    }

    public void addCartItem(Long userId, String name, String description, int price) throws NoSuchElementException {
        Item item = itemRepository.findByName(name);

        CartItem cartItem = new CartItem(name, description, price);
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
            cart = cartRepository.save(cart);
        }

        cart.addCartItem(cartItem);
        item.addCartItem(cartItem);
        cartItem.setItem(item);
        cartItem.setCart(cart);

        cartRepository.save(cart);
        itemRepository.save(item);
    }

    public void deleteCartItem(Long userId, Long cartItemId) throws NoSuchElementException {

        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null){
            throw new NoSuchElementException("Cart not found with userId: " + userId);
        }

        CartItem cartItem = null;
        for (CartItem cartItem1: cart.getCartItems()){
            if (cartItem1.getId().equals(cartItemId)){
                cartItem = cartItem1;
                cart.deleteCartItem(cartItem);
            }
        }
        if (cartItem == null){
            throw new NoSuchElementException("CartItem not found with id: " + cartItemId);
        }
        Item item = cartItem.getItem();
        item.deleteCartItem(cartItem);

        itemRepository.save(item);
        cartRepository.save(cart);
    }
}