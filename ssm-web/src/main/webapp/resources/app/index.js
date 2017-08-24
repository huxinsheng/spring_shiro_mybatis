$(function () {
    initLeftMenu();
    $('.personal-config').on('click', function () {
        console.info('设置');
    });
    $('.personal-profile').on('click', function () {
        console.info('个人资料');
    })
});
var path = '/ssm/analysis/';
//初始化左侧
function initLeftMenu() {
    http.getJSON("/core/menu/loadUserMenus", function (result) {
        $('.nav-list').sidebarMenu({data:result});
    });
}

