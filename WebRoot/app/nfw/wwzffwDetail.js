
//获取地址栏参数，name:参数名称
 function getUrlParms(name){
   var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
   var r = window.location.search.substr(1).match(reg);
   if(r!=null)
   return unescape(r[2]);
   return null;
   }

$(document).ready(function (){
	
	$(".select2").select2();
	
	if(curId != ''){
		get(curId);
	}
	
});

function get(id)
{
	$.get(getContextPath()+"/wwzffwController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#wwzfdx').val(obj.data.wwzfdx);
				
				$('#wwdxlx').val(obj.data.wwdxlx);
				
				$('#wwlx').val(obj.data.wwlx);
				
				$('#wwpwwjqk').val(obj.data.wwpwwjqk);
				
				$('#wwpwwjly').val(obj.data.wwpwwjly);
				
				
				
				$('#qtcywwry').val(obj.data.qtcywwry);
				
				$('#wwzfxq').val(obj.data.wwzfxq);
				
				$('#bz').val(obj.data.bz);
				
				if(obj.data.sqcywwry != ''){
					var sub = obj.data.sqcywwry.split(",")
					var arr = new Array();
					for(var i = 0; i < sub.length; i++){
						arr.push(sub[i]);
					}
					$('#sqcywwry').val(arr).trigger('change');
				}
				if(obj.data.wwfs != ''){
					var arr = obj.data.wwfs.split(",");
					$("input[type=checkbox]").val(arr);
				}
				
				var picturesArr = obj.data.fj.split(VALUE_SPLITTER);				
				for(var j=0;j<picturesArr.length;j++)				
				{					
					if(picturesArr[j] != '')					
					{						
						$('#picturespicktable').append('<tr><td>'+picturesArr[j]+'</td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+picturesArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					
					}
				}
			}
		});
}

function addOrUpdate()
{
	var wwfs = '';
	$.each($('input:checkbox:checked'),function(){
		if(wwfs == '') wwfs = $(this).val();
		else wwfs += "," + $(this).val();
	});
	var sqcywwry = '';
	var arr = $('#sqcywwry').val();
	if(arr.length > 0){
		sqcywwry = arr.join(',');
	}
	$.post(getContextPath()+"/wwzffwController/addOrUpdate",
	{
		id:curId,
		wwzfdx:$('#wwzfdx').val(),
		wwdxlx:$('#wwdxlx').val(),
		wwlx:$('#wwlx').val(),
		wwfs:wwfs,
		wwpwwjqk:$('#wwpwwjqk').val(),
		wwpwwjly:$('#wwpwwjly').val(),
		sqcywwry:sqcywwry,
		qtcywwry:$('#qtcywwry').val(),
		wwzfxq:$('#wwzfxq').val(),
		bz:$('#bz').val(),
		fj:$('#pictures').val()
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
			jError("事项创建失败!"+data.errMsg,{
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
	curId = '';
	$('#main-content').load("./nfw/wwzffw.html", function () {
		
	});
}
