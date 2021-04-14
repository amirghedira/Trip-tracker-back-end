package com.miecolo.authservice.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.miecolo.authservice.dto.UserDTO;
import com.miecolo.authservice.dto.UserUpdateDTO;
import com.miecolo.authservice.entity.User;
import com.miecolo.authservice.exception.BadIdException;
import com.miecolo.authservice.mapper.UserUpdateMapper;
import com.miecolo.authservice.repository.UserRepository;
import com.miecolo.authservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
     UserRepository userRepository;
    @Autowired
    ModelMapper mapper;

    @Override
    public ResponseEntity<?> patchUser(String id, JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException, BadIdException, IllegalAccessException {

        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty())
            throw new BadIdException("User Id not found");

        UserUpdateDTO userUpdateDTO = mapper.map(user.get(),UserUpdateDTO.class);


        userUpdateDTO = applyPatchToUser(jsonPatch, userUpdateDTO);

        return ResponseEntity.ok(  userRepository.save((User) map(user.get(),userUpdateDTO)) ) ;
    }

    private UserUpdateDTO applyPatchToUser(
            JsonPatch patch, UserUpdateDTO targetCustomer) throws JsonPatchException, JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(targetCustomer, JsonNode.class));
        return objectMapper.treeToValue(patched, UserUpdateDTO.class);
    }

    private Object map(Object target , Object source) throws IllegalAccessException {

                for (Field sourceField : source.getClass().getDeclaredFields()) {
                    sourceField.setAccessible(true);
                    for (Field targetField : target.getClass().getDeclaredFields()) {
                        targetField.setAccessible(true);
                        if (sourceField.getName().equals(targetField.getName()))
                            targetField.set(target,sourceField.get(source));

                    }
                }

            return target;
    }

    public UserDTO getById(String id) throws BadIdException {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty())
            throw new BadIdException("User Id not found");

        UserDTO userDTO = mapper.map(user.get(),UserDTO.class);

        return userDTO;
    }

    public List<UserDTO> getByIds(List<String> ids) throws BadIdException {
        List<User> users = userRepository.findAllByIdIn(ids);

        List<UserDTO> userDTOS = new ArrayList<>();

        users.forEach(user -> {
            userDTOS.add(mapper.map(user,UserDTO.class));
        });

        return userDTOS;
    }
}
