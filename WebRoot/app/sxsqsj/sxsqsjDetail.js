
//获取地址栏参数，name:参数名称
 function getUrlParms(name){
   var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
   var r = window.location.search.substr(1).match(reg);
   if(r!=null)
   return unescape(r[2]);
   return null;
   }

$(document).ready(function (){
	
	var checkin = $('#begindate').datepicker({
			format: 'yyyy-mm-dd',
			todayBtn: 'linked',
			onRender: function(date) {
				console.log('onRender startDate');
				//return date.valueOf() < now.valueOf() ? 'disabled' : '';
			}
		}).on('changeDate', function(ev) {
				/*if (ev.date.valueOf() > checkout.date.valueOf()) {
					var newDate = new Date(ev.date)
					newDate.setDate(newDate.getDate() + 1);
					checkout.setValue(newDate);
				}*/
				checkin.hide();
				//$('.dpd2')[0].focus();
				
				console.log('time Change');
			}).data('datepicker');
		
	var checkin = $('#enddate').datepicker({
			format: 'yyyy-mm-dd',
			todayBtn: 'linked',
			onRender: function(date) {
				console.log('onRender startDate');
				//return date.valueOf() < now.valueOf() ? 'disabled' : '';
			}
		}).on('changeDate', function(ev) {
				/*if (ev.date.valueOf() > checkout.date.valueOf()) {
					var newDate = new Date(ev.date)
					newDate.setDate(newDate.getDate() + 1);
					checkout.setValue(newDate);
				}*/
				checkin.hide();
				//$('.dpd2')[0].focus();
				
				console.log('time Change');
			}).data('datepicker');	
	
	//$('#sxdl').change(function(){
		
		$('#sxdl').val(curSXType);
		
		var sxxlArr = [];
				
		if(curSXType == '社区党建事项')
		{
			sxxlArr[0] = "<option value='党建活动'>党建活动</option>";
			sxxlArr[1] = "<option value='党员志愿活动'>党员志愿活动</option>";
		}
		else if(curSXType == '民主自治事项')
		{
			sxxlArr[0] = "<option value='社区代表会议'>社区代表会议</option>";
			sxxlArr[1] = "<option value='居委会换届'>居委会换届</option>";
		}
		else if(curSXType == '社区服务事项')
		{
			sxxlArr[0] = "<option value='就业指导'>就业指导</option>";
			sxxlArr[1] = "<option value='低保救助'>低保救助</option>";
			sxxlArr[2] = "<option value='优抚安置'>优抚安置</option>";
			sxxlArr[3] = "<option value='志愿者服务'>志愿者服务</option>";
			sxxlArr[4] = "<option value='精神文明建设'>精神文明建设</option>";
			sxxlArr[5] = "<option value='信访信息'>信访信息</option>";
		}
		else if(curSXType == '平安建设事项')
		{
			sxxlArr[0] = "<option value='民事纠纷调解'>民事纠纷调解</option>";
			sxxlArr[1] = "<option value='出租房屋管理'>出租房屋管理</option>";
			sxxlArr[2] = "<option value='养犬养宠管理'>养犬养宠管理</option>";
			sxxlArr[3] = "<option value='特殊人群关注'>特殊人群关注</option>";
			sxxlArr[4] = "<option value='消防安全事件'>消防安全事件</option>";
		}
		else if(curSXType == '文化教育事项')
		{
			sxxlArr[0] = "<option value='文化文艺活动'>文化文艺活动</option>";
			sxxlArr[1] = "<option value='文化团体活动'>文化团体活动</option>";
			sxxlArr[2] = "<option value='全民健身活动'>全民健身活动</option>";
			sxxlArr[3] = "<option value='入园入学登记'>入园入学登记</option>";
		}
		else if(curSXType == '社区环境事项')
		{
			sxxlArr[0] = "<option value='社区环境建设'>社区环境建设</option>";
			sxxlArr[1] = "<option value='垃圾分类管理'>垃圾分类管理</option>";
			sxxlArr[2] = "<option value='除害用药事件'>除害用药事件</option>";
		}		
		else if(curSXType == '卫生健康事项')
		{
			sxxlArr[0] = "<option value='居民健康宣传'>居民健康宣传</option>";
			sxxlArr[1] = "<option value='育龄妇女关怀'>育龄妇女关怀</option>";
			sxxlArr[2] = "<option value='重病人员关怀'>重病人员关怀</option>";
			sxxlArr[3] = "<option value='两癌人员关怀'>两癌人员关怀</option>";
		}	
		$('#sxxl').html(sxxlArr.join(''));
	//});
	
});

function get()
{
	$.get(getContextPath()+"/sxsqsjController/get?id="+curId,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#sxbm').val(obj.sxbm);
				$('#sxmc').val(obj.sxmc);
				$('#sxdl').val(obj.sxdl);
				$('#sxxl').val(obj.sxxl);
				$('#sxxq').val(obj.sxxq);
				$('#sxdd').val(obj.sxdd);
				$('#sxdsr').val(obj.sxdsr);
				$('#sxkssj').val(obj.sxkssj);
				$('#sxjssj').val(obj.sxjssj);
				$('#sxzj').val(obj.sxzj);
			}
		});
}

function addOrUpdate()
{
	$.post(getContextPath()+"/sxsqsjController/addOrUpdate",
	{
		id:curId,
		//sxbm:$('#sxbm').val(),
		sxmc:$('#sxmc').val(),
		sxdl:$('#sxdl').val(),
		sxxl:$('#sxxl').val(),
		sxxq:$('#sxxq').val(),
		sxdd:$('#sxdd').val(),
		sxdsr:$('#sxdsr').val(),
		sxkssj:$('#sxkssj').val(),
		sxjssj:$('#sxjssj').val(),
		sxzj:$('#sxzj').val()
	},
	function(result){
		var obj = jQuery.parseJSON(result);  
		if(obj.success)
		{
			jSuccess("事项创建成功!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'});
						
			gobackPage();
		}
		else
		{
			jError("事项创建失败!"+obj.errMsg,{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'});
			return ;
		}
	});
	
	/*
	var attributeTbl = $('#editable-sample').dataTable().fnGetData();
	if(attributeTbl.length == 0)
	{
		jError("至少应包含一个字段!",{
					VerticalPosition : 'center',
					HorizontalPosition : 'center'});
		return ;
	}
	
	var attributeArr = new Array();
	
	for(var i=0;i<attributeTbl.length;i++)
	{
		var id = attributeTbl[i].id;
		var attrZHName = attributeTbl[i].attrZHName;
		var attrENName = attributeTbl[i].attrENName;
		var attrComponentType = attributeTbl[i].attrComponentType;
		var attrDBType = attributeTbl[i].attrDBType;
		var attrValue = attributeTbl[i].attrValue;
		var attrDBLength = attributeTbl[i].attrDBLength;
		var seq = attributeTbl[i].seq;
		//var appDisplay = attributeTbl[i].appDisplay;
		var supportQuery = attributeTbl[i].supportQuery;
		var saved = attributeTbl[i].saved;
		
		if(attrZHName == undefined)
		{
			jError("有未保存的字段!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'});
			return ;
		}
		
		if(id === undefined) id = '';
		if(attrZHName === undefined) attrZHName = '';
		if(attrENName === undefined) attrENName = '';
		if(attrComponentType === undefined) attrComponentType = '';
		if(attrDBType === undefined) attrDBType = '';
		if(attrValue === undefined) attrValue = '';
		if(attrDBLength === undefined || attrDBLength == '') attrDBLength = 16;//对于字符串类型的字段,默认长度为16
		if(seq === undefined || seq == '') seq = i;
		
		attributeArr.push("{'id':'"+id+"','attrZHName':'"+attrZHName+"','attrENName':'"+attrENName+"','attrComponentType':'"+attrComponentType+"','attrDBType':'"+attrDBType+"','attrValue':'"+attrValue+"','attrDBLength':"+attrDBLength+",'seq':"+seq+",'supportQuery':"+supportQuery+"}");
	}
	
	var layerInfo = {'tableId':curTableId,'tableZHName':$('#tableZHName').val(),'tableType':'',
						'tableENName':$('#tableENName').val(),
						'tableDescription':$('#tableDescription').val(),
						'seq':$('#seq').val(),
						'tableAttribute':attributeArr};
						
	var attrJson = attributeArr.join(',');
	
	$.post(getContextPath()+"/tableController/addOrUpdateTable",
	{
		tableId:curTableId,
		tableZHName:$('#tableZHName').val(),
		tableType:'',
		tableENName:$('#tableENName').val(),
		tableDescription:$('#tableDescription').val(),
		seq:$('#seq').val(),
		tableAttribute:attrJson
	},
	function(result){
		var obj = jQuery.parseJSON(result);  
		if(obj.success)
		{
			jSuccess("表单创建成功!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'});
						
			gobackTablePage();
		}
		else
		{
			jError("表单创建失败!"+data.errMsg,{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'});
			return ;
		}
	});
	
	$.ajaxFileUpload
	(  
		{
			url:getContextPath()+'/tableController/addOrUpdateTable/',
			fileElementId: [], //文件上传控件的id属性  <input type="file" id="file" name="file" /> 注意，这里一定要有name值     
			dataType: 'json',//返回值类型 一般设置为json  
			data:layerInfo,
			success: function (data, status)  //服务器成功响应处理函数  
			{
				if(data.success == false)
				{
					jError("表单创建失败!"+data.errMsg,{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'});
					return ;
				}
				else
				{
					jSuccess("表单创建成功!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'});
						
					gobackTablePage();
				}
			},  
			error: function (data, status, e)//服务器响应失败处理函数  
			{  
				jError("表单创建失败!",{
					VerticalPosition : 'center',
					HorizontalPosition : 'center'});
			}  
		}  
	)  */
}

function gobackPage()
{
	$('#main-content').load("./sxsqsj/sxsqsj.html", function () {
		
	});
}
