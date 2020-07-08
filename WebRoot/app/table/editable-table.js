var EditableTable = function () {
	
    return {
		
        //main function to initiate the module
        init: function () {
            function restoreRow(oTable, nRow) {
				
                var aData = oTable.fnGetData(nRow);
                //var jqTds = $('>td', nRow);

                //for (var i = 0, iLen = jqTds.length; i < iLen; i++) {
					
					//debugger;
					
                    //oTable.fnUpdate(aData[i], nRow, i, false);
					
					oTable.fnUpdate(aData.attrZHName, nRow, 0, false);
					oTable.fnUpdate(aData.attrENName, nRow, 1, false);
					oTable.fnUpdate(aData.attrComponentType, nRow, 2, false);
					oTable.fnUpdate(aData.attrValue, nRow, 3, false);
					oTable.fnUpdate(aData.attrDBType, nRow, 4, false);
					oTable.fnUpdate(aData.attrDBLength, nRow, 5, false);
					oTable.fnUpdate(aData.seq, nRow, 6, false);
					oTable.fnUpdate(aData.supportQuery, nRow, 7, false);
					oTable.fnUpdate(aData.appListDisplay, nRow, 8, false);
					//oTable.fnUpdate(aData.appDisplay, nRow, 7, false);
					//oTable.fnUpdate(aData.appMapDisplay, nRow, 8, false);
					//oTable.fnUpdate(aData.mapLabel, nRow, 9, false);
					//oTable.fnUpdate(aData.mandatory, nRow, 10, false);
					oTable.fnUpdate('<a class="edit'+uuid+' btn-info btn-xs" href=""><i class="fa fa-pencil"></i></a>&nbsp;<a class="delete'+uuid+' btn btn-danger btn-xs" href=""><i class=\"fa fa-trash-o\"></i></a>', nRow, 9, false);
					
					//oTable.fnUpdate('', nRow, 8, false);
                //}

                oTable.fnDraw();
            }
			
            function editRow(oTable, nRow) {
				
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);
				
				jqTds[0].innerHTML = '<input type="text" class="form-control small" value="' + (aData == null?'':aData.attrZHName) + '">';
				jqTds[1].innerHTML = '<input type="text" class="form-control small" value="' + (aData == null?'':aData.attrENName) + '">';
				
				//jqTds[2].innerHTML = '<input type="text" class="form-control small" value="' + aData[2] + '">';
				jqTds[2].innerHTML = '<select class="form-control m-bot15">' + 
										'<option '+(aData != null && aData.attrComponentType == '单行文本'?'selected':'')+'>单行文本</option>' +
										'<option '+(aData != null && aData.attrComponentType == '单选'?'selected':'')+'>单选</option>' +
										'<option '+(aData != null && aData.attrComponentType == '下拉'?'selected':'')+'>下拉</option>' +
										'<option '+(aData != null && aData.attrComponentType == '搜索下拉'?'selected':'')+'>搜索下拉</option>' +
										'<option '+(aData != null && aData.attrComponentType == '多选'?'selected':'')+'>多选</option>' +
										'<option '+(aData != null && aData.attrComponentType == '图片'?'selected':'')+'>图片</option>' +
										'<option '+(aData != null && aData.attrComponentType == '数字-整型'?'selected':'')+'>数字-整型</option>' +
										'<option '+(aData != null && aData.attrComponentType == '数字-浮点'?'selected':'')+'>数字-浮点</option>' +	
										'<option '+(aData != null && aData.attrComponentType == '多行文本'?'selected':'')+'>多行文本</option>' +
										'<option '+(aData != null && aData.attrComponentType == '日期'?'selected':'')+'>日期</option>' +
										'<option '+(aData != null && aData.attrComponentType == '日期时间'?'selected':'')+'>日期时间</option>' +
										'<option '+(aData != null && aData.attrComponentType == '时间'?'selected':'')+'>时间</option>' +
										'<option '+(aData != null && aData.attrComponentType == '字典'?'selected':'')+'>字典</option>' +
										'<option '+(aData != null && aData.attrComponentType == '表单'?'selected':'')+'>表单</option>' +
										/*'<option '+(aData != null && aData.attrComponentType == '点'?'selected':'')+'>点</option>' +
										'<option '+(aData != null && aData.attrComponentType == '线'?'selected':'')+'>线</option>' +
										'<option '+(aData != null && aData.attrComponentType == '面'?'selected':'')+'>面</option>' +*/
										'</select>';//attrComponentType
				
				jqTds[3].innerHTML = '<input type="text" class="form-control small" value="' + (aData == null?'':aData.attrValue) + '">';
				//jqTds[4].innerHTML = '<input type="text" class="form-control small" value="' + aData[4] + '">';
				
				jqTds[4].innerHTML = '<select class="form-control m-bot15">' + 
										'<option '+(aData != null && aData.attrDBType == '字符串'?'selected':'')+'>字符串</option>' +
										'<option '+(aData != null && aData.attrDBType == '数字'?'selected':'')+'>数字</option>' +
										'<option '+(aData != null && aData.attrDBType == '日期'?'selected':'')+'>日期</option>' +
										'<option '+(aData != null && aData.attrDBType == '日期时间'?'selected':'')+'>日期时间</option>' +//selected
										'</select>';
				
				jqTds[5].innerHTML = '<input type="text" class="form-control small" style="width:80px;" value="' + (aData == null?'':aData.attrDBLength) + '">';
				jqTds[6].innerHTML = '<input type="text" class="form-control small" style="width:80px;" value="' + (aData == null?'':aData.seq) + '">';
				jqTds[7].innerHTML = '<select class="form-control m-bot15">' + 
										'<option '+(aData != null && aData.supportQuery == '是'?'selected':'')+'>是</option>' +
										'<option '+(aData != null && aData.supportQuery == '否'?'selected':'')+'>否</option>' +
										'</select>';
				jqTds[8].innerHTML = '<select class="form-control m-bot15">' + 
										'<option '+(aData != null && aData.appListDisplay == '最左'?'selected':'')+'>最左</option>' +
										'<option '+(aData != null && aData.appListDisplay == '左上'?'selected':'')+'>左上</option>' +
										'<option '+(aData != null && aData.appListDisplay == '左下'?'selected':'')+'>左下</option>' +
										'<option '+(aData != null && aData.appListDisplay == '右上'?'selected':'')+'>右上</option>' +
										'<option '+(aData != null && aData.appListDisplay == '电话'?'selected':'')+'>电话</option>' +
										'<option '+(aData != null && aData.appListDisplay == '右下'?'selected':'')+'>右下</option>' +
										'</select>';

				/*jqTds[8].innerHTML = '<select class="form-control m-bot15">' + 
										'<option '+(aData != null && aData.appMapDisplay == '是'?'selected':'')+'>是</option>' +
										'<option '+(aData != null && aData.appMapDisplay == '否'?'selected':'')+'>否</option>' +
										'</select>';//appDisplay
				jqTds[9].innerHTML = '<select class="form-control m-bot15">' + 
										'<option '+(aData != null && aData.mapLabel == '是'?'selected':'')+'>是</option>' +
										'<option '+(aData != null && aData.mapLabel == '否'?'selected':'')+'>否</option>' +
										'</select>';	
				
				if(aData.attrComponentType == '图片')//不必填/一张/多张
				{
					jqTds[10].innerHTML = '<select class="form-control m-bot15">' + 
										'<option '+(aData != null && aData.mandatory == '不必填'?'selected':'')+'>不必填</option>' +
										'<option '+(aData != null && aData.mandatory == '一张'?'selected':'')+'>一张</option>' +
										'<option '+(aData != null && aData.mandatory == '多张'?'selected':'')+'>多张</option>' +
										'</select>';
				}
				else
				{
					jqTds[10].innerHTML = '<select class="form-control m-bot15">' + 
										'<option '+(aData != null && aData.mandatory == '是'?'selected':'')+'>是</option>' +
										'<option '+(aData != null && aData.mandatory == '否'?'selected':'')+'>否</option>' +
										'</select>';
				}
				*/
				var dataMode = '';
				if(aData.id == '')
					dataMode = 'new';
							
				jqTds[9].innerHTML = '<a class="save'+uuid+' btn-success btn-xs" href=""><i class=\"fa fa-save\"></i></a>&nbsp;' +
										'<a class="cancel'+uuid+' btn-danger btn-xs" href="" data-mode="'+dataMode+'"><i class=\"fa fa-mail-reply\"></i></a>';
            }

            function saveRow(oTable, nRow) {
                var jqInputs = $('input', nRow);				
				var jqSelect = $('select', nRow);				
				
                oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
                oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
                oTable.fnUpdate(jqSelect[0].value, nRow, 2, false);
                oTable.fnUpdate(jqInputs[2].value, nRow, 3, false);
				oTable.fnUpdate(jqSelect[1].value, nRow, 4, false);
				oTable.fnUpdate(jqInputs[3].value, nRow, 5, false);
				oTable.fnUpdate(jqInputs[4].value, nRow, 6, false);
				oTable.fnUpdate(jqSelect[2].value, nRow, 7, false);
				oTable.fnUpdate(jqSelect[3].value, nRow, 8, false);
				/*oTable.fnUpdate(jqSelect[3].value, nRow, 8, false);
				oTable.fnUpdate(jqSelect[4].value, nRow, 9, false);
				oTable.fnUpdate(jqSelect[5].value, nRow, 10, false);*/
                oTable.fnUpdate('<a class="edit'+uuid+' btn-info btn-xs" href=""><i class="fa fa-pencil"></i></a>&nbsp;<a class="delete'+uuid+' btn btn-danger btn-xs" href=""><i class=\"fa fa-trash-o\"></i></a>', nRow, 9, false);
                //oTable.fnUpdate('', nRow, 8, false);
                oTable.fnDraw();
            }

            function cancelEditRow(oTable, nRow) {
                var jqInputs = $('input', nRow);				
				var jqSelect = $('select', nRow);				
                oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
                oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
                oTable.fnUpdate(jqSelect[0].value, nRow, 2, false);
                oTable.fnUpdate(jqInputs[2].value, nRow, 3, false);
				oTable.fnUpdate(jqSelect[1].value, nRow, 4, false);
				oTable.fnUpdate(jqInputs[3].value, nRow, 5, false);
				oTable.fnUpdate(jqInputs[4].value, nRow, 6, false);
				oTable.fnUpdate(jqSelect[2].value, nRow, 7, false);
				oTable.fnUpdate(jqSelect[3].value, nRow, 8, false);
				/*oTable.fnUpdate(jqSelect[3].value, nRow, 8, false);
				oTable.fnUpdate(jqSelect[4].value, nRow, 9, false);
				oTable.fnUpdate(jqSelect[5].value, nRow, 10, false);*/
                oTable.fnUpdate('<a class="edit'+uuid+' btn-info btn-xs" href=""><i class="fa fa-pencil"></i></a>', nRow, 9, false);
				//oTable.fnUpdate('<a class="delete" href=""><i class=\"fa fa-trash-o\"></i></a>', nRow, 7, false);
                oTable.fnDraw();
            }

            var oTable = $('#editable-sample').dataTable({
				"processing": true,
				"bJQueryUI": false,
				"bFilter": false,
				"bStateSave":true,
				"bAutoWidth": false, //自适应宽度 
				//"sPaginationType": "full_numbers", 
				iDisplayLength: 30,
				lengthChange: false,
				"bProcessing": true, 
				"bDestroy":true,
				"bSort": false, //是否使用排序 		
				"oLanguage": { 
					"sProcessing": "正在加载中......", 
					"sLengthMenu": "每页显示 _MENU_ 条记录", 
					"sZeroRecords": "对不起，查询不到相关数据！", 
					"sInfoEmpty":"",
					"sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录", 
					"sInfoFiltered": "数据表中共为 _MAX_ 条记录", 
					"sSearch": "搜索", 
					"oPaginate":  
					{ 
						"sFirst": "首页", 
						"sPrevious": "上一页", 
						"sNext": "下一页", 
						"sLast": "末页" 
					}
				}, //多语言配置					
				"columns": [
					{ "data": "attrZHName" ,"sClass":"text-center"},
					{ "data": "attrENName" ,"sClass":"text-center"},
					{ "data": "attrComponentType" ,"sClass":"text-center"},
					{ "data": "attrValue" ,"sClass":"text-center"},
					{ "data": "attrDBType" ,"sClass":"text-center"},
					{ "data": "attrDBLength" ,"sClass":"text-center"},
					{ "data": "seq" ,"sClass":"text-center"},
					{ "data": "supportQuery" ,"sClass":"text-center"},
					{ "data": "appListDisplay" ,"sClass":"text-center"},
					{ "data": "editField" ,"sClass":"text-center"}
				],
                "aoColumnDefs": [{
                        'bSortable': false,
                        'aTargets': [0]
                    }
                ]
            });

            jQuery('#editable-sample_wrapper .dataTables_filter input').addClass("form-control medium"); // modify table search input
            jQuery('#editable-sample_wrapper .dataTables_length select').addClass("form-control xsmall"); // modify table per page dropdown

            var nEditing = null;

            $('#editable-sample_new').click(function (e) {
				
				var attributeTbl = oTable.fnGetData();
				
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
					var supportQuery = attributeTbl[i].supportQuery;
					var appListDisplay = attributeTbl[i].appListDisplay;
					//var appMapDisplay = attributeTbl[i].appMapDisplay;
					//var mapLabel = attributeTbl[i].mapLabel;
					//var mandatory = attributeTbl[i].mandatory;
					var saved = attributeTbl[i].saved;
					
					if(attrENName == undefined || attrENName == '')
					{
						jError("不能同时编辑多个字段或有字段英文名为空!",{
									VerticalPosition : 'center',
									HorizontalPosition : 'center'});
						return ;
					}
				}
				
                e.preventDefault();
                var aiNew = oTable.fnAddData(/*['', '', '', '','','',
                        '<a class="edit" href="">编辑</a>', '<a class="cancel" data-mode="new" href="">放弃</a>'
                ]*/
				{
					id:'',
					attrZHName:'',
					attrENName:'',
					attrComponentType:'',
					attrValue:'',
					attrDBType:'',
					attrDBLength:'',					
					seq:'',
					supportQuery:'',
					appListDisplay:''/*,
					mapLabel:'',
					mandatory:'',
					appMapDisplay:'',*/
				}
				);
                var nRow = oTable.fnGetNodes(aiNew[0]);
                editRow(oTable, nRow);
                nEditing = nRow;
            });

            $('#editable-sample a.delete'+uuid+'').on('click', function (e) {
                e.preventDefault();

                if (confirm("确定删除 ?") == false) {
                    return;
                }
				
                var nRow = $(this).parents('tr')[0];
                oTable.fnDeleteRow(nRow);
                //alert("Deleted! Do not forget to do some ajax to sync with backend :)");
            });

            //$('#editable-sample a.cancel'+uuid+'').on('click', function (e) {
			$('#editable-sample').on('click', 'a.cancel'+uuid,function (e) {	
				
                e.preventDefault();
                if ($(this).attr("data-mode") == "new") {
                    var nRow = $(this).parents('tr')[0];
                    oTable.fnDeleteRow(nRow);
                } else {
                    restoreRow(oTable, nEditing);
                    nEditing = null;
                }
            });

            $('#editable-sample a.edit'+uuid+'').on('click', function (e) {
				
                e.preventDefault();
                /* Get the row as a parent of the link that was clicked on */
                var nRow = $(this).parents('tr')[0];

                if (nEditing !== null && nEditing != nRow) {
                    /* Currently editing - but not this row - restore the old before continuing to edit mode */
                    restoreRow(oTable, nEditing);
                    editRow(oTable, nRow);
                    nEditing = nRow;
                } else if (nEditing == nRow && this.innerHTML =='"<i class="fa fa-save"></i>"') {
                    /* Editing this row and want to save it */
                    saveRow(oTable, nEditing);
                    nEditing = null;
                    //alert("Updated! Do not forget to do some ajax to sync with backend :)");
                } else {
                    /* No edit in progress - let's start one */
                    editRow(oTable, nRow);
                    nEditing = nRow;
                }
            });
			
			//$('#editable-sample a.save'+uuid+'').on('click', function (e) {
			$('#editable-sample').on('click', 'a.save'+uuid, function (e) {	
                e.preventDefault();
				
                /* Get the row as a parent of the link that was clicked on */
                var nRow = $(this).parents('tr')[0];

				var jqInputs = $('input', nRow);				
				var jqSelect = $('select', nRow);				
				
				if(jqInputs[0] == undefined || jqInputs[0].value == '')
				{
					jError("字段中文名不能为空!",{
								VerticalPosition : 'center',
								HorizontalPosition : 'center'});
					return ;
				}
				
				if(jqInputs[1] == undefined || jqInputs[1].value == '')
				{
					jError("字段英文名不能为空!",{
								VerticalPosition : 'center',
								HorizontalPosition : 'center'});
					return ;
				}
				
				/* Editing this row and want to save it */
				saveRow(oTable, nEditing);
				nEditing = null;
                
            });
        }

    };

}();