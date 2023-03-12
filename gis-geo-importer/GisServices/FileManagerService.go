package service

import (
	"encoding/json"
	"fmt"
	"gis-geo-importer/GisDao"
	"gis-geo-importer/GisModels"
	"log"
	"os"
)

type FileManager struct {
	pathToFileFolder string
	fileDBConnector  *GisDao.FileDBConnector
}

func (f *FileManager) Init() {
	f.pathToFileFolder = "./files"
	f.fileDBConnector = &GisDao.FileDBConnector{}
}

func (f *FileManager) SaveNewFile(geojson *GisModels.FeatureCollection) (insertedId string, err error) {
	insertedId, err = f.fileDBConnector.SaveGeojsonToDB(geojson)
	if err != nil {
		return
	}

	file, _ := json.MarshalIndent(geojson, "", " ")

	errFile := os.WriteFile(fmt.Sprintf("%s/%s.geojson", f.pathToFileFolder, insertedId), file, 0644)

	if errFile != nil {
		log.Printf("(SaveNewFile) There was an error saving Geojson to disk: %v", errFile)
	}

	return
}

func (f *FileManager) GetFile(id string) (path string, err error) {
	path = fmt.Sprintf("%s/%s.geojson", f.pathToFileFolder, id)

	_, err = os.Stat(path)

	return
}

func (f *FileManager) DeleteFile(id string) (numberOfDeleted int, err error) {
	numberOfDeleted, err = f.fileDBConnector.DeleteGeojsonFromDB(id)
	if err != nil {
		return
	}

	errFile := os.Remove(fmt.Sprintf("%s/%s.geojson", f.pathToFileFolder, id))

	if errFile != nil {
		log.Printf("(DeleteFile) There was an error deleteing Geojson from disk: %v", errFile)
	}

	return
}
