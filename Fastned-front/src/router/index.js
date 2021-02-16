import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'  // HV mag weg later als ik HelloWorld wegwil
import NieuweLocatieAanmelding from '../views/NieuweLocatieAanmelding.vue'
import LocatieAanmelding from '../views/LocatieAanmelding.vue'
import Documentatie from '../views/Documentatie.vue'
import Probleem from '../views/Probleem.vue'
import NieuwProbleem from '../views/NieuwProbleem.vue'
import TravelbasePakket from '../views/TravelbasePakket.vue'


Vue.use(VueRouter)

  const routes = [
    {
      path: '/TravelbasePakket',
      name: 'TravelbasePakket',
      component: TravelbasePakket
    },
    {
      path: '/NieuwProbleem',
      name: 'NieuwProbleem',
      component: NieuwProbleem
    },
    {
      path: '/Probleem',
      name: 'Probleem',
      component: Probleem
    },
    {
      path: '/Documentatie',
      name: 'Documentatie',
      component: Documentatie
    },
    {
      path: '/Documentatie',
      name: 'Documentatie',
      component: Documentatie
    },
  {
    path: '/LocatieAanmelding',
    name: 'LocatieAanmelding',
    component: LocatieAanmelding
  },
  {
    path: '/NieuweLocatieAanmelding',
    name: 'NieuweLocatieAanmelding',
    component: NieuweLocatieAanmelding
  },
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
