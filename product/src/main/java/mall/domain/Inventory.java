package mall.domain;

import mall.domain.StockDecreased;
import mall.domain.StockIncreased;
import mall.ProductApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;


@Entity
@Table(name="Inventory_table")
@Data

public class Inventory  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private String productName;
    
    
    
    
    
    private Integer stock;

    @PostUpdate
    public void onPostUpdate(){
    }

    public static InventoryRepository repository(){
        InventoryRepository inventoryRepository = ProductApplication.applicationContext.getBean(InventoryRepository.class);
        return inventoryRepository;
    }




    public static void stockDecrease(DeliveryStarted deliveryStarted){
        repository().findById(deliveryStarted.getProductId()).ifPresent(inventory->{
            
            inventory.setStock(inventory.getStock() - deliveryStarted.getQty());
            repository().save(inventory);

            StockDecreased stockDecreased = new StockDecreased(inventory);
            stockDecreased.publishAfterCommit();

         });
    }
    
    public static void stockIncrease(DeliveryCanceled deliveryCanceled){     
        repository().findById(deliveryCanceled.getProductId()).ifPresent(inventory->{
            
            inventory.setStock(inventory.getStock() + deliveryCanceled.getQty());
            repository().save(inventory);

            StockIncreased stockIncreased = new StockIncreased(inventory);
            stockIncreased.publishAfterCommit();

         });
    }


}
