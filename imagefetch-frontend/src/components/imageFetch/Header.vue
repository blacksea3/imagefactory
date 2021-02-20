<template>
    <div id="header">
        <!-- 注: 此组件必须被安置在el-header下 -->
        <div>
            <el-link href="index" target="_blank" style="margin-left: 10px;">主页</el-link>
            <el-link href="serviceConfig" target="_blank" style="margin-left: 10px;">服务配置</el-link>
            <el-link href="taskConfig" target="_blank" style="margin-left: 10px;">任务配置</el-link>
            <el-link href="taskInstance" target="_blank" style="margin-left: 10px;">任务实例查看</el-link>
        </div>
    </div>
</template>

<script>
    export default {
        name: "ImageFetchHeader",
        data() {
            return {

            };
        }
    }
</script>

<style scoped>

</style>