package service

import (
	"context"
	amqp "github.com/rabbitmq/amqp091-go"
	"log"
	"time"
)

type NewGeojsonPublishService struct{}

func (service *NewGeojsonPublishService) PublishGeojsonToClients(geojsonId string) {
	conn, err := amqp.Dial("amqp://guest:guest@localhost:5672/")
	if err != nil {
		log.Printf("(PublishGeojsonToClients) There was and error connecting to rebbitmq server: %v", err)
		return
	}
	defer conn.Close()

	ch, err := conn.Channel()
	if err != nil {
		log.Printf("(PublishGeojsonToClients) There was and error opening a chanell to rebbitmq: %v", err)
		return
	}
	defer ch.Close()

	q, err := ch.QueueDeclare(
		"new-geojson-published", // name
		true,                    // durable
		false,                   // delete when unused
		false,                   // exclusive
		false,                   // no-wait
		nil,                     // arguments
	)
	if err != nil {
		log.Printf("(PublishGeojsonToClients) There was and error creating a Queue: %v", err)
		return
	}

	ctx, cancel := context.WithTimeout(context.Background(), 5*time.Second)
	defer cancel()

	err = ch.PublishWithContext(ctx,
		"new-geojson-published", // exchange
		q.Name,                  // routing key
		false,                   // mandatory
		false,                   // immediate
		amqp.Publishing{
			ContentType: "text/plain",
			Body:        []byte(geojsonId),
		})

	if err != nil {
		log.Printf("(PublishGeojsonToClients) There was and error publishing the massage: %v", err)
		return
	}

	log.Printf("(PublishGeojsonToClients) Succesfuly published message for new geojson: %s", geojsonId)
}
