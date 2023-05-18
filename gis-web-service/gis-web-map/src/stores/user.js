import {defineStore} from "pinia";
import {computed, ref} from "vue";

export const UserStore = defineStore('user', () => {
    const jwtToken = ref("");

    function setToken(token) {
        jwtToken.value = token;
    }

    function getToken() {
        return jwtToken.value;
    }

    function isLogedIn() {
        return jwtToken.value != "";
    }

    const geojson = ref([]);

    function setGeojson(json) {
        geojson.value = json;
    }

    function getGeojson() {
        return geojson.value;
    }

    function appendGeojson(json) {
        return geojson.value.concat(json);
    }

    return { setToken, getToken, isLogedIn, setGeojson, getGeojson, appendGeojson }
})