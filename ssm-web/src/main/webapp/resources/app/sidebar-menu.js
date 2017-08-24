(function ($) {
    $.fn.sidebarMenu = function (options) {
        options = $.extend({}, $.fn.sidebarMenu.defaults, options || {});
        var target = $(this);
        target.addClass('nav');
        target.addClass('nav-list');
        var url = window.location.pathname;

        /*var li = $('<li></li>');
        if (url == ctx + '/') {
            li.addClass('active');
        }
        var a = $('<a></a>');
        var id = 'menu_000000';
        var href = 'javascript:addTabs({id:\'000000\',title: \'首页\',close: true,url: \'' + ctx + "/" + '\',target:\'' + id + '\'});';
        a.attr('href', href);
        a.attr('id',id);
        var icon = $('<i></i>');
        icon.addClass("fa fa-home");
        var text = $('<span></span>');
        text.addClass('menu-text').text(" 系统首页");
        a.append(icon);
        a.append(text);
        li.append(a);
        target.append(li);*/

        if (options.data) {
            init(target, options.data);
        }
        else {
            if (!options.url) return;
            $.getJSON(options.url, options.param, function (data) {
                init(target, data);
            });
        }

        /*var menu = target.find("[href='" + url + "']");
        menu.parent().addClass('active');
        menu.parent().parentsUntil('.nav-list', 'li').addClass('active').addClass('open');*/

        function init(target, data) {
            $.each(data, function (i, item) {
                var li = $('<li></li>');
                var a = $('<a></a>');
                var icon = $('<i></i>');
                icon.addClass("menu-icon ").addClass(item.icon);
                var text = $('<span></span>');
                text.addClass('menu-text').text(" " + item.name);
                a.append(icon);
                a.append(text);
                if (item.subMenus && item.subMenus.length > 0) {
                    var subMenuId = "submenu_" + i;
                    a.attr('href', '#' + subMenuId);
                    a.addClass('dropdown-toggle');
                    var arrow = $('<b></b>');
                    arrow.addClass('arrow').addClass('fa fa-angle-down');
                    a.append(arrow);
                    li.append(a);
                    var menus = $('<ul></ul>');
                    menus.id = subMenuId;
                    menus.addClass('submenu');
                    init(menus, item.subMenus);
                    li.append(menus);
                }
                else {
                    var id = 'menu_' + item.id;
                    var href = 'javascript:addTabs({id:\'' + item.id + '\',title: \'' + item.name + '\',close: true,url: \'' + ctx + item.url + '\',target:\'' + id + '\'});';
                    a.attr('href', href);
                    a.attr('id', id);
                    li.append(a);
                }
                target.append(li);
            });
        }
    };

    $.fn.sidebarMenu.defaults = {
        url: null,
        param: null,
        data: null
    };
})(jQuery);