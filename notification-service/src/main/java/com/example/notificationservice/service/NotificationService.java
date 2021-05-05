package com.example.notificationservice.service;

import com.example.notificationservice.domain.NotificationEntity;
import com.example.notificationservice.repository.NotificationRepository;
import com.example.notificationservice.request.NotificationRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

@Service
public class NotificationService {

    private NotificationRepository notificationRepository;
    private RabbitTemplate rabbitTemplate;

    public NotificationService(NotificationRepository notificationRepository, RabbitTemplate rabbitTemplate) {
        this.notificationRepository = notificationRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public NotificationEntity saveNotification(NotificationRequest request) throws UnknownHostException {

        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setUserId(request.getUserId());
        notificationEntity.setMessage(request.getMessage());
        notificationEntity.setDate(new Date());
        notificationEntity.setServerIp(getServer());

        notificationRepository.save(notificationEntity);
        return notificationEntity;
    }

    public List<NotificationEntity> findAllNotification() {
        return notificationRepository.findAll();
    }

    private String getServer() throws UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();

        return ip.getHostName();
    }
}
