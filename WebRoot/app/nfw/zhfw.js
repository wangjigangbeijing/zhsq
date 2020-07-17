
$(document).ready(function (){
	
	$('#xgyqInfoAnchor').click(function () {
        loadXgyqInfo();
    });
	
	/*$('#jsjbAnchor').click(function () {
        loadJSJBFW();
    });*/
	
	if(curSXType == '' || curSXType == '新冠疫情信息')
		loadXgyqInfo();
	else if (curSXType == '接诉即办服务')
		loadJSJBFW();
});

var curSXType = '';

function loadXgyqInfo()
{
	$('#zhfwDiv').load("./nfw/zhfw/xgyqinfo.html", function () {
        
    });
}
/*
function loadJSJBFW()
{
	$('#zhfwDiv').load("./nfw/zhfw/jsjbfw.html", function () {
        
    });
}
*/

