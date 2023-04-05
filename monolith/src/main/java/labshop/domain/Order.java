package labshop.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import labshop.MonolithApplication;
import labshop.domain.OrderPlaced;
import lombok.Data;
import org.springframework.context.ApplicationContext;

@Entity
@Table(name = "Order_table")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productId;

    private Integer qty;

    private String customerId;

    private Double amount;

    @PostPersist
    public void onPostPersist() {
        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();
        /** TODO:  REST API Call to Inventory
        labshop.external.UpdateStockCommand updateStockCommand = new labshop.external.UpdateStockCommand();
        
        // TODO: fill the command properties to invoke below
        
        applicationContext().getBean(labshop.external.InventoryService.class)
           .updateStock({TODO: please put the id}, updateStockCommand);
        */

    }

    public static OrderRepository repository() {
        OrderRepository orderRepository = applicationContext()
            .getBean(OrderRepository.class);
        return orderRepository;
    }

    public static ApplicationContext applicationContext() {
        return MonolithApplication.applicationContext;
    }
}
