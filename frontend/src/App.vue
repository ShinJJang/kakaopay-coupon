<template>
  <div id="app">
    <vue-progress-bar></vue-progress-bar>
    <el-container>
      <header-component></header-component>
      <el-main>
        <router-view/>
      </el-main>
      <el-footer>Footer</el-footer>
    </el-container>
  </div>
</template>

<script>
import Header from '@/components/Header'

export default {
  name: 'App',
  components: {
    'header-component': Header,
  }
}
</script>

<style>
</style>
