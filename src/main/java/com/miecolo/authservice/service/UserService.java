package com.miecolo.authservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.miecolo.authservice.dto.UserDTO;
import com.miecolo.authservice.exception.BadIdException;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {
    public ResponseEntity<?> patchUser(String id, JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException, BadIdException, IllegalAccessException;
    public UserDTO getById(String id) throws BadIdException ;

}
