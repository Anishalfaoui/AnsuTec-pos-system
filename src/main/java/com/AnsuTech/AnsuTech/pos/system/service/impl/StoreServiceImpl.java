package com.AnsuTech.AnsuTech.pos.system.service.impl;

import com.AnsuTech.AnsuTech.pos.system.domain.StoreStatus;
import com.AnsuTech.AnsuTech.pos.system.exceptions.UserException;
import com.AnsuTech.AnsuTech.pos.system.mapper.StoreMapper;
import com.AnsuTech.AnsuTech.pos.system.model.Store;
import com.AnsuTech.AnsuTech.pos.system.model.StoreContact;
import com.AnsuTech.AnsuTech.pos.system.model.User;
import com.AnsuTech.AnsuTech.pos.system.payload.dto.StoreDto;
import com.AnsuTech.AnsuTech.pos.system.repository.StoreRepository;
import com.AnsuTech.AnsuTech.pos.system.repository.UserRepository;
import com.AnsuTech.AnsuTech.pos.system.service.StoreService;
import com.AnsuTech.AnsuTech.pos.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    @Override
    public StoreDto createStore(StoreDto storeDto, User user) {
        Store store = StoreMapper.toEntity(storeDto, user);
        return StoreMapper.toDTO(storeRepository.save(store));
    }

    @Override
    public StoreDto getStoreById(Long storeId) throws Exception {
        Store store = storeRepository.findById(storeId).orElseThrow
                (() -> new Exception("Store not found"));
        return StoreMapper.toDTO(store);
    }

    @Override
    public StoreDto updateStore(Long id, StoreDto storeDto) throws UserException {
        User currentUser = userService.getCurrentUser();
        Store existing = storeRepository.findById(id).orElse(storeRepository.findByStoreAdminId(currentUser.getId()));

        if (existing == null) {
            throw new UserException("Store not found");
        }
        existing.setBrand(storeDto.getBrand());
        existing.setDescription(storeDto.getDescription());
        if (storeDto.getStoreType() != null) {
            existing.setStoreType(storeDto.getStoreType());
        }
        if (storeDto.getContact() != null) {
            StoreContact contact = StoreContact.builder()
                    .address(storeDto.getContact().getAddress())
                    .phone(storeDto.getContact().getPhone())
                    .email(storeDto.getContact().getEmail())
                    .build();
            existing.setContact(contact);
        }
        Store updatedStore = storeRepository.save(existing);
        return StoreMapper.toDTO(updatedStore);
    }

    @Override
    public Store getStoreByAdmin() throws UserException {
        User admin =userService.getCurrentUser();

        return storeRepository.findByStoreAdminId(admin.getId());
    }

    @Override
    public List<StoreDto> getAllStores() {
        List<Store> stores = storeRepository.findAll();
        List<StoreDto> storeDtos = new ArrayList<>();
        for (Store store : stores) {
            storeDtos.add(StoreMapper.toDTO(store));
        }
        return storeDtos;
    }

    @Override
    public void deleteStore(Long id) throws UserException {
        Store store = getStoreByAdmin();
        storeRepository.delete(store);
    }

    @Override
    public StoreDto getStoreByEmployee() throws UserException {
        User currentUser =userService.getCurrentUser();

        return StoreMapper.toDTO(currentUser.getStore());
    }

    @Override
    public StoreDto moderateStore(Long id, StoreStatus status) throws Exception {
        Store store = storeRepository.findById(id).orElseThrow
                (() -> new Exception("Store not found"));
        store.setStatus(status);
        Store updatedStore = storeRepository.save(store);
        return StoreMapper.toDTO(updatedStore);
    }
}
