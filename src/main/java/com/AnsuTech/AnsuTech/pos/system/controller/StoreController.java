package com.AnsuTech.AnsuTech.pos.system.controller;


import com.AnsuTech.AnsuTech.pos.system.domain.StoreStatus;
import com.AnsuTech.AnsuTech.pos.system.exceptions.UserException;
import com.AnsuTech.AnsuTech.pos.system.mapper.StoreMapper;
import com.AnsuTech.AnsuTech.pos.system.model.Store;
import com.AnsuTech.AnsuTech.pos.system.model.User;
import com.AnsuTech.AnsuTech.pos.system.payload.dto.StoreDto;
import com.AnsuTech.AnsuTech.pos.system.payload.response.ApiResponse;
import com.AnsuTech.AnsuTech.pos.system.service.StoreService;
import com.AnsuTech.AnsuTech.pos.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store")
public class StoreController {
    private final StoreService storeService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<StoreDto> createStore(@RequestBody StoreDto storeDto,
                                         @RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(storeService.createStore(storeDto, user));

    }


    @GetMapping()
    public ResponseEntity<List<StoreDto>> getStoreById(
                                          @RequestHeader("Authorization") String jwt) throws Exception {

        return ResponseEntity.ok(storeService.getAllStores());

    }

    @GetMapping("/admin")
    public ResponseEntity<StoreDto> getStoreByAdmin(@RequestBody Long id,
                                          @RequestHeader("Authorization") String jwt) throws Exception {

        return ResponseEntity.ok(StoreMapper.toDTO(storeService.getStoreByAdmin()));

    }

    @GetMapping("/employee")
    public ResponseEntity<StoreDto> getStoreByEmployee(
                                          @RequestHeader("Authorization") String jwt) throws Exception {

        return ResponseEntity.ok(storeService.getStoreByEmployee());

    }


    @PutMapping("/{id}")
    public ResponseEntity<StoreDto> updateStore(@PathVariable Long id,
                                                @RequestBody StoreDto storeDto) throws Exception {
        return ResponseEntity.ok(storeService.updateStore(id, storeDto));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<ApiResponse> deleteStore(@PathVariable Long id
                                                   ) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Delete Store Successfully");
        storeService.deleteStore(id);
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/{id}")
    public ResponseEntity<StoreDto> getStoreById(@RequestBody Long id,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {

        return ResponseEntity.ok(storeService.getStoreById(id));

    }

    @PutMapping("/{id}/moderate")
    public ResponseEntity<StoreDto> moderateStore(@PathVariable Long id,
                                                @RequestParam StoreStatus status) throws Exception {
        return ResponseEntity.ok(storeService.moderateStore(id, status));
    }


}
