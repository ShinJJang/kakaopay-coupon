<template>
  <div>
    <h1>쿠폰 목록</h1>
    <el-pagination
      class="pagination"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page.sync="currentPage"
      :page-size="itemSizePerPage"
      layout="prev, pager, next, jumper"
      :total="totalItems">
    </el-pagination>
    <!-- TODO custom item size per page -->
    <div v-if="hasResult">
      <template>
        <el-table
          :data="couponList"
          stripe
          style="width: 100%">
          <el-table-column
            prop="id"
            label="ID"
            width="90">
          </el-table-column>
          <el-table-column
            prop="email"
            label="Email"
            width="180">
          </el-table-column>
          <el-table-column
            prop="code"
            label="Code"
            width="180">
          </el-table-column>
          <el-table-column
            prop="createdAt"
            label="Date">
          </el-table-column>
        </el-table>
      </template>
    </div>
    <back-to-top text="Back to top" visibleOffset="50"></back-to-top>
  </div>
</template>

<script>
import BackToTop from 'vue-backtotop'

export default {
  name: 'CouponList',
  components: {
    BackToTop
  },
  data: function () {
    return {
      couponList: [],
      itemSizePerPage: 15,
      currentPage: 1,
      totalPages: 1,
      totalItems: 1
    }
  },
  computed: {
    hasResult: function () {
      return this.couponList.length > 0
    }
  },
  methods: {
    getCouponList: function (page, size) {
      const zeroBasedPage = page - 1
      const baseURI = 'http://localhost:8080'
      const param = (page !== undefined || size !== undefined) ? `?page=${zeroBasedPage}&size=${size}` : ''
      this.$http.get(`${baseURI}/api/v1/coupon` + param)
        .then((result) => {
          this.couponList = result.data.content
          this.totalPages = result.data.totalPages
          this.totalItems = result.data.totalElements
        })
    },
    handleSizeChange (val) {
      console.log(`${val} items per page`)
    },
    handleCurrentChange (val) {
      this.page = val
      this.getCouponList(this.currentPage, this.itemSizePerPage)
    }
  },
  beforeMount () {
    this.getCouponList(this.currentPage, this.itemSizePerPage)
  }
}
</script>

<style>
.pagination {
  text-align: center;
}
</style>
