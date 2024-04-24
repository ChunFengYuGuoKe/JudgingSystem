<template>
    <div class="MyHeader">
        <div class="left">
            <div class="title">XX系统</div>
            <div class="subtitle">基于AST的程序源码分析</div>
        </div>

        <div class="right">
            <div class="user-info">
                <span style="margin-right: 20px;"> 欢迎您：{{ this.username }} </span>
                <span> 登录身份：</span>
                <span v-if="this.identity === 0">学生</span>
                <span v-if="this.identity === 1">教师</span>
            </div>

            <el-select v-model="selectedLanguageShow" @change="handleLanguageChange" placeholder="Select"
                style="width: 200px; margin-right: 20px;">
                <el-option v-for="item in this.languages" :key="item.value" :label="item.label" :value="item.value" />
            </el-select>

            <el-button type="danger" @click="logoutUser">退出</el-button>
        </div>
    </div>

</template>

<style scoped>
.MyHeader {
    background-color: #a0cfff;
    height: 100%;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.left {
    display: flex;
    align-items: center;
}

.title {
    font-size: 2em;
    margin-left: 30px;
    margin-right: 30px;
    color: #303133;
}

.subtitle {
    font-size: 1.2em;
    color: #909399;
}


.right {
    display: flex;
    align-items: center;
    margin-right: 30px;
}

.user-info {
    margin-right: 30px;
}
</style>

<script>
import { mapState, mapActions } from 'vuex';


export default {
    computed: {
        ...mapState('user', ['username', 'identity']),
        ...mapState('language', ['selectedLanguage', 'languages']),
    },
    data() {
        return {
            /**
             * 这里可能有一个待修复的bug，如果设置值为 null,，而传入的插件中不存在值为 null 的选项可能会出现问题（即显示1）
             * 原因主要是异步与同步的问题，查询插件是异步操作，而进入/homepage是同步操作
             * 可能同步操作做完时，异步操作还没有结束，那么 selectedLanguageShow 在 created 钩子函数里也难以绑定正确的值
             */
            selectedLanguageShow: null,
        };
    },
    methods: {
        ...mapActions('user', ['logoutUser']),
        ...mapActions('language', ['setSelectedLanguage']),
        handleLanguageChange(value) {
            this.setSelectedLanguage(value); // 调用 action 更新状态
        },
    }
}
</script>