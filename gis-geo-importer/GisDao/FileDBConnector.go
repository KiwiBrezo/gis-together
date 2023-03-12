package GisDao

import (
	"context"
	"fmt"
	"gis-geo-importer/GisModels"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/bson/primitive"
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

func (f *FileDBConnector) DeleteGeojsonFromDB(id string) (numberOfDeleted int, err error) {
	ctx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
	defer cancel()

	objId, _ := primitive.ObjectIDFromHex(id)

	result, err := GetCollection("geojson").DeleteOne(ctx, bson.M{"_id": objId})
	if err != nil {
		return
	}

	return int(result.DeletedCount), nil
}
