class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller: 'routeCalculation', action: 'index')
        "/route"(controller: 'routeCalculation', action: 'route')
		"500"(view:'/error')
	}
}
