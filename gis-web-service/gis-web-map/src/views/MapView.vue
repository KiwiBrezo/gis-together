<template>
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

export default {
  components: {
    LMap,
    LTileLayer,
  },
  data() {
    return {
      zoom: 16,
    };
  },
};
</script>

<style></style>