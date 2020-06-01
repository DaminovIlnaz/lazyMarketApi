package ru.itis.lazyMarket.service;

import com.google.common.collect.Sets;
import reactor.core.publisher.Flux;
import ru.itis.lazyMarket.models.User;
import ru.itis.lazyMarket.repositories.TestRepo;
import ru.itis.lazyMarket.repositories.UserRepository;
import ru.itis.lazyMarket.dto.UserRequest;
import ru.itis.lazyMarket.enums.RoleEnum;
import ru.itis.lazyMarket.jwt.JWTReactiveAuthenticationManager;
import ru.itis.lazyMarket.jwt.TokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final JWTReactiveAuthenticationManager jwtReactiveAuthenticationManager;

    public Mono<String> login(UserRequest request) {
        Authentication authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword());

        Mono<Authentication> authentication = this.jwtReactiveAuthenticationManager.authenticate(authenticationToken);
        ReactiveSecurityContextHolder.withAuthentication(authenticationToken);
        Mono<String> resp = authentication.map(tokenProvider::createToken);
        System.out.println("RESP: " + resp.toString());
        return resp;
    }

    public Mono<Void> createUser(UserRequest request) {
        User user = new User();
        user.setUserName(request.getUserName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Sets.newHashSet(RoleEnum.ROLE_ADMIN));
        userRepository.save(user);
        return Mono.empty();
    }


    public Flux<User> getAllUsers(){
        for (User u: userRepository.findAll()) {
            System.out.println(u.toString());
        }
        Flux<User> flux =  Flux.fromIterable(userRepository.findAll());
        return flux;
    }
}
