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

        <el-table-column>
            <template #header>
                <el-button type="primary" @click="dialogClassAddVisible = true">
                    班级导入
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

    <el-dialog v-model="dialogClassAddVisible" title="班级导入" width="800">
        <el-form :model="form" :rules="rules" ref="formData">

            <el-form-item label="班级" prop='class_file'>
                <el-upload :action="''" :auto-upload="false" :show-file-list="true" :on-change="handleFileChange">
                    <el-button size="small" type="primary">选择文件</el-button>
                </el-upload>
            </el-form-item>

        </el-form>

        <template #footer>
            <div class="dialog-footer">
                <el-button @click="dialogClassAddVisible = false">关闭</el-button>
                <el-button type="primary" @click="submitForm">提交</el-button>
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
        ...mapState('class', ['classes']),
        ...mapState('language', ['selectedLanguage']),
        ...mapGetters('ip', ['classIP', 'studentIP']),
    },
    data() {
        return {
            dialogClassVisible: false,
            dialogStudentVisible: false,
            dialogClassAddVisible: false,
            students: [],
            submissions: [],
            form: {
                class_file: null,
            },
            rules: {
                class_file: [{ validator: this.validateFile, trigger: 'change' }]
            },
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
        },

        handleFileChange(file) {
            this.form.class_file = file.raw; // 存储用户选择的文件
            // this.readFileContent(this.form.code);
        },

        submitForm() {
            // console.log('触发提交表单按钮');
            this.$refs.formData.validate(valid => {
                if (valid) {
                    this.dialogClassAddVisible = false;
                    // console.log('发送表单：', this.form);

                    const formData_tmp = new FormData();
                    formData_tmp.append('class_file', this.form.class_file);

                    // 发送 POST 请求
                    fetch(`${this.classIP}/import`, {
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
    }
}
</script>