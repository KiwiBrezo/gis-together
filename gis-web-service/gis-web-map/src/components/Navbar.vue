<template>
  <div class="flex w-full mb-8">
    <nav class="flex-1">
      <RouterLink to="/map">Map</RouterLink>
    </nav>
    <nav class="flex-1" v-if="!UserStore().isLogedIn()">
      <RouterLink to="/login">Login</RouterLink>
    </nav>
    <nav class="flex-1" v-if="UserStore().isLogedIn()">
      <RouterLink to="/add-geojson">Add Geojson</RouterLink>
    </nav>
  </div>
</template>

<script>
import {UserStore} from "@/stores/user";

export default {
  methods: {UserStore},
  components: { },
  data() {
    return { };
  },
};
</script>

<style scoped>
.menu {
  width: 100%;
  height: 30px;
  background: red;
}

</style>