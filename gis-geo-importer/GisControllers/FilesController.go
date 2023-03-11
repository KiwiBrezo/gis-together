package GisControllers

import (
	"fmt"
	"gis-geo-importer/GisModels"
	GisService "gis-geo-importer/GisServices"
	"github.com/gin-gonic/gin"
	"log"
	"net/http"
)

type FilesController struct {
	httpRouter          *gin.Engine
	filesManagerService GisService.FileManager
}

func (f *FilesController) Init(router *gin.Engine) {
	f.httpRouter = router
	f.filesManagerService = GisService.FileManager{}
}

func (f *FilesController) BindEndpoints() {
	f.httpRouter.GET("/api/v1/file/:id", f.getFile)
	f.httpRouter.POST("/api/v1/file", f.uploadFile)
	f.httpRouter.DELETE("/api/v1/file/:id", f.deleteFile)
}

func (f *FilesController) uploadFile(c *gin.Context) {
	var geojsonData GisModels.FeatureCollection

	err := c.ShouldBindJSON(&geojsonData)

	if err != nil {
		log.Printf("There was an error with parsing recived json: %v", err)
		c.JSON(http.StatusInternalServerError, gin.H{
			"error": fmt.Sprintf("There was an error with parsing recived json: %v", err),
		})
	}

	f.filesManagerService.SaveNewFile(&geojsonData)

	c.JSON(http.StatusOK, gin.H{
		"status": geojsonData,
	})
}

func (f *FilesController) getFile(c *gin.Context) {
	fileId := c.Param("id")
	c.JSON(http.StatusOK, gin.H{
		"status": fmt.Sprintf("Here is file: %s", fileId),
	})
}

func (f *FilesController) deleteFile(c *gin.Context) {
	fileId := c.Param("id")
	c.JSON(http.StatusOK, gin.H{
		"status": fmt.Sprintf("Deleted file: %s", fileId),
	})
}
