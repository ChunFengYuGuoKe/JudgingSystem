<template>
    <el-table :data="ranks" height="250" style="width: 100%" stripe max-height="250">
        <el-table-column prop="id" label="学生编号" width="300" />
        <el-table-column prop="clazz" label="学生班级" width="300" />
        <el-table-column prop="score" label="分数" width="300" />
    </el-table>
</template>

<script>
import { mapState } from 'vuex'

export default {
    computed: {
        ...mapState('rank', ['ranks']),
    },
    methods: {
    }
}
</script>
