<!DOCTYPE html>
<html>
<head>
    <#include "common.ftl">
</head>
<body>
<div class="layui-upload">
    <button type="button" class="layui-btn" id="uploadBtn">选择视频</button>
    <div class="layui-upload-list">
        <input type="hidden" name="videoUrl" id="videoUrl" value="">
        <video src="" controls></video>
    </div>
</div>
<script src="https://cdn.staticfile.org/layui/2.5.6/layui.js"></script>
<script src="https://cdn.staticfile.org/jquery/3.5.1/jquery.min.js"></script>
<script>
    layui.use(['upload', 'layer'], function() {
        var upload = layui.upload;
        var layer = layui.layer;
        // 选择视频按钮点击事件
        $('#uploadBtn').on('click', function() {
            upload.render({
                elem: this,
                url: 'your_upload_video_url', // 上传接口地址
                accept: 'video', // 允许上传的文件类型
                done: function(res) {
                    if (res.code === 0) {
                        var videoUrl = res.data.videoUrl; // 上传成功后返回的视频地址
                        $('#videoUrl').val(videoUrl);
                        $('video').attr('src', videoUrl);
                    } else {
                        layer.msg('上传失败');
                    }
                },
                error: function() {
                    layer.msg('上传错误');
                }
            });
        });
    });
</script>
</body>
</html>
