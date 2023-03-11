package main

import (
	GisApi "gis-geo-importer/GisApi"
)

func main() {
	var endpointRouter = GisApi.HttpApi{}

	endpointRouter.Init()

	endpointRouter.StartServer("localhost:8080")
}
