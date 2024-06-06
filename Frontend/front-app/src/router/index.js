import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../components/HelloWorld.vue'
import FactoryView from '../views/FactoryView.vue'
import FactoryInfoView from '../views/FactoryInfoView.vue'
import AddChocoView from '../views/AddChocoView.vue'
import EditChocoView from '../views/EditChocoView.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/factories',
      name: 'factores',
      component: FactoryView
    },
    {
      path: '/factoryInfo',
      name: 'factoryInfo',
      component: FactoryInfoView
    },
    {
      path: '/addChoco',
      name: 'addChoco',
      component: AddChocoView
    },
    {
      path: '/editChoco/:id',
      name: 'editChoco',
      component: EditChocoView
    }
]
})
export default router
