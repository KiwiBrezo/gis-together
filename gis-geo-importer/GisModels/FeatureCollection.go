package GisModels

type FeatureCollection struct {
	Type     string      `json:"type"`
	Name     string      `json:"name"`
	Crs      interface{} `json:"crs"`
	Features []Feature   `json:"features"`
}
