package edu.cpp.CYS.controller;

import edu.cpp.CYS.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class SubscriberController {

    @Autowired
    private SubscriberRepository subscriberRepository;
}
