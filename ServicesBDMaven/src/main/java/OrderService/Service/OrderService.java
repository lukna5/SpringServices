package OrderService.Service;

import OrderService.Entitys.Order;
import OrderService.Entitys.Item;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Long userId, String userName, String userEmail, List<Item> items, String deliveryAddress) {
        if (items != null && items.size() > 0) {
            Order order = new Order(userId, userName, userEmail, items, deliveryAddress);
            return orderRepository.save(order);
        }
        throw new IllegalArgumentException("Products should be not null and their size > 0");
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new NoSuchElementException("Haven't element with this id: " + orderId));
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

}
