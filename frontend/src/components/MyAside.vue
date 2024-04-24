<template>
    <div>
        <el-menu class="MyMenu" active-text-color="#409eff" background-color="#ffffff" text-color="#303133"
            default-active="1">
            <el-menu-item index="1" @click="this.setSelectedMenuItemIndex(1)">
                题目列表
            </el-menu-item>
            <el-menu-item index="2" @click="this.setSelectedMenuItemIndex(2)" v-if="this.identity === 0">
                提交记录
            </el-menu-item>
            <el-menu-item index="3" @click="this.setSelectedMenuItemIndex(3)" v-if="this.identity === 1">
                班级列表
            </el-menu-item>
            <el-menu-item index="4" @click="this.setSelectedMenuItemIndex(4)">
                排行榜
            </el-menu-item>
        </el-menu>
    </div>
</template>

<script>
import { mapState, mapMutations } from 'vuex';

export default {
    computed: {
        ...mapState('user', ['identity']),
    },
    methods: {
        ...mapMutations('menu', ['setSelectedMenuItemIndex']),
    }
};
</script>
