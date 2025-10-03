package com.AnsuTech.AnsuTech.pos.system.service.impl;

import com.AnsuTech.AnsuTech.pos.system.payload.dto.StoreDto;
import com.AnsuTech.AnsuTech.pos.system.repository.StoreRepository;
import com.AnsuTech.AnsuTech.pos.system.repository.UserRepository;
import com.AnsuTech.AnsuTech.pos.system.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    @Override
    public StoreDto createStore(StoreDto storeDto) {
        return null;
    }

    @Override
    public StoreDto getStoreById(Long storeId) {
        return null;
    }

    @Override
    public StoreDto updateStore(Long id, StoreDto storeDto) {
        return null;
    }

    @Override
    public StoreDto getStoreByAdmin() {
        return null;
    }

    @Override
    public List<StoreDto> getAllStores() {
        return List.of();
    }

    @Override
    public StoreDto deleteStore(Long id) {
        return null;
    }

    @Override
    public StoreDto getStoreByEmployee() {
        return null;
    }
}
