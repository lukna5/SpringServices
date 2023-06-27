package ItemService.Repositories;

import ItemService.Entitys.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByUserId(Long userId);
    void deleteAllByUserId(Long userId);
}
