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
                <el-main>Main</el-main>
            </el-container>
        </el-container>
    </div>

</template>

<style scoped></style>

<script>
import MyHeader from '@/components/MyHeader.vue';
import MyAside from '@/components/MyAside.vue'

export default {
    components: {
        MyHeader,
        MyAside
    }
}
</script>