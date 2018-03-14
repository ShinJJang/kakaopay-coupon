<template>
  <div>
    <h1>관리자 쿠폰 발급</h1>
    <el-row :gutter="20">
      <el-col :span="14">
        <div class="grid-content">
          <el-input
            placeholder="user@email.com"
            v-model="email"
            clearable>
          </el-input>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="grid-content">
          <el-button plain @click="issueCoupon">발급</el-button>
        </div>
      </el-col>
    </el-row>
    <div v-if="hasMsg" class="msg-content">
      <el-alert
        :title="msg"
        :type="isError"
        show-icon>
      </el-alert>
    </div>
  </div>
</template>

<script>
export default {
  name: 'CouponIssue',
  data () {
    return {
      email: '',
      msg: '',
      errorCode: ''
    }
  },
  computed: {
    hasMsg: function () {
      return this.msg.length > 0
    },
    isError: function () {
      return (this.errorCode.length > 0) ? 'error' : 'success'
    }
  },
  methods: {
    issueCoupon: function () {
      const data = { 'email': this.email }
      const baseURI = 'http://localhost:8080'
      this.$http.post(`${baseURI}/api/v1/coupon`, data)
        .then((result) => {
          this.msg = this.email + '으로 쿠폰이 발급 되었습니다.'
          this.errorCode = ''
          this.$emit('coupon-issued')
        }).catch((error) => {
          this.msg = error.response.data.msg
          this.errorCode = error.response.data.errorCode
        })
    }
  }
}
</script>

<style>
.msg-content {
  margin-top: 20px;
  width: 400px;
}
</style>
