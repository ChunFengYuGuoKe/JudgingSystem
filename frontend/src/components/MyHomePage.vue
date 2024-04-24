<template>
    <div>
        <el-container class="MyHomePage">
            <el-header style="margin: 0%; padding: 0%;">
                <MyHeader />
            </el-header>

            <el-container>
                <el-aside width="200px">
                    <MyAside />
                </el-aside>
                <el-main style="margin: 0%; padding: 0%;">
                    <component :is="selectedComponent"></component>
                </el-main>
            </el-container>
        </el-container>
    </div>

</template>

<style scoped></style>

<script>
import { mapState } from 'vuex';
import MyHeader from './MyHeader.vue';
import MyAside from './MyAside.vue'
import MyProblems from './MyProblems.vue';
import MySubmission from './MySubmission.vue';
import MyClasses from './MyClasses.vue';
import MyRank from './MyRank.vue'

export default {
    computed: {
        ...mapState('menu', ['selectedMenuItemIndex']),
        selectedComponent() {
            switch (this.selectedMenuItemIndex) {
                case 1:
                    return MyProblems; // 题目列表组件
                case 2:
                    return MySubmission; // 提交记录组件
                case 3:
                    return MyClasses; // 班级列表组件
                case 4:
                    return MyRank; // 排行榜组件
                default:
                    return null;
            }
        }
    },
    components: {
        MyHeader,
        MyAside
    }
}
</script>