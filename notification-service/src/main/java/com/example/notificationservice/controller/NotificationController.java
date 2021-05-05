package com.example.notificationservice.controller;

import com.example.notificationservice.domain.NotificationEntity;
import com.example.notificationservice.request.NotificationRequest;
import com.example.notificationservice.service.NotificationService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.List;


@RestController
@RequestMapping("/notification")
@Api(tags = {"NotificationController"})
public class NotificationController {

    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping(value = "/saneNotification")
    public ResponseEntity<NotificationEntity> saneNotification(@RequestBody NotificationRequest request) throws RuntimeException, UnknownHostException {
        return new ResponseEntity<>(notificationService.saveNotification(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findAllNotification")
    public ResponseEntity<List<NotificationEntity>> findAllNotification() throws RuntimeException {
        return new ResponseEntity<>(notificationService.findAllNotification(), HttpStatus.OK);
    }
}
