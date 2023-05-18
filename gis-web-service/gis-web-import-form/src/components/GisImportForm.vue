<template>
  <div class="grid gap-1 grid-cols-1 grid-rows-2">
    <textarea v-model="this.geojsonInput" rows="13" class="geojson-input text-black"></textarea>
    <p>{{this.userToken}}</p>
    <button @click="this.addNewGeojson()">Dodaj novi Geojson</button>
  </div>
</template>

<script>
import {UserStore} from "@/stores/user";

export default {
  name: "GisImportForm",
  data() {
    return {
      geojsonInput: "",
      userToken: ""
    }
  },
  mounted() {
    window.addEventListener('message', function(event) {
      console.log(event);
      console.log(event.data.value);
      UserStore().setToken(event.data.value);
    }, false);
  },
  methods: {
    addNewGeojson() {
      this.userToken = UserStore().getToken();
    }
  }
}
</script>

<style scoped>
.geojson-input {
  width: 400px;
}
</style>