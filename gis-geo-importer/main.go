package main

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

func getHealth(c *gin.Context) {
	c.IndentedJSON(http.StatusOK, "test")
}

func main() {
	router := gin.Default()

	router.GET("/", getHealth)

	router.Run("localhost:8080")
}
