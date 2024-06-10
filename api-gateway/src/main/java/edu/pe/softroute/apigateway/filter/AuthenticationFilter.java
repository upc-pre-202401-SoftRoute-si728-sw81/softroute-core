package edu.pe.softroute.apigateway.filter;

import edu.pe.softroute.apigateway.dto.JwtInfoDto;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

  private final RouteValidator validator;
  private final WebClient.Builder webClient;

  public AuthenticationFilter(RouteValidator validator, WebClient.Builder webClient) {
      super(Config.class);
      this.validator = validator;
      this.webClient = webClient;
  }

  @Override
  public GatewayFilter apply(Config config) {
      return ((exchange, chain) -> {
          if (validator.isSecured.test(exchange.getRequest())) {

            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
              return onError(exchange, HttpStatus.UNAUTHORIZED);
            }

            String tokenHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String[] chunks = tokenHeader.split(" ");

            if (chunks.length != 2 || !chunks[0].equals("Bearer")) {
              return onError(exchange, HttpStatus.UNAUTHORIZED);
            }

            String token = chunks[1];

            return webClient.build()
                .get()
                .uri("http://IAM-SERVICE/api/v1/auth/validate?token=" + token)
                .retrieve().bodyToMono(JwtInfoDto.class)
                .map( t -> {
                  exchange.getRequest().mutate()
                      .header("userId", t.getUserId())
                      .header("companyId", t.getCompanyId())
                      .build();
                  return exchange;
                })
                .flatMap(chain::filter)
                .onErrorResume(e -> onError(exchange, HttpStatus.UNAUTHORIZED));
          }
          return chain.filter(exchange);
      });
  }

  public Mono<Void> onError(ServerWebExchange exchange, HttpStatus status){
      ServerHttpResponse response = exchange.getResponse();
      response.setStatusCode(status);
      return response.setComplete();
  }

  public static class Config {}
}