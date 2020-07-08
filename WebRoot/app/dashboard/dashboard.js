

$(document).ready(function (){
    $.get(getContextPath()+"/loginController/getCurrentLoginUserInfo",
		function(result){
			var obj = jQuery.parseJSON(result);  
			
			/*if(obj.userType == USER_TYPE_WEB_STREET)
			{
				$('#queryDiv').hide();
			}*/
	});	
	
	var checkin = $('.dpd1').datepicker({
            onRender: function(date) {
                return date.valueOf() < now.valueOf() ? 'disabled' : '';
            }
        }).on('changeDate', function(ev) {
                if (ev.date.valueOf() > checkout.date.valueOf()) {
                    var newDate = new Date(ev.date)
                    newDate.setDate(newDate.getDate() + 1);
                    checkout.setValue(newDate);
                }
                checkin.hide();
                $('.dpd2')[0].focus();
            }).data('datepicker');
        var checkout = $('.dpd2').datepicker({
            onRender: function(date) {
                return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
            }
        }).on('changeDate', function(ev) {
                checkout.hide();
            }).data('datepicker');
			
			
			
			
			
			
			
			
			
	var trendChartOption = {
    /*title : {
        text: '未来一周气温变化',
        subtext: '纯属虚构'
    },*/
    tooltip : {
        trigger: 'axis'
    },
    /*legend: {
        data:['最高气温','最低气温']
    },*/
    toolbox: {
        show : true,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            magicType : {show: true, type: ['line', 'bar']},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            data : ['201803','201804','201805','201806','201807','201808','201809']
        }
    ],
    yAxis : [
        {
            type : 'value',
            axisLabel : {
                formatter: '{value}'
            }
        }
    ],
    series : [
        {
            name:'最高气温',
            type:'line',
            data:[11, 11, 15, 13, 12, 13, 10],
            markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name: '平均值'}
                ]
            }
        },
        {
            name:'最低气温',
            type:'line',
            data:[1, -2, 2, 5, 3, 2, 0],
            markPoint : {
                data : [
                    {name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name : '平均值'}
                ]
            }
        }
    ]
};
               
trendChart = echarts.init(document.getElementById('trendChart'));
			trendChart.setOption(trendChartOption);

					
			
});



//var trendChart = null;

function loadEntData()
{
	var entStreet = $('#entStreetSelect').val();
	var buildType = $('#buildType').val();
	var fromTime = $('#fromTime').val();
	var toTime = $('#toTime').val();
	
	$.get(getContextPath()+"/dataStatController/loadStatChart?entStreet="+entStreet+"&buildType="+buildType+"&fromTime="+fromTime+"&toTime="+toTime,
		function(result){
			var obj = jQuery.parseJSON(result);  
			
			//采集条数统计趋势图
			var dateArr = [];
			var cntArr = [];				
			for(var i=0;obj.trendChart != null && i<obj.trendChart.length;i++)
			{
				var tempObject = obj.trendChart[i];
				
				dateArr[i] = tempObject.collectDate;
				cntArr[i] = tempObject.cnt;
			}
				
			var trendChartOption = {
				title: {
					text: '每日数据采集统计'
				},
				tooltip: {
					trigger: 'axis'
				},
				legend: {
					//data:['最高气温','最低气温']
					data:['采集条数']
				},
				toolbox: {
					show: true,
					feature: {
						dataZoom: {
							yAxisIndex: 'none'
						},
						dataView: {readOnly: false},
						magicType: {type: ['line', 'bar']},
						restore: {},
						saveAsImage: {}
					}
				},
				xAxis:  {
					type: 'category',
					boundaryGap: false,
					data: dateArr//['周一','周二','周三','周四','周五','周六','周日']
				},
				yAxis: {
					type: 'value',
					axisLabel: {
						//formatter: '{value} °C'
					}
				},
				series: [
					{
						name:'采集条数',
						type:'line',
						data:cntArr//[11, 11, 15, 13, 12, 13, 10],
						/*markPoint: {
							data: [
								{type: 'max', name: '最大值'},
								{type: 'min', name: '最小值'}
							]
						}*/,
						markLine: {
							data: [
								{type: 'average', name: '平均值'}
							]
						}
					}
				]
			};
			
			trendChart = echarts.init(document.getElementById('trendChart'));
			trendChart.setOption(trendChartOption);
			
			
			
			if(obj.singleStreet == true)
			{
				$("#streetChartDiv").removeClass().addClass("col-lg-4");
				$("#streetTableDiv").removeClass().addClass("col-lg-8");
				
				var legendArr = [];
				
				for(var i=0;i<obj.pieChart.length;i++)
				{
					var tempObject = obj.pieChart[i];
					
					legendArr[i] = tempObject.name;
				}
				
				var streetChartOption = {
				title : {
					text: '按服务类型分布图',
					//subtext: '纯属虚构',
					x:'center'
				},
				tooltip : {
					trigger: 'item',
					formatter: "{a} <br/>{b} : {c} ({d}%)"
				},
				/*legend: {
					orient: 'vertical',
					left: 'left',
					data: legendArr//['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
				},*/
				series : [
					{
						//name: '访问来源',
						type: 'pie',
						radius : '55%',
						center: ['50%', '60%'],
						data:obj.pieChart/*[
							{value:335, name:'直接访问'},
							{value:310, name:'邮件营销'},
							{value:234, name:'联盟广告'},
							{value:135, name:'视频广告'},
							{value:1548, name:'搜索引擎'}
						]*/,
						itemStyle: {
							emphasis: {
								shadowBlur: 10,
								shadowOffsetX: 0,
								shadowColor: 'rgba(0, 0, 0, 0.5)'
							}
						}
					}
				]};
				
				streetChart = echarts.init(document.getElementById('streetChart'));
				streetChart.setOption(streetChartOption);
			}
			else
			{
				$("#streetChartDiv").removeClass().addClass("col-lg-12");
				$("#streetTableDiv").removeClass().addClass("col-lg-12");
				
				var legendArr = [];
				var streetArr = [];
				var dataArr = [];
				//debugger;
				for(var i=0;obj.multiStreet != null && i<obj.multiStreet.length;i++)
				{
					var dataObj = {type: 'bar',stack: '总量',
							label: {
								normal: {
									show: false,
									position: 'insideRight'
								}
							},
							data:[]
					};
					var tmpObject = obj.multiStreet[i];
					
					for ( var p in tmpObject )
					{
						if ( typeof ( tmpObject [ p ]) == " function " )
						{
						}
						else 
						{
							if(p == 'A')
							{
								legendArr[legendArr.length] = tmpObject [ p];
								dataObj.name = tmpObject [ p];
							} 
							else
							{
								if(i == 0)
								{
									streetArr[streetArr.length] = p;								
								}
								dataObj.data[dataObj.data.length] = tmpObject [ p];
							}
						} 
					}
					dataArr[dataArr.length] = dataObj;
				}
				
				
				streetChartOption = {
					tooltip : {
						trigger: 'axis',
						axisPointer : {            // 坐标轴指示器，坐标轴触发有效
							type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
						}
					},
					legend: {
						data: legendArr//['直接访问', '邮件营销','联盟广告','视频广告','搜索引擎']
					},
					grid: {
						left: '3%',
						right: '4%',
						bottom: '10%',
						containLabel: true
					},
					yAxis:  {
						type: 'value'
					},
					xAxis: {
						axisLabel: {  
						   interval:0,  
						   rotate:40  
						},  
						type: 'category',
						data: streetArr//['周一','周二','周三','周四','周五','周六','周日']
					},
					series: dataArr/*[
						{
							name: '直接访问',
							type: 'bar',
							stack: '总量',
							label: {
								normal: {
									show: true,
									position: 'insideRight'
								}
							},
							data: [320, 302, 301, 334, 390, 330, 320]
						},
						{
							name: '邮件营销',
							type: 'bar',
							stack: '总量',
							label: {
								normal: {
									show: true,
									position: 'insideRight'
								}
							},
							data: [120, 132, 101, 134, 90, 230, 210]
						},
						{
							name: '联盟广告',
							type: 'bar',
							stack: '总量',
							label: {
								normal: {
									show: true,
									position: 'insideRight'
								}
							},
							data: [220, 182, 191, 234, 290, 330, 310]
						},
						{
							name: '视频广告',
							type: 'bar',
							stack: '总量',
							label: {
								normal: {
									show: true,
									position: 'insideRight'
								}
							},
							data: [150, 212, 201, 154, 190, 330, 410]
						},
						{
							name: '搜索引擎',
							type: 'bar',
							stack: '总量',
							label: {
								normal: {
									show: true,
									position: 'insideRight'
								}
							},
							data: [820, 832, 901, 934, 1290, 1330, 1320]
						}
					]*/
				};
				streetChart = echarts.init(document.getElementById('streetChart'));
				streetChart.setOption(streetChartOption);
			}
			
			
			var streetTblContent = '<table class="table table-bordered table-striped table-condensed">';
			for(var i=0;i<obj.table.length;i++)
			{
				var tmpObject = obj.table[i];
				if(i == 0)
				{
					var tableHead = '<tr>';
					for ( var p in tmpObject )
					{
					  if ( typeof ( tmpObject [ p ]) == " function " )
					  {
					  } 
					  else 
					  {
						  if(p == 'A')
							  p = '所属街道';
						tableHead += '<th class="text-center">'+p+'</th>';
					  } 
					}
					tableHead += '</tr>';					
					streetTblContent += tableHead;
				}
				
				var record = '<tr>';
				for ( var p in tmpObject )
				{
				  if ( typeof ( tmpObject [ p ]) == " function " )
				  {
					  
				  } 
				  else 
				  { 
					record += '<td>'+tmpObject [ p]+'</td>';
				  } 
				}
				record += '</tr>';
				streetTblContent += record;
			}
			streetTblContent += '</table>';
					
			$('#streetTbl').html(streetTblContent);
			var iHeight = 300 > 40+obj.table.length*40?300:40+obj.table.length*40;
			$("#streetTbl").height(iHeight);
	});	
}
	

function dataSearch()
{
	loadEntData();
}
