/**
 * 公共函数库，主要是一些JS工具函数，各种插件的公共设置
 * @author HuXinsheng
 */
(function ($) {

    $.common = {};

    $.fn.extend({
        initForm: function (options) {
            //默认参数
            var defaults = {
                jsonValue: "",
                isDebug: false   //是否需要调试，这个用于开发阶段，发布阶段请将设置为false，默认为false,true将会把name value打印出来
            };
            //设置参数
            var setting = $.extend({}, defaults, options);
            var form = this;
            var jsonValue = setting.jsonValue;
            $(form)[0].reset();
            //如果传入的json字符串，将转为json对象
            if ($.type(setting.jsonValue) === "string") {
                jsonValue = $.parseJSON(jsonValue);
            }
            //如果传入的json对象为空，则不做任何操作
            if (!$.isEmptyObject(jsonValue)) {
                var debugInfo = "";
                $.each(jsonValue, function (key, value) {
                    //是否开启调试，开启将会把name value打印出来
                    if (setting.isDebug) {
                        console.info("name:" + key + "; value:" + value);
                        debugInfo += "name:" + key + "; value:" + value + " || ";
                    }
                    var formField = form.find("[name='" + key + "']");
                    if ($.type(formField[0]) === "undefined") {
                        if (setting.isDebug) {
                            console.info("can not find name:[" + key + "] in form!!!");    //没找到指定name的表单
                        }
                    } else {
                        var fieldTagName = formField[0].tagName.toLowerCase();
                        if (fieldTagName === "input") {
                            if (formField.attr("type") === "radio") {
                                $("input:radio[name='" + key + "'][value='" + value + "']").attr("checked", "checked");
                            } else {
                                if (value === 'true') {
                                    value = 1;
                                } else if (value === 'false') {
                                    value = 0;
                                }
                                formField.val(value);
                            }
                        } else if (fieldTagName === "select") {
                            //do something special
                            formField.val(value);
                        } else if (fieldTagName === "textarea") {
                            //do something special
                            formField.val(value);
                        } else {
                            formField.val(value);
                        }
                    }
                })
                if (setting.isDebug) {
                    console.info(debugInfo);
                }
            }
            return form;    //返回对象，提供链式操作
        }
    });
    $.fn.extend({
        serializeObject: function () {
            var o = {};
            var a = this.serializeArray();
            $.each(a, function () {
                if (o[this.name]) {
                    if (!o[this.name].push) {
                        o[this.name] = [o[this.name]];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            });
            return o;
        }
    });
    // 手机号码验证
    $.validator.addMethod("isMobile", function (value, element) {
        var length = value.length;
        var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, "手机号不合法");
    // 添加验证方法：至少包含两种规则
    $.validator.addMethod("strongPWD", function (value, element) {
        if (passwordLevel(value) == 1) {
            return false;
        }
        return true
    }, "密码至少数字加字母组合");
})(jQuery);

//-- Javascript对象扩展--开始-//
/**
 * 去掉开头、结尾的空格
 *
 * @return {}
 */
String.prototype.trim = function () {
    return this.replace(/(^\s+)|\s+$/g, "");
};

/**
 * 转换字符串为json对象
 */
String.prototype.toJson = function () {
    return eval('(' + this + ')');
};

String.prototype.endsWithIgnoreCase = function (str) {
    return (this.toUpperCase().match(str.toUpperCase() + "$") == str.toUpperCase()) ||
        (this.toLowerCase().match(str.toLowerCase() + "$") == str.toLowerCase());
}

/**
 * 输出2010-02-05格式的日期字符串
 *
 * @return {}
 */
Date.prototype.toDateStr = function () {
    return ($.common.browser.isMozila() || $.common.browser.isChrome() ? (this.getYear() + 1900) : this.getYear()) + "-" +
        (this.getMonth() < 10 ? "0" + this.getMonth() : this.getMonth()) +
        "-" +
        (this.getDate() < 10 ? "0" + this.getDate() : this.getDate());
};

/**
 * 日期格式化
 * @param {Object} format
 */
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(), //day
        "h+": this.getHours(), //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
        "S": this.getMilliseconds() //millisecond
    }
    if (/(y+)/.test(format))
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(format))
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
    return format;
}


/**
 * 将字符串格式的日期转换为日期类型对象
 * @param {Object} strDate
 */
Date.toDate = function (strDate) {
    var strDs = strDate.split('-');
    var year = parseInt(strDs[0]);
    var month = parseInt(strDs[1]);
    var date = parseInt(strDs[2]);
    return new Date(year, month, date);
};

/**
 * 通过当前时间计算当前周数
 */
Date.prototype.getWeekNumber = function () {
    var d = new Date(this.getFullYear(), this.getMonth(), this.getDate(), 0, 0, 0);
    var DoW = d.getDay();
    d.setDate(d.getDate() - (DoW + 6) % 7 + 3); // Nearest Thu
    var ms = d.valueOf(); // GMT
    d.setMonth(0);
    d.setDate(4); // Thu in Week 1
    return Math.round((ms - d.valueOf()) / (7 * 864e5)) + 1;
}

//+---------------------------------------------------
//| 日期计算
//+---------------------------------------------------
Date.prototype.DateAdd = function (strInterval, Number) {
    var dtTmp = this;
    switch (strInterval) {
        case 's':
            return new Date(Date.parse(dtTmp) + (1000 * Number));
        case 'n':
            return new Date(Date.parse(dtTmp) + (60000 * Number));
        case 'h':
            return new Date(Date.parse(dtTmp) + (3600000 * Number));
        case 'd':
            return new Date(Date.parse(dtTmp) + (86400000 * Number));
        case 'w':
            return new Date(Date.parse(dtTmp) + ((86400000 * 7) * Number));
        case 'q':
            return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + Number * 3, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
        case 'm':
            return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + Number, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
        case 'y':
            return new Date((dtTmp.getFullYear() + Number), dtTmp.getMonth(), dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
    }
};

//-- Javascript对象扩展--结束 -//

//-- 自定义类-开始 --/
function StringBuffer() {
    this._strings_ = new Array();
}

StringBuffer.prototype.append = function (str) {
    this._strings_.push(str);
    return this;
};

StringBuffer.prototype.toString = function () {
    return this._strings_.join("").trim();
};

/**
 * 以键值对存储
 */
function Map() {
    var struct = function (key, value) {
        this.key = key;
        this.value = value;
    };

    var put = function (key, value) {
        for (var i = 0; i < this.arr.length; i++) {
            if (this.arr[i].key === key) {
                this.arr[i].value = value;
                return;
            }
        }
        this.arr[this.arr.length] = new struct(key, value);
        this._keys[this._keys.length] = key;
    };

    var get = function (key) {
        for (var i = 0; i < this.arr.length; i++) {
            if (this.arr[i].key === key) {
                return this.arr[i].value;
            }
        }
        return null;
    };

    var remove = function (key) {
        var v;
        for (var i = 0; i < this.arr.length; i++) {
            v = this.arr.pop();
            if (v.key === key) {
                continue;
            }
            this.arr.unshift(v);
            this._keys.unshift(v);
        }
    };

    var size = function () {
        return this.arr.length;
    };

    var keys = function () {
        return this._keys;
    };

    var isEmpty = function () {
        return this.arr.length <= 0;
    };

    this.arr = new Array();
    this._keys = new Array();
    this.keys = keys;
    this.get = get;
    this.put = put;
    this.remove = remove;
    this.size = size;
    this.isEmpty = isEmpty;
}

/**
 * 更新jquery ui css
 * @param {Object} locStr
 */
function updateCSS(locStr) {
    var cssLink = $('<link href="' + locStr + '" type="text/css" rel="Stylesheet" class="ui-theme" />');
    $("head").append(cssLink);
    if ($("link.ui-theme").size() > 3) {
        $("link.ui-theme:first").remove();
    }
}

/**
 * 更新自定义CSS
 */
function updateCustomCss() {
    var customStyleUrl = ctx + '/css/style.css';
    var cssLink = $('<link href="' + customStyleUrl + '" type="text/css" rel="Stylesheet" class="custom-style" />');
    $("head").append(cssLink);
    if ($("link.custom-style").size() > 3) {
        $("link.custom-style:first").remove();
    }
}

/**
 * 引入css、script文件
 * @param {Object} file
 */
function include(file) {
    var files = typeof file == "string" ? [file] : file;
    for (var i = 0; i < files.length; i++) {
        var name = files[i].replace(/^\s|\s$/g, "");
        var att = name.split('.');
        var ext = att[att.length - 1].toLowerCase();
        var isCSS = ext == "css";
        var tag = isCSS ? "link" : "script";
        var attr = isCSS ? " type='text/css' rel='stylesheet' " : " language='javascript' type='text/javascript' ";
        var link = (isCSS ? "href" : "src") + "='" + '' + name + "'";
        if ($(tag + "[" + link + "]").length == 0) {
            $("\\<" + tag + attr + link + ">\\<\\/" + tag + ">").appendTo('head');
        }
    }
}
//-- 自定义类-结束 --/

$.validator.addMethod("character", function (value, param) {
    if (/^[a-zA-Z0-9_]{1,}$/.test(value)) {
        if (param && param.length > 0) {
            return value.replace(/[^\x00-\xff]/g, "**").length <= param[0];
        }
        return true;
    } else {
        return false;
    }
}, "请输入英文、数字、下划线的组合,且长度不能大于{0}个字符");
$.validator.addMethod("minLength", function (value, param) {
    return value.replace(/[^\x00-\xff]/g, "**").length == param[0];
}, "该输入框长度不能少于{0}！");
$.validator.addMethod("maxLength", function (value, param) {
    return value.replace(/[^\x00-\xff]/g, "**").length <= param[0];
}, "该输入框长度不能大于{0}！");

/**
 * 初始化下拉框数据
 * @param e 下拉框对象
 * @param data 数据
 * @param key 数据key
 * @param name 数据名称
 */
function initSelect(e, data, key, name) {
    if (e) {
        var html = '';
        $.each(data, function (i, item) {
            html += '<option value="' + item[key] + '">' + item[name] + '</option>';
        });
        $(e).append($(html));
    }
}
/**
 * 初始化下拉框数据
 * @param e 下拉框对象
 * @param data 数据
 * @param key 数据key
 * @param name 数据名称
 */
function initGroupSelect(e, data, key, name) {
    if (e) {
        var html = '';
        $.each(data, function (i, item) {
            html += '<optgroup label="' + item.roleName + '">';
            if (item.userExts && item.userExts.length > 0) {
                $.each(item.userExts, function (j, userItem) {
                    html += '<option value="' + userItem[key] + '">' + userItem[name] + '</option>';
                })
            }
            html += '</optgroup>';
        });
        $(e).append($(html));
        $(e).selectpicker('refresh');
        $(e).selectpicker('show');
    }
}

function fnGetTableData(oTable, field, val) {
    var tableData = oTable.fnGetData();
    var rObj = {};
    $.each(tableData, function (i, item) {
        if (item[field] == val) {
            rObj = item;
            return;
        }
    });
    return rObj;
}

function initError(error, element) {
    var elem = $(element),
        corners = ['right top', 'bottom left'],
        flipIt = elem.parents('span.right').length > 0;
    if (!error.is(':empty')) {
        var target = elem.filter(':not(.has-error)');
        //对应multiselect控件
        if ($(target).is("input:hidden") || ($(target).is("select") && $(target).css("display") == "none")) {
            target = $(target).next();
        }
        elem.filter(':not(.has-error)').qtip({
            overwrite: false,
            content: error,
            position: {
                my: corners[flipIt ? 0 : 1],
                at: corners[flipIt ? 1 : 0],
                target: target,
                viewport: $(window)
            },
            show: {
                event: false,
                ready: true
            },
            hide: false,
            style: {
                classes: 'qtip-red' // Make it red... the classic error colour!
            }
        }).qtip('option', 'content.text', error);
    } else {
        $(elem).closest('.form-group').removeClass('has-error').addClass('has-info');
        elem.qtip('destroy');
    }
}

function getCollectionData(data, key, value) {
    var obj = {};
    if (data && data.length > 0) {
        $.each(data, function () {
            var item = this;
            if (item[key] == value) {
                obj = item;
                return false;
            }
        })
    }
    return obj;
}
function confirmDialog(title, info, callback) {
    bootbox.confirm({
        buttons: {
            confirm: {
                label: '<i class="icon-fire bigger-110"></i>确定',
                className: 'btn-warning'
            },
            cancel: {
                label: '取消',
                className: 'btn-default'
            }
        },
        message: '<h5>' + title + '</h5>',
        callback: callback,
        title: "<i class='icon-trash red'></i> " + info
    });
}
function enableTooltips(table, target) {
    $(table).find(target).tooltip({container: 'body'});
}
function initJqgridSize(grid) {
    var parent_column = $(grid).closest('[class*="col-"]');
    //resize to fit page size
    $(window).on('resize.jqGrid', function () {
        $(grid).jqGrid('setGridWidth', parent_column.width());
    });

    $(document).on('settings.ace.jqGrid', function (ev, event_name, collapsed) {
        if (event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed') {
            setTimeout(function () {
                $(grid).jqGrid('setGridWidth', parent_column.width());
            }, 20);
        }
    });
}
function getArrayIndex(dataArray, key, value) {
    var index = -1;
    if (dataArray && dataArray.length > 0) {
        $.each(dataArray, function (i, item) {
            if (item[key] == value) {
                index = i;
                return false;
            }
        })
    }
    return index;
}
function getArrayObject(dataArray, key, value) {
    var obj;
    if (dataArray && dataArray.length > 0) {
        $.each(dataArray, function (i, item) {
            if (item[key] == value) {
                obj = item;
                return false;
            }
        })
    }
    return obj;
}
function fillOption(el_id, defaultV, data, id, text) {
    var el = $(el_id);
    if (data) {
        var selected = false;
        $.each(data, function (i, item) {
            if (item[id] == defaultV) {
                selected = true;
            }
            var option = '<option value="' + item[id] + '">' + item[text] + '</option>';
            el.append(option);
        });
        if (selected)
            el.val(defaultV).trigger('change');
        else {
            //el.val('0').trigger('change');
        }
    }
}
function passwordLevel(password) {
    var Modes = 0;
    for (i = 0; i < password.length; i++) {
        Modes |= CharMode(password.charCodeAt(i));
    }
    return bitTotal(Modes);

    //CharMode函数
    function CharMode(iN) {
        if (iN >= 48 && iN <= 57)//数字
            return 1;
        if (iN >= 65 && iN <= 90) //大写字母
            return 2;
        if ((iN >= 97 && iN <= 122) || (iN >= 65 && iN <= 90)) //大小写
            return 4;
        else
            return 8; //特殊字符
    }

    //bitTotal函数
    function bitTotal(num) {
        modes = 0;
        for (i = 0; i < 4; i++) {
            if (num & 1) modes++;
            num >>>= 1;
        }
        return modes;
    }
}

function drawTable(t, status) {
    $('body').showLoading({
        'addClass': 'loading-indicator-bars'
    });
    if (!status) {
        status = false;
    }
    t.fnDraw(status);
    resetIframeHeight();
}
function drawGrid(g,options) {
    $('body').showLoading({
        'addClass': 'loading-indicator-bars'
    });
    g.trigger("reloadGrid", options);
    resetIframeHeight();
}