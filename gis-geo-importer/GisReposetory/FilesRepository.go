package GisReposetory

import (
	"context"
	"gis-geo-importer/GisModels"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/bson/primitive"
	"time"
)

type FilesRepository struct {
}

func (repository *FilesRepository) SaveGeojsonToDB(geojson *GisModels.FeatureCollection) (id string, err error) {
	ctx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
	defer cancel()

	result, err := GetCollection("geojson").InsertOne(ctx, geojson)
	if err != nil {
		return
	}

	return result.InsertedID.(primitive.ObjectID).Hex(), nil
}

func (repository *FilesRepository) DeleteGeojsonFromDB(id string) (numberOfDeleted int, err error) {
	ctx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
	defer cancel()

	objId, _ := primitive.ObjectIDFromHex(id)

	result, err := GetCollection("geojson").DeleteOne(ctx, bson.M{"_id": objId})
	if err != nil {
		return
	}

	return int(result.DeletedCount), nil
}
