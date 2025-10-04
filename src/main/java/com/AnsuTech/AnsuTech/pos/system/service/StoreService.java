package com.AnsuTech.AnsuTech.pos.system.service;

import com.AnsuTech.AnsuTech.pos.system.domain.StoreStatus;
import com.AnsuTech.AnsuTech.pos.system.exceptions.UserException;
import com.AnsuTech.AnsuTech.pos.system.model.Store;
import com.AnsuTech.AnsuTech.pos.system.model.User;
import com.AnsuTech.AnsuTech.pos.system.payload.dto.StoreDto;

import java.util.List;

public interface StoreService {

    StoreDto createStore(StoreDto storeDto, User user);
    StoreDto getStoreById(Long storeId) throws Exception;
    StoreDto updateStore(Long id ,StoreDto storeDto) throws UserException;
    Store getStoreByAdmin() throws UserException;
    List<StoreDto> getAllStores();
    void deleteStore(Long id) throws UserException;
    StoreDto getStoreByEmployee() throws UserException;
    StoreDto moderateStore(Long id, StoreStatus status) throws Exception;



}
