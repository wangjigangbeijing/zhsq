
var curUserType = -1;

var curType = '';

$(document).ready(function (){
	
	$('#btnAdd').click(addsqzzqd);
	
	//小区信息
	$('#xqxxAnchor').click(function () {
		curType = '小区信息';		
		loadCommunity();
    });
	
	//房屋信息
	$('#fwxxAnchor').click(function () {		
		curType = '房屋信息';		
		loadRoom();        
    });
	
	//家庭信息
	$('#jtxxAnchor').click(function () {
		curType = '家庭信息';		
		loadFamily();  
    });
	
	//居民信息
	$('#jmxxAnchor').click(function () {
		curType = '居民信息';	
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
		curType = '周边道路';	
		loadRoad();
    });
	
	//党组织
	$('#dzzAnchor').click(function () {
		
		curType = '党组织';	
		loadPartyOrganization();
       
    });
	
	//市政设施
	$('#szbjAnchor').click(function () {
		
		curType = '市政设施';	
		loadPublicFacilities();
		
    });
	
	//住宅楼宇
	$('#zzlyAnchor').click(function () {
		
		curType = '住宅楼宇';	
		loadResidebuilding();
		
    });
	
	//商务楼宇
	$('#swlyAnchor').click(function () {
		
		curType = '商务楼宇';	
		loadBizbuilding();
		
    });
	
	//地下空间
	$('#dxkjAnchor').click(function () {
		
		curType = '地下空间';	
		loadUndergroundspace();
		
    });
	
	//车辆信息
	$('#clxxAnchor').click(function () {
		
		curType = '车辆信息';	
		loadVehicle();
		
    });
	
	//文体设施
	$('#wtssAnchor').click(function () {
		
		curType = '文体设施';	
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
		
		curType = '停车资源';	
		loadParking();
		
    });
	
	//法人组织
	$('#frzzAnchor').click(function () {
		
		curType = '法人组织';	
		loadOrganization();
		
    });
	
	//志愿者队伍
	$('#zyzdwAnchor').click(function () {
		
		curType = '志愿者队伍';	
		loadVolunteerteam();
		
    });
	
	//居民团体
	$('#jmttAnchor').click(function () {
		
		curType = '居民团体';	
		loadPopulationgroup();
		
    });
	
	//服务网点
	$('#ffwdAnchor').click(function () {
		
		curType = '服务网点';	
		loadServicestore();
		
    });
	
	//垃圾站
	$('#ljzAnchor').click(function () {
		
		curType = '垃圾站';	
		loadRubbish();
		
    });
	
	//河湖水系
	$('#hhsxAnchor').click(function () {
		
		curType = '河湖水系';	
		loadWatersystem();
		
    });
	
	//避难场所
	$('#bncsAnchor').click(function () {
		
		curType = '避难场所';	
		loadShelter();
		
    });
	
	if(curType == '' || curType == '居民信息')
	{
		loadResident();
	}
	else if(curType == '小区信息')
	{
		loadCommunity();
	}
	else if(curType == '房屋信息')
	{
		loadRoom();
	}
	else if(curType == '家庭信息')
	{
		loadFamily();
	}
	else if(curType =='周边道路')
		loadRoad();

	else if(curType == '党组织')	
		loadPartyOrganization();

	else if(curType == '市政设施')
		loadPublicFacilities();

	else if(curType == '住宅楼宇')	
		loadResidebuilding();

	else if(curType == '商务楼宇')	
		loadBizbuilding();

	else if(curType == '地下空间')	
		loadUndergroundspace();

	else if(curType == '车辆信息')	
		loadVehicle();

	else if(curType == '文体设施')	
		loadCultureFacilities();

	else if(curType == '停车资源')	
		loadParking();

	else if(curType == '法人组织')	
		loadOrganization();

	else if(curType == '志愿者队伍')
		loadVolunteerteam();

	else if(curType == '居民团体')	
		loadPopulationgroup();

	else if(curType == '服务网点')	
		loadServicestore();

	else if(curType == '垃圾站')
		loadRubbish();
	
	else if(curType == '河湖水系')
		loadWatersystem();
	
	else if(curType == '避难场所')
		loadShelter();
	
	
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
			//$('#szssSpan').html(obj.);
			$('#wtssSpan').html(obj.culturefacilities);//文体设施
			//$('#tczySpan').html(obj.);
			$('#bncsSpan').html(obj.shelter);//避难场所
			$('#ffwdSpan').html(obj.service_store);//服务网点
			$('#ljzSpan').html(obj.ljz);//垃圾站
		}
	});	
});

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
	
		$('#jcsqsjDiv').load("./jcsqsj/organization/organization.html", function () {
        
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










