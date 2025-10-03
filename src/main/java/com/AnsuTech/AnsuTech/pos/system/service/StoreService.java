package com.AnsuTech.AnsuTech.pos.system.service;

import com.AnsuTech.AnsuTech.pos.system.model.User;
import com.AnsuTech.AnsuTech.pos.system.payload.dto.StoreDto;

import java.util.List;

public interface StoreService {

    StoreDto createStore(StoreDto storeDto);
    StoreDto getStoreById(Long storeId);
    StoreDto updateStore(Long id ,StoreDto storeDto);
    StoreDto getStoreByAdmin();
    List<StoreDto> getAllStores();
    StoreDto deleteStore(Long id);
    StoreDto getStoreByEmployee();



}
