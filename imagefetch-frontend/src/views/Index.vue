<template>
    <div id="index">
        <el-container>
            <el-container>
                <el-header>
                    <my-header></my-header>
                </el-header>
                <el-main style="min-height: 550px;">
                    Main
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
        name: "Index",
        data(){
            return{
                blocks:{"something":"xxx"}
            }
        }
    }
</script>

<style scoped>
    .el-header, .el-footer {
        background-color: #B3C0D1;
        color: #333;
    }

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
        line-height: 200px;
    }

    .el-main {
        background-color: #E9EEF3;
        color: #333;
        text-align: center;
        line-height: 160px;
    }

    body > .el-container {
        margin-bottom: 40px;
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }
</style>