<template>
  <div class="LoginPage">
    <el-container>
      <el-main>
        <form @submit.prevent="login">
          <label for="username">Username:</label>
          <input type="text" id="username" v-model="username" required>

          <label for="password">Password:</label>
          <input type="password" id="password" v-model="password" required>
          
          <el-button type="primary" round>Submit</el-button>
        </form>
      </el-main>
    </el-container>
  </div>
</template>
  
<script>
export default {
  data() {
    return {
      username: '',
      password: ''
    };
  },
  methods: {
    login() {
      console.log('Login Form');
    }
  }
};
</script>
  
<style>
html, body {
  background-color: #D5F3F4;
  margin: 0; /* 清除默认边距 */
  padding: 0; /* 清除默认内边距 */
}

.LoginPage {
  background-color: #FFFFFF;
  display: flex;
  width: 50%;
}
</style>