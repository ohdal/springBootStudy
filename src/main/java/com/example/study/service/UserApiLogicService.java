package com.example.study.service;

import com.example.study.ifc.CrudInterface;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        // 1-1. request data
        UserApiRequest userApiRequest = request.getData();

        // 1-2. user 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status("REGISTERED") // TODO: 추후 Enum으로 바꾸기
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = userRepository.save(user);

        // 1-3. 생성된 데이터 -> UserApiResponse return
        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        // 2-1. id -> repository getOne, getById
        Optional<User> optional = userRepository.findById(id);

        // 2-2. user -> userApiResponse return
        return optional
                .map(user -> response(user))
                .orElseGet(() -> Header.ERROR("데이터 없음"));

        // 2-3. 한번에 처리할 수도 있음
        // return userRepository.findById(id)
        //         .map(user -> response(user))
        //         .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        // 3-1. data 가져오기
        UserApiRequest userApiRequest = request.getData();

        // 3-2. id로 user 찾기
        Optional<User> optional = userRepository.findById(userApiRequest.getId());

        return optional.map(user -> {
            // 3-3. update
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt());
            return user;
        })
                .map(user -> userRepository.save(user))
                .map(updateUser -> response(updateUser))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        // 4-1. id -> repository -> user 찾기
        Optional<User> optional = userRepository.findById(id);

        // 4-2. delete하기
        return optional.map(user -> {
            userRepository.delete(user);
            // 4-3. return response
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<UserApiResponse> response(User user) {
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) // TODO : 암호화, 길이
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        return Header.OK(userApiResponse);
    }
}
