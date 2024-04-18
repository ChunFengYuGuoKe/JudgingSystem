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
                <span v-if="this.identity === 1">学生</span>
                <span v-if="this.identity === 2">教师</span>
            </div>

            <el-select v-model="selectedLanguage" @change="handleLanguageChange" placeholder="Select" style="width: 200px; margin-right: 20px;">
                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
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
import { mapState, mapActions, mapMutations } from 'vuex';

export default {
    computed: {
        ...mapState('user', ['username', 'identity']),
        ...mapState('language', ['selectedLanguage', 'languages']),
        // ...mapGetters('language', ['getLanguages']),
        options() {
            return this.languages;
        }
    },
    data() {
        return {
            language: 1,
        };
    },
    methods: {
        ...mapActions('user', ['logoutUser']),
        ...mapMutations('language', ['setSelectedLanguage']),
        handleLanguageChange(value) {
            this.setSelectedLanguage(value); // 调用 mutation 更新状态
        }
    }
}
</script>