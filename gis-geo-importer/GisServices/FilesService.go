package service

import (
	"encoding/json"
	"fmt"
	"gis-geo-importer/GisModels"
	"gis-geo-importer/GisReposetory"
	"github.com/anjieych/go-activeMQ"
	"log"
	"os"
)

type FilesService struct {
	pathToFileFolder string
	fileDBConnector  *GisReposetory.FilesRepository
}

func (service *FilesService) Init() {
	service.pathToFileFolder = "./files"
	service.fileDBConnector = &GisReposetory.FilesRepository{}
}

func (service *FilesService) SaveNewFile(geojson *GisModels.FeatureCollection) (insertedId string, err error) {
	insertedId, err = service.fileDBConnector.SaveGeojsonToDB(geojson)
	if err != nil {
		return
	}

	file, _ := json.MarshalIndent(geojson, "", " ")

	errFile := os.WriteFile(fmt.Sprintf("%s/%s.geojson", service.pathToFileFolder, insertedId), file, 0644)

	if errFile != nil {
		log.Printf("(SaveNewFile) There was an error saving Geojson to disk: %v", errFile)
	}

	errMq := activeMQ.NewActiveMQ("localhost:61613").Send("/queue/insertedGeojson", "test from 1")

	if errMq != nil {
		log.Printf("(SaveNewFile) There was an error sending message to ActiveMq: %v", errMq)
	}

	return
}

func (service *FilesService) GetFile(id string) (path string, err error) {
	path = fmt.Sprintf("%s/%s.geojson", service.pathToFileFolder, id)

	_, err = os.Stat(path)

	return
}

func (service *FilesService) DeleteFile(id string) (numberOfDeleted int, err error) {
	numberOfDeleted, err = service.fileDBConnector.DeleteGeojsonFromDB(id)
	if err != nil {
		return
	}

	errFile := os.Remove(fmt.Sprintf("%s/%s.geojson", service.pathToFileFolder, id))

	if errFile != nil {
		log.Printf("(DeleteFile) There was an error deleteing Geojson from disk: %v", errFile)
	}

	return
}
