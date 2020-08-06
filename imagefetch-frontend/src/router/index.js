import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

import PageOne from '../views/PageOne.vue'
import PageTwo from '../views/PageTwo.vue'
import PageThree from '../views/PageThree.vue'
import PageFour from '../views/PageFour.vue'
import ImageFetch from "../views/ImageFetch";

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'App',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/index',
    name: 'Index',
    component: () => import('../views/Index.vue')
  },
    {
      path: '/imageFetch/serviceConfig',
      name: 'ImageFetch-ServiceConfig',
      component: () => import('../views/imageFetch/ServiceConfig')
    },
    {
      path: '/imageFetch/taskConfig',
      name: 'ImageFetch-TaskConfig',
      component: () => import('../views/imageFetch/TaskConfig')
    },
    {
      path: '/imageFetch/task',
      name: 'ImageFetch-Task',
      component: () => import('../views/imageFetch/Task')
    }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
