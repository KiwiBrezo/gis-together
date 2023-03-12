package GisDao

import (
	"context"
	"fmt"
	"gis-geo-importer/GisModels"
	"time"
)

type FileDBConnector struct {
}

func (f *FileDBConnector) SaveGeojsonToDB(geojson *GisModels.FeatureCollection) (id string, err error) {
	ctx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
	defer cancel()

	result, err := GetCollection("geojson").InsertOne(ctx, geojson)
	if err != nil {
		return
	}

	return fmt.Sprintf("%s", result.InsertedID), nil
}

func (f *FileDBConnector) DeleteGeojsonToDB(id string) (idDeleted string, err error) {

	return "", nil
}
