<template>
    <el-table :data="classes" height="250" style="width: 100%" stripe max-height="250">
        <el-table-column prop="id" label="班级编号" width="300" />
        <el-table-column prop="count" label="学生数量" width="300" />
        <el-table-column label="操作">
            <template #default="{ row }">
                <el-button plain @click="classShow(row.id)">
                    详情
                </el-button>
            </template>
        </el-table-column>
    </el-table>

    <el-dialog v-model="dialogClassVisible" title="班级详情" width="800">
        <el-table :data="students" height="250" style="width: 100%" stripe max-height="250">
            <el-table-column prop="id" label="学生编号" width="300" />
            <el-table-column prop="score" label="平均分数" width="300" />
            <!-- <el-table-column label="操作">
                <template #default="{ row }">
                    <el-button plain @click="studentShow(row.id)">
                        详情
                    </el-button>
                </template>
            </el-table-column> -->
        </el-table>
    </el-dialog>

    <el-dialog v-model="dialogStudentVisible" title="学生详情" width="800">
        <el-table :data="submissions" height="250" style="width: 100%" stripe max-height="250">
            <el-table-column prop="id_submit" label="提交编号" width="300" />
            <el-table-column prop="title" label="题目标题" width="300" />
            <el-table-column prop="score" label="分数" width="300" />
        </el-table>
    </el-dialog>

</template>

<style></style>

<script>
import { mapState, mapGetters } from 'vuex';

export default {
    computed: {
        ...mapState('user', ['username']),
        ...mapState('class', ['classes']),
        ...mapState('language', ['selectedLanguage']),
        ...mapGetters('ip', ['classIP', 'studentIP']),
    },
    data() {
        return {
            dialogClassVisible: false,
            dialogStudentVisible: false,
            students: [],
            submissions: [],
        }
    },
    methods: {
        classShow(id) {
            // console.log(id);
            fetch(`${this.studentIP}/${id}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
            })
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    } else {
                        throw new Error('Network response was not ok.');
                    }
                })
                .then(data => {
                    if (data.code % 10 === 1) {
                        this.students = data.data;
                        this.dialogClassVisible = true;
                    } else {
                        this.$message.error(data.msg);
                    }
                })
                .catch(error => {
                    // 请求失败，处理错误
                    this.$message.error('出现错误' + error);
                });
        },

        studentShow(id_stu) {
            // console.log('展示学生信息，访问url:', `${this.studentIP}/${id_class}/${id_stu}`);
            fetch(`${this.submitIP}/${id_stu}/${this.selectedLanguage}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
            })
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    } else {
                        throw new Error('Network response was not ok.');
                    }
                })
                .then(data => {
                    if (data.code % 10 === 1) {
                        this.submissions = data.data;
                        this.dialogStudentVisible = true;
                    } else {
                        this.$message.error(data.msg);
                    }
                })
                .catch(error => {
                    // 请求失败，处理错误
                    this.$message.error('出现错误' + error);
                });
        }
    }
}
</script>