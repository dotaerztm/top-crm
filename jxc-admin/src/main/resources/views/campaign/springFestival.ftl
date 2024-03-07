<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>春节H5抽奖</title>
    <#include "../common.ftl">
</head>

<body>
    <div class="layui-container">
        <div class="layui-row">
            <div class="layui-col-md3 layui-col-md-offset2">
                <input class="layui-input" type="text" name="id" id="id" required  lay-verify="required" placeholder="请输入ID" autocomplete="off" >
            </div>
            <div class="layui-col-md4 layui-col-md-offset1">
                <input class="layui-input" type="text" name="mobile" id="mobile" required  lay-verify="required" placeholder="请输入手机号" autocomplete="off" >
            </div>
            <button id = "search" type="button" class="layui-btn layui-btn layui-btn-danger layui-col-md1 layui-col-md-offset1">搜索</button>
        </div>
        <table  id="test" lay-filter="demo"></table>
    </div>
<script>
    //初始化 table
    layui.use(['table','layer'],function(){
        var table = layui.table;
        table.render({
            elem: '#test'
            ,url: '/campaign/selectTableBySpringFestival'
            ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,page: true
            ,limit: 10
            ,id:'tableId'
            ,method:'post'
            ,cols: [[
                {field:'id', width:80,title: 'ID', sort: true}
                ,{field:'openid',  title: 'openid'}
                ,{field:'nickName', title: '用户昵称'}
                ,{field:'headImgUrl', title: '头像', align: 'center',sort:false,
                    templet:function (res) {
                        let photograph3 = '<div lay-event="photograph3"><img src='+res.headImgUrl+'></div>';
                        return photograph3;
                    }
                }
                ,{field:'updateTime', width:140, title: '最后提交时间'}
                ,{field:'forwardImgUrl', title: '祈福图片1',width: '12%', align: 'center',sort:false,
                    templet:function (res) {
                        let photograph1 = '<div lay-event="photograph1"><img src='+res.forwardImgUrl+'></div>';
                        return photograph1;
                    }
                }
                ,{field:'socialImgUrl', title: '祈福图片2',width: '12%', align: 'center',sort:false,
                    templet:function (res) {
                        let photograph2 = '<div lay-event="photograph2"><img src='+res.socialImgUrl+'></div>';
                        return photograph2;
                    }
                }
                ,{field:'mobile',  title: '手机号'}
                ,{field:'isStudent', width:92,title: '是否学员',
                  templet:function(d){
                    return d.isStudent == '0'?'否':'是';
                  }
                 }
                ,{field:'wealth', width:128, title: '操作',
                    templet:function (res){
                        let update = "<button type=\"button\" class=\"layui-btn layui-btn-sm layui-btn-danger\" lay-event=\"update\">更改学员状态</button>";
                     return update ;
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

        //搜索按钮
        $('#search').on('click',function(){
            var mobileValue = $('#mobile');
            var idValue = $('#id');
            table.reload('tableId', {
                page:{
                    curr:1
                },
                where:{
                    mobile:mobileValue.val(),
                    id:idValue.val()
                }
            });
        });

        //绑定操作事件
        table.on('tool(demo)',function (res){
            if(res.event == 'update'){//修改
                layer.confirm('确认修改?', {icon: 3, title:'提示'}, function(index){
                    $.ajax({
                        url: '/campaign/updateStudentStatus/',
                        method:'post',
                        async: false,
                        data: JSON.stringify(res.data.id),
                        contentType: 'application/json', // 请求的内容类型为JSON
                        success: function(data){
                            table.reload("tableId");//刷新table页面
                            layer.close(index);//关闭confirm确认弹窗
                        }
                    })
                });
            }else if(res.event == 'photograph1'){//图片放大
                 let t = $(res).find("forwardImgUrl");
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
                     content: '<div style="text-align:center"><img src="' + res.data.forwardImgUrl + '" /></div>'
                 });
             }else if(res.event == 'photograph2'){//图片放大
               let t = $(res).find("socialImgUrl");
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
                   content: '<div style="text-align:center"><img src="' + res.data.socialImgUrl + '" /></div>'
               });
           }else if(res.event == 'photograph3'){//图片放大
               let t = $(res).find("headImgUrl");
               if(t == null||t == ''){
                   return;
               }
               layer.open({
                   type: 1,
                   skin: 'layui-layer-rim', //加上边框
                   area: ['20%', '35%'], //宽高
                   shadeClose: true, //开启遮罩关闭
                   end: function (index, layero) {
                       return false;
                   },
                   content: '<div style="text-align:center"><img src="' + res.data.headImgUrl + '" /></div>'
               });
           }
        });

    });
</script>
</body>
</html>