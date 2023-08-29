package test

import (
	"gis-geo-importer/api"
	"github.com/go-playground/assert/v2"
	"net/http"
	"net/http/httptest"
	"testing"
)

func Test_httpPing(t *testing.T) {
	httpApi := GisApi.HttpApi{}
	httpApi.Init()

	w := httptest.NewRecorder()
	req, _ := http.NewRequest("GET", "/ping", nil)
	httpApi.GetRouter().ServeHTTP(w, req)

	assert.Equal(t, 200, w.Code)
}

/*func Test_httpSwagger(t *testing.T) {
	httpApi := api.HttpApi{}
	httpApi.Init()

	w := httptest.NewRecorder()
	req, _ := http.NewRequest("GET", "/swagger/index.html", nil)
	httpApi.GetRouter().ServeHTTP(w, req)

	assert.Equal(t, 200, w.Code)
}*/
