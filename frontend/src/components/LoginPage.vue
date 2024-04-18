<template>

    <div class="LoginPage">
        <el-row justify="center" align="middle">
            <h2>XX平台</h2>
        </el-row>

        <el-row justify="center" align="middle">
            <p>基于AST的程序源码分析</p>
        </el-row>

        <el-row justify="center" align="middle">
            <div class="avatar">
                <el-avatar :size="100"> user </el-avatar>
            </div>
        </el-row>

        <form action="/login" method="POST">
            <el-row justify="center" align="middle">
                <el-col :span="8">
                    <div class="form-group">
                        <label for="user_id">用户名:</label>
                    </div>
                </el-col>
                <el-col :span="12">
                    <div class="form-group">
                        <el-input v-model="formData.user_id" style="width: 100%" placeholder="Please input" />
                    </div>
                </el-col>
            </el-row>

            <el-row justify="center" align="middle">
                <el-col :span="8">
                    <div class="form-group">
                        <label for="password">密码:</label>
                    </div>
                </el-col>
                <el-col :span="12">
                    <div class="form-group">
                        <el-input v-model="formData.password" style="width: 100%" type="password"
                            placeholder="Please input password" show-password />
                    </div>
                </el-col>
            </el-row>

            <el-row justify="center" align="middle">
                <el-col :span="8">
                    <div class="form-group">
                        <label for="identity">登录身份:</label>
                    </div>
                </el-col>
                <el-col :span="12">
                    <div class="form-group">
                        <el-select v-model="formData.identity" placeholder="Select" size="large" style="width: 80%">
                            <el-option v-for="item in options" :key="item.value" :label="item.label"
                                :value="item.value" />
                        </el-select>
                    </div>
                </el-col>
            </el-row>

            <el-row justify="center" align="middle" style="margin:10%;">
                <el-col :span="12">
                    <el-button size="large" style="display: block; margin: 0 auto;">注册</el-button>
                </el-col>
                <el-col :span="12">
                    <el-button size="large" type="primary" @click="login"
                        style="display: block; margin: 0 auto;">登录</el-button>
                </el-col>
            </el-row>
        </form>

    </div>

</template>

<style scoped>
.LoginPage {
    width: 30%;
    height: 600px;
    background-color: #ffffff;
    margin: 0 auto;
    margin-top: 25px;
    padding: 20px;
    /* 添加内边距 */
    border: 1px solid #ccc;
    /* 添加边框 */
    box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
    /* 添加阴影 */
    box-sizing: border-box;
    display: flex;
    flex-direction: column;
}

.avatar {
    margin-top: 5%;
    margin-bottom: 5%;
}

.form-group {
    margin-top: 2.5%;
    margin-bottom: 2.5%;
}
</style>

<script>
import { mapActions, mapGetters } from 'vuex';

export default {
    computed: {
        ...mapGetters('ip', ['loginIP', 'pluginsIP']),
    },
    data() {
        return {
            formData: {
                user_id: '',
                password: '',
                identity: '' // 选中的值
            },
            options: [ // 下拉选项
                { value: 1, label: '学生' },
                { value: 2, label: '教师' },
            ]
        };
    },
    methods: {
        ...mapActions('user', ['loginUser']),
        ...mapActions('language', ['setPlugins']),
        getPlugin() {
            console.log('调用getPlugin方法设置插件');
            fetch(this.pluginsIP, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
            })
                .then(response => {
                    if (response.ok) {
                        // 处理成功响应
                        console.log('成功响应');

                        return response.json(); // 返回一个 Promise，解析后得到 JSON 数据
                    } else {
                        // 处理失败响应
                        console.log('失败响应：', response);

                        throw new Error('Network response was not ok.');
                    }
                })
                .then(data => {
                    this.setPlugins({
                        payload: data.data
                    });
                })
                .catch(error => {
                    // 处理请求错误
                    console.log('出现错误' + error);
                });
        },
        login() {
            // 处理按钮点击事件的代码
            console.log('按钮被点击了！');
            console.log('后端服务器地址:' + this.$store.state.ip.backendIP);
            console.log('访问地址:' + this.loginIP);

            // 使用 fetch 或 axios 发送 POST 请求到指定的 URL 地址
            fetch(this.loginIP, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(this.formData),
                // mode: 'no-cors'
            })
                .then(response => {
                    if (response.ok) {
                        // 处理成功响应
                        console.log('成功响应');

                        return response.json(); // 返回一个 Promise，解析后得到 JSON 数据
                    } else {
                        // 处理失败响应
                        console.log('失败响应：', response);

                        throw new Error('Network response was not ok.');
                    }
                })
                .then(data => {
                    // 处理响应的 JSON 数据
                    // console.log(data);
                    this.loginUser({
                        username: this.formData.user_id,
                        identity: this.formData.identity,
                        jwt: data.data
                    });
                    // console.log(this.formData.user_id, this.formData.identity, data.data);
                    this.getPlugin();
                })
                .catch(error => {
                    // 处理请求错误
                    console.log('出现错误' + error);
                });
        }
    },
};

</script>
