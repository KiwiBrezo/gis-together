import {defineStore} from "pinia";
import {computed, ref} from "vue";

export const UserStore = defineStore('user', () => {
    const jwtToken = ref("")
    function setToken(token) {
        jwtToken.value = token;
    }

    function getToken() {
        return jwtToken.value;
    }

    function isLogedIn() {
        return jwtToken.value != "";
    }

    return { setToken, getToken, isLogedIn }
})