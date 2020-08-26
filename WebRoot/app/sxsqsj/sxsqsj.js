
$(document).ready(function (){
	
	$('#sqdjAnchor').click(function () {
        loadSxsqsjPageByType('社区党建事项');
    });
	
	$('#mjzzAnchor').click(function () {
        loadSxsqsjPageByType('民主自治事项');
    });
	
	$('#sqfwAnchor').click(function () {
        loadSxsqsjPageByType('社区服务事项');
    });
	
	$('#pajsAnchor').click(function () {
        loadSxsqsjPageByType('平安建设事项');
    });
	
	$('#whjyAnchor').click(function () {
        loadSxsqsjPageByType('文化教育事项');
    });
	
	$('#sqhjAnchor').click(function () {
        loadSxsqsjPageByType('社区环境事项');
    });
	
	$('#wsjkAnchor').click(function () {
        loadSxsqsjPageByType('卫生健康事项');
    });
	
	$('#sqzzAnchor').click(function () {
        loadSxsqsjPageByType('社区组织事项');
    });
	
	if(curSXType == '')
	{
		curSXType = '社区党建事项';	
	}
	
	loadSxsqsjPageByType(curSXType);
	
	$.get(getContextPath()+"/sxsqsjController/count",
	function(result){
		var obj = jQuery.parseJSON(result);  
		if(obj.success)
		{
			$('#sqdjSpan').html(obj.sqdj);//社区党建
			$('#mzzzSpan').html(obj.mzzz);//民主自治			
			$('#sqfwSpan').html(obj.sqfw);//社区服务
			$('#pajsSpan').html(obj.pajs);//平安建设
			$('#whjySpan').html(obj.whjy);//文化教育
			$('#sqhjSpan').html(obj.sqhj);//社区环境
			$('#wsjkSpan').html(obj.wsjk);//卫生健康
		}
	});
});

function loadSxsqsjPageByType(sxType)
{
	curSXType = sxType;
	$('#sxsqsjDiv').load("./sxsqsj/sxsqsjList.html", function () {
        
    });
}




