


$(document).ready(function (){

	getsqname();
	
	getOrganizationInfo();
	
	getMinQing();
	
	getOther();
	
	//getConvenience();
	
	
});	

function getsqname(){
	$.get(getContextPath()+'/homeController/getsqname',
		function(result){
		
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				//$("#sqname").html("(" + obj.data + ")");
				$("#sqtitle").html(obj.data);
			}
		});	
}



function getOrganizationInfo()
{
	$.get(getContextPath()+'/homeChartController/getOrganizationInfo',
		function(result){
		
			var obj = jQuery.parseJSON(result);  
			console.log(obj);
			if(obj.success)
			{
				if(obj.hasowner == 0){
					$("#sqxx").hide();
				}
				else {
					$('#sqnote').text(obj.noteShort);//社区简介
					
					$('#sqyear').text(obj.year);//社区建成时间
					
					$('#sqtype').text(obj.type);//社区类型
					
					$('#sqarea').text(obj.area+'平方公里');//社区面积
					
					$('#sqroomarea').text(obj.roomArea+'平方米');//居住面积
					
					$('#sqresidentcnt').text(obj.residentCnt);//总人口数
				}
				
				var pictures = obj.pictures;//社区美景
				
				var picStr = '';
				
				if(pictures != null)
				{
					var picArr = pictures.split(',');
					
					for(var i=0;i<picArr.length;i++)
					{
						var picli = '<li><dd><img src="'+getContextPath()+"/fileController/download?fileName="+picArr[i]+'"></dd></li>';
							
						$('#picul').append(picli);
						
						var picliBig = '<li><a href="#"><img src="'+getContextPath()+"/fileController/download?fileName="+picArr[i]+'"></a> </li>';
						
						$('#picliBig').append(picliBig);
					}
					
					
					function G(s){

						return document.getElementById(s);

					}

					

					function getStyle(obj, attr){

						if(obj.currentStyle){

							return obj.currentStyle[attr];

						}else{

							return getComputedStyle(obj, false)[attr];

						}

					}

	

					function Animate(obj, json){

						if(obj.timer){

							clearInterval(obj.timer);

						}

						obj.timer = setInterval(function(){

							for(var attr in json){

								var iCur = parseInt(getStyle(obj, attr));

								iCur = iCur ? iCur : 0;

								var iSpeed = (json[attr] - iCur) / 4;

								iSpeed = iSpeed > 0 ? Math.ceil(iSpeed) : Math.floor(iSpeed);

								obj.style[attr] = iCur + iSpeed + 'px';

								if(iCur == json[attr]){

									clearInterval(obj.timer);

								}

							}

						}, 30);

					}



					var oPic = G("picBox");

					var oList = G("listBox");

					

					var oPrev = G("prev");

					var oNext = G("next");

					var oPrevTop = G("prevTop");

					var oNextTop = G("nextTop");



					var oPicLi = oPic.getElementsByTagName("li");

					var oListLi = oList.getElementsByTagName("li");

					var len1 = oPicLi.length;

					var len2 = oListLi.length;

					

					var oPicUl = oPic.getElementsByTagName("ul")[0];

					var oListUl = oList.getElementsByTagName("ul")[0];

					debugger;
					var w1 = oPicLi[0].offsetWidth;

					var w2 = oListLi[0].offsetHeight;



					oPicUl.style.width = w1 * len1 + "px";

					oListUl.style.height = (w2+9) * len2 + "px";



					var index = 0;

					

					var num = 4;

					var num2 = Math.ceil(num / 1.5);



					function Change(){



						Animate(oPicUl, {left: - index * w1});

						

						if(index < num2){

							Animate(oListUl, {top: 0});

						}else if(index + num2 <= len2){

							Animate(oListUl, {top: - (index - num2 + 1) * w2});

						}else{

							Animate(oListUl, {top: - (len2 - num) * w2});

						}



						for (var i = 0; i < len2; i++) {

							oListLi[i].className = "";

							if(i == index){

								oListLi[i].className = "on";

							}

						}

					}

	

					oNextTop.onclick = oNext.onclick = function(){

						

						index ++;

						index = index == len2 ? 0 : index;

						Change();

					}

					

					oPrev.onmouseover = oNext.onmouseover = oPrevTop.onmouseover = oNextTop.onmouseover = function(){

						clearInterval(timer);

						}

					oPrev.onmouseout = oNext.onmouseout = oPrevTop.onmouseout = oNextTop.onmouseout = function(){

						timer=setInterval(autoPlay,4000);

						}



					oPrevTop.onclick = oPrev.onclick = function(){



						index --;

						index = index == -1 ? len2 -1 : index;

						Change();

					}

	

					var timer=null;

					timer=setInterval(autoPlay,4000);

					function autoPlay(){

						index ++;

						index = index == len2 ? 0 : index;

						Change();

					}

					for (var i = 0; i < len2; i++) {

						oListLi[i].index = i;

						oListLi[i].onclick = function(){

							index = this.index;



							Change();

						}

					}	
				}
				
				obj.underGroundArea;//底下空间面积
				
				$('#residentDensity').text(obj.residentDensity);//人口密度
				
				$('#averageRoomArea').text(obj.averageRoomArea);//人均住房面积
				
				$('#averageUnderGroundArea').text(obj.averageUnderGroundArea);//人均地下空间面积
				
				$('#publcFacilityDensity').text(obj.publcFacilityDensity);//市政设施密度
				
				$('#averageRubbishNum').text(obj.averageRubbishNum);//人均垃圾设施数量
				
				$('#averageAdvertisementNum').text(obj.averageAdvertisementNum);//人均宣传设施数量
				
				$('#averageTreeNum').text(averageTreeNum);//人均绿植数量
				
				$('#averageToiletNum').text(obj.averageToiletNum);//人均公厕
				
				$('#averageResidentJZQTcwDiv').html(obj.averageResidentJZQTcw+'<span>【'+obj.jzqtcwCnt+'/('+obj.area+')】</span>');//

				$('#averageResidentTcwDiv').html(obj.averageResidentTcw+'<span>【'+obj.tcwCnt+'/('+obj.area+')】</span>');

				$('#averageFamilyJZQTcwDiv').html(obj.averageFamilyJZQTcw+'<span>【'+obj.jzqtcwCnt+'/('+obj.famliyCnt+'/100)】</span>');

				$('#averageFamilyTcwDiv').html(obj.averageFamilyTcw+'<span>【'+obj.tcwCnt+'/('+obj.famliyCnt+'/100)】</span>');
				
				$('#averageCultureFacArea').text(obj.averageCultureFacArea+'【'+obj.cultureFacilitiesArea+'/('+obj.residentCnt+'/100)】');
				
				$('#averageCultureFacNum').text(obj.averageCultureFacNum+'【'+obj.cultureFacilitiesNum+'/('+obj.residentCnt+'/100)】');
				
				//每百人社区工作者占比情况
				$("#sqPercentDiv").width(obj.sqPercent+'%');				
				$("#sqPercentSpan").html('<b>'+obj.sqCnt+'</b>/('+obj.residentCnt+'/100)');

				//党员占比情况				
				$("#partyMemberPercentDiv").width(obj.partyMemberPercent+'%');				
				$("#partyMemberPercentSpan").html('<b>'+obj.partyMemberCnt+'</b>/'+obj.residentCnt);

				//社区工作者党员占比情况
				$("#sqOfPartyMemberPercentDiv").width(obj.sqOfPartyMember+'%');
				$("#sqOfPartyMemberPercentSpan").html('<b>'+obj.sqOrgMemberPartyMemberNum+'</b>/'+obj.sqOrgMemberNum+'');

				//志愿者比例情况				
				$("#volunteerPercentDiv").width(obj.volunteerPercent+'%');				
				$("#volunteerPercentSpan").html('<b>'+obj.volunteerCnt+'</b>/'+obj.residentCnt);

				//志愿者党员占比情况
				$("#volunteerOfPartyMemberPercentSpan").html('<b>'+obj.volunteerPartyMemberNum+'</b>/'+obj.volunteerNum);	
				$("#volunteerOfPartyMemberPercentDiv").width(obj.volunteerOfPartyMember+'%');				
				
				$("#policeNum").text(obj.policeNum);
				$("#cameraNum").text(obj.cameraNum);
				$("#cameradensity").text(obj.cameraDensity);
				
				
				var serviceStoreArr = obj.serviceStoreArr;

				var serviceStoreType = [];
				var serviceStoreNum = [];
				var serviceStoreNum100 = [];
				
				
				for(var i=0;i<serviceStoreArr.length;i++)
				{
					var serviceStoreObj = serviceStoreArr[i];
					
					serviceStoreType[i] = serviceStoreObj.serviceStore;
					serviceStoreNum[i] = serviceStoreObj.serviceStoreNum;
					serviceStoreNum100[i] = serviceStoreObj.serviceStoreNum100;
				}

				var chartConvenienceSecond = echarts.init(document.getElementById('convenienceDivSecond'));

				// 指定图表的配置项和数据
				var optionConvenienceSecond = {
						tooltip: {
							trigger: 'axis',
							axisPointer: {
								type: 'cross',
								crossStyle: {
									color: '#999'
								}
							}
						},
						/*toolbox: {
							feature: {
								dataView: {show: true, readOnly: false},
								magicType: {show: true, type: ['line', 'bar']},
								restore: {show: true},
								saveAsImage: {show: true}
							}
						},*/
						legend: {
							data: ['网点数', '每百人网点数']
						},
						xAxis: [
							{
								type: 'category',
								data: serviceStoreType,//['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
								axisPointer: {
									type: 'shadow'
								}
							}
						],
						yAxis: [
							{
								type: 'value',
								name: '网点数',
								min: 0,
								max: 100,
								interval: 10/*,
								axisLabel: {
									formatter: '{value} ml'
								}*/
							},
							{
								type: 'value',
								name: '每百人网点数',
								min: 0,
								max: 1,
								interval: 0.1/*,
								axisLabel: {
									formatter: '{value} °C'
								}*/
							}
						],
						series: [
							{
								name: '网点数',
								type: 'bar',
								data: serviceStoreNum//[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
							},
							{
								name: '每百人网点数',
								type: 'bar',
								yAxisIndex: 1,
								data: serviceStoreNum100//[2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
							}
						]
					};

				chartConvenienceSecond.setOption(optionConvenienceSecond);

			}
			
		});	
}


function getMinQing()
{
	$.get(getContextPath()+'/homeChartController/getMinQing',
		function(result){
		
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				var chartGender = echarts.init(document.getElementById('chartGenderDiv'));

				var genderArr = [];
				var genderData = [];
				
				for(var i=0;i<obj.genderArr.length;i++)
				{
					genderArr[i] = obj.genderArr[i].gender;
					genderData[i] = {value:obj.genderArr[i].genderNum,name:obj.genderArr[i].gender};
				}
				
				var residenceStatusArr = [];
				var residenceStatusData = [];
				
				for(var i=0;i<obj.residenceStatusArr.length;i++)
				{
					residenceStatusArr[i] = obj.residenceStatusArr[i].residenceStatus;
					residenceStatusData[i] = {value:obj.residenceStatusArr[i].residenceStatusNum,name:obj.residenceStatusArr[i].residenceStatus};
				}
				
				var optionGender = {
					title: {
							text: '性别分布图',
							right: 20
						},
					tooltip: {
						trigger: 'item',
						formatter: '{a} <br/>{b}: {c} ({d}%)'
					},
					legend: {
						orient: 'vertical',
						left: 10,
						data: genderArr//['直达', '营销广告', '搜索引擎']
					},
					series: [
						{
							name: '性别比例',
							type: 'pie',
							selectedMode: 'single',
							//radius: [0, '30%'],

							label: {
								position: 'inner'
							},
							labelLine: {
								show: false
							},
							data: genderData
						}/*,
						{
							name: '户籍类别',
							type: 'pie',
							radius: ['40%', '55%'],
							data: residenceStatusData
						}*/
					]
				};

				chartGender.setOption(optionGender);
				
				
				
				var ageArr = [];
				var ageData = [];
				
				for(var i=0;i<obj.ageArr.length;i++)
				{
					ageArr[i] = obj.ageArr[i].age;
					ageData[i] = {value:obj.ageArr[i].ageNum,name:obj.ageArr[i].age};
				}
				
				
				var chartAge = echarts.init(document.getElementById('chartAgeDiv'));

				// 指定图表的配置项和数据
				var optionAge = {
						title: {
							text: '年龄段分布图',
							right: 20
						},
						tooltip: {
							trigger: 'item',
							formatter: '{a} <br/>{b} : {c} ({d}%)'
						},/*
						legend: {
							left: 'center',
							top: 'bottom',
							data: ageArr
						},*/
						series: [
							{
								name: '面积模式',
								type: 'pie',
								radius: [30, 110],
								//center: ['75%', '50%'],
								roseType: 'area',
								data: ageData
							}
						]
					};
				chartAge.setOption(optionAge);
				
				
				
				var residenceStatusArr = [];
				var residenceStatusData = [];
				
				for(var i=0;i<obj.residenceStatusArr.length;i++)
				{
					residenceStatusArr[i] = obj.residenceStatusArr[i].residenceStatus;
					residenceStatusData[i] = {value:obj.residenceStatusArr[i].residenceStatusNum,name:obj.residenceStatusArr[i].residenceStatus};
				}
				
				
				var chartHukou = echarts.init(document.getElementById('chartHukouDiv'));

				// 指定图表的配置项和数据
				var optionHukou = {
					title: {
						text: '户口分布图',
						right: 20
					},
					tooltip: {
						trigger: 'item',
						formatter: '{a} <br/>{b}: {c} ({d}%)'
					},
					legend: {
						orient: 'vertical',
						left: 10,
						data: residenceStatusArr//['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
					},
					series: [
						{
							name: '户口分布',
							type: 'pie',
							radius: ['50%', '70%'],
							avoidLabelOverlap: false,
							label: {
								show: false,
								position: 'center'
							},
							emphasis: {
								label: {
									show: true,
									fontSize: '30',
									fontWeight: 'bold'
								}
							},
							labelLine: {
								show: false
							},
							data: residenceStatusData
						}
					]
				};

				chartHukou.setOption(optionHukou);
				
				
				
				
				var nationArr = [];
				var nationData = [];
				
				for(var i=0;i<obj.nationArr.length;i++)
				{
					nationArr[i] = obj.nationArr[i].nation;
					nationData[i] = {value:obj.nationArr[i].nationNum,name:obj.nationArr[i].nation};
				}
				
				console.log(nationData);
				
				//var chartHukou = echarts.init(document.getElementById('chartHukouDiv'));


				var chartNation = echarts.init(document.getElementById('chartNationDiv'));

				// 指定图表的配置项和数据
				var optionNation = {
					title: {
						text: '民族分布图',
						right: 20
					},
					series: {
						type: 'sunburst',
						highlightPolicy: 'ancestor',
						data: nationData,
						radius: [0, '95%'],
						sort: null,
						levels: [{}, {
							r0: '70%',
							r: '50%',
							itemStyle: {
								borderWidth: 2
							},
							label: {
								rotate: 'tangential'
							}
						}, {
							r0: '35%',
							r: '70%',
							label: {
								align: 'right'
							}
						}, {
							r0: '70%',
							r: '72%',
							label: {
								position: 'outside',
								padding: 3,
								silent: false
							},
							itemStyle: {
								borderWidth: 3
							}
						}]
					}
				};

				chartNation.setOption(optionNation);
				
				
				
				
				var politicalStatusArr = [];
				var politicalStatusData = [];
				
				for(var i=0;i<obj.politicalStatusArr.length;i++)
				{
					politicalStatusArr[i] = obj.politicalStatusArr[i].politicalstatus;
					politicalStatusData[i] = {value:obj.politicalStatusArr[i].politicalStatusNum,name:obj.politicalStatusArr[i].politicalstatus};
				}
				
				var chartParty = echarts.init(document.getElementById('chartPartyDiv'));

				// 指定图表的配置项和数据
				var optionParty = {
					title: {
						text: '政治面貌分布图',						
						right: 20
					},
					tooltip: {
						trigger: 'item',
						formatter: '{a} <br/>{b} : {c} ({d}%)'
					},
					legend: {
						orient: 'vertical',
						left: 10,						
						data: politicalStatusArr//['rose1', 'rose2', 'rose3', 'rose4', 'rose5', 'rose6', 'rose7', 'rose8']
					},
					toolbox: {
						show: false,
						feature: {
							mark: {show: true},
							dataView: {show: true, readOnly: false},
							magicType: {
								show: true,
								type: ['pie', 'funnel']
							},
							restore: {show: true},
							saveAsImage: {show: true}
						}
					},
					series: [
						{
							name: '政治面貌分布图',
							type: 'pie',
							radius: [20, 110],
							//center: ['25%', '50%'],
							roseType: 'radius',
							label: {
								show: false
							},
							emphasis: {
								label: {
									show: true
								}
							},
							data: politicalStatusData
						}
					]
				};

				chartParty.setOption(optionParty);
				
								
								
				var professionArr = [];
				var professionData = [];
				
				for(var i=0;i<obj.professionArr.length;i++)
				{
					professionArr[i] = obj.professionArr[i].profession;
					professionData[i] = {value:obj.professionArr[i].professionNum,name:obj.professionArr[i].profession};
				}				
								
				
				var chartEmploy = echarts.init(document.getElementById('chartEmployDiv'));

				// 指定图表的配置项和数据
				var optionEmploy = {
					title: {
						text: '就业状态图',
						right: 20
					},
					tooltip: {
						trigger: 'item',
						formatter: '{a} <br/>{b}: {c} ({d}%)'
					},
					legend: {
						orient: 'vertical',
						left: 10,
						data: professionArr//['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
					},
					series: [
						{
							name: '职业状态分布图',
							type: 'pie',
							radius: ['50%', '70%'],
							avoidLabelOverlap: false,
							label: {
								show: false,
								position: 'center'
							},
							emphasis: {
								label: {
									show: true,
									fontSize: '30',
									fontWeight: 'bold'
								}
							},
							labelLine: {
								show: false
							},
							data: professionData/*[
								{value: 335, name: '直接访问'},
								{value: 310, name: '邮件营销'},
								{value: 234, name: '联盟广告'},
								{value: 135, name: '视频广告'},
								{value: 1548, name: '搜索引擎'}
							]*/
						}
					]
				};

				chartEmploy.setOption(optionEmploy);
				
				
				


				
			}
		});	
}



function getOther()
{
	$.get(getContextPath()+'/homeChartController/getOther',
		function(result){
		
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				
				//var chartPartyPercent = echarts.init(document.getElementById('chartPartyPercentDiv'));

				/*// 指定图表的配置项和数据
				var optionPartyPercent = {
					tooltip: {
						trigger: 'axis',
						axisPointer: {            // 坐标轴指示器，坐标轴触发有效
							type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
						}
					},
					legend: {
						data: ['事项类别数量']
					},
					xAxis: [
						{
							type: 'category',
							data: ['社区党建', '民主自治', '社区服务', '平安建设', '社区环境', '卫生健康']
						}
					],
					yAxis: [
						{
							type: 'value'
						}
					],
					series: [
						{
							name: '数量',
							type: 'bar',
							data: []
						}
					]
				};

				chartPartyPercent.setOption(optionPartyPercent);
				*/
				
				
				
				
				
				var marriageArr = [];
				var marriageData = [];
				
				for(var i=0;i<obj.marriageArr.length;i++)
				{
					marriageArr[i] = obj.marriageArr[i].marriage;
					marriageData[i] = {value:obj.marriageArr[i].marriageNum,name:obj.marriageArr[i].marriage};
				}
				
				var chartMarriage = echarts.init(document.getElementById('chartMarriageDiv'));

				// 指定图表的配置项和数据
				var optionMarriage = {
					tooltip: {
						trigger: 'item',
						formatter: '{a} <br/>{b}: {c} ({d}%)'
					},
					legend: {
						orient: 'vertical',
						left: 10,
						data: marriageArr//['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
					},
					series: [
						{
							name: '婚姻状况',
							type: 'pie',
							radius: ['50%', '70%'],
							avoidLabelOverlap: false,
							label: {
								show: false,
								position: 'center'
							},
							emphasis: {
								label: {
									show: true,
									fontSize: '30',
									fontWeight: 'bold'
								}
							},
							labelLine: {
								show: false
							},
							data: marriageData
						}
					]
				};

				chartMarriage.setOption(optionMarriage);
				
				
				
				
				
				var educationArr = [];
				var educationData = [];
				
				for(var i=0;i<obj.educationArr.length;i++)
				{
					educationArr[i] = obj.educationArr[i].education;
					educationData[i] = {value:obj.educationArr[i].educationNum,name:obj.educationArr[i].education};
				}
				
				var chartEducation = echarts.init(document.getElementById('chartEducationDiv'));

				// 指定图表的配置项和数据
				var optionEducation = {
					tooltip: {
						trigger: 'item',
						formatter: '{b}: {c} ({d}%)'
					},
					/*legend: {
						orient: 'vertical',
						left: 10,
						data: educationArr//['直达', '营销广告', '搜索引擎']
					},*/
					series: [
						{
							//name: '教育程度',
							type: 'pie',
							radius: ['40%', '55%'],
							label: {
								formatter: '{b|{b}：}{c}  {per|{d}%}  ',
								backgroundColor: '#eee',
								borderColor: '#aaa',
								borderWidth: 1,
								borderRadius: 4,
								rich: {
									/*a: {
										color: '#999',
										lineHeight: 22,
										align: 'center'
									},*/
									hr: {
										borderColor: '#aaa',
										width: '100%',
										borderWidth: 0.5,
										height: 0
									},
									b: {
										fontSize: 16,
										lineHeight: 33
									},
									per: {
										color: '#eee',
										backgroundColor: '#334455',
										padding: [2, 4],
										borderRadius: 2
									}
								}
							},
							data: educationData/*[
								{value: 335, name: '直达'},
								{value: 310, name: '邮件营销'},
								{value: 234, name: '联盟广告'}
							]*/
						}
					]
				};

				chartEducation.setOption(optionEducation);
				
							
				
				
				
						
			}
		});	
}


function getConvenience()
{
	$.get(getContextPath()+'/homeChartController/getConvenience',
		function(result){
		
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				/*var chartConvenienceFist = echarts.init(document.getElementById('convenienceDivFirst'));

				// 指定图表的配置项和数据
				var optionConvenienceFirst = {
					tooltip: {
						trigger: 'axis',
						axisPointer: {            // 坐标轴指示器，坐标轴触发有效
							type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
						}
					},
					legend: {
						data: ['事项类别数量']
					},
					xAxis: [
						{
							type: 'category',
							data: ['社区党建', '民主自治', '社区服务', '平安建设', '社区环境', '卫生健康']
						}
					],
					yAxis: [
						{
							type: 'value'
						}
					],
					series: [
						{
							name: '数量',
							type: 'bar',
							data: []
						}
					]
				};

				chartConvenienceFist.setOption(optionConvenienceFirst);
				
				
				
				
				
				
				
				
				
				var chartConvenienceSecond = echarts.init(document.getElementById('convenienceDivSecond'));

				// 指定图表的配置项和数据
				var optionConvenienceSecond = {
					tooltip: {
						trigger: 'item',
						formatter: '{a} <br/>{b}: {c} ({d}%)'
					},
					legend: {
						orient: 'vertical',
						left: 10,
						data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
					},
					series: [
						{
							name: '访问来源',
							type: 'pie',
							radius: ['50%', '70%'],
							avoidLabelOverlap: false,
							label: {
								show: false,
								position: 'center'
							},
							emphasis: {
								label: {
									show: true,
									fontSize: '30',
									fontWeight: 'bold'
								}
							},
							labelLine: {
								show: false
							},
							data: [
								{value: 335, name: '直接访问'},
								{value: 310, name: '邮件营销'},
								{value: 234, name: '联盟广告'},
								{value: 135, name: '视频广告'},
								{value: 1548, name: '搜索引擎'}
							]
						}
					]
				};

				chartConvenienceSecond.setOption(optionConvenienceSecond);
				*/
						
			}
		});	
}




