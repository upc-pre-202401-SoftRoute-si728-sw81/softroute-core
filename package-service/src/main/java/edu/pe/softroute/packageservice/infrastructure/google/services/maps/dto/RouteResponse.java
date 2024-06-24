package edu.pe.softroute.packageservice.infrastructure.google.services.maps.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RouteResponse {
    private List<Route> routes;
}