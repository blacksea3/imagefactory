<template>
    <el-container>
        <el-container>
            <el-header>
                <image-fetch-header></image-fetch-header>
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
                            <el-button type="primary" v-on:click="queryTaskInstance()">更新</el-button>
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
                                <el-table-column
                                        label="查询原子任务"
                                        width="120">
                                    <template slot-scope="scope">
                                    <el-button
                                            size="mini"
                                            type="primary"
                                            @click="redirect(scope.row.detailUrl)">查询原子任务</el-button>
                                    </template>
                                </el-table-column>
                                <el-table-column
                                        label="激活任务实例"
                                        width="120">
                                    <template slot-scope="scope">
                                        <div v-if="scope.row.status === 'disable'">
                                            <el-button
                                                    size="mini"
                                                    type="success"
                                                    @click="enableTaskInstance(scope.row.id)"
                                                    >激活任务实例</el-button>
                                        </div>
                                        <div v-else>
                                            <el-button
                                                    size="mini"
                                                    type="success"
                                                    @click="enableTaskInstance(scope.row.id)"
                                                    disabled>激活任务实例</el-button>
                                        </div>
                                    </template>
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
        name: "TaskInstance",
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
                        _this.taskInstanceData = [];
                        for (let taskInstanceIndex = 0; taskInstanceIndex < response.data.length; taskInstanceIndex++){
                            let temp = {
                                id: response.data[taskInstanceIndex].id,
                                name: response.data[taskInstanceIndex].name,
                                description: response.data[taskInstanceIndex].description,
                                configName: response.data[taskInstanceIndex].configName,
                                status: response.data[taskInstanceIndex].status,
                                priority: response.data[taskInstanceIndex].priority,
                                totalNum: response.data[taskInstanceIndex].totalNum,
                                handleNum: response.data[taskInstanceIndex].handleNum,
                                serviceName: response.data[taskInstanceIndex].serviceName,
                                gmtCreate: response.data[taskInstanceIndex].gmtCreate,
                                gmtModified: response.data[taskInstanceIndex].gmtModified,
                                detailUrl: "http://127.0.0.1:8080/imageFetch/taskDetail?instanceName=" + encodeURI(response.data[taskInstanceIndex].name),
                            };
                            _this.taskInstanceData.push(temp);
                        }
                        _this.$forceUpdate();   //数组需要强制使用更新
                        //console.log(response);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },

            /**
             * 重定向
             * url: 路径
             */
            redirect: function(url){
                window.location.href=url;
            },

            /**
             * 更新任务实例
             */
            enableTaskInstance: function(id){
                let _this = this;

                let reqData = {
                    num: id,
                };

                axios({
                    method:'post',
                    url:'http://127.0.0.1:8081/enableTaskInstance',
                    responseType:'json',
                    headers:{
                        'Content-Type': 'application/json'
                    },
                    data: JSON.stringify(reqData)
                })
                    .then(function (response) {
                        if (response.data.success === 'true'){
                            _this.$message('激活成功');
                            _this.queryTaskInstance();
                        }else{
                            _this.$message('激活失败');
                        }
                        console.log(response);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
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