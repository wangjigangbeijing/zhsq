
$(document).ready(function (){
	
	$('#xgyqInfoAnchor').click(function () {
        loadXgyqInfo('新冠疫情信息');
    });
	
	$('#jsjbAnchor').click(function () {
        loadJSJBFW('接诉即办服务');
    });
	
	
	loadXgyqInfo();
});

var curSXType = '';

function loadXgyqInfo()
{
	$('#zhfwDiv').load("./nfw/zhfw/xgyqinfo.html", function () {
        
    });
}

function loadJSJBFW()
{
	$('#zhfwDiv').load("./nfw/zhfw/jsjbfw.html", function () {
        
    });
}


