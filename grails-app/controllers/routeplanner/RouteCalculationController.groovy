package routeplanner

import groovyx.net.http.HTTPBuilder

class RouteCalculationController {
    def routeService
    def webCallService

    def index(params)
    {
        Map model = [:]
        if (!params.redirected){ model.visible = "hidden" }
        model.errorMessage = params.errorMessage
        model
    }

    def route(params)
    {
        String errorMessage = ""
        Map model = [:]
        model.locations = new ArrayList<Map>()
        List<String> addresses = params.addresses.replaceAll(' ', '%20').split('\\r\\n')

        if (addresses.size() >= 2)
        {

            model.locations.add([distance: 0, address: addresses[0].replaceAll('%20', ' ')])

            String startingPoint = addresses[0]
            String endingPoint

            for (int ii = 1; ii < addresses.size(); ++ii)
            {
                endingPoint = addresses[ii]

                def http = new HTTPBuilder('http://maps.googleapis.com/maps/api/directions/json?'
                        + "origin=" + startingPoint + "&destination="
                        + endingPoint + "&sensor=false")

                Object json = webCallService.makeHttpRequest(http)
                if (json.status == "OK")
                    model.locations.add([distance: json.routes.legs[0].distance[0].value, address: endingPoint.replaceAll('%20', ' ')])
                else
                {
                    errorMessage = "You did not enter a valid address or one of your addresses was invalid. Please enter at least 2 valid addresses."
                    break
                }
            }

            if (errorMessage == "")
            {
                model.locations.sort { a, b ->
                    a.distance <=> b.distance ?: a.address <=> b.address
                }

                model = routeService.adjustRouteForRoundTrip(model)
                return model
            }
        }
        else { errorMessage = "You did not enter enough addresses. Please enter at least 2 valid addresses." }
        redirect(controller: 'routeCalculation', action: 'index', params: [redirected: true, errorMessage: errorMessage])
    }
}
