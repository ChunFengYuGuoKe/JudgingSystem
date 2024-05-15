<template>
    <el-table :data="filterTableData" height="250" style="width: 100%" stripe max-height="250">
        <el-table-column prop="id" label="学生编号" width="300" />
        <el-table-column prop="clazz" label="学生班级" width="300" />
        <el-table-column prop="score" label="分数" width="300" />
        <el-table-column align="right">
            <template #header>
                <el-input v-model="search" size="small" placeholder="输入班级号" width="250" />
            </template>
        </el-table-column>
    </el-table>
</template>

<script>
import { mapState } from 'vuex'

export default {
    computed: {
        ...mapState('rank', ['ranks']),
        filterTableData() {
            return this.generateTableData();
        }
    },
    methods: {
    },
    data() {
        return {
            search: null,
            generateTableData: function () {
                return this.ranks.filter(data =>
                    !this.search || data.clazz.includes(this.search)
                );
            }
        }
    },
}
</script>
