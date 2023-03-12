package GisDao

import (
	"context"
	"gis-geo-importer/GisConfigs"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
	"log"
	"time"
)

var mongoDBInstance *mongo.Client = nil

func ConnectToMongoDB() {
	if mongoDBInstance != nil {
		return
	}

	DBInstance, err := mongo.NewClient(options.Client().ApplyURI(GisConfigs.GetENVByKey("MONGODB_URL")))
	if err != nil {
		log.Fatalf("(ConnectToMongoDB) There was an error creating the mongoDB mongoDBInstance: %v", err)
	}

	ctx, _ := context.WithTimeout(context.Background(), 10*time.Second)
	err = DBInstance.Connect(ctx)

	if err != nil {
		log.Fatalf("(ConnectToMongoDB) There was an error connecting to the mongoDB with mongoDBInstance: %v", err)
	}

	//ping the database
	err = DBInstance.Ping(ctx, nil)
	if err != nil {
		log.Fatalf("(ConnectToMongoDB) There was an error pinging the mongodb database: %v", err)
	}

	mongoDBInstance = DBInstance

	log.Printf("(ConnectToMongoDB) Successfuly Connected to MongoDB")
}

func GetMongoDB() *mongo.Client {
	if mongoDBInstance == nil {
		ConnectToMongoDB()
	}

	return mongoDBInstance
}

func GetCollection(collection string) *mongo.Collection {
	return GetMongoDB().Database("gis-together").Collection(collection)
}
