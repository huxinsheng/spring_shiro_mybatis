var dateUtils = ( function () {
    return {
        /**
         * getXAxis（）方法作用：获取开始日期和结束日期之间（包含开始日期和结束日期）的日期数组，可作为时间轴坐标等
         * @param  filters: {tab:0/1/2, startTime:开始日期, endTime:结束日期}
         * 说明： tab：取值0或1或2，分别表示日、月、年，对应的startTime和endTime的格式分别为'yyyy-MM-dd'、'yyyy-MM'、'yyyy'
         */
        getXAxis: function (filters) {
            if (!filters.startTime || !filters.endTime)
                return [];
            var tab = filters.tab;
            var startTime = new Date(filters.startTime);
            var endTime = new Date(filters.endTime);
            var length = 0;   //日期跨度变量
            if (0 == tab) {
                length = (endTime.getTime() - startTime.getTime()) / (1000 * 24 * 60 * 60) + 1;
            } else if (1 == tab) {
                length = (endTime.getFullYear() - startTime.getFullYear()) * 12 + (endTime.getMonth() - startTime.getMonth()) + 1;
            } else {
                length = endTime.getFullYear() - startTime.getFullYear() + 1;
            }
            var xAxis = new Array(length);

            for (var i = 1; i < length; i++) {
                if (0 == tab) {
                    xAxis[0] = new Date(filters.startTime).format("yyyy/MM/dd");
                    startTime.setDate(startTime.getDate() + 1);
                    xAxis[i] = startTime.format("yyyy/MM/dd");
                } else if (1 == tab) {
                    xAxis[0] = new Date(filters.startTime).format("yyyy/MM");
                    startTime.setMonth(startTime.getMonth() + 1);
                    xAxis[i] = startTime.format("yyyy/MM");
                } else {
                    startTime.setFullYear(startTime.getFullYear() + 1);
                    xAxis[i] = startTime.format("yyyy");
                }
            }

            return xAxis;

        },
        /**
         * 根据X坐标数组生成对应的Y坐标数组
         * @param  xArr: 上面getXAxis()方法返回的X坐标（时间轴坐标）数组;
         *  list:[{xAxis:"2017-03-01", yAxis:"200"},{xAxis:"2017-03-02", yAxis:"100"},...]  后台返回的坐标点数组，按时间升序排列
         */
        getYAxis: function (xArr, list) {
            var len = xArr.length;
            var yAxis = new Array(len);
            var j = 0;
            listLen = list.length;
            for (i in xArr) {
                if (j < listLen && xArr[i] == list[j].xAxis) {
                    yAxis[i] = list[j].yAxis;
                    j++;
                } else {
                    yAxis[i] = 0;
                }
            }

            return yAxis;
        },
        /**
         * 获取两个日期天数
         * @param  filters: {startTime:开始日期, endTime:结束日期}
         */
        getDays: function (filters) {
            var start = filters.startTime;
            var end = filters.endTime;
            start = start.replace(/-/g, "/");
            var startdate = new Date(start);
            end = end.replace(/-/g, "/");
            var enddate = new Date(end);

            var time = enddate.getTime() - startdate.getTime();
            var days = parseInt(time / (1000 * 60 * 60 * 24));
            return days;
        }
    };
});