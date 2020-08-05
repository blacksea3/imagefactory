<template>
    <div class="home_test">
        <table>
            <tr>
                <td>
                    {{msg}}
                </td>
            </tr>
        </table>
    </div>
</template>

<script>
    export default {
        name: "Test",
        data(){
            return{
                msg:"Hello vue"
            }
        },
        created(){
            let _this = this;

            // 上面的请求也可以这样做
            axios({
                method:'get',
                url:'http://127.0.0.1:8081/hello',
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
</script>

<style scoped>

</style>