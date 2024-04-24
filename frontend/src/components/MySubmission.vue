<template>
    <el-table :data="submissions" height="250" style="width: 100%" stripe max-height="250">
        <el-table-column prop="id_submit" label="提交编号" width="300" />
        <el-table-column prop="title" label="题目标题" width="300" />
        <el-table-column prop="score" label="分数" width="300" />
        <el-table-column label="操作">
            <template #default="{ row }">
                <el-button plain @click="submissionShow(row.id_submit)">
                    详情
                </el-button>
            </template>
        </el-table-column>
    </el-table>

    <el-dialog v-model="dialogVisible" title="提交记录" width="800">
        <el-row>
            <el-col :span="12">
                <div>
                    题目编号：{{ submission.id }}
                </div>
            </el-col>
            <el-col :span="12">
                <div>
                    题目标题：{{ submission.title }}
                </div>
            </el-col>
        </el-row>

        <el-row>
            <div>
                黑盒测试信息：{{ submission.black }}
            </div>
        </el-row>

        <el-row>
            <div>
                白盒测试信息：{{ submission.white }}
            </div>
        </el-row>

        <el-row>
            <div>
                分数：{{ submission.score }}
            </div>
        </el-row>

        <template #footer>
            <div class="dialog-footer">
                <el-button @click="dialogVisible = false">关闭</el-button>
            </div>
        </template>
    </el-dialog>
</template>

<script>
import { mapState, mapGetters } from 'vuex'

export default {
    computed: {
        ...mapState('user', ['username']),
        ...mapState('language', ['selectedLanguage']),
        ...mapState('submission', ['submissions']),
        ...mapGetters('ip', ['homeworkIP', 'submitIP']),
    },
    data() {
        return {
            dialogVisible: false,
            submission: {
                id: null,
                title: null,
                black: null,
                white: null,
                score: null
            },
        }
    },
    methods: {
        submissionShow(id) {
            fetch(`${this.submitIP}/${id}`, {
                method: 'GET',
            })
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    } else {
                        throw new Error('Network response was not ok.');
                    }
                })
                .then(data => {
                    // 处理服务器返回的数据
                    // console.log('服务器返回的数据:', data.data[0]);
                    if (data.code % 10 === 1) {
                        this.dialogVisible = true;
                        this.submission = data.data;
                    } else {
                        this.$message.error(data.msg);
                    }
                })
                .catch(error => {
                    this.$message.error('出现错误' + error);
                });
        },
    }
}
</script>
