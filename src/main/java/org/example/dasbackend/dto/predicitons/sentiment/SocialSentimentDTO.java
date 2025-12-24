package org.example.dasbackend.dto.predicitons.sentiment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialSentimentDTO {

    private AggregateSocialDTO aggregate;

    @JsonProperty("fear_greed_index")
    private FearGreedIndexDTO fearGreedIndex;

    @JsonProperty("reddit_posts")
    private List<Object> redditPosts;
}
