package com.example.userservice.service;

import com.example.userservice.config.MqConfig;
import com.example.userservice.domain.UserEntity;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.request.NotificationRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private RabbitTemplate rabbitTemplate;


    public UserService(UserRepository userRepository, RabbitTemplate rabbitTemplate) {
        this.userRepository = userRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public UserEntity saveUser(UserEntity userEntity){
        userRepository.save(userEntity);
        sendNotification(userEntity);
        return userEntity;
    }

    public List<UserEntity> fetchAllUser() {
        return userRepository.findAll();
    }

    private void sendNotification(UserEntity userEntity){
        NotificationRequest notificationRequest = new NotificationRequest();
        notificationRequest.setUserId(userEntity.getId());
        notificationRequest.setMessage("Registration succesful. Name : "+userEntity.getFullName()
                +" Email :"+userEntity.getEmail());

        rabbitTemplate.convertAndSend(MqConfig.EXCHANGE,MqConfig.ROUTING_KEY, notificationRequest);
    }
}
