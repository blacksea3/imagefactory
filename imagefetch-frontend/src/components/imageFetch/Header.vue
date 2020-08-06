<template>
    <div id="header">
        <!-- 注: 此组件必须被安置在el-header下 -->
        <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="查询服务配置" name="query">查询服务配置</el-tab-pane>
            <el-tab-pane label="添加服务配置" name="add">添加服务配置</el-tab-pane>
            <el-tab-pane label="更新服务配置" name="update">更新服务配置</el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
    export default {
        name: "ImageFetchHeader",
        data() {
            return {
                activeName: 'add'
            };
        },
        methods: {
            handleClick(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    }
</script>

<style scoped>

</style>