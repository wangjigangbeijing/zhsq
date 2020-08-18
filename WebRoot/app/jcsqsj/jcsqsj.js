
$(document).ready(function (){
	
	//$('#btnAdd').click(addsqzzqd);
	
	//小区信息
	$('#xqxxAnchor').click(function () {
		curJCSQSJType = '小区信息';		
		loadCommunity();
    });
	
	//房屋信息
	$('#fwxxAnchor').click(function () {		
		curJCSQSJType = '房屋信息';		
		loadRoom();        
    });
	
	//家庭信息
	$('#jtxxAnchor').click(function () {
		curJCSQSJType = '家庭信息';		
		loadFamily();  
    });
	
	//居民信息
	$('#jmxxAnchor').click(function () {
		curJCSQSJType = '居民信息';	
		loadResident();
    });
	
	//常驻人口
	$('#czrkAnchor').click(function () {
		alert('未实现');
        /*$('#jcsqsjDiv').load("./jcsqsj/community/community.html", function () {
        
		});*/
    });
	
	//流动人口
	$('#ldrkAnchor').click(function () {
        
		alert('未实现');
		/*$('#jcsqsjDiv').load("./jcsqsj/community/community.html", function () {
        
		});*/
    });
	
	//周边道路
	$('#zbdlAnchor').click(function () {
		curJCSQSJType = '周边道路';	
		loadRoad();
    });
	
	//党组织
	$('#dzzAnchor').click(function () {
		
		curJCSQSJType = '党组织';	
		loadPartyOrganization();
       
    });
	
	//市政设施
	$('#szbjAnchor').click(function () {
		
		curJCSQSJType = '市政设施';	
		loadPublicFacilities();
		
    });
	
	//住宅楼宇
	$('#zzlyAnchor').click(function () {
		
		curJCSQSJType = '住宅楼宇';	
		loadResidebuilding();
		
    });
	
	//商务楼宇
	$('#swlyAnchor').click(function () {
		
		curJCSQSJType = '商务楼宇';	
		loadBizbuilding();
		
    });
	
	//地下空间
	$('#dxkjAnchor').click(function () {
		
		curJCSQSJType = '地下空间';	
		loadUndergroundspace();
		
    });
	
	//车辆信息
	$('#clxxAnchor').click(function () {
		
		curJCSQSJType = '车辆信息';	
		loadVehicle();
		
    });
	
	//文体设施
	$('#wtssAnchor').click(function () {
		
		curJCSQSJType = '文体设施';	
		loadCultureFacilities();
		
    });
	
	//商业门店
	$('#symdAnchor').click(function () {
		
		alert('未实现');
		/*$('#jcsqsjDiv').load("./jcsqsj/community/community.html", function () {
        
		});*/
    });
	
	//停车资源
	$('#tczyAnchor').click(function () {
		
		curJCSQSJType = '停车资源';	
		loadParking();
		
    });
	
	//法人组织
	$('#frzzAnchor').click(function () {
		
		curJCSQSJType = '法人组织';	
		loadOrganization();
		
    });
	
	//志愿者队伍
	$('#zyzdwAnchor').click(function () {
		
		curJCSQSJType = '志愿者队伍';	
		loadVolunteerteam();
		
    });
	
	//居民团体
	$('#jmttAnchor').click(function () {
		
		curJCSQSJType = '居民团体';	
		loadPopulationgroup();
		
    });
	
	//服务网点
	$('#ffwdAnchor').click(function () {
		
		curJCSQSJType = '服务网点';	
		loadServicestore();
		
    });
	
	//垃圾站
	$('#ljzAnchor').click(function () {
		
		curJCSQSJType = '垃圾站';	
		loadRubbish();
		
    });
	
	//河湖水系
	$('#hhsxAnchor').click(function () {
		
		curJCSQSJType = '河湖水系';	
		loadWatersystem();
		
    });
	
	//避难场所
	$('#bncsAnchor').click(function () {
		
		curJCSQSJType = '避难场所';	
		loadShelter();
		
    });
	
	//党员信息
	$('#dyxxAnchor').click(function () {
		
		curJCSQSJType = '党员信息';	
		loaddyxx();
		
    });
	
	//宣传设施
	$('#xcssAnchor').click(function () {
		
		curJCSQSJType = '宣传设施';	
		loadxcss();
		
    });
	
	//公用市政设施
	$('#gyszssAnchor').click(function () {
		
		curJCSQSJType = '公用市政设施';	
		loadgyszss();
		
    });
	
	//交通市政设施
	$('#jtszssAnchor').click(function () {
		
		curJCSQSJType = '交通市政设施';	
		loadjtszss();
		
    });
	
	//市容环境市政设施
	$('#srhjszssAnchor').click(function () {
		
		curJCSQSJType = '市容环境市政设施';	
		loadsrhjszss();
		
    });
	
	//园林绿化市政设施
	$('#yllhszssAnchor').click(function () {
		
		curJCSQSJType = '园林绿化市政设施';	
		loadyllhszss();
		
    });
	
	//其他市政设施
	$('#qtszssAnchor').click(function () {
		
		curJCSQSJType = '其他市政设施';	
		loadqtszss();
		
    });
	
	//消防通道
	$('#xftdAnchor').click(function () {
		
		curJCSQSJType = '消防通道';	
		loadxftd();
		
    });
	
	//消防设施
	$('#xfssAnchor').click(function () {
		
		curJCSQSJType = '消防设施';	
		loadxfss();
		
    });
	
	
	//停车位区域
	$('#tcwqyAnchor').click(function () {
		
		curJCSQSJType = '停车位区域';	
		loadtcwqy();
		
    });
	
	//一般停车场出入口
	$('#ybtcccrkAnchor').click(function () {
		
		curJCSQSJType = '一般停车场出入口';	
		loadybtcccrk();
		
    });
	
	//一般停车场
	$('#ybtccAnchor').click(function () {
		
		curJCSQSJType = '一般停车场';	
		loadybtcc();
		
    });
	
	//非机动车停车位
	$('#fjdctcwAnchor').click(function () {
		
		curJCSQSJType = '非机动车停车位';	
		loadfjdctcw();
		
    });
	
	//道路停车场
	$('#dltccAnchor').click(function () {
		
		curJCSQSJType = '道路停车场';	
		loaddltcc();
		
    });
	
//小区出入口
	$('#xqcrkAnchor').click(function () {
		
		curJCSQSJType = '小区出入口';	
		loadxqcrk();
		
    });
	
	//其他楼宇
	$('#dltccAnchor').click(function () {
		
		curJCSQSJType = '其他楼宇';	
		loadqtly();
		
    });
	
	//易积水点
	$('#yjsdAnchor').click(function () {
		
		curJCSQSJType = '易积水点';	
		loadyjsd();
		
    });
});

loadJCSQSJ();

function loadJCSQSJ()
{
	
	if(curJCSQSJType == '' || curJCSQSJType == '居民信息')
	{
		loadResident();
	}
	else if(curJCSQSJType == '小区信息')
	{
		loadCommunity();
	}
	else if(curJCSQSJType == '房屋信息')
	{
		loadRoom();
	}
	else if(curJCSQSJType == '家庭信息')
	{
		loadFamily();
	}
	else if(curJCSQSJType =='周边道路')
		loadRoad();

	else if(curJCSQSJType == '党组织')	
		loadPartyOrganization();

	else if(curJCSQSJType == '市政设施')
		loadPublicFacilities();

	else if(curJCSQSJType == '住宅楼宇')	
		loadResidebuilding();

	else if(curJCSQSJType == '商务楼宇')	
		loadBizbuilding();

	else if(curJCSQSJType == '地下空间')	
		loadUndergroundspace();

	else if(curJCSQSJType == '车辆信息')	
		loadVehicle();

	else if(curJCSQSJType == '文体设施')	
		loadCultureFacilities();

	else if(curJCSQSJType == '停车资源')	
		loadParking();

	else if(curJCSQSJType == '法人组织')	
		loadOrganization();

	else if(curJCSQSJType == '志愿者队伍')
		loadVolunteerteam();

	else if(curJCSQSJType == '居民团体')	
		loadPopulationgroup();

	else if(curJCSQSJType == '服务网点')	
		loadServicestore();

	else if(curJCSQSJType == '垃圾站')
		loadRubbish();
	
	else if(curJCSQSJType == '河湖水系')
		loadWatersystem();
	
	else if(curJCSQSJType == '避难场所')
		loadShelter();
	
	else if(curJCSQSJType == '党员信息')
		loaddyxx();
	
	else if(curJCSQSJType == '宣传设施')
		loadxcss();
	
	else if(curJCSQSJType == '公用市政设施')
		loadgyszss();
	
	else if(curJCSQSJType == '交通市政设施')
		loadjtszss();
	
	else if(curJCSQSJType == '市容环境市政设施')
		loadsrhjszss();
	
	else if(curJCSQSJType == '园林绿化市政设施')
		loadyllhszss();
	
	else if(curJCSQSJType == '其他市政设施')
		loadqtszss();
	
	else if(curJCSQSJType == '消防通道')
		loadxftd();
	
	else if(curJCSQSJType == '消防设施')
		loadxfss();
	
	else if(curJCSQSJType == '停车位区域')
		loadtcwqy();
	
	else if(curJCSQSJType == '一般停车场出入口')
		loadybtcccrk();
	
	else if(curJCSQSJType == '一般停车场')
		loadybtcc();
	
	else if(curJCSQSJType == '非机动车停车位')
		loadfjdctcw();
	
	else if(curJCSQSJType == '道路停车场')
		loaddltcc();
	
	else if(curJCSQSJType == '小区出入口')
		loadxqcrk();
	
	else if(curJCSQSJType == '其他楼宇')
		loadqtly();
	
	else if(curJCSQSJType == '易积水点')
		loadyjsd();
	
	$.get(getContextPath()+"/jcsqsjController/get",
	function(result){
		var obj = jQuery.parseJSON(result);  
		if(obj.success)
		{
			$('#xqxxSpan').html(obj.community);//小区信息
			$('#zzlySpan').html(obj.residebuilding);//住宅楼宇
			
			$('#fwxxSpan').html(obj.room);//房屋信息
			$('#jtxxSpan').html(obj.family);//家庭信息
			$('#jmxxSpan').html(obj.resident);//居民信息
			$('#clxxSpan').html(obj.vehicle);//车辆信息
			$('#swlySpan').html(obj.commercialbuilding);//商务楼宇
			$('#frzzSpan').html(obj.organization);//法人组织
			$('#dxkjSpan').html(obj.undergroundspace);//地下空间
			$('#dzzxxSpan').html(obj.partyorganization);//党组织信息
			$('#zyzdwSpan').html(obj.volunteerteam);//志愿者队伍
			$('#jmttSpan').html(obj.populationgroup);//居民团体
			$('#zbdlSpan').html(obj.roads);//周边道路
			$('#hhsxSpan').html(obj.watersystem);//河湖水系
			$('#szssSpan').html(obj.publicfacilities);//市政设施
			$('#wtssSpan').html(obj.culturefacilities);//文体设施
			$('#tczySpan').html(obj.parking);
			$('#bncsSpan').html(obj.shelter);//避难场所
			$('#fwwdSpan').html(obj.service_store);//服务网点
			$('#ljzSpan').html(obj.ljz);//垃圾站
			
			$('#dyxxSpan').html(obj.dyxx);//  党员信息 	
			$('#xcssSpan').html(obj.xcss);//	宣传设施	
			$('#gyszssSpan').html(obj.gyszss);//	公用市政设施	
			$('#jtszssSpan').html(obj.jtszss);//	交通市政设施	
			$('#srhjszssSpan').html(obj.srhjszss);//	市容环境市政设施	
			$('#yllhszssSpan').html(obj.yllhszss);//	园林绿化市政设施	
			$('#qtszssSpan').html(obj.qtszss);//	其他市政设施	
			$('#xftdSpan').html(obj.xftd);//		消防通道	
			$('#xfssSpan').html(obj.xfss);//		消防设施	
			$('#tcwqySpan').html(obj.tcwqy);//		停车位区域	
			$('#ybtcccrkSpan').html(obj.ybtcccrk);//	一般停车场出入口	
			$('#ybtccSpan').html(obj.ybtcc);//	一般停车场	
			$('#fjdctcwSpan').html(obj.fjdctcw);//	非机动车停车位	
			$('#dltccSpan').html(obj.dltcc);//		道路停车场	
			
		}
	});	
}

function loadResident()
{
	$('#jcsqsjDiv').load("./jcsqsj/resident/resident.html", function () {
        
	});
}

function loadCommunity()
{	
	$('#jcsqsjDiv').load("./jcsqsj/community/community.html", function () {
	
	});
}

function loadRoom()
{
	$('#jcsqsjDiv').load("./jcsqsj/room/room.html", function () {
	
	});
}

function loadFamily()
{
	$('#jcsqsjDiv').load("./jcsqsj/family/family.html", function () {
	
	});
}

function loadRoad()
{		
	$('#jcsqsjDiv').load("./jcsqsj/roads/roads.html", function () {
	
	});
}

function loadPartyOrganization()
{
	 $('#jcsqsjDiv').load("./jcsqsj/partyorganization/partyorganization.html", function () {
        
	});
}

function loadPublicFacilities()
{
        $('#jcsqsjDiv').load("./jcsqsj/publicfacilities/publicfacilities.html", function () {
        
		});
	
}

function loadResidebuilding()
{
	
        $('#jcsqsjDiv').load("./jcsqsj/residebuilding/residebuilding.html", function () {
        
		});
}

function loadBizbuilding()
{
        $('#jcsqsjDiv').load("./jcsqsj/jc_bizbuilding/jc_bizbuilding.html", function () {
        
		});
	
}

function loadUndergroundspace()
{
        $('#jcsqsjDiv').load("./jcsqsj/undergroundspace/undergroundspace.html", function () {
        
		});
	
}

function loadVehicle()
{
	
        $('#jcsqsjDiv').load("./jcsqsj/vehicle/vehicle.html", function () {
        
		});
}

function loadCultureFacilities()
{
	
        $('#jcsqsjDiv').load("./jcsqsj/culturefacilities/culturefacilities.html", function () {
        
		});
}

function loadParking()
{
	
		$('#jcsqsjDiv').load("./jcsqsj/parking/parking.html", function () {
        
		});
}

function loadOrganization()
{
	
		$('#jcsqsjDiv').load("./jcsqsj/jc_organization/jc_organization.html", function () {
        
		});
}

function loadVolunteerteam()
{
	
		$('#jcsqsjDiv').load("./jcsqsj/volunteerteam/volunteerteam.html", function () {
        
		});
}

function loadPopulationgroup()
{
	
		$('#jcsqsjDiv').load("./jcsqsj/populationgroup/populationgroup.html", function () {
        
		});
}

function loadServicestore()
{
	
		$('#jcsqsjDiv').load("./jcsqsj/service_store/service_store.html", function () {
        
		});
}

function loadRubbish()
{
	$('#jcsqsjDiv').load("./jcsqsj/jc_rubbish/jc_rubbish.html", function () {
	
	});
}

function loadWatersystem()
{
	$('#jcsqsjDiv').load("./jcsqsj/watersystem/watersystem.html", function () {
	
	});
}

function loadShelter()
{
	$('#jcsqsjDiv').load("./jcsqsj/shelter/shelter.html", function () {
	
	});
}

//党员信息
function loaddyxx()
{
	$('#jcsqsjDiv').load("./jcsqsj/jc_partymember/jc_partymember.html", function () {
	
	});
}
//宣传设施
function loadxcss()
{
	$('#jcsqsjDiv').load("./jcsqsj/jc_advertisement/jc_advertisement.html", function () {
	
	});
}
//公用市政设施
function loadgyszss()
{
	$('#jcsqsjDiv').load("./jcsqsj/jc_pubfacilities_gy/jc_pubfacilities_gy.html", function () {
	
	});
}
//交通市政设施
function loadjtszss()
{
	$('#jcsqsjDiv').load("./jcsqsj/jc_pubfacilities_jt/jc_pubfacilities_jt.html", function () {
	
	});
}
//市容环境市政设施
function loadsrhjszss()
{
	$('#jcsqsjDiv').load("./jcsqsj/jc_pubfacilities_hj/jc_pubfacilities_hj.html", function () {
	
	});
}
//园林绿化市政设施
function loadyllhszss()
{
	$('#jcsqsjDiv').load("./jcsqsj/jc_pubfacilities_lh/jc_pubfacilities_lh.html", function () {
	
	});
}
//其他市政设施
function loadqtszss()
{
	$('#jcsqsjDiv').load("./jcsqsj/jc_pubfacilities_qt/jc_pubfacilities_qt.html", function () {
	
	});
}
//消防通道
function loadxftd()
{
	$('#jcsqsjDiv').load("./jcsqsj/jc_xftd/jc_xftd.html", function () {
	
	});
}
//消防设施
function loadxfss()
{
	$('#jcsqsjDiv').load("./jcsqsj/jc_xfss/jc_xfss.html", function () {
	
	});
}
//停车位区域
function loadtcwqy()
{
	$('#jcsqsjDiv').load("./jcsqsj/jc_tc_tcw/jc_tc_tcw.html", function () {
	
	});
}
//一般停车场出入口
function loadybtcccrk()
{
	$('#jcsqsjDiv').load("./jcsqsj/jc_tc_tcccrk/jc_tc_tcccrk.html", function () {
	
	});
}
//一般停车场
function loadybtcc()
{
	$('#jcsqsjDiv').load("./jcsqsj/jc_tc_ybtcc/jc_tc_ybtcc.html", function () {
	
	});
}
//非机动车停车位
function loadfjdctcw()
{
	$('#jcsqsjDiv').load("./jcsqsj/jc_tc_fjdctcw/jc_tc_fjdctcw.html", function () {
	
	});
}
//道路停车场
function loaddltcc()
{
	$('#jcsqsjDiv').load("./jcsqsj/jc_tc_dltcc/jc_tc_dltcc.html", function () {
	
	});
}

//小区出入口
function loadxqcrk()
{
	$('#jcsqsjDiv').load("./jcsqsj/jc_xqway/jc_xqway.html", function () {
	
	});
}
//其他楼宇
function loadqtly()
{
	$('#jcsqsjDiv').load("./jcsqsj/jc_qtbuilding/jc_qtbuilding.html", function () {
	
	});
}
//易积水点
function loadyjsd()
{
	$('#jcsqsjDiv').load("./jcsqsj/jc_waterpoint/jc_waterpoint.html", function () {
	
	});
}

function forkme(){
	if($('#card-body').is(':hidden')){
		$("#showicon").attr("class", "fas fa-chevron-up");
		$("#card-body").show();
		$("#firstshow").hide();
		$("#searchtype").val(2);
		$("#forktool").show();
	}
	else {
		$("#showicon").attr("class", "fas fa-chevron-down");
		$("#card-body").hide();
		$("#firstshow").show();
		$("#searchtype").val(1);
		$("#forktool").hide();
	}
	
}




