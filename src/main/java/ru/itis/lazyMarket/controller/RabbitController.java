package ru.itis.lazyMarket.controller;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;
import ru.itis.lazyMarket.dto.BasketDto;
import ru.itis.lazyMarket.service.BasketsService;

@Controller
@RequestMapping("/rabbit")
public class RabbitController {
    Logger logger = Logger.getLogger(RabbitController.class);

    @Autowired
    RabbitTemplate template;

    /*
    имеем возможность выполнять маршрутизацию на основе нескольких критериев
     */
    @RequestMapping("/emit/{key}/{message}")
    @ResponseBody
    String error(@PathVariable("key") String key, @PathVariable("message") String message) {
        logger.info(String.format("Emit '%s' to '%s'",message,key));
        template.convertAndSend(key, message);
        return String.format("Emit '%s' to '%s'",message,key);
    }

    @Autowired
    BasketsService basketsService;

    @RequestMapping("/makeOrder")
    @ResponseBody
    Mono<Void> makeOrder(@RequestBody BasketDto basketDto) {
        System.out.println(basketDto.toString());
        basketsService.makeOrder(basketDto);
        template.convertAndSend("makeOrder", basketDto.toString());
        return Mono.empty();
    }


}

