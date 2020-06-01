package ru.itis.lazyMarket;
/*Ilnaz Daminov*/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.thymeleaf.spring5.context.webflux.ISpringWebFluxContext;
import ru.itis.lazyMarket.config.RabbitConfiguration;
import ru.itis.lazyMarket.models.Basket;
import ru.itis.lazyMarket.models.Buyer;
import ru.itis.lazyMarket.models.Product;
import ru.itis.lazyMarket.models.Supermarket;
import ru.itis.lazyMarket.repositories.BasketsRepository;
import ru.itis.lazyMarket.repositories.BuyersRepository;
import ru.itis.lazyMarket.repositories.ProductsRepository;
import ru.itis.lazyMarket.repositories.SupermarketsRepository;
/*import ru.itis.lazyMarket.repositories.BasketsRepository;
import ru.itis.lazyMarket.repositories.BuyersRepository;
import ru.itis.lazyMarket.repositories.ProductsRepository;
import ru.itis.lazyMarket.repositories.SupermarketsRepository;*/

import java.util.Arrays;
import java.util.Collections;

@EnableJpaAuditing
@SpringBootApplication
@Import(RabbitConfiguration.class)
@ComponentScan("ru.itis")
public class LazyMarketApp {

    public static void main(String[] args) {
        /*SpringApplication app = new SpringApplication(LazyMarketApp.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8081"));
        app.run(args);*/

        ApplicationContext context = SpringApplication.run(LazyMarketApp.class, args);

        BasketsRepository basketsRepository = (BasketsRepository)context.getBean(BasketsRepository.class);
        BuyersRepository buyersRepository = (BuyersRepository)context.getBean(BuyersRepository.class);
        ProductsRepository productsRepository = (ProductsRepository)context.getBean(ProductsRepository.class);
        SupermarketsRepository supermarketsRepository = (SupermarketsRepository)context.getBean(SupermarketsRepository.class);
        Supermarket fiveOchka = Supermarket.builder().description("Продуктовый магазин для нищебродов. Сам там затариваюсь.").title("5ochka").state("Draft").build();
        Supermarket fkuzWill = Supermarket.builder().description("Продуктовый магазин для изысканных гураманов. На самом деле тот же 5ochka.").title("FkuzWill").state("Open").build();
        supermarketsRepository.saveAll(Arrays.asList(fiveOchka, fkuzWill));
        Product chocolate = Product.builder().name("Шоколадка").price(100.0).supermarket(fiveOchka).build();
        Product soda = Product.builder().name("Газировка").price(200.0).supermarket(fiveOchka).build();
        Product crisps = Product.builder().name("Чипсы").price(150.0).supermarket(fiveOchka).build();
        Product banana = Product.builder().name("Связка бананов").price(100.0).supermarket(fkuzWill).build();
        Product bred = Product.builder().name("Хлеб").price(100.0).supermarket(fkuzWill).build();
        Product juse = Product.builder().name("Сок").price(100.0).supermarket(fkuzWill).build();
        Product cottageСheese = Product.builder().name("Какое-то коттеджное молоко, хз. На самом деле обычный творожок.").price(200.0).supermarket(fiveOchka).build();
        Product milk = Product.builder().name("Овсяное молочко. Гурманы обычное не пьют.").price(100.0).supermarket(fiveOchka).build();

        productsRepository.saveAll(Arrays.asList(chocolate, soda, crisps, banana, cottageСheese, milk, bred, juse));

        Buyer mrFerenezz = Buyer.builder().firstName("Мистер").lastName("Ferenezz").build();
        Buyer mrSalimov = Buyer.builder().firstName("Мистер").lastName("Zorro").build();
        buyersRepository.saveAll(Arrays.asList(mrFerenezz, mrSalimov));
        Basket.builder().buyer(mrFerenezz).supermarket(fkuzWill).build();
        Basket.builder().buyer(mrSalimov).supermarket(fiveOchka).build();
    }

    @Bean
    public SecurityWebFilterChain corsGlobalSpringSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf().disable();
        return http.build();
    }




}

