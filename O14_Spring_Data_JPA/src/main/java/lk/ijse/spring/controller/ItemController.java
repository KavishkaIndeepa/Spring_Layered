package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.dto.ItemDTO;
import lk.ijse.spring.service.ItemService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
@CrossOrigin
public class ItemController {

    @Autowired
    ItemService service;

    @PostMapping
    public ResponseUtil saveItem(ItemDTO item) {
        service.saveItem(item);
        return new ResponseUtil("Ok","Successfully Added",item);
    }

    @DeleteMapping(params = {"code"})
    public ResponseUtil deleteItem(String code){
        service.deleteItem(code);
        return new ResponseUtil("Ok","Successfully Deleted",code);
    }

    @GetMapping
    public ResponseUtil getAllItem(){
        return new ResponseUtil("Ok","Successfully Loaded",service.getAllItem());
    }

    @GetMapping(params = {"code"})
    public ResponseUtil findItem(String code){
        return new ResponseUtil("Ok","Successfull", service.findItem(code));
    }

    @PutMapping
    public ResponseUtil updateItem(@RequestBody ItemDTO i){
        service.updateItem(i);
        return new ResponseUtil("Ok","Successfully Updated",i);
    }
}
