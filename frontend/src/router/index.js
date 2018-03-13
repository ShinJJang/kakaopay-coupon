import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

// route-level code splitting
const HelloWorld = () => import('@/components/HelloWorld')
const CouponList = () => import('@/components/CouponList')
const CouponIssue = () => import('@/components/CouponIssue')

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/coupon/list',
      name: 'CouponList',
      component: CouponList
    },
    {
      path: '/coupon/issue',
      name: 'CouponIssue',
      component: CouponIssue
    }
  ]
})
