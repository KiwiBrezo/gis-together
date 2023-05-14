<template>
  <div>
    <button v-if="UserStore().isLogedIn()" @click="this.connectToServerAndGetGeojson()">Pridruzi se seji!</button>
  </div>
  <div style="height:800px; width:1200px">
    <l-map ref="map" v-model:zoom="zoom" :center="[46.559007, 15.638031]" :use-global-leaflet="false">
      <l-tile-layer
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          layer-type="base"
          name="OpenStreetMap"
      ></l-tile-layer>
    </l-map>
  </div>
</template>

<script>
import "leaflet/dist/leaflet.css";
import { LMap, LTileLayer } from "@vue-leaflet/vue-leaflet";
import {UserStore} from "@/stores/user";
import SockJS from "sockjs-client/dist/sockjs";
import Stomp from "webstomp-client";

export default {
  components: {
    LMap,
    LTileLayer,
  },
  data() {
    return {
      zoom: 16,
      sock: null,
      client: null
    };
  },
  mounted() {
    this.sock = new SockJS('http://localhost:8082/geojson-connector');
    this.client = Stomp.over(this.sock);

    this.client.connect({}, frame => {
      this.client.subscribe("/topic/feature-collections", payload => {
        const geojson = JSON.parse(payload.body);

        console.log("Recived data on startup");
        console.log(geojson);

      });

      this.client.subscribe("/topic/push/feature-collections/new", payload => {
        const geojson = JSON.parse(payload.body);

        console.log("Recived data for new geojson");
        console.log(geojson);

      });

      this.client.subscribe("/topic/push/feature-collections/all", payload => {
        const geojson = JSON.parse(payload.body);

        console.log("Recived data all geojson from db");
        console.log(geojson);
      });
    });
  },
  methods: {
    UserStore,
    connectToServerAndGetGeojson() {
      this.client.send('/app/get-all', {}, JSON.stringify({jwtToken: UserStore().getToken() || ""}));
    }
  }
};
</script>

<style></style>