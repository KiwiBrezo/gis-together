<template>
  <div class="grid gap-1 grid-cols-1 grid-rows-2">
    <textarea v-model="this.geojsonInput" rows="20" class="geojson-input"></textarea>
    <button @click="this.addNewGeojson()">Dodaj novi Geojson</button>
  </div>
</template>

<script>
export default {
  name: "GisImportForm",
  data() {
    return {
      geojsonInput: ""
    }
  },
  methods: {
    addNewGeojson() {

    }
  }
}
</script>

<style scoped>
.geojson-input {
  width: 400px;
}
</style>