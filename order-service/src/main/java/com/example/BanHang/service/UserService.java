package com.example.BanHang.service;

import com.example.BanHang.dto.request.UserCreationRequest;
import com.example.BanHang.dto.request.UserUpdateRequest;
import com.example.BanHang.dto.response.UserResponse;
import com.example.BanHang.entity.User;
import com.example.BanHang.enums.Role;
import com.example.BanHang.exception.AppException;
import com.example.BanHang.exception.ErrorCode;
import com.example.BanHang.mapper.UserMapper;
import com.example.BanHang.repository.RoleRepository;
import com.example.BanHang.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserMapper userMapper;
    UserRepository userRepository;
    RoleRepository roleRepository;
    CartService cartService;
    PasswordEncoder passwordEncoder =new BCryptPasswordEncoder();

    public UserResponse createUser(UserCreationRequest request){
        if(userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        PasswordEncoder passwordEncoder =new BCryptPasswordEncoder();
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

//        HashSet<String> hashSet=new HashSet<>();
//        hashSet.add(Role.USER.name());
//        user.setRoles(hashSet);

        userRepository.save(user);


        return userMapper.toUserResponse(user);
    }
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAuthority('update')")
    public List<UserResponse> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponse)
                .toList();
    }
    @PostAuthorize("returnObject.username==authentication.name")
    public UserResponse getUser(String id){
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }
    public void deleteUser(String id){
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }else {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
    }
    public UserResponse updateUser(String userId,UserUpdateRequest request){
        User user= userRepository.findById(userId).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));
        userMapper.updateUser(user,request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles =roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user= userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserResponse(user);
    }
}


