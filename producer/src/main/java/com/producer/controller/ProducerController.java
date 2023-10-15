package com.producer.controller;


import com.producer.service.SenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class ProducerController {

    private final SenderService senderService;

    @GetMapping("/message1")
    public ResponseEntity<String> getMessage1(@RequestParam String key, @RequestParam String msg) {
        senderService.sendMessagePartition(key, msg);
        return ResponseEntity.ok(msg);
    }

    @GetMapping("/message2")
    public ResponseEntity<String> sendMsg2(@RequestParam String key,
                                           @RequestParam String msg) {
        senderService.sendMessageWithoutPartition(key, msg);
        return ResponseEntity.ok(msg);
    }

}
