

$(document).ready(function (){
	
	$('#btnAdd').click(ShowAddModal);
	
	$('.dpYears').datepicker({
		autoclose: true
	});
	
	//$('#btnReset').click(Reset);
	
	$('#btnSearch').click(load);
	
	//load();
	
	
	$.ajax({
	  type: 'POST',
	  url: getContextPath()+"/dictionaryController/getDataOfDic",
	  data: JSON.stringify({id:'ofcommunity'}),
	  contentType: "application/json",
	  success:function(result){
				
			if(result.success)
			{
				$('#ofcommunity').html('');
				var filterArr = [];
				
				filterArr[0] = "<option value=''></option>";				
				
				for(var i=0;i<result.value.length;i++)
				{
					var filter = result.value[i];
					
					filterArr[i+1] = "<option value='" + filter.key + "'>" + filter.value + "</option>";						
				}
				$('#ofcommunity').html(filterArr.join(''));
				
				$('#ofresidebuilding').html('');				
				$('#ofunit').html('');
				$('#ofroom').html('');
				$('#offamily').html('');
			}
			else
			{
				jError("获取社区列表失败!",{
					VerticalPosition : 'center',
					HorizontalPosition : 'center'
				});
			}
		},
	  dataType: "json"
	});
	
	$('#ofcommunity').change(function(){
		
		var ofcommunity = $('#ofcommunity').val();
		
		$.ajax({
		  type: 'POST',
		  url: getContextPath()+"/dictionaryController/getDataOfDic",
		  data: JSON.stringify({id:'ofresidebuilding',params:[{enname:'ofcommunity',value:ofcommunity}]}),
		  contentType: "application/json",
		  success:function(result){
					
				if(result.success)
				{
					$('#ofresidebuilding').html('');
					var filterArr = [];
					filterArr[0] = "<option value=''></option>";				
					for(var i=0;i<result.value.length;i++)
					{
						var filter = result.value[i];
						
						filterArr[i+1] = "<option value='" + filter.key + "'>" + filter.value + "</option>";						
					}
					$('#ofresidebuilding').html(filterArr.join(''));
					
					$('#ofunit').html('');
					$('#ofroom').html('');
					$('#offamily').html('');
				}
				else
				{
					jError("获取居民楼列表失败!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'
					});
				}
			},
		  dataType: "json"
		});
	});
	
	$('#ofresidebuilding').change(function(){
		
		var ofresidebuilding = $('#ofresidebuilding').val();
		var ofcommunity = $('#ofcommunity').val();
		
		$.ajax({
		  type: 'POST',
		  url: getContextPath()+"/dictionaryController/getDataOfDic",
		  data: JSON.stringify({id:'ofunit',params:[{enname:'ofcommunity',value:ofcommunity},{enname:'ofresidebuilding',value:ofresidebuilding}]}),
		  contentType: "application/json",
		  success:function(result){
					
				if(result.success)
				{
					$('#ofunit').html('');
					var filterArr = [];
					filterArr[0] = "<option value=''></option>";							
					for(var i=0;i<result.value.length;i++)
					{
						var filter = result.value[i];
						
						filterArr[i+1] = "<option value='" + filter.key + "'>" + filter.value + "</option>";						
					}
					$('#ofunit').html(filterArr.join(''));
					
					$('#ofroom').html('');
					$('#offamily').html('');
				}
				else
				{
					jError("获取单元列表失败!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'
					});
				}
			},
		  dataType: "json"
		});
	});
	
	$('#ofunit').change(function(){
		
		var ofresidebuilding = $('#ofresidebuilding').val();
		var ofcommunity = $('#ofcommunity').val();
		var ofunit = $('#ofunit').val();
		
		$.ajax({
		  type: 'POST',
		  url: getContextPath()+"/dictionaryController/getDataOfDic",
		  data: JSON.stringify({id:'ofroom',params:[{enname:'ofcommunity',value:ofcommunity},{enname:'ofresidebuilding',value:ofresidebuilding},{enname:'ofunit',value:ofunit}]}),
		  contentType: "application/json",
		  success:function(result){
					
				if(result.success)
				{
					$('#ofroom').html('');
					var filterArr = [];
					filterArr[0] = "<option value=''></option>";							
					for(var i=0;i<result.value.length;i++)
					{
						var filter = result.value[i];
						
						filterArr[i+1] = "<option value='" + filter.key + "'>" + filter.value + "</option>";						
					}
					$('#ofroom').html(filterArr.join(''));
					$('#offamily').html('');
				}
				else
				{
					jError("获取房间列表失败!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'
					});
				}
			},
		  dataType: "json"
		});
	});
	
	$('#ofroom').change(function(){
		
		var ofresidebuilding = $('#ofresidebuilding').val();
		var ofcommunity = $('#ofcommunity').val();
		var ofunit = $('#ofunit').val();
		var ofroom = $('#ofroom').val();
		
		$.ajax({
		  type: 'POST',
		  url: getContextPath()+"/dictionaryController/getDataOfDic",
		  data: JSON.stringify({id:'offamily',params:[{enname:'ofcommunity',value:ofcommunity},{enname:'ofresidebuilding',value:ofresidebuilding},{enname:'ofunit',value:ofunit},{enname:'ofroom',value:ofroom}]}),
		  contentType: "application/json",
		  success:function(result){
					
				if(result.success)
				{
					$('#offamily').html('');
					var filterArr = [];
					filterArr[0] = "<option value=''></option>";							
					for(var i=0;i<result.value.length;i++)
					{
						var filter = result.value[i];
						
						filterArr[i+1] = "<option value='" + filter.key + "'>" + filter.value + "</option>";						
					}
					$('#offamily').html(filterArr.join(''));
				}
				else
				{
					jError("获取房间列表失败!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'
					});
				}
			},
		  dataType: "json"
		});
	});
	
	
	if(curId != '')
		viewDetail(curId);
});


function viewDetail(id)
{
	$.get(getContextPath()+"/residentController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#dataid').val(obj.dataid);
				$('#name').val(obj.name);
				$('#identitytype').val(obj.identitytype);
				$('#idnumber').val(obj.idnumber);
				if(obj.characteristics != null){
					var characteristicsArr = obj.characteristics.split(VALUE_SPLITTER);
					for(var j=0;j<characteristicsArr.length;j++)
					{
						if(characteristicsArr[j] != '')
						{
							$("input[name='characteristics'][value='"+characteristicsArr[j]+"']").attr('checked','true');
						}
					}	
				}	
				/*$('#ofcommunity').val(obj.ofcommunity);
				$('#ofresidebuilding').val(obj.ofresidebuilding);
				$('#ofunit').val(obj.ofunit);
				$('#ofroom').val(obj.ofroom);
				$('#offamily').val(obj.offamily);*/
				
				$('#ofcommunity').html('');
				var filterArr = [];				
				filterArr[0] = "<option value='"+obj.ofcommunity+"'>"+obj.ofcommunity+"</option>";				
				$('#ofcommunity').html(filterArr.join(''));
				$('#ofcommunity').attr("readonly","readonly");
				
				$('#ofresidebuilding').html('');
				var filterArr = [];				
				filterArr[0] = "<option value='"+obj.ofresidebuilding+"'>"+obj.ofresidebuildingname+"</option>";				
				$('#ofresidebuilding').html(filterArr.join(''));
				$('#ofresidebuilding').attr("readonly","readonly");
				
				$('#ofunit').html('');
				var filterArr = [];				
				filterArr[0] = "<option value='"+obj.ofunit+"'>"+obj.ofunit+"</option>";				
				$('#ofunit').html(filterArr.join(''));
				$('#ofunit').attr("readonly","readonly");	
				
				$('#ofroom').html('');
				var filterArr = [];				
				filterArr[0] = "<option value='"+obj.ofroom+"'>"+obj.ofroomname+"</option>";				
				$('#ofroom').html(filterArr.join(''));
				$('#ofroom').attr("readonly","readonly");	
				
				$('#offamily').html('');
				var filterArr = [];				
				filterArr[0] = "<option value='"+obj.offamily+"'>"+obj.offamilyname+"</option>";				
				$('#offamily').html(filterArr.join(''));
				$('#offamily').attr("readonly","readonly");	
				
				$('#sex').val(obj.sex);
				$('#residencestatus').val(obj.residencestatus);
				$('#ishouseholder').val(obj.ishouseholder);
				$('#relationshiphouseholder').val(obj.relationshiphouseholder);
				$('#registrationcategory').val(obj.registrationcategory);
				$('#nationality').val(obj.nationality);
				$('#homeplace').val(obj.homeplace);
				$('#birthday').val(obj.birthday);
				$('#age').val(obj.age);
				$('#nation').val(obj.nation);
				$('#politicalstatus').val(obj.politicalstatus);
				$('#usedname').val(obj.usedname);
				$('#residenceaddress').val(obj.residenceaddress);
				$('#residentialaddress').val(obj.residentialaddress);
				$('#education').val(obj.education);
				$('#professionstatus').val(obj.professionstatus);
				$('#professiontype').val(obj.professiontype);
				$('#workunit').val(obj.workunit);
				$('#job').val(obj.job);
				$('#issocialsecurity').val(obj.issocialsecurity);
				$('#email').val(obj.email);
				$('#tel').val(obj.tel);
				$('#fax').val(obj.fax);
				$('#mobile').val(obj.mobile);
				$('#eligiousbelief').val(obj.eligiousbelief);
				$('#marriage').val(obj.marriage);
				$('#isforeignmarriage').val(obj.isforeignmarriage);
				$('#health').val(obj.health);
				$('#military').val(obj.military);
				$('#stature').val(obj.stature);
				$('#blood').val(obj.blood);
				$('#hobby').val(obj.hobby);
				$('#reasonnotresidence').val(obj.reasonnotresidence);
				$('#custodian').val(obj.custodian);
				$('#custodiantel').val(obj.custodiantel);
				$('#custodianrelationship').val(obj.custodianrelationship);
				$('#custodianincommunity').val(obj.custodianincommunity);
				var picturesArr = obj.pictures.split(VALUE_SPLITTER);				for(var j=0;j<picturesArr.length;j++)				{					if(picturesArr[j] != '')					{						$('#picturespicktable').append('<tr><td>'+picturesArr[j]+'</td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+picturesArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					}				}				$('#note').val(obj.note);
				$('#dy_partymembertype').val(obj.dy_partymembertype);
				$('#dy_of_partyorganization').val(obj.dy_of_partyorganization);
				$('#dy_joinpartydate').val(obj.dy_joinpartydate);
				$('#dy_inpartydate').val(obj.dy_inpartydate);
				$('#dy_membership').val(obj.dy_membership);
				$('#dy_islost').val(obj.dy_islost);
				$('#dy_lostdate').val(obj.dy_lostdate);
				$('#dy_movemember').val(obj.dy_movemember);
				$('#dy_moveto').val(obj.dy_moveto);
				$('#zdr_type').val(obj.zdr_type);
				$('#zdr_reason').val(obj.zdr_reason);
				$('#zdr_custodian').val(obj.zdr_custodian);
				$('#zdr_custodiantel').val(obj.zdr_custodiantel);
				$('#jzr_correctioncontent').val(obj.jzr_correctioncontent);
				$('#jzr_correctiondate').val(obj.jzr_correctiondate);
				$('#jzr_correctionaddress').val(obj.jzr_correctionaddress);
				$('#sy_unemployedreason').val(obj.sy_unemployedreason);
				$('#sy_unemployedreemployment').val(obj.sy_unemployedreemployment);
				$('#sy_reemploymentunit').val(obj.sy_reemploymentunit);
				$('#ylfn_firstmarriagedate').val(obj.ylfn_firstmarriagedate);
				$('#ylfn_children').val(obj.ylfn_children);
				$('#ylfn_bornchildren').val(obj.ylfn_bornchildren);
				$('#ylfn_pregnancy').val(obj.ylfn_pregnancy);
				$('#ylfn_lastmenstruation').val(obj.ylfn_lastmenstruation);
				$('#ylfn_terminationpregnancy').val(obj.ylfn_terminationpregnancy);
				$('#lnr_oldid').val(obj.lnr_oldid);
				$('#lnr_economicsources').val(obj.lnr_economicsources);
				$('#lnr_livingconditions').val(obj.lnr_livingconditions);
				$('#lnr_selfcare').val(obj.lnr_selfcare);
				$('#lnr_specialsubsidies').val(obj.lnr_specialsubsidies);
				$('#lnr_emergencycontact').val(obj.lnr_emergencycontact);
				$('#lnr_emergencycontactrelated').val(obj.lnr_emergencycontactrelated);
				$('#lnr_emergencycontacttel').val(obj.lnr_emergencycontacttel);
				$('#lnr_physicalcondition').val(obj.lnr_physicalcondition);
				$('#lnr_medicationcondition').val(obj.lnr_medicationcondition);
				$('#cj_disabilitytype').val(obj.cj_disabilitytype);
				$('#cj_disabilitylevel').val(obj.cj_disabilitylevel);
				$('#cj_disabilityreason').val(obj.cj_disabilityreason);
				$('#cj_disabilityemployment').val(obj.cj_disabilityemployment);
				$('#jsb_type').val(obj.jsb_type);
				$('#jsb_medicalhistory').val(obj.jsb_medicalhistory);
				$('#jhr_name').val(obj.jhr_name);
				$('#hjr_tel').val(obj.hjr_tel);
				$('#zyz_certificate_id').val(obj.zyz_certificate_id);
				$('#zyz_special_skill').val(obj.zyz_special_skill);
				$('#jmdb_representative_level').val(obj.jmdb_representative_level);
				$('#jmdb_startofterm').val(obj.jmdb_startofterm);
				$('#jmdb_endofterm').val(obj.jmdb_endofterm);
				$('#wtgg_skill_type').val(obj.wtgg_skill_type);
				$('#wtgg_special_skill').val(obj.wtgg_special_skill);

			}
		});
}

function gobackPage()
{
	
	curId = '';
	
	$('#main-content').load("./jcsqsj/resident/resident.html", function () {
		
    });
	
}
/*
function ShowAddModal()
{
	$('#modalDetail').show();
	
	$('#modalTitle').text('新增');
	
	$('#addOrUpdateBtn').text('确定');
	
}
*/
function addOrUpdate()
{
	var valcharacteristicsArr=new Array();
$('input[name="characteristics"]:checked').each(function()
{      
	valcharacteristicsArr.push($(this).val());//向数组中添加元素  		
});
var valcharacteristics = valcharacteristicsArr.join(VALUE_SPLITTER);//将数组元素连接起来以构建一个字符串

	
	$.post(getContextPath()+"/residentController/addOrUpdate",
	{
		id:curId,
				dataid:$('#dataid').val(),
		name:$('#name').val(),
		identitytype:$('#identitytype').val(),
		idnumber:$('#idnumber').val(),
		characteristics:valcharacteristics,
		ofcommunity:$('#ofcommunity').val(),
		ofresidebuilding:$('#ofresidebuilding').val(),
		ofunit:$('#ofunit').val(),
		ofroom:$('#ofroom').val(),
		offamily:$('#offamily').val(),
		sex:$('#sex').val(),
		residencestatus:$('#residencestatus').val(),
		ishouseholder:$('#ishouseholder').val(),
		relationshiphouseholder:$('#relationshiphouseholder').val(),
		registrationcategory:$('#registrationcategory').val(),
		nationality:$('#nationality').val(),
		homeplace:$('#homeplace').val(),
		birthday:$('#birthday').val(),
		age:$('#age').val(),
		nation:$('#nation').val(),
		politicalstatus:$('#politicalstatus').val(),
		usedname:$('#usedname').val(),
		residenceaddress:$('#residenceaddress').val(),
		residentialaddress:$('#residentialaddress').val(),
		education:$('#education').val(),
		professionstatus:$('#professionstatus').val(),
		professiontype:$('#professiontype').val(),
		workunit:$('#workunit').val(),
		job:$('#job').val(),
		issocialsecurity:$('#issocialsecurity').val(),
		email:$('#email').val(),
		tel:$('#tel').val(),
		fax:$('#fax').val(),
		mobile:$('#mobile').val(),
		eligiousbelief:$('#eligiousbelief').val(),
		marriage:$('#marriage').val(),
		isforeignmarriage:$('#isforeignmarriage').val(),
		health:$('#health').val(),
		military:$('#military').val(),
		stature:$('#stature').val(),
		blood:$('#blood').val(),
		hobby:$('#hobby').val(),
		reasonnotresidence:$('#reasonnotresidence').val(),
		custodian:$('#custodian').val(),
		custodiantel:$('#custodiantel').val(),
		custodianrelationship:$('#custodianrelationship').val(),
		custodianincommunity:$('#custodianincommunity').val(),
		pictures:$('#pictures').val(),
		note:$('#note').val(),
		dy_partymembertype:$('#dy_partymembertype').val(),
		dy_of_partyorganization:$('#dy_of_partyorganization').val(),
		dy_joinpartydate:$('#dy_joinpartydate').val(),
		dy_inpartydate:$('#dy_inpartydate').val(),
		dy_membership:$('#dy_membership').val(),
		dy_islost:$('#dy_islost').val(),
		dy_lostdate:$('#dy_lostdate').val(),
		dy_movemember:$('#dy_movemember').val(),
		dy_moveto:$('#dy_moveto').val(),
		zdr_type:$('#zdr_type').val(),
		zdr_reason:$('#zdr_reason').val(),
		zdr_custodian:$('#zdr_custodian').val(),
		zdr_custodiantel:$('#zdr_custodiantel').val(),
		jzr_correctioncontent:$('#jzr_correctioncontent').val(),
		jzr_correctiondate:$('#jzr_correctiondate').val(),
		jzr_correctionaddress:$('#jzr_correctionaddress').val(),
		sy_unemployedreason:$('#sy_unemployedreason').val(),
		sy_unemployedreemployment:$('#sy_unemployedreemployment').val(),
		sy_reemploymentunit:$('#sy_reemploymentunit').val(),
		ylfn_firstmarriagedate:$('#ylfn_firstmarriagedate').val(),
		ylfn_children:$('#ylfn_children').val(),
		ylfn_bornchildren:$('#ylfn_bornchildren').val(),
		ylfn_pregnancy:$('#ylfn_pregnancy').val(),
		ylfn_lastmenstruation:$('#ylfn_lastmenstruation').val(),
		ylfn_terminationpregnancy:$('#ylfn_terminationpregnancy').val(),
		lnr_oldid:$('#lnr_oldid').val(),
		lnr_economicsources:$('#lnr_economicsources').val(),
		lnr_livingconditions:$('#lnr_livingconditions').val(),
		lnr_selfcare:$('#lnr_selfcare').val(),
		lnr_specialsubsidies:$('#lnr_specialsubsidies').val(),
		lnr_emergencycontact:$('#lnr_emergencycontact').val(),
		lnr_emergencycontactrelated:$('#lnr_emergencycontactrelated').val(),
		lnr_emergencycontacttel:$('#lnr_emergencycontacttel').val(),
		lnr_physicalcondition:$('#lnr_physicalcondition').val(),
		lnr_medicationcondition:$('#lnr_medicationcondition').val(),
		cj_disabilitytype:$('#cj_disabilitytype').val(),
		cj_disabilitylevel:$('#cj_disabilitylevel').val(),
		cj_disabilityreason:$('#cj_disabilityreason').val(),
		cj_disabilityemployment:$('#cj_disabilityemployment').val(),
		jsb_type:$('#jsb_type').val(),
		jsb_medicalhistory:$('#jsb_medicalhistory').val(),
		jhr_name:$('#jhr_name').val(),
		hjr_tel:$('#hjr_tel').val(),
		zyz_certificate_id:$('#zyz_certificate_id').val(),
		zyz_special_skill:$('#zyz_special_skill').val(),
		jmdb_representative_level:$('#jmdb_representative_level').val(),
		jmdb_startofterm:$('#jmdb_startofterm').val(),
		jmdb_endofterm:$('#jmdb_endofterm').val(),
		wtgg_skill_type:$('#wtgg_skill_type').val(),
		wtgg_special_skill:$('#wtgg_special_skill').val()
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