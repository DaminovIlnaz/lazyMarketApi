package ru.itis.lazyMarket.controller;

/*Ilnaz Daminov*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import ru.itis.lazyMarket.dto.UserRequest;
import ru.itis.lazyMarket.entries.UserFromApi;
import ru.itis.lazyMarket.models.User;
import ru.itis.lazyMarket.service.ApiDataService;
import ru.itis.lazyMarket.service.UserService;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/authorize")
public class UserController {

    private final UserService userService;

    @PostMapping
    public Mono<String> login(@Valid @RequestBody UserRequest request) {
        return userService.login(request);
    }

    @PostMapping("/user")
    public Mono<Void> createUser(@RequestBody UserRequest request){
        System.out.println(request.getPassword() + " " + request.getUserName());
        return userService.createUser(request);
    }

    /*@RequestMapping(value = "/hell",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE,
            method = RequestMethod.GET)*/
    @PostMapping("/hell")
    public Flux<User> getAll(){
        return userService.getAllUsers();
    }


    @Autowired
    private ApiDataService apiDataService;

    @RequestMapping(value = "/getAllUsers",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE,
            method = RequestMethod.POST)
    public Flux<UserFromApi> getAllP(){
        return apiDataService.getAll();
    }
}

