<template>
    <el-header>
        <el-menu :router="true"
            class="el-menu"
            mode="horizontal"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b">
            <el-menu-item index="/coupon/list">쿠폰 목록</el-menu-item>
            <el-menu-item index="/coupon/issue">
            <i class="el-icon-circle-plus-outline"></i>쿠폰 발급
            </el-menu-item>
            <el-menu-item index="#">
                <a href="https://github.com/ShinJJang/kakaopay-coupon" target="_blank">Github</a>
            </el-menu-item>
        </el-menu>
        <!-- <router-link to="/" exact>
            <img class="logo" src="~./assets/logo.png" alt="logo">
        </router-link> -->
    </el-header>
</template>

<script>
export default {
  name: 'Header'
}
</script>

<style>
</style>
