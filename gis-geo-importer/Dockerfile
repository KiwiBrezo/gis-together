FROM golang:alpine AS builder
# Install git.
# Git is required for fetching the dependencies.
RUN apk update && apk add --no-cache 'git=~2'

# Install dependencies
ENV GO111MODULE=on
WORKDIR $GOPATH/src/packages/gis-geo-importer/
COPY . .

# Fetch dependencies.
# Using go get.
RUN go get -d -v

# Build the binary.
RUN CGO_ENABLED=0 GOOS=linux go build -a -installsuffix cgo -o /go/gis-geo-importer .

############################
FROM alpine:3

WORKDIR /

# Copy our static executable.
COPY --from=builder /go/gis-geo-importer /go/gis-geo-importer
#COPY public /go/public
COPY .env.docker /go/.env

RUN mkdir /go/files

ENV PORT 8080
ENV GIN_MODE release
EXPOSE 8080

WORKDIR /go

# Run the Go Gin binary.
ENTRYPOINT ["/go/gis-geo-importer"]