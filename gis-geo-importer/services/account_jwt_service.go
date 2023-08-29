package service

import (
	"context"
	"gis-geo-importer/configs"
	"gis-geo-importer/grpc"
	"google.golang.org/grpc"
	"google.golang.org/grpc/credentials/insecure"
	"log"
	"strconv"
	"strings"
	"time"
)

//protoc --proto_path=proto --go_out=. --go-grpc_out=. JwtGrpc.proto

type AccountJwtService struct{}

func (service *AccountJwtService) CheckIfTokenIsValid(token string) (isValid bool) {
	isValid = false

	conn, err := grpc.Dial(configs.GetENVByKey("ACCOUNT_SERVICE"), grpc.WithTransportCredentials(insecure.NewCredentials()))
	if err != nil {
		log.Printf("(CheckIfTokenIsValid) There was an error connecting to Account service gRPC: %v", err)
		return
	}

	defer conn.Close()

	client := grpc.NewJwtGrpcClient(conn)

	ctx, cancel := context.WithTimeout(context.Background(), time.Second)
	defer cancel()

	req := grpc.JwtRequest{
		Token: strings.Replace(token, "Bearer ", "", -1),
	}

	res, err := client.CheckJwtValidity(ctx, &req)
	if err != nil {
		log.Printf("(CheckIfTokenIsValid) There was an error checking the jwt token %v", err)
		return
	}

	isValid, err = strconv.ParseBool(res.Valid)
	if err != nil {
		log.Printf("(CheckIfTokenIsValid) There was an error converting response string to boolean: %v", err)
		return
	}

	return
}
