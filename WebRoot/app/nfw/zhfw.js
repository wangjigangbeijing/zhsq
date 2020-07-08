
$(document).ready(function (){
	
	$('#xgyqInfoAnchor').click(function () {
        loadXgyqInfo('新冠疫情信息');
    });
	
	loadXgyqInfo();
});

var curSXType = '';

function loadXgyqInfo()
{
	$('#zhfwDiv').load("./nfw/zhfw/xgyqinfo.html", function () {
        
    });
}




