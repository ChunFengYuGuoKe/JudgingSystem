<template>
    <el-table :data="problems" height="250" style="width: 100%" stripe max-height="250">
        <el-table-column prop="id" label="题目编号" width="180" />
        <el-table-column prop="title" label="题目标题" width="180" />
        <el-table-column>
            <template #default="{ row }">
                <el-button plain @click="click(row.id)">
                    详情
                </el-button>
            </template>
        </el-table-column>
    </el-table>


    <el-dialog v-model="dialogFormVisible" title="题目详情" width="800">
        <el-row>
            <el-col :span="12">
                <div>
                    题目编号：{{ problem.id }}
                </div>
            </el-col>
            <el-col :span="12">
                <div>
                    题目标题：{{ problem.title }}
                </div>
            </el-col>
        </el-row>

        <el-row>
            <el-col :span="24">
                <div>
                    内容：{{ problem.description }}
                </div>
            </el-col>
        </el-row>

        <el-form :model="form" :rules="rules" ref="formData">
            <el-form-item label="代码提交" prop='code'>
                <el-upload :action="''" :auto-upload="false" :show-file-list="true" :on-change="handleFileChange">
                    <el-button size="small" type="primary">选择文件</el-button>
                </el-upload>
            </el-form-item>
        </el-form>

        <template #footer>
            <div class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取消</el-button>
                <el-button type="primary" @click="submitForm">提交</el-button>
            </div>
        </template>
    </el-dialog>

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

<style></style>

<script>
import { mapState, mapGetters } from 'vuex';


export default {
    computed: {
        ...mapState('user', ['username']),
        ...mapState('language', ['selectedLanguage']),
        ...mapState('problem', ['problems']),
        ...mapGetters('ip', ['homeworkIP', 'submitIP']),
    },
    data() {
        return {
            dialogFormVisible: false,
            dialogVisible: false,
            problem: {
                id: null,
                title: null,
                description: null,
            },
            form: {
                id_stu: null,
                id_homework: null,
                id_lang: null,
                code: null
            },
            submission: {
                id: null,
                title: null,
                black: null,
                white: null,
                score: null
            },
            rules: {
                code: [{ validator: this.validateFile, trigger: 'change' }]
            }
        }
    },
    methods: {
        validateFile(rule, value, callback) {
            if (value === null) {
                callback(new Error('请选择文件'));
            } else {
                callback(); // 校验通过
            }
        },
        click(id) {
            // console.log('访问url', `${this.homeworkIP}/${this.selectedLanguage}/${id}`);
            fetch(`${this.homeworkIP}/${this.selectedLanguage}/${id}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
            })
                .then(response => {
                    return response.json(); // 返回一个 Promise，解析后得到 JSON 数据
                })
                .then(data => {
                    // console.log("赋值操作给tableData:", data.data);
                    // commit('setProblems', data.data);
                    // console.log('data:', data.data[0]);
                    this.problem = data.data[0];

                    this.form.id_homework = this.problem.id;
                    this.form.id_lang = this.selectedLanguage;
                    this.form.id_stu = this.username;
                })
                .catch(error => {
                    // 请求失败，处理错误
                    console.error('Error fetching data:', error);
                });
            this.dialogFormVisible = true;
        },
        handleFileChange(file) {
            this.form.code = file.raw; // 存储用户选择的文件
        },
        submissionShow(id) {
            fetch(`${this.submitIP}/${id}`, {
                method: 'GET',
            })
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    } else {
                        console.error('失败');
                    }
                })
                .then(data => {
                    // 处理服务器返回的数据
                    console.log('服务器返回的数据:', data.data[0]);
                    this.dialogVisible = true;
                    this.submission = data.data[0];
                })
                .catch(error => {
                    console.error('发生错误:', error);
                });
        },
        submitForm() {
            // console.log('触发提交表单按钮');
            this.$refs.formData.validate(valid => {
                if (valid) {
                    this.dialogFormVisible = false;
                    // console.log('发送表单：', this.form);

                    // 发送 POST 请求
                    fetch(this.submitIP, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        },
                        body: this.form
                    })
                        .then(response => {
                            if (response.ok) {
                                // 处理上传成功的逻辑
                                console.log('文件上传成功');
                                return response.json();
                            } else {
                                // 处理上传失败的逻辑
                                console.error('文件上传失败');
                            }
                        })
                        .then(data => {
                            // 处理服务器返回的数据
                            console.log('服务器返回的数据:', data.data[0].id);
                            this.submissionShow(data.data[0].id);
                        })
                        .catch(error => {
                            console.error('发生错误:', error);
                        });
                } else {
                    console.error('表单验证失败');
                    return false;
                }
            });
        }
    }
}
</script>