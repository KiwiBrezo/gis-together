<script setup>
import { RouterLink, RouterView } from 'vue-router'
import NavComponent from "@/components/NavComponent.vue";
</script>

<template>
  <div class="grid grid-cols-1 grid-rows-1">
    <NavComponent />

    <RouterView />
  </div>
</template>

<style scoped>

</style>
