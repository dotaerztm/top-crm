<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>士气集团后台系统</title>
    <#include "common.ftl">
</head>
<body class="layui-layout-body layuimini-all">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header header">
        <div class="layui-logo" align="left">
            <a href="" >
                <img src="images/logo.png" alt="logo" >
                <h1 style="margin: 0 0 0 3px">士气集团后台系统</h1>
            </a>
        </div>
        <a>
            <div class="layuimini-tool"><i title="展开" class="fa fa-outdent" data-side-fold="1"></i></div>
        </a>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item mobile layui-hide-xs" lay-unselect>
                <a href="javascript:;" data-check-screen="full"><i class="fa fa-arrows-alt"></i></a>
            </li>
            <li class="layui-nav-item layuimini-setting">
                <a href="javascript:;">${(user.userName)!}</a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="javascript:;" data-iframe-tab="${ctx.contextPath}/user/setting" data-title="基本资料" data-icon="fa fa-gears">基本资料</a>
                    </dd>
                    <dd>
                        <a href="javascript:;" data-iframe-tab="${ctx.contextPath}/user/password" data-title="修改密码" data-icon="fa fa-gears">修改密码</a>
                    </dd>
                    <dd>
                        <a href="${ctx.contextPath}/signout" class="login-out">退出登录</a>
                    </dd>
                </dl>
            </li>
            <li class="layui-nav-item layuimini-select-bgcolor mobile layui-hide-xs" lay-unselect>
                <a href="javascript:;"></a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll layui-left-menu">
                <ul class="layui-nav layui-nav-tree layui-left-nav-tree layui-this" id="currency">
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-gears"></i><span class="layui-left-nav"> 系统设置</span> <span class="layui-nav-more"></span></a>
                        <dl class="layui-nav-child">
                                <dd>
                                    <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11" data-tab="user/userList" target="_self"><i class="fa fa-user"></i><span class="layui-left-nav"> 用户管理</span></a>
                                </dd>

<#--                                <dd class="">-->
<#--                                    <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-12" data-tab="role/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 角色管理</span></a>-->
<#--                                </dd>-->
<#--                                <dd class="">-->
<#--                                    <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-13" data-tab="menu/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 菜单管理</span></a>-->
<#--                                </dd>-->
                        </dl>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-gears"></i><span class="layui-left-nav">团队管理</span> <span class="layui-nav-more"></span></a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11" data-tab="teamManage/founder" target="_self"><i class="fa fa-user"></i><span class="layui-left-nav"> 集团创始人</span></a>
                            </dd>

                            <dd class="">
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-12" data-tab="teamManage/coreTeam" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 核心团队</span></a>
                            </dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-gears"></i><span class="layui-left-nav"> 商业合作</span> <span class="layui-nav-more"></span></a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11" data-tab="cooperation/businessDirection" target="_self"><i class="fa fa-user"></i><span class="layui-left-nav">商业方向</span></a>
                            </dd>
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11" data-tab="cooperation/directionDetail" target="_self"><i class="fa fa-user"></i><span class="layui-left-nav">商业方向-详情页</span></a>
                            </dd>

                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11" data-tab="cooperation/brandDirection" target="_self"><i class="fa fa-user"></i><span class="layui-left-nav">品牌方向</span></a>
                            </dd>
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11" data-tab="cooperation/brandDetail" target="_self"><i class="fa fa-user"></i><span class="layui-left-nav">品牌方向-详情页</span></a>
                            </dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-gears"></i><span class="layui-left-nav"> 士气大学</span> <span class="layui-nav-more"></span></a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11" data-tab="university/gradeOne" target="_self"><i class="fa fa-user"></i><span class="layui-left-nav">士气一年级</span></a>
                            </dd>
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11" data-tab="university/gradeOneDetail" target="_self"><i class="fa fa-user"></i><span class="layui-left-nav">士气一年级-详情页</span></a>
                            </dd>

                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11" data-tab="university/gradeTwo" target="_self"><i class="fa fa-user"></i><span class="layui-left-nav">士气二年级</span></a>
                            </dd>
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11" data-tab="university/gradeTwoDetail" target="_self"><i class="fa fa-user"></i><span class="layui-left-nav">士气二年级-详情页</span></a>
                            </dd>

                            <dd class="">
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-12" data-tab="university/publicWelfare" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 士气公益课</span></a>
                            </dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-gears"></i><span class="layui-left-nav"> 士气众</span> <span class="layui-nav-more"></span></a>
                        <dl class="layui-nav-child">
                            <dd class="">
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-12" data-tab="many/information" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 士气资讯</span></a>
                            </dd>

                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11" data-tab="many/works" target="_self"><i class="fa fa-user"></i><span class="layui-left-nav"> 士气作品</span></a>
                            </dd>
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11" data-tab="many/worksDetailImage" target="_self"><i class="fa fa-user"></i><span class="layui-left-nav"> 作品详情-图片</span></a>
                            </dd>
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11" data-tab="many/worksDetailVideo" target="_self"><i class="fa fa-user"></i><span class="layui-left-nav"> 作品详情-视频</span></a>
                            </dd>
                        </dl>
                    </li>

                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-gears"></i><span class="layui-left-nav"> 活动管理</span> <span class="layui-nav-more"></span></a>
                        <dl class="layui-nav-child">
                            <dd class="">
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-12" data-tab="student/studentTable" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 学员管理</span></a>
                            </dd>
                        </dl>
                        <dl class="layui-nav-child">
                            <dd class="">
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-12" data-tab="campaign/springFestival" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 春节活动</span></a>
                            </dd>
                        </dl>
                    </li>

<#--                    <li class="layui-nav-item">-->
<#--                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-gears"></i><span class="layui-left-nav"> 基础资料</span> <span class="layui-nav-more"></span></a>-->
<#--                        <dl class="layui-nav-child">-->
<#--                            <dd>-->
<#--                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11" data-tab="supplier/index" target="_self"><i class="fa fa-user"></i><span class="layui-left-nav"> 供应商管理</span></a>-->
<#--                            </dd>-->

<#--                            <dd class="">-->
<#--                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-12" data-tab="customer/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 客户管理</span></a>-->
<#--                            </dd>-->
<#--                            <dd class="">-->
<#--                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-13" data-tab="goods/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 商品管理</span></a>-->
<#--                            </dd>-->
<#--                            <dd class="">-->
<#--                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-13" data-tab="goodsType/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 商品分类管理</span></a>-->
<#--                            </dd>-->
<#--                            <dd class="">-->
<#--                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-13" data-tab="stock/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 期初库存</span></a>-->
<#--                            </dd>-->
<#--                        </dl>-->
<#--                    </li>-->

                    <span class="layui-nav-bar" style="top: 201px; height: 0px; opacity: 0;"></span>
                </ul>
        </div>
    </div>

    <div class="layui-body">
        <div class="layui-tab" lay-filter="layuiminiTab" id="top_tabs_box">
            <ul class="layui-tab-title" id="top_tabs">
                <li class="layui-this" id="layuiminiHomeTabId" lay-id="welcome"><i class="fa fa-home"></i> <span>首页</span></li>
            </ul>


            <ul class="layui-nav closeBox">
                <li class="layui-nav-item">
                    <a href="javascript:;"> <i class="fa fa-dot-circle-o"></i> 页面操作</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-page-close="other"><i class="fa fa-window-close"></i> 关闭其他</a></dd>
                        <dd><a href="javascript:;" data-page-close="all"><i class="fa fa-window-close-o"></i> 关闭全部</a></dd>
                    </dl>
                </li>
            </ul>
            <div class="layui-tab-content clildFrame">
                <div id="layuiminiHomeTabIframe" class="layui-tab-item layui-show">
                </div>
            </div>
        </div>



    </div>

</div>

<script type="text/javascript" src="${ctx.contextPath}/js/main.js"></script>
</body>
</html>
