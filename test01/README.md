## vehicleUrl

### service: http://localhost:8080/ws/vehicleUrl.wsdl

### PostMan (retorna XML)
    - Peticion POST
    - Body -> raw -> XML
    - Texto en el BODY
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                           xmlns:gs="http://cbrl.com/soap/service/example/vehicle/domain">
            <soapenv:Header/>
            <soapenv:Body>
                <gs:searchVehicleRequest>
                    <gs:color>Blue</gs:color>
                    <gs:make>BMW</gs:make>
                </gs:searchVehicleRequest>
            </soapenv:Body>
        </soapenv:Envelope>

### Python (retorna JSON)
    from suds.client import Client
    N4_wsdlQuery = "http://localhost:8080/ws/vehicleUrl.wsdl"
    client = Client(N4_wsdlQuery, cache=None, faults=False)
    response = client.service.searchVehicle('Blue', 'BMW')
    print response