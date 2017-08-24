var default_opts = {
    lines: 10, // 条状数目
    length: 15, // 条状长度
    width: 7, // 条状宽度
    radius: 10, // 条状中心的半径
    corners: 1, // 条状的圆滑度(0..1)
    rotate: 0, // 条状旋转的角度
    direction: 1, // 条状旋转的速度
    color: '#000', //颜色
    speed: 1, // 条状旋转的速度
    trail: 60, // Afterglow percentage
    shadow: false, // Whether to render a shadow
    hwaccel: false, // Whether to use hardware acceleration
    className: 'spinner', // The CSS class to assign to the spinner
    zIndex: 5, // The z-index (defaults to 2000000000)
    top: '200px', // Top position relative to parent in px    //200PX
    left: 'auto' // Left position relative to parent in px
};
$.ajaxSetup({
    contentType: "application/x-www-form-urlencoded;charset=utf-8",
    complete: function (XMLHttpRequest, textStatus) {
        var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
        //通过XMLHttpRequest取得响应头，sessionstatus，
        if (sessionstatus == "timeout") {
            //如果超时就处理 ，指定要跳转的页面
            window.parent.parent.location.replace(ctx + "/login");
        }
    }
});
/*var _spin = new Spinner(default_opts);*/
//页面提示
function showPageTips(style, message) {
    if (!message) {
        message = style;
        style = 'success';
    }
    var el = $('<div class="alert alert-' + style + '">' + message + '</div>');
    $('body').append(el);
    el.css({
        'position': 'fixed',
        'z-index': '10000',
        'top': '0',
        'left': '50%',
        'margin-top': '0px',
        'min-width': '300px',
        'margin-left': function () {
            return -$(this).width() / 2;
        },
        'text-align': 'center',
        'padding': '12px'
    });
    el.animate({
        'opacity': 0
    }, 6000);
}

//页面提示
function showSuccessTips(message) {
    showPageTips('success', message);
}

//页面提示
function showWarningTips(message) {
    showPageTips('warning', message);
}

//页面提示
function showErrorTips(message) {
    showPageTips('danger', message);
}
/**
 * 显示模式对话框
 * @param dialog 对话框dom元素，可以是class名，也可以id
 * @param title 对话框标题
 * @param validate  form验证对象
 * @param form form对象
 * @param formData form数据，可为空
 */
function showDialog(dialog, title, validate, form, formData) {
    if (!formData)
        formData = {};
    $(form + ' .form-group').removeClass('has-error');
    validate.resetForm();
    $(form)[0].reset();
    $(form).initForm({jsonValue: formData, isDebug: false});
    $(dialog + ' .modal-title').html(title);
    /* 完成拖拽 */
    $(dialog).draggable({
        cursor: "move",
        handle: '.modal-header'
    });
    $(dialog).modal('show');

}
/**
 * 显示模式对话框
 * @param divEl 编辑divdom元素，可以是class名，也可以id
 * @param title 编辑div的标题
 * @param validate  form验证对象
 * @param form form对象
 * @param formData form数据，可为空
 */
function showEditDiv(divEl, title, validate, form, formData) {
    if (!formData)
        formData = {};
    $(form + ' .form-group').removeClass('has-error');
    validate.resetForm();
    $(divEl + ' .table-header').html(title);
    $(form)[0].reset();
    $(form).initForm({jsonValue: formData, isDebug: false});
    $(divEl).show();

}
function resetIframeHeight(){
    $('.tab-content .active',window.parent.document).find('iframe').height(500);
    $('.tab-content .active',window.parent.document).find('iframe').height(document.body.scrollHeight)
}
$(function () {
    $('.bootbox-close-button').on('click', function () {
        $('.bootbox input[type=checkbox]:checked').each(function () {
            $(this).attr('checked', false);
        });
        $('.bootbox').modal('hide');
        cleanTree();
    });
});
function cleanTree() {
    $('.bootbox input[type=checkbox]:checked').each(function () {
        $(this).attr('checked', false);
    });
    $('.tree').removeData("fu.tree");
    //清空事件
    $('.tree').unbind('click');
}
var editorConfig = [
    'fontfamily', //字体
    'fontsize', //字号
    'paragraph', //段落格式
    'justifyleft', //居左对齐
    'justifyright', //居右对齐
    'justifycenter', //居中对齐
    'justifyjustify', //两端对齐
    'forecolor', //字体颜色
    'undo', //撤销
    'redo', //重做
    'bold', //加粗
    'indent', //首行缩进
    //'snapscreen', //截图
    'italic', //斜体
    'underline', //下划线
    'strikethrough', //删除线
    'subscript', //下标
    'fontborder', //字符边框
    'superscript', //上标
    'formatmatch', //格式刷
    'anchor', //锚点
    'source', //源代码
    'blockquote', //引用
    'pasteplain', //纯文本粘贴模式
    'selectall', //全选
    //'print', //打印
    'horizontal', //分隔线
    'removeformat', //清除格式
    'time', //时间
    'date', //日期
    'unlink', //取消链接
    //'insertrow', //前插入行
    //'insertcol', //前插入列
    //'mergeright', //右合并单元格
    //'mergedown', //下合并单元格
    //'deleterow', //删除行
    //'deletecol', //删除列
    //'splittorows', //拆分成行
    //'splittocols', //拆分成列
    //'splittocells', //完全拆分单元格
    //'deletecaption', //删除表格标题
    'inserttitle', //插入标题
    //'mergecells', //合并多个单元格
    //'deletetable', //删除表格
    'cleardoc', //清空文档
    //'insertparagraphbeforetable', //"表格前插入行"
    //'insertcode', //代码语言
    'simpleupload', //单图上传
    'insertimage', //多图上传
    //'edittable', //表格属性
    //'edittd', //单元格属性
    'link', //超链接
    //'emotion', //表情
    'spechars', //特殊字符
    'searchreplace', //查询替换
    //'map', //Baidu地图
    //'gmap', //Google地图
    //'insertvideo', //视频
    'backcolor', //背景色
    'insertorderedlist', //有序列表
    'insertunorderedlist', //无序列表
    'fullscreen', //全屏
    'directionalityltr', //从左向右输入
    'directionalityrtl', //从右向左输入
    'rowspacingtop', //段前距
    'rowspacingbottom', //段后距
    //'pagebreak', //分页
    //'insertframe', //插入Iframe
    //'imagenone', //默认
    //'imageleft', //左浮动
    //'imageright', //右浮动
    'attachment', //附件
    'imagecenter', //居中
    'wordimage', //图片转存
    'lineheight', //行间距
    'edittip ', //编辑提示
    //'customstyle', //自定义标题
    'autotypeset', //自动排版
    //'webapp', //百度应用
    //'touppercase', //字母大写
    //'tolowercase', //字母小写
    //'background', //背景
    //'template', //模板
    //'scrawl', //涂鸦
    //'music', //音乐
    //'inserttable', //插入表格
    //'drafts', // 从草稿箱加载
    //'charts', // 图表
    'preview', //预览
    'help' //帮助
];
