<template>
    <div class="ImageFetchMain">
        <el-container>
            <el-container>
                <el-header>

                </el-header>
                <el-main style="min-height: 550px;">
                    <el-tabs v-model="activeName" @tab-click="handleClick">
                        <el-tab-pane label="查询服务配置" name="query">
                            <el-row>
                                查询服务配置
                            </el-row>
                            <el-row>
                                <el-button type="primary" v-on:click="queryAllServiceConfig()">执行</el-button>
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
                                            label="服务名"
                                            width="120">
                                    </el-table-column>
                                    <el-table-column
                                            prop="sysName"
                                            label="系统名"
                                            width="120">
                                    </el-table-column>
                                    <el-table-column
                                            prop="beanName"
                                            label="Bean名"
                                            width="120">
                                    </el-table-column>
                                    <el-table-column
                                            prop="beanType"
                                            label="Bean类型"
                                            width="300">
                                    </el-table-column>
                                    <el-table-column
                                            prop="extInfo"
                                            label="额外信息"
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
                        <el-tab-pane label="添加服务配置" name="add">
                            <el-input placeholder="服务名" v-model="serviceConfigName" clearable></el-input>
                            <el-input placeholder="系统名" v-model="serviceConfigSystemName" clearable></el-input>
                            <el-input placeholder="Bean名" v-model="serviceConfigBeanName" clearable></el-input>
                            <el-input placeholder="Bean类型" v-model="serviceConfigBeanType" clearable></el-input>
                            <el-input placeholder="额外信息" v-model="serviceConfigExtInfo" clearable></el-input>
                            <el-row>
                                <el-button type="primary" v-on:click="addServiceConfig()">执行</el-button>
                            </el-row>
                            <el-row>
                                <el-tag>
                                    执行信息:{{msg}}
                                </el-tag>
                            </el-row>
                        </el-tab-pane>
                        <el-tab-pane label="更新服务配置" name="update">
                            <el-input placeholder="Id" v-model="serviceConfigId" clearable></el-input>
                            <el-input placeholder="服务名" v-model="serviceConfigName" clearable></el-input>
                            <el-input placeholder="系统名" v-model="serviceConfigSystemName" clearable></el-input>
                            <el-input placeholder="Bean名" v-model="serviceConfigBeanName" clearable></el-input>
                            <el-input placeholder="Bean类型" v-model="serviceConfigBeanType" clearable></el-input>
                            <el-input placeholder="额外信息" v-model="serviceConfigExtInfo" clearable></el-input>
                            <el-row>
                                <el-button type="primary" v-on:click="updateServiceConfig()">执行</el-button>
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
                serviceConfigId: '1',
                serviceConfigName: '',
                serviceConfigSystemName: '',
                serviceConfigBeanName: '',
                serviceConfigBeanType: '',
                serviceConfigExtInfo: '',
                tableData: []
            }
        },
        //创建后
        created(){

        },
        methods: {
            /**
             * 查询所有服务配置
             * 无参数
             * 无返回值
             * 直接修改页面信息
             */
            queryAllServiceConfig:function () {
                let _this = this;

                axios({
                    method:'post',
                    url:'http://127.0.0.1:8081/queryAllServiceConfig',
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
             * 增加服务配置
             * 无参数
             * 无返回值
             * 直接获取页面输入框信息，然后修改页面
             */
            addServiceConfig:function(){
                let _this = this;

                let reqData = {
                    name: _this.serviceConfigName,
                    sysName: _this.serviceConfigSystemName,
                    beanName: _this.serviceConfigBeanName,
                    beanType: _this.serviceConfigBeanType,
                    extInfo: _this.serviceConfigExtInfo
                };

                axios({
                    method:'post',
                    url:'http://127.0.0.1:8081/addServiceConfig',
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
             * 更新服务配置
             * 无参数
             * 无返回值
             * 直接获取页面输入框信息，然后修改页面
             */
            updateServiceConfig:function(){
                let _this = this;

                let reqData = {
                    id: _this.serviceConfigId,
                    name: _this.serviceConfigName,
                    sysName: _this.serviceConfigSystemName,
                    beanName: _this.serviceConfigBeanName,
                    beanType: _this.serviceConfigBeanType,
                    extInfo: _this.serviceConfigExtInfo
                };

                axios({
                    method:'post',
                    url:'http://127.0.0.1:8081/updateServiceConfig',
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