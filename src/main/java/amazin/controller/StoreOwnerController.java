package amazin.controller;

import amazin.model.StoreOwner;
import amazin.repository.StoreOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StoreOwnerController {
    private StoreOwnerRepository storeOwnerRepository;

    @Autowired
    public StoreOwnerController(StoreOwnerRepository storeOwnerRepository){
        this.storeOwnerRepository = storeOwnerRepository;
    }

    @RequestMapping("/storeOwner")
    public void createStoreOwner(@RequestBody StoreOwner storeOwner){
        storeOwnerRepository.save(storeOwner);
    }

    @RequestMapping(value = "/storeOwner", method = RequestMethod.GET)
    public StoreOwner getStoreOwner(@RequestParam("id") Long id){
        return storeOwnerRepository.findStoreOwnerById(id);
    }

}
