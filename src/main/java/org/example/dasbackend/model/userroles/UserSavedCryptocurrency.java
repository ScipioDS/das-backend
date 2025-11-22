package org.example.dasbackend.model.userroles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users_saved_cryptocurrencies")
public class UserSavedCryptocurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "saved_cryptocurrencies_id")
    private Long cryptocurrencyId;
}
