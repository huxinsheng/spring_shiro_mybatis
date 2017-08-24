var http = {};
$(document).ajaxComplete(function (event, xhr, settings) {
    if (xhr.getResponseHeader("sessionstatus") == "timeout") {
        if (xhr.getResponseHeader("loginPath")) {
            showErrorTips("会话过期，请重新登陆!");
            window.top.location.replace(xhr.getResponseHeader("loginPath"));
        } else {
            showErrorTips("请求超时请重新登陆 !");
        }
    }
});
http.facadeUrl = "/webApi/facade";
http.param = function (data) {
    if (!data) return null;
    var array = new Array();
    for (var name in data) {
        var key = name;
        var value = data[name];
        if (value === null) {
            continue;
        }
        if (Object.prototype.toString.call(value) === '[object Array]') {
            var parent = key;
            var children = value;
            for (var i = 0; i < children.length; i++) {
                var json = children[i];
                if (Object.prototype.toString.call(json) === '[object Object]') {
                    for (var name in json) {
                        key = parent + '[' + i + '].' + name;
                        value = json[name];
                        if (value == null) {
                            continue;
                        }
                        key = encodeURIComponent(key);
                        value = encodeURIComponent(value);
                        array.push(key + '=' + value);
                    }
                } else {
                    key = parent + '[' + i + ']';
                    key = encodeURIComponent(key);
                    value = encodeURIComponent(json);
                    array.push(key + '=' + value);
                }
            }
        } else {
            key = encodeURIComponent(key);
            value = encodeURIComponent(value);
            array.push(key + '=' + value);
        }
    }
    return array.join('&');
};
http.post = function (url, param, success, error) {
    if (typeof(param) == 'function') {
        success = param;
        error = success;
        param = null;
    }
    $('body').showLoading({
        'addClass': 'loading-indicator-bars'
    });
    $.ajax({
        type: "POST",
        url: ctx + url,
        data: this.param(param),
        success: function (r) {
            if (r) {
                r = $.parseJSON(r);
                if (!r.errcode) {
                    if (success) {
                        success(r);
                    }
                } else {
                    if (error) {
                        error(r);
                    }
                    showErrorTips(r.errmsg);
                }
            } else {
                success();
            }
            $('body').hideLoading();
        },
        error: function (data, status) {
            if (error) {
                error(data);
            }
            showErrorTips('系统忙,请稍后再试');
            $('body').hideLoading();
        }
    });
};
http.getJSON = function (url, param, callback) {
    if ($.isFunction(param)) {
        callback = param;
        param = undefined;
    }
    $('body').showLoading({
        'addClass': 'loading-indicator-bars'
    });
    $.getJSON(ctx + url, this.param(param), function (r) {
        if (!r.errcode) {
            if (callback) {
                callback(r);
            }
            $('body').hideLoading();
        } else {
            showErrorTips(r.errmsg);
            $('body').hideLoading();
        }
    });
};
http.load = function (el, url, param, callback) {
    if ($.isFunction(param)) {
        callback = param;
        param = undefined;
    }
    $('body').showLoading({
        'addClass': 'loading-indicator-bars'

    });
    if (el) {
        $(el).load(ctx + url, param, callback);
    } else {
        $.load(url, param, callback);
    }
};
http.getURI = function () {
    
};