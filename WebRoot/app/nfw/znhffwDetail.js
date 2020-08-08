
//获取地址栏参数，name:参数名称
 function getUrlParms(name){
   var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
   var r = window.location.search.substr(1).match(reg);
   if(r!=null)
   return unescape(r[2]);
   return null;
   }

$(document).ready(function (){
	
	$("#characterQry").select2({	 
		multiple: true
	});
	
	$("#characterQry").val('abc').trigger("change"); //赋值一个不存在的value,解决默认选择第一个的问题
	
	if(curId != ''){
		
		get();
	}
	
	var hfsj = $('#hfsj').datepicker({
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
				//checkin.hide();
				//$('.dpd2')[0].focus();
			
			}).data('datepicker');
	
});

function get()
{
	$.get(getContextPath()+"/znhffwController/get?id="+curId,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#dsr').val(obj.data.dsr);
				
				$('#dsrxm').val(obj.data.dsrxm);
				
				$('#dsrlxdh').val(obj.data.dsrlxdh);
				
				$('#hfsfcg').val(obj.data.hfsfcg);
				
				$('#sffkqk').val(obj.data.sffkqk);
				
				$('#dsrsfmy').val(obj.data.dsrsfmy);
				
				$('#bmqksm').val(obj.data.bmqksm);
				
				$('#bz').val(obj.data.bz);
				
				$('#hfr').val(obj.data.hfr);
				
				$('#hfsj').val(obj.data.hfsj);
				
				var picturesArr = obj.data.hfly.split(VALUE_SPLITTER);				
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
	$.post(getContextPath()+"/znhffwController/addOrUpdate",
	{
		id:curId,
		jsjbid:jsjbId,
		dsr:$('#dsr').val(),
		dsrxm:$('#dsrxm').val(),
		dsrlxdh:$('#dsrlxdh').val(),
		hfsfcg:$('#hfsfcg').val(),
		sffkqk:$('#sffkqk').val(),
		dsrsfmy:$('#dsrsfmy').val(),
		bmqksm:$('#bmqksm').val(),
		bz:$('#bz').val(),
		hfr:$('#hfr').val(),
		hfsj:$('#hfsj').val(),
		hfly:$('#pictures').val()
	},
	function(result){
		var obj = jQuery.parseJSON(result);  
		if(obj.success)
		{
			jSuccess("回访创建成功!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'});
						
			gobackPage();
		}
		else
		{
			jError("回访创建失败!",{
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
	$('#main-content').load("./nfw/znhffw.html", function () {
		
	});
}
