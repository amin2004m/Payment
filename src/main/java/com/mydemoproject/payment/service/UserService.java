package com.mydemoproject.payment.service;

import com.mydemoproject.payment.dto.request.UserRequest;
import com.mydemoproject.payment.dto.response.UserResponse;
import com.mydemoproject.payment.entity.User;
import com.mydemoproject.payment.exceptions.UserAlreadyExistsException;
import com.mydemoproject.payment.exceptions.UserNotFoundException;
import com.mydemoproject.payment.mapper.UserDtoMapper;
import com.mydemoproject.payment.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

import static com.mydemoproject.payment.exceptions.ErrorEnum.USER_ALREADY_EXISTS;
import static com.mydemoproject.payment.exceptions.ErrorEnum.USER_NOT_FOUND;
import static com.mydemoproject.payment.mapper.UserDtoMapper.mapToUserResponse;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;

    public UserResponse createUser(@Valid UserRequest request) {
        userRepository.findByUsername(request.getUsername())
                .ifPresent(
                        user -> {
                            throw new UserAlreadyExistsException(USER_ALREADY_EXISTS.getErrorMessage());
                        });
        User newUser = UserDtoMapper.mapToUserEntity(request);
        User savedUser = userRepository.save(newUser);
        return UserDtoMapper.mapToUserResponse(savedUser);
    }

    public UserResponse login(@Valid String username, String password) {
        var user = userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND.getErrorMessage()));
        return mapToUserResponse(user);

    }

    public void deleteUserById(@Valid Long id) {
        userRepository.deleteById(id);
    }

}
