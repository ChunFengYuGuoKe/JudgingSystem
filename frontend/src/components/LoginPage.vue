<template>
    <div class="LoginPage">
        <el-row justify="center" align="middle">
            <h2>XX平台</h2>
        </el-row>

        <el-row justify="center" align="middle">
            <p>基于AST的程序源码分析</p>
        </el-row>

        <el-row justify="center" align="middle">
            <div style="margin-top: 5%; margin-bottom: 5%;">
                <el-avatar :size="100"> user </el-avatar>
            </div>
        </el-row>

        <el-form :model="form" :rules="rules" ref="formData" label-width="140px" style="text-align: center;">
            <el-form-item label="用户名" prop="username">
                <el-input v-model="form.username" style="width: 200px" placeholder="Please input" />
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input v-model="form.password" style="width: 200px" type="password"
                    placeholder="Please input password" show-password />
            </el-form-item>
            <el-form-item label="登录身份" prop="identity">
                <el-radio-group v-model="form.identity">
                    <el-radio :value="0" size="large" border>学生</el-radio>
                    <el-radio :value="1" size="large" border>教师</el-radio>
                </el-radio-group>
            </el-form-item>

        </el-form>
        <div style="text-align: center; margin-top: 20px;">
            <el-button size="large" @click="register" style="margin-right: 40px;">注册</el-button>
            <el-button size="large" type="primary" @click="login" style="margin-left: 40px;">登录</el-button>
        </div>
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
    border: 1px solid #ccc;
    box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
    box-sizing: border-box;
    display: flex;
    flex-direction: column;
}
</style>

<script>
import { mapActions, mapGetters, mapState } from 'vuex';

export default {
    computed: {
        ...mapState('user', ['username']),
        ...mapGetters('ip', ['loginIP', 'pluginsIP', 'classIP']),
    },
    data() {
        return {
            form: {
                username: '',
                password: '',
                identity: 0
            },
            rules: {
                username: [
                    { required: true, message: '请输入用户名', trigger: 'blur' },
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                ],
            }
        };
    },
    methods: {
        ...mapActions('user', ['loginUser']),
        ...mapActions('language', ['setLanguages']),
        ...mapActions('class', ['fetchClasses']),

        getPlugin() {
            // console.log('调用getPlugin方法设置插件');
            fetch(this.pluginsIP, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
            })
                .then(response => {
                    if (response.ok) {
                        // 处理成功响应
                        return response.json(); // 返回一个 Promise，解析后得到 JSON 数据
                    } else {
                        // 处理失败响应
                        throw new Error('Network response was not ok.');
                    }
                })
                .then(data => {
                    if (data.code % 10 === 1) {
                        this.setLanguages(data.data);
                    } else {
                        this.$message.error(data.msg);
                    }
                })
                .catch(error => {
                    console.log('出现错误' + error);
                });
        },

        login() {
            // 处理按钮点击事件的代码
            this.$refs.formData.validate(valid => {
                if (valid) {
                    // console.log("登录功能，向后端发送表单:", this.form);
                    fetch(this.loginIP, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                            'token': '',
                        },
                        body: JSON.stringify(this.form),
                    })
                        .then(response => {
                            if (response.ok) {
                                // 处理成功响应
                                return response.json(); // 返回一个 Promise，解析后得到 JSON 数据
                            } else {
                                // 处理失败响应
                                throw new Error('Network response was not ok.');
                            }
                        })
                        .then(data => {
                            // 处理响应的 JSON 数据
                            if (data.code % 10 === 1) {
                                this.getPlugin();
                                this.$message({
                                    message: '登录成功',
                                    type: 'success',
                                });
                                this.loginUser({
                                    username: this.form.username,
                                    identity: this.form.identity,
                                    jwt: data.data
                                });
                                if (this.form.identity == 1) {
                                    this.fetchClasses({
                                        url: `${this.classIP}/${this.username}`,
                                    });
                                }
                            } else {
                                this.$message.error(data.msg);
                            }
                        })
                        .catch(error => {
                            // 处理请求错误
                            this.$message.error('出现错误：' + error);
                        });
                } else {
                    this.$message.error('表单验证失败');
                    return false;
                }
            });
        },

        register() {
            this.$message('暂未开通注册功能');
        }
    },
};

</script>
