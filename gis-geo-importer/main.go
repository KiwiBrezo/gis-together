package main

import (
	GisRouters "gis-geo-importer/GisRouters"
)

func main() {
	var endpointRouter = GisRouters.EndpointRouter{}

	endpointRouter.Init()

	endpointRouter.Router.Run("localhost:8080")
}
