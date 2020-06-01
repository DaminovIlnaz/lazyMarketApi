package ru.itis.lazyMarket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.lazyMarket.service.SupermarketService;

@RepositoryRestController
public class HateoasController {
    private static final Logger log = LoggerFactory.getLogger(HateoasController.class);
    @Autowired
    private SupermarketService supermarketsService;

    public HateoasController() {
    }

    @RequestMapping(
            value = {"/supermarkets/{supermarket-id}/open"},
            method = {RequestMethod.PUT}
    )
    @ResponseBody
    public ResponseEntity<?> publish(@PathVariable("supermarket-id") Long supermarketId) {
        log.info(supermarketId.toString());
        return ResponseEntity.ok(new EntityModel(this.supermarketsService.publish(supermarketId), new Link[0]));
    }
}
