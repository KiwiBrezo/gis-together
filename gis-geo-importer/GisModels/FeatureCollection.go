package GisModels

type FeatureCollection struct {
	Type     string                 `json:"type"`
	Name     string                 `json:"name"`
	Crs      map[string]interface{} `json:"crs"`
	Features []Feature              `json:"features"`
}
