package com.example.notificationservice.service;

import com.example.notificationservice.config.MqConfig;
import com.example.notificationservice.request.CustomMessageRequest;
import com.example.notificationservice.request.NotificationRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;

@Component
public class MessageListener {
    private NotificationService notificationService;

    public MessageListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = MqConfig.QUEUE)
    public void listener(CustomMessageRequest message) throws UnknownHostException {

        NotificationRequest notificationRequest = new NotificationRequest();
        notificationRequest.setUserId(message.getUserId());
        notificationRequest.setMessage(message.getMessage());

        try {
            Thread.sleep(20000);
            notificationService.saveNotification(notificationRequest);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
