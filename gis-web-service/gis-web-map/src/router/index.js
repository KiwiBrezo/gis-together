import { createRouter, createWebHistory } from 'vue-router'
import MapView from "@/views/Map.vue";
import Login from "@/views/Login.vue";
import AddGeojson from "@/views/AddGeojson.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/map',
      name: 'map',
      component: MapView
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/add-geojson',
      name: 'add-geojson',
      component: AddGeojson
    }
  ]
})

export default router
