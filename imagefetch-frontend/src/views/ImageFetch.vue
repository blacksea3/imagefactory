<template>
    <div class="ImageFetchMain">
        <el-container>
            <el-container>
                <el-header>
                    <image-fetch-header></image-fetch-header>
                </el-header>
                <el-main style="min-height: 550px;">
                    <el-row>

                    </el-row>
                    <el-row>
                        <el-button type="primary" v-on:click="queryAllServiceConfig()">执行</el-button>
                    </el-row>
                    <el-row>
                        <el-tag>
                            执行信息:{{msg}}
                        </el-tag>
                    </el-row>
                </el-main>
                <el-footer style="height: 120px;">
                    <my-footer></my-footer>
                </el-footer>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "ImageFetch",
        //初始化数据
        data(){
            return{
                msg:"Hello vue"
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

                // 上面的请求也可以这样做
                axios({
                    method:'post',
                    url:'http://127.0.0.1:8081/queryAllServiceConfig',
                    responseType:'json'
                })
                    .then(function (response) {
                        _this.msg = response.data;
                        console.log(response);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        }
    }
</script>

<style scoped>

</style>