<template>
    <div class="ImageFetchMain">
        <el-container>
            <el-container>
                <el-header>

                </el-header>
                <el-main style="min-height: 550px;">
                    <el-tabs v-model="activeName" @tab-click="handleClick">
                        <el-tab-pane label="查询任务配置" name="query">
                            <el-row>
                                查询任务配置
                            </el-row>
                            <el-row>
                                <el-button type="primary" v-on:click="queryAllTaskConfig()">执行</el-button>
                            </el-row>
                            <el-row>
                                <el-table
                                        :data="tableData"
                                        style="width: 100%"
                                        height="250">
                                    <el-table-column
                                            fixed
                                            prop="id"
                                            label="Id"
                                            width="150">
                                    </el-table-column>
                                    <el-table-column
                                            prop="name"
                                            label="任务名"
                                            width="120">
                                    </el-table-column>
                                    <el-table-column
                                            prop="description"
                                            label="描述"
                                            width="120">
                                    </el-table-column>
                                    <el-table-column
                                            prop="status"
                                            label="状态"
                                            width="120">
                                    </el-table-column>
                                    <el-table-column
                                            prop="extInfo"
                                            label="额外信息"
                                            width="120">
                                    </el-table-column>
                                    <el-table-column
                                            prop="serviceName"
                                            label="服务名"
                                            width="300">
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
                        <el-tab-pane label="添加任务配置" name="add">
                            <el-input placeholder="任务名" v-model="taskConfigName" clearable></el-input>
                            <el-input placeholder="描述" v-model="taskConfigDescription" clearable></el-input>
                            <el-input placeholder="状态" v-model="taskConfigStatus" clearable></el-input>
                            <el-input placeholder="额外信息" v-model="taskConfigExtInfo" clearable></el-input>
                            <el-input placeholder="服务名" v-model="taskConfigServiceName" clearable></el-input>
                            <el-row>
                                <el-button type="primary" v-on:click="addTaskConfig()">执行</el-button>
                            </el-row>
                            <el-row>
                                <el-tag>
                                    执行信息:{{msg}}
                                </el-tag>
                            </el-row>
                        </el-tab-pane>
                        <el-tab-pane label="更新任务配置" name="update">
                            <el-input placeholder="Id" v-model="taskConfigId" clearable></el-input>
                            <el-input placeholder="任务名" v-model="taskConfigName" clearable></el-input>
                            <el-input placeholder="描述" v-model="taskConfigDescription" clearable></el-input>
                            <el-input placeholder="状态" v-model="taskConfigStatus" clearable></el-input>
                            <el-input placeholder="额外信息" v-model="taskConfigExtInfo" clearable></el-input>
                            <el-input placeholder="服务名" v-model="taskConfigServiceName" clearable></el-input>
                            <el-row>
                                <el-button type="primary" v-on:click="updateTaskConfig()">执行</el-button>
                            </el-row>
                            <el-row>
                                <el-tag>
                                    执行信息:{{msg}}
                                </el-tag>
                            </el-row>
                        </el-tab-pane>
                    </el-tabs>
                </el-main>
                <el-footer style="height: 120px;">
                    <image-fetch-footer></image-fetch-footer>
                </el-footer>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "Query",
        //初始化数据
        data(){
            return{
                msg:"Hello vue",
                activeName: 'add',
                taskConfigId: '1',
                taskConfigName: '',
                taskConfigDescription: '',
                taskConfigStatus: '',
                taskConfigExtInfo: '',
                taskConfigServiceName: '',
                tableData: []
            }
        },
        //创建后
        created(){

        },
        methods: {
            /**
             * 查询所有任务配置
             * 无参数
             * 无返回值
             * 直接修改页面信息
             */
            queryAllTaskConfig:function () {
                let _this = this;

                axios({
                    method:'post',
                    url:'http://127.0.0.1:8081/queryAllTaskConfig',
                    responseType:'json'
                })
                    .then(function (response) {
                        _this.tableData = response.data;
                        console.log(response);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            /**
             * 增加任务配置
             * 无参数
             * 无返回值
             * 直接获取页面输入框信息，然后修改页面
             */
            addTaskConfig:function(){
                let _this = this;

                let reqData = {
                    name: _this.taskConfigName,
                    description: _this.taskConfigDescription,
                    status: _this.taskConfigStatus,
                    extInfo: _this.taskConfigExtInfo,
                    serviceName: _this.taskConfigServiceName
                };

                axios({
                    method:'post',
                    url:'http://127.0.0.1:8081/addTaskConfig',
                    responseType:'json',
                    headers:{
                        'Content-Type': 'application/json'
                    },
                    data: JSON.stringify(reqData)
                })
                    .then(function (response) {
                        _this.msg = response.data;
                        _this.$forceUpdate();   //数组需要强制使用更新
                        console.log(response);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            /**
             * 更新任务配置
             * 无参数
             * 无返回值
             * 直接获取页面输入框信息，然后修改页面
             */
            updateTaskConfig:function(){
                let _this = this;

                let reqData = {
                    id: _this.taskConfigId,
                    name: _this.taskConfigName,
                    description: _this.taskConfigDescription,
                    status: _this.taskConfigStatus,
                    extInfo: _this.taskConfigExtInfo,
                    serviceName: _this.taskConfigServiceName
                };

                axios({
                    method:'post',
                    url:'http://127.0.0.1:8081/updateTaskConfig',
                    responseType:'json',
                    headers:{
                        'Content-Type': 'application/json'
                    },
                    data: JSON.stringify(reqData)
                })
                    .then(function (response) {
                        _this.msg = response.data;
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