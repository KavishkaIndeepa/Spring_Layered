package lk.ijse.spring.service;

import lk.ijse.spring.dto.OrderDetailsDTO;
import lk.ijse.spring.dto.OrdersDTO;

import java.util.List;

public interface PurchaseOrderService {
    public void purchaseOrder(OrdersDTO ordersDTO);

}
