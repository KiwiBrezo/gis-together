package main

import (
	"gis-geo-importer/GisApi"
	"gis-geo-importer/GisDao"
)

// @title           Swagger geo-import API
// @version         1.0
// @description     List of exposed endpoint and how-to use it.
// @termsOfService  http://swagger.io/terms/

// @contact.name   API Support
// @contact.url    http://www.swagger.io/support
// @contact.email  support@swagger.io

// @license.name  Apache 2.0
// @license.url   http://www.apache.org/licenses/LICENSE-2.0.html
func main() {
	var endpointRouter = GisApi.HttpApi{}

	GisDao.ConnectToMongoDB()

	endpointRouter.Init()

	endpointRouter.StartServer("0.0.0.0:8080")
}
