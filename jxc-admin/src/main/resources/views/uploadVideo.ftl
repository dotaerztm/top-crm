<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>上传视频</title>
    <#include "common.ftl">
    <style>
        .layui-container{
            background-color: whitesmoke;
            padding: 20px;
        }
    </style>

</head>
<body>
    <article>
        <div class="layui-container">
            <div class="layui-row">
                <form class="layui-form" action="">
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">文本域</label>
                        <div class="layui-input-block">
                            <textarea name="describe"  id = "describe" placeholder="输入视频描述" class="layui-textarea"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">视频上传</label>
                        <button type="button" class="layui-btn" id="ID-upload-btn">
                            <i class="layui-icon layui-icon-upload"></i> 视频上传
                        </button>
                    </div>
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">视频预览</label>
                        <div class="layui-input-block">
                            <img style="width: 200px;height: 200px" id="ID-upload-img">
                        </div>
                    </div>

                    <input type="hidden" name="path" id="path" value="">

                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit lay-filter="formDemo" id="ID-upload-form-btn" >立即提交</button>
                            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </article>
    <script src="${ctx.contextPath}/js/layui.js" charset="utf-8"></script>
<script>
    //视频上传
    layui.use(function(){
        let upload = layui.upload;
        let $ = layui.$;
        //视频上传
        let uploadInst = upload.render({
            elem: '#ID-upload-btn',//绑定元素
            url: '/video/upload/',//上传接口
            accept: 'video',//视频
            size: 10000000,
        done: function(res){//回调方法
                if(res.code == 200){
                    let path = res.data.src;
                    $('#ID-upload-img').attr('src', path);
                    $('#path').attr('value', path);
                }
                layer.msg(res.message);
            },

        });
    });

    //提交
    layui.use(function(){
        let form = layui.form;
        let $ = layui.$;
        form.on('submit(formDemo)', function(data){
            $.ajax({
                url: '/video/save/',//上传接口
                method:'post',
                data:JSON.stringify(data.field),
                dataType:'JOSN',
                done: function(res){//回调方法
                    layer.msg(res.message);
                },
            })
        });
    });

</script>
</body>
</html>