
//获取地址栏参数，name:参数名称
 function getUrlParms(name){
   var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
   var r = window.location.search.substr(1).match(reg);
   if(r!=null)
   return unescape(r[2]);
   return null;
   }

$(document).ready(function (){
	
	$("#sxkssj").datetimepicker({
		format: 'yyyy-mm-dd hh:ii',
		autoclose: true,
		todayBtn: true,
		pickerPosition: "bottom-left"

	});

	$("#sxjssj").datetimepicker({
		format: 'yyyy-mm-dd hh:ii',
		autoclose: true,
		todayBtn: true,
		pickerPosition: "bottom-left"

	});

	
		
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
				$('#sxdsrname').val(obj.sxdsrname);
				$('#sxjbr').val(obj.sxjbr);
				$('#sxjbrname').val(obj.sxjbrname);
				$('#sxdsrname').attr('readonly','readonly');
				$('#sxkssj').val(obj.sxkssj);
				$('#sxjssj').val(obj.sxjssj);
				$('#sxzj').val(obj.sxzj);
				
				$('#selectResidentDiv').hide();
				
				if(obj.fj != null)
				{
					var picturesArr = obj.fj.split(VALUE_SPLITTER);				
					for(var j=0;picturesArr != null && j<picturesArr.length;j++)				
					{					
						if(picturesArr[j] != '')					
						{						
							$('#picturespicktable').append('<tr><td>'+picturesArr[j]+'</td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+picturesArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					
						}
					}
				}
			}
		});
}

function addOrUpdate()
{
	var sxdsr = $('#sxdsr').val();
	if(sxdsr == null || sxdsr == undefined || sxdsr == '')
		sxdsr = $('#sxdsrname').val();
	
	var sxjbr = $('#sxjbr').val();
	if(sxjbr == null || sxjbr == undefined || sxjbr == '')
		sxjbr = $('#sxjbrname').val();
	
	$.post(getContextPath()+"/sxsqsjController/addOrUpdate",
	{
		id:curId,		
		sxmc:$('#sxmc').val(),
		sxdl:$('#sxdl').val(),
		sxxl:$('#sxxl').val(),
		sxxq:$('#sxxq').val(),
		sxdd:$('#sxdd').val(),
		sxdsr:sxdsr,
		sxjbr:sxjbr,
		sxkssj:$('#sxkssj').val(),
		sxjssj:$('#sxjssj').val(),
		sxzj:$('#sxzj').val(),
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
			jError("事项创建失败!"+obj.errMsg,{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'});
			return ;
		}
	});
}

function gobackPage()
{
	$('#main-content').load("./sxsqsj/sxsqsj.html", function () {
		
	});
}
