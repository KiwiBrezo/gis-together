import { createRouter, createWebHistory } from 'vue-router'
import GisImportForm from "@/components/GisImportForm.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'import-form',
      component: GisImportForm
    }
  ]
})

export default router
