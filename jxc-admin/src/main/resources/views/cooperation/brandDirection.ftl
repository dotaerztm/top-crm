<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>品牌方向</title>
    <#include "../common.ftl">
</head>

<body>
    <div class="layui-container">
        <table  id="test" lay-filter="demo"></table>
        <button id = "addInfo" type="button" class="layui-btn  layui-btn-danger layui-btn-fluid">添加品牌方向</button>
    </div>

<script>
    //初始化 table
    layui.use(['table','layer'],function(){
        var table = layui.table;
        table.render({
            elem: '#test'
            ,url: '/cooperation/selectImageFromTableByBrandDirection'
            ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,page: true
            ,limit: 10
            ,id:'tableId'
            ,method:'post'
            ,cols: [[
                {field:'id', width:60,title: 'ID'}
                ,{field:'fileName', title: '标题'}
                ,{field:'fileGroup', title: '作者', }
                ,{field:'url', title: '缩略图',width: '20%', align: 'center',sort:false,
                    templet:function (res) {
                        let photograph = '<div lay-event="photograph"><img src='+res.url+'></div>';
                        return photograph;
                    }
                }
                ,{field:'sort', width:80,title: '排序', sort: true }
                ,{field:'wealth', width:137, title: '操作',
                    templet:function (res){
                        let del = "<button type=\"button\" class=\"layui-btn layui-btn-sm layui-btn-danger\" lay-event=\"del\">删除</button>";
                        let update = "<button type=\"button\" class=\"layui-btn layui-btn-sm layui-btn-danger\" lay-event=\"update\">修改</button>";
                     return update + del;
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
        $("#addInfo").click(function (){
            layer.open({
                type:2,
                title:'添加品牌方向',
                content:"brandDirectionAdd",
                area:['640px','620px'],//宽,高
                end:function (){//关闭之后执行
                    //重载table数据
                    table.reload("tableId");
                }
            });
        });

        //绑定操作事件
        table.on('tool(demo)',function (res){
            if(res.event == 'del'){//删除
                layer.confirm('确认删除?', {icon: 3, title:'提示'}, function(index){
                    $.ajax({
                        url: '/image/delImageMain/',
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
            }else if(res.event == 'update'){//修改
                layer.open({
                    type:2,//iframe弹出层
                    title:'修改品牌方向',
                    content:"brandDirectionUpdate",
                    area:['640px','620px'],//宽,高
                    end:function (){//关闭页面执行
                        table.reload("tableId");//刷新table页面
                    },
                    success: function(layero, index){
                        var body = layer.getChildFrame('body', index);
                        body.find("#id").val(res.data.id);
                        body.find("#name").val(res.data.fileName);
                        body.find("#group").val(res.data.fileGroup);
                        body.find("#sort").val(res.data.sort);
                        body.find("#path").val(res.data.url);
                        body.find("img[id='ID-upload-img']").attr('src',res.data.url);
                    }
                });
            }else if(res.event == 'photograph'){//图片放大
                let t = $(res).find("img");
                if(t == null||t == ''){
                    return;
                }
                layer.open({
                    type: 1,
                    skin: 'layui-layer-rim', //加上边框
                    area: ['95%', '95%'], //宽高
                    shadeClose: true, //开启遮罩关闭
                    end: function (index, layero) {
                        return false;
                    },
                    content: '<div style="text-align:center"><img src="' + res.data.url + '" /></div>'
                });
            }
        });

    });
</script>
</body>
</html>