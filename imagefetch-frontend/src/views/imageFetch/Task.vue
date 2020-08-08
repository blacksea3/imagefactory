<template>
    <el-container>
        <el-container>
            <el-header>

            </el-header>
            <el-main style="min-height: 550px;">
                <el-tabs v-model="activeName" @tab-click="handleClick">
                    <el-tab-pane label="上传压缩包" name="upload" style="min-height: 300px;">
                        <el-row>
                            上传压缩包
                        </el-row>
                        <el-upload
                                class="upload-demo"
                                action="http://localhost:8081/uploadZipImages"
                                :on-preview="handlePreview"
                                :on-remove="handleRemove"
                                :before-remove="beforeRemove"
                                name="file"
                                :file-list="fileList">
                            <el-button size="small" type="primary">点击上传</el-button>
                            <div slot="tip" class="el-upload__tip">只能上传zip文件，且不超过8Mb</div>
                        </el-upload>
                    </el-tab-pane>
                    <el-tab-pane label="查询任务实例" name="query">
                        <el-row>
                            查询任务实例
                        </el-row>
                        <el-row>
                            <el-button type="primary" v-on:click="testClick()">更新</el-button>
                        </el-row>
                        <el-row>
                            <el-table
                                    :data="taskInstanceData"
                                    style="width: 100%"
                                    height="250">
                                <el-table-column
                                        fixed
                                        prop="id"
                                        label="Id"
                                        width="120">
                                </el-table-column>
                                <el-table-column
                                        prop="name"
                                        label="实例名"
                                        width="120">
                                </el-table-column>
                                <el-table-column
                                        prop="description"
                                        label="描述"
                                        width="120">
                                </el-table-column>
                                <el-table-column
                                        prop="configName"
                                        label="任务配置名"
                                        width="120">
                                </el-table-column>
                                <el-table-column
                                        prop="status"
                                        label="状态"
                                        width="120">
                                </el-table-column>
                                <el-table-column
                                        prop="priority"
                                        label="优先级"
                                        width="120">
                                </el-table-column>
                                <el-table-column
                                        prop="totalNum"
                                        label="总原子任务数"
                                        width="120">
                                </el-table-column>
                                <el-table-column
                                        prop="handleNum"
                                        label="已处理原子任务数"
                                        width="120">
                                </el-table-column>
                                <el-table-column
                                        prop="serviceName"
                                        label="服务名"
                                        width="120">
                                </el-table-column>
                                <el-table-column
                                        prop="gmtCreate"
                                        label="添加时间"
                                        width="240">
                                </el-table-column>
                                <el-table-column
                                        prop="gmtModified"
                                        label="修改时间"
                                        width="240">
                                </el-table-column>
                            </el-table>
                        </el-row>
                    </el-tab-pane>
                </el-tabs>
            </el-main>
            <el-footer style="height: 120px;">
                <image-fetch-footer></image-fetch-footer>
            </el-footer>
        </el-container>
    </el-container>
</template>

<script>
    export default {
        name: "Task",
        data() {
            let _this = this;
            _this.taskInstanceData = [];
            _this.queryTaskInstance();

            return {
                //fileList: [{name: 'food.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}, {name: 'food2.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}]
                fileList: [],
                activeName: 'query',
            };
        },
        methods: {
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
                console.log(file);
            },
            beforeRemove(file, fileList) {
                return this.$confirm(`确定移除 ${ file.name }？`);
            },

            /**
             * 查询实例
             * 无参数
             * 无返回值
             * 直接获取页面输入框信息，然后修改页面
             */
            queryTaskInstance:function(){
                let _this = this;

                axios({
                    method:'post',
                    url:'http://127.0.0.1:8081/queryTaskInstance',
                    responseType:'json',
                    headers:{
                        'Content-Type': 'application/json'
                    }
                })
                    .then(function (response) {
                        _this.taskInstanceData = response.data;
                        _this.$forceUpdate();   //数组需要强制使用更新
                        console.log(response);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },

            testClick: function(){
                let _this = this;
                console.log(_this.taskInstanceData);
            },

            /**
             * 标签点击
             * @param key 点击的标签
             * @param keyPath 鼠标动作
             */
            handleClick(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    }
</script>

<style scoped>

</style>