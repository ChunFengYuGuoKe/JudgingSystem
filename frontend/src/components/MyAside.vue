<template>
    <div>
        <el-menu class="MyMenu" active-text-color="#409eff" background-color="#ffffff" text-color="#303133"
            default-active="1">
            <el-menu-item index="1">
                <span>题目列表</span>
            </el-menu-item>
            <el-menu-item index="2">
                <span>提交记录</span>
            </el-menu-item>
            <el-menu-item index="3">
                <span>班级列表</span>
            </el-menu-item>
        </el-menu>
    </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
    computed: {
        ...mapState('user', ['identity']),
        isStudent() {
            return this.identity === 'student';
        }
    },
    created() {
        console.log(this.identity);
    }
};
</script>
