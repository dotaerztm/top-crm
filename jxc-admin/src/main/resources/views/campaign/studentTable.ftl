<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>学员列表</title>
    <#include "../common.ftl">
</head>

<body>
    <div class="layui-container">
        <div class="layui-row">
            <div class="layui-col-md4 layui-col-md-offset6">
                <input class="layui-input" type="text" name="mobile" id="mobile" required  lay-verify="required" placeholder="请输入手机号" autocomplete="off" >
            </div>
            <button id = "search" type="button" class="layui-btn layui-btn layui-btn-danger layui-col-md1 layui-col-md-offset1">搜索</button>
        </div>
        <table  id="test" lay-filter="demo"></table>
        <button id = "addStudent" type="button" class="layui-btn  layui-btn-danger layui-btn-fluid">添加学员</button>
    </div>
<script>
    //初始化 table
    layui.use(['table','layer'],function(){
        var table = layui.table;
        table.render({
            elem: '#test'
            ,url: '/student/selectTableByStudent'
            ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,page: true
            ,limit: 10
            ,id:'tableId'
            ,method:'post'
            ,cols: [[
                {field:'id', width:100,title: 'ID', sort: true}
                ,{field:'mobile',  title: '手机号'}
                ,{field:'insertTime',  title: '新增时间'}
                ,{field:'wealth', width:137, title: '操作',
                    templet:function (res){
                        let del = "<button type=\"button\" class=\"layui-btn layui-btn-sm layui-btn-danger\" lay-event=\"del\">删除学员</button>";
                     return del ;
                    }
                }
            ]]
            ,parseData: function(res){ //res 即为原始返回的数据
                return {
                    "code": res.code == 200?0:res.code, //解析接口状态
                    "msg": res.message, //解析提示文本
                    "count": res.data.count, //解析数据长度
                    "data": res.data.list //解析数据列表
                };
            }
        });

        //添加按钮 页面跳转
        var $ = layui.jquery;
        var layer = layui.layer;
        //绑定操作事件
        table.on('tool(demo)',function (res){
            if(res.event == 'del'){//删除
                layer.confirm('确认删除?', {icon: 3, title:'提示'}, function(index){
                    $.ajax({
                        url: '/student/del/',
                        method:'post',
                        async: false,
                        data: JSON.stringify(res.data.id),
                        contentType: 'application/json', // 请求的内容类型为JSON
                        success: function(data){//回调方法
                            if(data.code == 200){
                                res.del();//删除table中该数据
                                layer.close(index);//关闭confirm确认弹窗
                            }
                            layer.msg(data.message);
                        },
                    })
                });
            }
        });

        //搜索按钮
        $('#search').on('click',function(){
            var mobileValue = $('#mobile');
            table.reload('tableId', {
                page:{
                    curr:1
                },
                where:{
                    mobile:mobileValue.val()
                }
            });
        });


        //添加按钮 页面跳转
                var $ = layui.jquery;
                var layer = layui.layer;
                $("#addStudent").click(function (){
                    layer.open({
                        type:2,
                        title:'添加学员',
                        content:"studentAdd",
                        area:['640px','280px'],//宽,高
                        end:function (){//关闭之后执行
                            //重载table数据
                            table.reload("tableId");
                        }
                    });
                });

    });
</script>
</body>
</html>