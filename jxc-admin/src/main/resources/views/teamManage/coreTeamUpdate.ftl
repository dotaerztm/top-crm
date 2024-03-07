<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改核心成员</title>
    <#include "../common.ftl">
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
                <form class="layui-form" action="" >

                    <#-- 固定类别-->
                    <input type="hidden" name="type"  value="2">
                    <input type="hidden" id="id" name="id">

                    <div class="layui-form-item">
                        <label class="layui-form-label">核心成员</label>
                        <div class="layui-input-block">
                            <input type="text" id ="author" name="author" required  lay-verify="required" placeholder="请输入成员姓名" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">职位</label>
                        <div class="layui-input-block">
                            <input type="text" id="job" name="job" required  lay-verify="required" placeholder="请输入职位信息" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">排序</label>
                        <div class="layui-input-block">
                            <input type="text" id="sort" name="sort" required  lay-verify="required" placeholder="请输入排序数字" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">描述</label>
                        <div class="layui-input-block">
                            <textarea name="remarks"  id="remarks" placeholder="输入描述" class="layui-textarea"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">图片上传</label>
                        <button type="button" class="layui-btn" id="ID-upload-btn">
                            <i class="layui-icon layui-icon-upload"></i> 图片上传
                        </button>
                    </div>
                    <div class="layui-form-item layui-form-text" >
                        <label class="layui-form-label">图片预览</label>
                        <div class="layui-input-block">
                            <img style="width: 150px;height: 150px" id="ID-upload-img">
                        </div>
                    </div>

                    <input type="hidden" name="path" id="path" value="">

                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit lay-filter="formDemo" id="ID-upload-form-btn" >确认修改</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </article>
    <script src="${ctx.contextPath}/js/layui.js" charset="utf-8"></script>
<script>
    //图片上传
    layui.use(function(){
        let upload = layui.upload;
        let $ = layui.$;
        //图片上传
        let uploadInst = upload.render({
            elem: '#ID-upload-btn',//绑定元素
            url: '/image/upload/',//上传接口
            done: function(res){//回调方法
                if(res.code == 200){
                    let path = res.data.src;
                    $('#ID-upload-img').attr('src', path);
                    $('#path').attr('value', path);
                }
                layer.msg(res.message);
            },

        });

        let form = layui.form;

        form.on('submit(formDemo)', function(data){
            $.ajax({
                url: '/image/update/',//保存接口
                method:'post',
                async: false,
                data: JSON.stringify(data.field),
                contentType: 'application/json', // 请求的内容类型为JSON
                success: function(res){//回调方法
                    if(res.code == 200){
                        layer.msg(res.message);
                        let index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭
                    }
                    layer.msg(res.message);
                },
            })
        });


    });



</script>
</body>
</html>