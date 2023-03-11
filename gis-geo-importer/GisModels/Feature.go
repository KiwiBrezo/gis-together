package GisModels

type Feature struct {
	Type       string      `json:"type"`
	Properties interface{} `json:"properties"`
	Geometry   Geometry    `json:"geometry"`
}
