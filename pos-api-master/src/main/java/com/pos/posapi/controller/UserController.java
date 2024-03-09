package com.pos.posapi.controller;

import com.pos.posapi.dto.requestdto.RequestUserSaveDto;
import com.pos.posapi.dto.responsedto.ResponseUserDataDTO;
import com.pos.posapi.dto.responsedto.core.CommonResponseDTO;
import com.pos.posapi.service.UserService;
import com.pos.posapi.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/user")
@CrossOrigin
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = {"/register"})
    public ResponseEntity<StandardResponse> registerUser(
            @Valid @RequestBody RequestUserSaveDto dto) {
        CommonResponseDTO registeredStateData = userService.createUser(dto);
        return new ResponseEntity<>(new StandardResponse(registeredStateData.getCode(),
                registeredStateData.getMessage(), registeredStateData.getData()),
                registeredStateData.getCode() == 201 ? HttpStatus.CREATED :
                        registeredStateData.getCode() == 409 ? HttpStatus.CONFLICT :
                                registeredStateData.getCode() == 423 ? HttpStatus.LOCKED :
                                        HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping(path = {"/get-all-user-data"}, params = {"token"})
    public ResponseEntity<StandardResponse> getAllUserData(
            @RequestParam(value = "token") String token
    ){
        ResponseUserDataDTO userDataDTO = userService.getAllUserData(token);
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "ALl User Data ",
                        userDataDTO
                ),HttpStatus.OK
        );
    }

    @GetMapping("/get-all")
    public ResponseEntity<StandardResponse> getAll(
    ){
        List<ResponseUserDataDTO> userDataDTO = userService.getAll();
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "ALl User Data ",
                        userDataDTO
                ),HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity<StandardResponse> deleteUser(
            @RequestParam(value = "id") String id
    ){
        CommonResponseDTO responseDto = userService.deleteUser(id);
        return new ResponseEntity<>(
                new StandardResponse(
                        responseDto.getCode(),
                        responseDto.getMessage(),
                        responseDto.getData()
                ),HttpStatus.NO_CONTENT
        );
    }

}
