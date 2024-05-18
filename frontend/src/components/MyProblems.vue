<template>
    <el-table :data="problems" height="250" style="width: 100%" stripe max-height="250">
        <el-table-column prop="id" label="题目编号" width="300" />
        <el-table-column prop="title" label="题目标题" width="300" />
        <el-table-column label="操作">
            <template #default="{ row }">
                <el-button plain @click="problemShow(row.id)">
                    详情
                </el-button>
                <el-button type="danger" @click="problemDelete(row.id)" v-if="this.identity === 1">
                    删除题目
                </el-button>
            </template>
        </el-table-column>
        
        <el-table-column>
            <template #header>
                <el-button type="primary" @click="addShow" v-if="this.identity === 1">
                    新增题目
                </el-button>
            </template>
        </el-table-column>

    </el-table>

    <el-dialog v-model="dialogProblemVisible" title="题目详情" width="800">
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

        <el-form :model="form" :rules="rules" ref="formData" v-if="this.identity === 0">
            <el-form-item label="代码提交" prop='code'>
                <el-upload :action="''" :auto-upload="false" :show-file-list="true" :on-change="handleFileChange"
                    :limit="1">
                    <el-button size="small" type="primary">选择文件</el-button>
                </el-upload>
                <!-- <input type="file" ref="fileInput" @change="handleFileChange"> -->
            </el-form-item>
        </el-form>

        <template #footer>
            <div class="dialog-footer">
                <el-button @click="dialogProblemVisible = false">关闭</el-button>
                <el-button type="primary" @click="submitForm" v-if="this.identity === 0">提交</el-button>
            </div>
        </template>

    </el-dialog>

    <el-dialog v-model="dialogSubmissionVisible" title="提交记录" width="800">
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
                <el-button @click="dialogSubmissionVisible = false">关闭</el-button>
            </div>
        </template>
    </el-dialog>

    <el-dialog v-model="dialogAddVisible" title="新增题目" width="800">
        <el-form :model="form2" :rules="rules2" ref="formData2">
            <el-form-item label="题目标题" prop="title">
                <el-input v-model="form2.title" style="width: 240px" placeholder="Please input" />
            </el-form-item>

            <el-form-item label="题目内容" prop="description">
                <el-input v-model="form2.description" style="width: 700px" :autosize="{ minRows: 10 }" type="textarea"
                    placeholder="Please input" />
            </el-form-item>


            <el-form-item label="参考代码" prop='solution'>
                <el-upload :action="''" :auto-upload="false" :show-file-list="true" :on-change="handleFileChange2">
                    <el-button size="small" type="primary">选择文件</el-button>
                </el-upload>
            </el-form-item>

            <el-form-item label="测试用例" prop='usecases'>
                <el-upload :action="''" :auto-upload="false" :show-file-list="true" :on-change="handleFileChange3">
                    <el-button size="small" type="primary">选择文件</el-button>
                </el-upload>
            </el-form-item>
        </el-form>

        <template #footer>
            <div class="dialog-footer">
                <el-button @click="dialogAddVisible = false">关闭</el-button>
                <el-button type="primary" @click="submitForm2">提交</el-button>
            </div>
        </template>

    </el-dialog>


</template>

<style></style>

<script>
import { mapState, mapGetters, mapActions } from 'vuex';


export default {
    computed: {
        ...mapState('user', ['username', 'identity', 'jwt']),
        ...mapState('language', ['selectedLanguage']),
        ...mapState('problem', ['problems']),
        ...mapGetters('ip', ['homeworkIP', 'submitIP', 'homeworkAddIP']),
    },
    data() {
        return {
            dialogProblemVisible: false,
            dialogSubmissionVisible: false,
            dialogAddVisible: false,
            problem: {
                id: null,
                title: null,
                description: null,
            },
            form: {
                username: null,
                homework: null,
                language: null,
                code: null
            },
            rules: {
                code: [{ validator: this.validateFile, trigger: 'change' }]
            },
            submission: {
                id: null,
                title: null,
                black: null,
                white: null,
                score: null
            },
            form2: {
                title: null,
                description: null,
                language: null,
                solution: null,
                usecases: null,
            },
            rules2: {
                title: [
                    { required: true, message: '请输入题目标题', trigger: 'blur' },
                ],
                description: [
                    { required: true, message: '请输入题目内容', trigger: 'blur' },
                ],
                solution: [
                    { validator: this.validateFile, trigger: 'change' }
                ],
                usecases: [
                    { validator: this.validateFile, trigger: 'change' }
                ],
            },
        }
    },
    methods: {
        ...mapActions('problem', ['fetchProblems']),
        validateFile(rule, value, callback) {
            if (value === null) {
                callback(new Error('请选择文件'));
            } else {
                callback(); // 校验通过
            }
        },
        readFileContent(file) {
            const reader = new FileReader();
            reader.onload = (event) => {
                console.log('File Content:', event.target.result);
            };
            reader.readAsText(file);
        },
        handleFileChange(file) {
            this.form.code = file.raw; // 存储用户选择的文件
            // this.readFileContent(this.form.code);
        },
        handleFileChange2(file) {
            this.form2.solution = file.raw; // 存储用户选择的文件
        },
        handleFileChange3(file) {
            this.form2.usecases = file.raw; // 存储用户选择的文件
        },

        addShow() {
            this.dialogAddVisible = true;
            this.form2.language = this.selectedLanguage;
        },

        problemShow(id) {
            // console.log('访问url', `${this.homeworkIP}/${this.selectedLanguage}/${id}`);
            fetch(`${this.homeworkIP}/${this.selectedLanguage}/${id}`, {
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
                        this.problem = data.data;
                        this.form.homework = this.problem.id;
                        this.form.language = this.selectedLanguage;
                        this.form.username = this.username;
                        this.dialogProblemVisible = true;
                    } else {
                        this.$message.error(data.msg);
                    }
                })
                .catch(error => {
                    // 请求失败，处理错误
                    this.$message.error('出现错误' + error);
                });
        },

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
                    if (data.code % 10 === 1) {
                        this.dialogSubmissionVisible = true;
                        this.submission = data.data;
                    } else {
                        this.$message.error(data.msg);
                    }
                })
                .catch(error => {
                    this.$message.error('出现错误' + error);
                });
        },

        submitForm() {
            // console.log('触发提交表单按钮');
            this.$refs.formData.validate(valid => {
                if (valid) {
                    this.dialogProblemVisible = false;
                    // console.log('发送表单：', this.form);

                    const formData_tmp = new FormData();
                    formData_tmp.append('code', this.form.code);
                    formData_tmp.append('username', this.form.username);
                    formData_tmp.append('homework', this.form.homework);
                    formData_tmp.append('language', this.form.language);

                    // 发送 POST 请求
                    fetch(this.submitIP, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'multipart/form-data',
                            'Authorization': `Bearer ${this.jwt}`,
                        },
                        body: formData_tmp
                    })
                        .then(response => {
                            if (response.ok) {
                                // 处理上传成功的逻辑
                                // console.log('文件上传成功');

                                return response.json();
                            } else {
                                // 处理上传失败的逻辑
                                throw new Error('Network response was not ok.');
                            }
                        })
                        .then(data => {
                            // 处理服务器返回的数据

                            if (data.code % 10 === 1) {
                                this.$message({
                                    message: '提交成功',
                                    type: 'success',
                                })
                                // console.log('服务器返回的数据:', data.data[0].id);
                                this.submissionShow(data.data.id);
                            }
                            else {
                                this.$message.error('Oops, this is a error message.\n' + data.msg);
                            }
                        })
                        .catch(error => {
                            // console.error('发生错误:', error);
                            this.$message.error('出现错误' + error);
                        });
                } else {
                    this.$message.error('表单验证失败');
                    return false;
                }
            });
        },

        submitForm2() {
            // console.log('触发提交表单按钮');
            this.$refs.formData2.validate(valid => {
                if (valid) {
                    this.dialogAddVisible = false;
                    console.log('发送表单：', this.form2);

                    const formData_tmp = new FormData();
                    formData_tmp.append('title', this.form2.title);
                    formData_tmp.append('description', this.form2.description);
                    formData_tmp.append('language', this.form2.language);
                    formData_tmp.append('solution', this.form2.solution);
                    formData_tmp.append('usecases', this.form2.usecases);

                    // 发送 POST 请求
                    fetch(this.homeworkAddIP, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'multipart/form-data',
                            'Authorization': `Bearer ${this.jwt}`,
                        },
                        body: formData_tmp
                    })
                        .then(response => {
                            if (response.ok) {
                                // 处理上传成功的逻辑
                                // console.log('文件上传成功');

                                return response.json();
                            } else {
                                // 处理上传失败的逻辑
                                throw new Error('Network response was not ok.');
                            }
                        })
                        .then(data => {
                            // 处理服务器返回的数据

                            if (data.code % 10 === 1) {
                                this.$message({
                                    message: '提交成功',
                                    type: 'success',
                                })
                                this.fetchProblems({
                                    url: `${this.homeworkIP}/${this.selectedLanguage}`,
                                });
                            }
                            else {
                                this.$message.error('Oops, this is a error message.\n' + data.msg);
                            }
                        })
                        .catch(error => {
                            // console.error('发生错误:', error);
                            this.$message.error('出现错误' + error);
                        });
                } else {
                    this.$message.error('表单验证失败');
                    return false;
                }
            });
        },

        problemDelete(id) {
            // console.log('访问url', `${this.homeworkIP}/${id}`);
            fetch(`${this.homeworkIP}/${id}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${this.jwt}`,
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
                        this.$message({
                            message: '成功删除',
                            type: 'success',
                        });
                        this.fetchProblems({
                            url: `${this.homeworkIP}/${this.selectedLanguage}`,
                        });
                    } else {
                        this.$message.error(data.msg);
                    }
                })
                .catch(error => {
                    // 请求失败，处理错误
                    this.$message.error('出现错误' + error);
                });
        },
    }
}
</script>