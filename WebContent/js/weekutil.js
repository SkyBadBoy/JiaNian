    var now = new Date();                    //当前日期
    var nowDayOfWeek = now.getDay();         //今天本周的第几天
    var nowDay = now.getDate();              //当前日
    var nowMonth = now.getMonth();           //当前月
    var nowYear = now.getYear();             //当前年
    nowYear += (nowYear < 2000) ? 1900 : 0;  //

	var now = new Date(); 
	var nowTime = now.getTime() ; 
	var day = now.getDay();
	var oneDayLong = 24*60*60*1000 ; 
	var MondayTime = nowTime - (day-1)*oneDayLong  ; 
	var SundayTime =  nowTime + (7-day)*oneDayLong ; 
	var monday = new Date(MondayTime);
	var sunday = new Date(SundayTime);

	Date.prototype.Format = function (fmt) { //author: meizz 
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
	}
	


	function mondayStart(){
		return monday.Format("yyyy-MM-dd");
	}
	function sundayEnd(){
		return sunday.Format("yyyy-MM-dd");
	}
	
    
    
    var lastMonthDate = new Date();  //上月日期
    lastMonthDate.setDate(1);
    lastMonthDate.setMonth(lastMonthDate.getMonth()-1);
    var lastYear = lastMonthDate.getYear();
    var lastMonth = lastMonthDate.getMonth();

    //格式化日期：yyyy-MM-dd
    function formatDate(date) {
        var myyear = date.getFullYear();
        var mymonth = date.getMonth()+1;
        var myweekday = date.getDate();

        if(mymonth < 10){
            mymonth = "0" + mymonth;
        }
        if(myweekday < 10){
            myweekday = "0" + myweekday;
        }
        return (myyear+"-"+mymonth + "-" + myweekday);
    }

    //获得某月的天数
    function getMonthDays(myMonth){
        var monthStartDate = new Date(nowYear, myMonth, 1);
        var monthEndDate = new Date(nowYear, myMonth + 1, 1);
        var   days   =   (monthEndDate   -   monthStartDate)/(1000   *   60   *   60   *   24);
        return   days;
    }

    //获得本季度的开始月份
    function getQuarterStartMonth(){
        var quarterStartMonth = 0;
        if(nowMonth<3){
            quarterStartMonth = 0;
        }
        if(2<6){
            quarterStartMonth = 3;
        }
        if(5<9){
            quarterStartMonth = 6;
        }
        if(nowMonth>8){
            quarterStartMonth = 9;
        }
        return quarterStartMonth;
    }

    //今天
    var getCurrentDate = new Date(nowYear, nowMonth, nowDay);
    var getCurrentDate  = formatDate(getCurrentDate)

    //昨天
    var getYesterdayDate = new Date(nowYear, nowMonth, nowDay - 1);
    var getYesterdayDate =  formatDate(getYesterdayDate);



    //获得本月的开始日期
    var getMonthStartDate = new Date(nowYear, nowMonth, 1);
    function getMonthStartDate1(){
    	return getMonthStartDate.Format("yyyy-MM-dd");
    }

    //获得本月的结束日期
    var getMonthEndDate = new Date(nowYear, nowMonth, getMonthDays(nowMonth));
    function getMonthEndDate1(){
    	return getMonthStartDate.Format("yyyy-MM-dd");
    }

    //获得上月开始时间
    var getLastMonthStartDate = new Date(nowYear, lastMonth, 1);
    var getLastMonthStartDate = formatDate(getLastMonthStartDate);

    //获得上月结束时间
    var getLastMonthEndDate = new Date(nowYear, lastMonth, getMonthDays(lastMonth));
    var getLastMonthEndDate = formatDate(getLastMonthEndDate);
    
    
    function getPreviousWeekStartEnd(date){

    	var date = new Date() || date, day, start, end, dayMSec = 24 * 3600 * 1000;

    	today = date.getDay() - 0;

    	end = date.getTime() - today * dayMSec;

    	start = end - 6 * dayMSec;

    	return {start : getFormatTime(start), end : getFormatTime(end)};

    	function getFormatTime(time){

    	date.setTime(time);

    	return date.getFullYear() + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2) ;

    	}

    	}
    
 