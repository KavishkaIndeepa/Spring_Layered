package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.OrderDetailsDTO;
import lk.ijse.spring.dto.OrdersDTO;
import lk.ijse.spring.entity.Item;
import lk.ijse.spring.entity.OrderDetails;
import lk.ijse.spring.entity.Orders;
import lk.ijse.spring.repo.CustomerRepo;
import lk.ijse.spring.repo.ItemRepo;
import lk.ijse.spring.repo.OrderDetailsRepo;
import lk.ijse.spring.repo.OrdersRepo;
import lk.ijse.spring.service.PurchaseOrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    OrdersRepo ordersRepo;

    @Autowired
    OrderDetailsRepo orderDetailsRepo;

    @Autowired
    ItemRepo itemRepo;
    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ModelMapper mapper;


    @Override
    public void purchaseOrder(OrdersDTO ordersDTO) {
        if (ordersRepo.existsById(ordersDTO.getOid())) {
            throw new RuntimeException(ordersDTO.getOid()+ " Is already available.!");
        }

        Orders order = mapper.map(ordersDTO, Orders.class);
        ordersRepo.save(order);

        //add Order details also

        for (OrderDetails details: order.getOrderDetails()) {
            Item item = itemRepo.findById(details.getItemCode()).get();
            item.setQtyOnHand(item.getQtyOnHand()-details.getQty());
            itemRepo.save(item);
        }


    }



}
