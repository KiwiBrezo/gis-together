<template>
  <div class="flex w-full mb-8">
    <nav class="flex-1">
      <RouterLink to="/map">Map</RouterLink>
    </nav>
    <nav class="flex-1">
      <RouterLink to="/login">Login</RouterLink>
    </nav>
    <nav class="flex-1">
      <RouterLink to="/add-geojson">Add Geojson</RouterLink>
    </nav>
  </div>
</template>

<script>
export default {
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