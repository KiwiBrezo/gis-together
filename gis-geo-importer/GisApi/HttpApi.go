package GisApi

import (
	"gis-geo-importer/GisControllers"
	"github.com/gin-gonic/gin"
)

type HttpApi struct {
	Router *gin.Engine
}

func (api *HttpApi) Init() *HttpApi {
	api.Router = gin.Default()
	api.bindEndpoints()

	return api
}

func (api *HttpApi) StartServer(addr string) {
	api.Router.Run(addr)
}

func (api *HttpApi) bindEndpoints() {
	filesController := GisControllers.FilesController{}
	filesController.Init(api.Router)
	filesController.BindEndpoints()
}
