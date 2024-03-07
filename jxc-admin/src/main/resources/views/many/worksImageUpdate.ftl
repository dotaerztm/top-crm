<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改图片作品</title>
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
                    <input type="hidden" name="type"  value="8">
                    <input type="hidden" name="typeFlag"  value="0">
                    <input type="hidden" name="id" id="id">

                    <div class="layui-form-item">
                        <label class="layui-form-label">标题</label>
                        <div class="layui-input-block">
                            <input type="text"  name="name" id="name" required  lay-verify="required" placeholder="请输入作品标题" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">作者</label>
                        <div class="layui-input-block">
                            <input type="text"  name="author" id="author" required  lay-verify="required" placeholder="请输入作者标题" autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">作者头像</label>
                        <button type="button" class="layui-btn" id="ID-upload-author-btn">
                            <i class="layui-icon layui-icon-upload"></i>头像上传
                        </button>
                    </div>
                    <div class="layui-form-item layui-form-text" >
                        <label class="layui-form-label">头像预览</label>
                        <div class="layui-input-block">
                            <img style="width: 150px;height: 150px" id="ID-upload-img-author">
                        </div>
                    </div>
                    <input type="hidden" name="authorImage" id="authorImage" value="">

                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">缩略图上传</label>
                        <button type="button" class="layui-btn" id="ID-upload-btn">
                            <i class="layui-icon layui-icon-upload"></i>缩略图上传
                        </button>
                    </div>
                    <div class="layui-form-item layui-form-text" >
                        <label class="layui-form-label">缩略图预览</label>
                        <div class="layui-input-block">
                            <img style="width: 150px;height: 150px" id="ID-upload-img">
                        </div>
                    </div>
                    <input type="hidden" name="path" id="path" value="">

                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">备注</label>
                        <div class="layui-input-block">
                            <textarea name="remarks"  id="remarks"  placeholder="请输入备注信息" class="layui-textarea"></textarea>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">排序</label>
                        <div class="layui-input-block">
                            <input type="text" name="sort" id="sort" required  lay-verify="required" placeholder="请输入排序数字" autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit lay-filter="formDemo" id="ID-upload-form-btn" >立即提交</button>
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

        //图片详情上传
        upload.render({
            elem: '#ID-upload-detail-btn',//绑定元素
            url: '/image/upload/',//上传接口
            done: function(res){//回调方法
                if(res.code == 200){
                    let detailPath = res.data.src;
                    $('#ID-upload-img-detail').attr('src', detailPath);
                    $('#detailPath').attr('value', detailPath);
                }
                layer.msg(res.message);
            },

        });

        //作者头像上传
        upload.render({
            elem: '#ID-upload-author-btn',//绑定元素
            url: '/image/upload/',//上传接口
            done: function(res){//回调方法
                if(res.code == 200){
                    let authorImage = res.data.src;
                    $('#ID-upload-img-author').attr('src', authorImage);
                    $('#authorImage').attr('value', authorImage);
                }
                layer.msg(res.message);
            },

        });

        let form = layui.form;
        form.on('submit(formDemo)', function(data){
            $.ajax({
                url: '/imageAndVideo/updateMain/',//保存接口
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