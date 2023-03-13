package GisApi

import (
	"gis-geo-importer/GisControllers"
	"gis-geo-importer/docs"
	"github.com/gin-gonic/gin"
	swaggerfiles "github.com/swaggo/files"
	ginSwagger "github.com/swaggo/gin-swagger"
)

type HttpApi struct {
	router *gin.Engine
}

func (api *HttpApi) Init() *HttpApi {
	api.router = gin.Default()
	api.bindSwagger()
	api.bindEndpoints()

	return api
}

func (api *HttpApi) StartServer(addr string) {
	api.router.Run(addr)
}

func (api *HttpApi) GetRouter() *gin.Engine {
	return api.router
}

func (api *HttpApi) bindEndpoints() {
	filesController := GisControllers.FilesController{}
	filesController.Init(api.router)
	filesController.BindEndpoints()
}

func (api *HttpApi) bindSwagger() {
	docs.SwaggerInfo.BasePath = "/api/v1"
	api.router.GET("/swagger/*any", ginSwagger.WrapHandler(swaggerfiles.Handler))
}
