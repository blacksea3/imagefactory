<template>
    <el-container>
        <el-container>
            <el-header>
                <image-fetch-header></image-fetch-header>
            </el-header>
            <el-main style="min-height: 550px;">
                <el-tabs v-model="activeName" @tab-click="handleClick">
                    <el-tab-pane label="展示" name="show" style="min-height: 300px;">
                        <el-card class="box-card" v-for="imageData in imageDatas" :key="imageData.name">
                            <div><span class="demonstration">源文件名:{{ imageData.name }}</span></div>
                            <div>
                                <span class="demonstration">源图片:</span>
                                <el-image
                                        style="width: 100px; height: 100px"
                                        :src="imageData.sourceUrl">
                                </el-image>
                            </div>
                            <div><span class="demonstration">状态:{{ imageData.status }}</span></div>
                            <div><span class="demonstration">服务名:{{ imageData.serviceName }}</span></div>
                            <div v-if="imageData.status === 'success'">
                                <span class="demonstration">结果图片:</span>
                                <el-image
                                        style="width: 100px; height: 100px"
                                        :src="imageData.resultUrl">
                                </el-image>
                            </div>
                            <div v-else-if="imageData.status === 'fail'">
                                失败原因:{{ imageData.result }}
                            </div>
                            <div v-else>
                                图片未处理结束
                            </div>
                        </el-card>
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
        name: "TaskDetail",
        data(){
            this.imageDatas = [];
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
                    info: this.getParaFromUrl('instanceName')
                };

                //console.log(JSON.stringify(reqData));
                axios({
                    method:'post',
                    url:'http://127.0.0.1:8081/queryTaskDetail',
                    responseType:'json',
                    headers:{
                        'Content-Type': 'application/json'
                    },
                    data: JSON.stringify(reqData)
                })
                    .then(function (response) {
                        //取数据
                        _this.imageDatas = [];
                        for (let mImageIndex = 0; mImageIndex < response.data.length; mImageIndex++){
                            let imageData = {
                                name: response.data[mImageIndex].content,
                                sourceUrl: "http://localhost:8081/queryImageContent/?fileName=" + encodeURI(response.data[mImageIndex].content),
                                status: response.data[mImageIndex].status,
                                serviceName: response.data[mImageIndex].serviceName,
                                resultUrl: "http://localhost:8081/queryImageContent/?fileName=" + encodeURI(response.data[mImageIndex].result),
                                result: response.data[mImageIndex].result,
                            }
                            _this.imageDatas.push(imageData);
                        }
                        console.log(_this.imageDatas);
                        //数组需要强制使用更新
                        _this.$forceUpdate();
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
        },
    }
</script>

<style scoped>

</style>