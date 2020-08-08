<template>
    <el-container>
        <el-container>
            <el-header>

            </el-header>
            <el-main style="min-height: 550px;">
                <el-tabs v-model="activeName" @tab-click="handleClick">
                    <el-tab-pane label="展示" name="show" style="min-height: 300px;">
                        <el-row>
                            <el-button type="primary" v-on:click="testClick()">测试</el-button>
                        </el-row>
                        <el-row>
                            <el-table
                                    :data="imageData"
                                    style="width: 100%"
                                    height="250">
                                <el-table-column
                                        fixed
                                        prop="path"
                                        label="路径"
                                        width="600">
                                </el-table-column>
                            </el-table>
                        </el-row>
                        <el-row>
                            <el-image v-for="url in urls" :key="url" :src="url" lazy></el-image>
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
        name: "MyImage",
        data(){
            this.imageData = [];
            this.urls = [];
            this.queryImageFileNames();
            return{
                activeName: 'show'
            };
        },
        //创建后
        created(){

        },
        methods:{
            /**
             * 参考:https://www.jianshu.com/p/adca66e1a473
             * key: 键
             */
            getParaFromUrl:function(key){
                let url = window.location.href ;          //获取当前url
                let dz_url = url.split('#')[0];  //获取#/之前的字符串
                let cs = dz_url.split('?')[1];   //获取?之后的参数字符串
                let cs_arr = cs.split('&');      //参数字符串分割为数组
                let tot={};
                for(let i=0;i<cs_arr.length;i++){         //遍历数组，拿到json对象
                    tot[cs_arr[i].split('=')[0]] = cs_arr[i].split('=')[1]
                }
                return tot[key]; //这样就拿到了参数中的数据                                          
            },
            /**
             * 查询指定文件夹的图片
             * 无参数
             * 无返回值
             * 直接修改页面信息
             */
            queryImageFileNames:function () {
                let _this = this;

                let reqData = {
                    path: this.getParaFromUrl('path')
                };

                //console.log(JSON.stringify(reqData));
                axios({
                    method:'post',
                    url:'http://127.0.0.1:8081/queryImageFileNames',
                    responseType:'json',
                    headers:{
                        'Content-Type': 'application/json'
                    },
                    data: JSON.stringify(reqData)
                })
                    .then(function (response) {
                        _this.imageData = response.data;    //取数据
                        _this.queryImageContent();
                        _this.$forceUpdate();   //数组需要强制使用更新
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            /**
             * 查询图片内容
             */
            queryImageContent:function () {
                let _this = this;
                let _urls = [];

                console.log(this.imageData);
                for (let mImageIndex = 0; mImageIndex < _this.imageData.length; mImageIndex++){
                    let mPath = "http://localhost:8081/queryImageContent/?fileName=" + encodeURI(_this.imageData[mImageIndex].path);
                    console.log("读取图片:" + mPath);
                    _urls.push(mPath);
                }
                _this.urls = _urls;
                console.log(this.urls);
                _this.$forceUpdate();   //数组需要强制使用更新
            },
            /**
             * 标签点击
             * @param key 点击的标签
             * @param keyPath 鼠标动作
             */
            handleClick(key, keyPath) {
                console.log(key, keyPath);
            }
        },
    }
</script>

<style scoped>

</style>