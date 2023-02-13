package mall.domain;

import mall.domain.*;
import mall.infra.AbstractEvent;
import java.util.*;
import lombok.*;


@Data
@ToString
public class DeliveryComplete extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Long productId;
    private Integer qty;
    private String productName;
    private String status;

    public DeliveryComplete(Delivery aggregate){
        super(aggregate);
    }
    public DeliveryComplete(){
        super();
    }
}
