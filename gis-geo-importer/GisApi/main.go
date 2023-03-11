package GisApi

import (
	"gis-geo-importer/GisControllers"
	"github.com/gin-gonic/gin"
)

type HttpApi struct {
	Router *gin.Engine
}

func (r *HttpApi) Init() *HttpApi {
	r.Router = gin.Default()
	r.bindEndpoints()

	return r
}

func (r *HttpApi) StartServer(addr string) {
	r.Router.Run(addr)
}

func (r *HttpApi) bindEndpoints() {
	filesController := GisControllers.FilesController{}
	filesController.Init(r.Router)
	filesController.BindEndpoints()
}
