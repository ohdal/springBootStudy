package com.example.study.controller.api;

import com.example.study.ifc.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.ItemApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.service.ItemApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/item")
public class ItemApiController implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private ItemApiLogicService itemApiLogicService;

    @Override
    @PostMapping("")
    public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) {
        log.info("{}, {}", "Item Create", request);
        return itemApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<ItemApiResponse> read(@PathVariable(name = "id") Long id) {
        log.info("{}, {}", "Item Read", id);
        return itemApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {
        log.info("{}, {}", "Item Update", request);
        return itemApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header<ItemApiResponse> delete(@PathVariable(name = "id") Long id) {
        log.info("{}, {}", "Item Delete", id);
        return itemApiLogicService.delete(id);
    }
}