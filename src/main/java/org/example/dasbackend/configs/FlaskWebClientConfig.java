package org.example.dasbackend.configs;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class FlaskWebClientConfig {

    @Value("${flask.api.url:http://localhost:5000}")
    private String flaskApiUrl;

    @Value("${webclient.connect.timeout:5000}")
    private int connectTimeout;

    @Value("${webclient.read.timeout:10000}")
    private int readTimeout;

    @Value("${webclient.write.timeout:10000}")
    private int writeTimeout;

    @Bean(name = "flaskWebClient")
    public WebClient flaskWebClient() {
        return WebClient.builder()
                .baseUrl(flaskApiUrl)
                .build();
    }

}
