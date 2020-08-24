

$(document).ready(function (){
	
	$('#btnAdd').click(ShowAddModal);
	
	$('.dpYears').datepicker({
		autoclose: true
	});
	
	//$('#btnReset').click(Reset);
	
	$('#btnSearch').click(load);
	
	//load();
	
	/*$('input[type=radio][name=cwtype]').change(function() {
        if (this.value == '一般停车位') {
            alert("Allot Thai Gayo Bhai");
        }
        else if (this.value == '道路停车位') {
            alert("Transfer Thai Gayo");
        }
    });*/
	
	$.ajax({
	  type: 'POST',
	  url: getContextPath()+"/dictionaryController/getDataOfDic",
	  data: JSON.stringify({id:'inparkname'}),
	  contentType: "application/json",
	  success:function(result){
				
			if(result.success)
			{
				$('#inparkname').html('');
				var filterArr = [];
				
				filterArr[0] = "<option value=''></option>";				
				
				for(var i=0;i<result.value.length;i++)
				{
					var filter = result.value[i];
					
					filterArr[i+1] = "<option value='" + filter.key + "'>" + filter.value + "</option>";						
				}
				$('#inparkname').html(filterArr.join(''));
				
				if(curId != '')
					viewDetail(curId);
			}
			else
			{
				jError("获取停车场列表失败!",{
					VerticalPosition : 'center',
					HorizontalPosition : 'center'
				});
			}
		},
	  dataType: "json"
	});
	
});


function viewDetail(id)
{
	$.get(getContextPath()+"/jc_tc_tcwController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				$('#inparkname').val(obj.inparkname);
				//$('#cwtype').val(obj.cwtype);
				$("input[name='cwtype'][value='"+obj.cwtype+"']").attr("checked",true); 
				$('#location').val(obj.location);
				$('#numbers').val(obj.numbers);
				//$('#UseType').val(obj.UseType);
				$("input[name='UseType'][value='"+obj.UseType+"']").attr("checked",true); 
				//$('#sizeType').val(obj.sizeType);
				$("input[name='sizeType'][value='"+obj.sizeType+"']").attr("checked",true); 
				//$('#heightType').val(obj.heightType);
				$("input[name='heightType'][value='"+obj.heightType+"']").attr("checked",true); 
				$('#arrange').val(obj.arrange);
				//$('#hascharge').val(obj.hascharge);
				$("input[name='hascharge'][value='"+obj.hascharge+"']").attr("checked",true); 
				$('#chargenum').val(obj.chargenum);
				$('#cwcode').val(obj.cwcode);
				
				
				$('#pciture').val(obj.pciture);
				var pcitureArr = obj.pciture.split(VALUE_SPLITTER);  
				var header = getContextPath()+"/fileController/download?fileName=";	

				for(var j=0;j<pcitureArr.length;j++)				
				{if(pcitureArr[j] != '')	
				{	
					var url = header + pcitureArr[j];
					if(url.indexOf('.pdf') >= 0 || url.indexOf('.PDF') >= 0){
						var uurl = getContextPath() + "/dist/js/pdf.html?param=" + url;
						$('#pciturepicktable').append('<tr><td><a href="' + uurl + '" target="_blank")>' + pcitureArr[j] + '</a></td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+pcitureArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');											
					}						
					else 
					{							
						$('#pciturepicktable').append('<tr><td><a href="' + url + '" data-lightbox="' + pcitureArr[j] + '" data-title="' + pcitureArr[j] + '" style="color:#64A600; font-size: 12px;">'+pcitureArr[j]+'</a></td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+pcitureArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');											
					}					
				}				
			}	
				
				
				('#note').val(obj.note);

					
			}
		});
}

function gobackPage()
{
	curId = '';
	
	$('#main-content').load("./jcsqsj/jcsqsj.html", function () {
		
    });
	
}

function addOrUpdate()
{
	$.post(getContextPath()+"/jc_tc_tcwController/addOrUpdate",
	{
		id:curId,
		inparkname:$('#inparkname').val(),
		cwtype:$('input:radio[name="cwtype"]:checked').val(),//$('#cwtype').val(),
		location:$('#location').val(),
		numbers:$('#numbers').val(),
		UseType:$('input:radio[name="UseType"]:checked').val(),//$('#UseType').val(),
		sizeType:$('input:radio[name="sizeType"]:checked').val(),//$('#sizeType').val(),
		heightType:$('input:radio[name="heightType"]:checked').val(),//$('#heightType').val(),
		arrange:$('#arrange').val(),
		hascharge:$('input:radio[name="hascharge"]:checked').val(),//$('#hascharge').val(),
		chargenum:$('#chargenum').val(),
		cwcode:$('#cwcode').val(),
		pciture:$('#pciture').val(),
		note:$('#note').val()
	},
	function(result){
		var obj = jQuery.parseJSON(result);  
		if(obj.success)
		{
			jSuccess("数据修改成功!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
			
			gobackPage();
			
			//load();
		}
		else
		{
			jError("数据修改失败!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
		}
	});
}


function downloadAttach(fileName)
{
	var url = getContextPath()+"/fileController/download?fileName="+fileName;
	
	window.open(encodeURI(url));
	
	//window.open(getContextPath()+"/fileController/downLoad/"+encodeURI(obj.fileName));
}
