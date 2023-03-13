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
	filesManagerService GisService.FilesService
}

func (controller *FilesController) Init(router *gin.Engine) {
	controller.httpRouter = router
	controller.filesManagerService = GisService.FilesService{}
	controller.filesManagerService.Init()
}

func (controller *FilesController) BindEndpoints() {
	controller.httpRouter.GET("/api/v1/file/:id", controller.getFile)
	controller.httpRouter.POST("/api/v1/file", controller.uploadFile)
	controller.httpRouter.DELETE("/api/v1/file/:id", controller.deleteFile)
}

func (controller *FilesController) uploadFile(c *gin.Context) {
	var geojsonData GisModels.FeatureCollection

	err := c.ShouldBindJSON(&geojsonData)

	if err != nil {
		log.Printf("(uploadFile) There was an error with parsing recived json: %v", err)

		c.JSON(http.StatusInternalServerError, gin.H{
			"error": fmt.Sprintf("There was an error with parsing recived json: %v", err),
		})

		return
	}

	insertedId, err := controller.filesManagerService.SaveNewFile(&geojsonData)
	if err != nil {
		log.Printf("(uploadFile) There was an error with inserting into DB: %v", err)

		c.JSON(http.StatusInternalServerError, gin.H{
			"error": fmt.Sprintf("There was an error with inserting into DB: %v", err),
		})

		return
	}

	c.JSON(http.StatusOK, gin.H{
		"status": fmt.Sprintf("Inserted Geojson with id: %s", insertedId),
	})
}

func (controller *FilesController) getFile(c *gin.Context) {
	fileId := c.Param("id")

	path, err := controller.filesManagerService.GetFile(fileId)
	if err != nil {
		log.Printf("(getFile) There is no file with id: %s (err: %v)", fileId, err)

		c.JSON(http.StatusNotFound, gin.H{
			"error": fmt.Sprintf("(getFile) There is no file with id: %s (err: %v)", fileId, err),
		})

		return
	}

	c.FileAttachment(path, fmt.Sprintf("%s.geojson", fileId))
}

func (controller *FilesController) deleteFile(c *gin.Context) {
	fileId := c.Param("id")

	numberOfDeleted, err := controller.filesManagerService.DeleteFile(fileId)

	if err != nil {
		log.Printf("(deleteFile) There was an error deleting geojson with id (%s) from DB: %v", fileId, err)

		c.JSON(http.StatusInternalServerError, gin.H{
			"error": fmt.Sprintf("(getFile) There was an error deleting geojson with id (%s) from DB: %v", fileId, err),
		})

		return
	}

	c.JSON(http.StatusOK, gin.H{
		"status": fmt.Sprintf("There where %d items successfuly deleted with id %s", numberOfDeleted, fileId),
	})
}
