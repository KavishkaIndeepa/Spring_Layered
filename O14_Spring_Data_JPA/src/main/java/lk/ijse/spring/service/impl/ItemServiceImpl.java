package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.dto.ItemDTO;
import lk.ijse.spring.entity.Customer;
import lk.ijse.spring.entity.Item;
import lk.ijse.spring.repo.ItemRepo;
import lk.ijse.spring.service.ItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional // manage all the transactions here // AOP
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepo itemRepo;

    @Autowired
    ModelMapper mapper;


    @Override
    public void saveItem(ItemDTO item) {
//        itemRepo.save(mapper.map(item, Item.class));
        if (itemRepo.existsById(item.getCode())){
            throw new RuntimeException (item.getCode()+" is already available, please insert a new code");
        }
        Item map= mapper.map(item, Item.class);
        itemRepo.save(map);
    }


    @Override
    public void deleteItem(String code) {
        if (!itemRepo.existsById(code)) {
            throw new RuntimeException(code+ " Item is not available, please check the code before delete.!");
        }
        itemRepo.deleteById(code);
    }

    @Override
    public List<ItemDTO> getAllItem() {
        List<Item> all = itemRepo.findAll();
        return mapper.map(all, new TypeToken<List<ItemDTO>>() {}.getType());
    }

    @Override
    public ItemDTO findItem(String code) {
        if (!itemRepo.existsById(code)) {
            throw new RuntimeException(code+ " Item is not available, please check the code.!");
        }
        Item item = itemRepo.findById(code).get();
        return mapper.map(item,ItemDTO.class);
    }

    @Override
    public void updateItem(ItemDTO i) {
        if (!itemRepo.existsById(i.getCode())) {
            throw new RuntimeException(i.getCode()+ " Item is not available, please check the code before update.!");
        }
        Item map = mapper.map(i, Item.class);
        itemRepo.save(map);
    }
}
