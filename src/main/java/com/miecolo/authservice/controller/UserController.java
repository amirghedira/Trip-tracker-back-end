package com.miecolo.authservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.miecolo.authservice.dto.UserDTO;
import com.miecolo.authservice.exception.BadIdException;
import com.miecolo.authservice.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchUser(@PathVariable String id, @RequestBody JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException, BadIdException, IllegalAccessException {
        return userService.patchUser(id,jsonPatch);
    }

    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable String id) throws BadIdException {
        return userService.getById(id);
    }
    @PostMapping("")
    public List<UserDTO> getByIdS(@RequestBody List<String> ids) throws BadIdException {
        return userService.getByIds(ids);
    }

}
