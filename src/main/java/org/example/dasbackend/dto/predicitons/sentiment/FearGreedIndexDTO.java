package org.example.dasbackend.dto.predicitons.sentiment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FearGreedIndexDTO {

    private String classification;

    private String sentiment;

    private String timestamp;

    private Integer value;
}
