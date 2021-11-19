package com.cbrl.soap.service.example.vehicle;

import com.cbrl.soap.service.example.vehicle.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.cbrl.soap.service.example.vehicle.domain.SearchVehicleRequest;
import com.cbrl.soap.service.example.vehicle.domain.SearchVehicleResponse;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cebrail
 * @Endpoint registers the class with Spring WS as a potential candidate for processing incoming SOAP messages.
 * @PayloadRoot is then used by Spring WS to pick the handler method based on the message’s namespace and localPart.
 * @RequestPayload indicates that the incoming message will be mapped to the method’s request parameter.
 * The @ResponsePayload annotation makes Spring WS map the returned value to the response payload.
 */

@Endpoint
public class VehicleEndPoint {
    private static final String NAMESPACE_URI = "http://3xs.es/soap/service/example/vehicle/domain";

    @Autowired
    private VehicleRepository repository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "searchVehicleRequest")
    @ResponsePayload
    public SearchVehicleResponse searchVehicleRequest(@RequestPayload SearchVehicleRequest request) {
        SearchVehicleResponse response = new SearchVehicleResponse();
        if (request.getMake() != null) {
            response.getVehicles().addAll(repository.getVehiclesByMake(request.getMake()));
        } else {
            response.getVehicles().addAll(repository.getVehiclesByColor(request.getColor()));
        }
        if (request.getColor() != null) {
            List<Vehicle> aux = response.getVehicles().stream()
                    .filter(it -> request.getColor().equals(it.getColor()))
                    .collect(Collectors.toList());
            response.getVehicles().clear();
            response.getVehicles().addAll(aux);
        }
        return response;
    }
}
