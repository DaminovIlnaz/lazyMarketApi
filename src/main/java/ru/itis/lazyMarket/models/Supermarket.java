package ru.itis.lazyMarket.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Supermarket {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    @OneToMany(
            mappedBy = "supermarket"
    )
    @JsonIgnore
    private List<Product> products;
    @OneToMany(
            mappedBy = "supermarket"
    )
    @JsonIgnore
    private List<Basket> baskets;
    private String state;

    public void publish() {
        if (this.state.equals("Draft")) {
            this.state = "Open";
        } else if (this.state.equals("Closed")) {
            throw new IllegalStateException();
        }

    }

}

