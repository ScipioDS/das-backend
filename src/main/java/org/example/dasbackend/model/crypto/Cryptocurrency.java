package org.example.dasbackend.model.crypto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cryptocurrency {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String ticker;

    private String name;

    private Float price;

    private OffsetDateTime lastUpdated;
}
