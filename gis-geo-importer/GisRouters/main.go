package GisRouters

import (
	"github.com/gin-gonic/gin"
	"net/http"
)

type EndpointRouter struct {
	Router *gin.Engine
}

func (r *EndpointRouter) Init() *EndpointRouter {
	r.Router = gin.Default()
	r.bindEndpoints()
	return r
}

func (r *EndpointRouter) bindEndpoints() {
	r.Router.GET("/", test)
}

func test(c *gin.Context) {
	c.IndentedJSON(http.StatusOK, gin.H{
		"test": "Hello",
	})
}
