package org.example.dasbackend.dto.predicitons.sentiment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsSentimentDTO {

    private AggregateNewsDTO aggregate;

    private List<Object> articles;  // Empty array in the example
}
