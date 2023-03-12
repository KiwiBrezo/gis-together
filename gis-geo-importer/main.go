package main

import (
	GisApi "gis-geo-importer/GisApi"
	"gis-geo-importer/GisDao"
)

func main() {
	var endpointRouter = GisApi.HttpApi{}

	GisDao.ConnectToMongoDB()

	endpointRouter.Init()

	endpointRouter.StartServer("localhost:8080")
}
