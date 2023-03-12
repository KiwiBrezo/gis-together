package service

import (
	"gis-geo-importer/GisDao"
	"gis-geo-importer/GisModels"
)

type FileManager struct {
	pathToFileFolder string
	fileDBConnector  *GisDao.FileDBConnector
}

func (f *FileManager) Init() {
	f.pathToFileFolder = ""
	f.fileDBConnector = &GisDao.FileDBConnector{}
}

func (f *FileManager) SaveNewFile(geojson *GisModels.FeatureCollection) (insertedId string, err error) {
	insertedId, err = f.fileDBConnector.SaveGeojsonToDB(geojson)
	return
}

func (f *FileManager) GetFile(id string) {

}

func (f *FileManager) DeleteFile(id string) (numberOfDeleted int, err error) {
	numberOfDeleted, err = f.fileDBConnector.DeleteGeojsonFromDB(id)
	return
}
