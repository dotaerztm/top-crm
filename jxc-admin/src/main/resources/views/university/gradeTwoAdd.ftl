<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加二年级课程</title>
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
                <input type="hidden" name="type"  value="6">

                <div class="layui-form-item">
                    <label class="layui-form-label">标题</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">作者</label>
                    <div class="layui-input-block">
                        <input type="text" name="author" required  lay-verify="required" placeholder="请输入作者信息" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">描述</label>
                    <div class="layui-input-block">
                        <input type="text" name="remarks" required  lay-verify="required" placeholder="请输入描述信息" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">排序</label>
                    <div class="layui-input-block">
                        <input type="text" name="sort" required  lay-verify="required" placeholder="请输入排序数字" autocomplete="off" class="layui-input">
                    </div>
                </div>

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
                    <label class="layui-form-label">介绍图1上传</label>
                    <button type="button" class="layui-btn" id="ID-upload-btn-introduction1">
                        <i class="layui-icon layui-icon-upload"></i>介绍图1上传
                    </button>
                </div>
                <div class="layui-form-item layui-form-text" >
                    <label class="layui-form-label">介绍图1预览</label>
                    <div class="layui-input-block">
                        <img style="width: 150px;height: 150px" id="ID-upload-img-introduction1">
                    </div>
                </div>
                <input type="hidden" name="introduction1" id="introduction1" value="">

                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">介绍图2上传</label>
                    <button type="button" class="layui-btn" id="ID-upload-btn-introduction2">
                        <i class="layui-icon layui-icon-upload"></i>课程介绍图2上传
                    </button>
                </div>
                <div class="layui-form-item layui-form-text" >
                    <label class="layui-form-label">介绍图2预览</label>
                    <div class="layui-input-block">
                        <img style="width: 150px;height: 150px" id="ID-upload-img-introduction2">
                    </div>
                </div>
                <input type="hidden" name="introduction2" id="introduction2" value="">

                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">联系方式上传</label>
                    <button type="button" class="layui-btn" id="ID-upload-btn-contactUrl">
                        <i class="layui-icon layui-icon-upload"></i>联系方式上传
                    </button>
                </div>
                <div class="layui-form-item layui-form-text" >
                    <label class="layui-form-label">联系方式预览</label>
                    <div class="layui-input-block">
                        <img style="width: 150px;height: 150px" id="ID-upload-img-contactUrl">
                    </div>
                </div>
                <input type="hidden" name="contactUrl" id="contactUrl" value="">

                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">ClassTime文本</label>
                    <div class="layui-input-block">
                        <textarea name="classTimeContent"  placeholder="输入ClassTime文本" class="layui-textarea"></textarea>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">课程价格</label>
                    <div class="layui-input-block">
                        <input type="text" name="assignmentMiddle" required  lay-verify="required" placeholder="Assignment课程价格" autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">Assignment文本</label>
                    <div class="layui-input-block">
                        <textarea name="assignmentContent"  placeholder="输入Assignment文本" class="layui-textarea"></textarea>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">Assignment外链</label>
                    <div class="layui-input-block">
                        <input type="text" name="assignmentLink" required  lay-verify="required" placeholder="请输入Assignment外链" autocomplete="off" class="layui-input">
                    </div>
                </div>

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

        let uploadInst1 = upload.render({
            elem: '#ID-upload-btn-introduction1',//绑定元素
            url: '/image/upload/',//上传接口
            done: function(res){//回调方法
                if(res.code == 200){
                    let path = res.data.src;
                    $('#ID-upload-img-introduction1').attr('src', path);
                    $('#introduction1').attr('value', path);
                }
                layer.msg(res.message);
            },

        });

        let uploadInst2 = upload.render({
            elem: '#ID-upload-btn-introduction2',//绑定元素
            url: '/image/upload/',//上传接口
            done: function(res){//回调方法
                if(res.code == 200){
                    let path = res.data.src;
                    $('#ID-upload-img-introduction2').attr('src', path);
                    $('#introduction2').attr('value', path);
                }
                layer.msg(res.message);
            },

        });

        let uploadInst3 = upload.render({
            elem: '#ID-upload-btn-contactUrl',//绑定元素
            url: '/image/upload/',//上传接口
            done: function(res){//回调方法
                if(res.code == 200){
                    let path = res.data.src;
                    $('#ID-upload-img-contactUrl').attr('src', path);
                    $('#contactUrl').attr('value', path);
                }
                layer.msg(res.message);
            },

        });

        let form = layui.form;


        form.on('submit(formDemo)', function(data){
            $.ajax({
                url: '/image/saveCheckName/',//保存接口
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