var prefix = "/his/outpatient"
$(function() {
	selectLoad();
	load();
});

function load() {
	$("#exampleTable").bootstrapTable({
        dataType: "json",
        method: 'get',
        dataType: "json",
        cache: false,
        url:prefix + "/list",
		queryParams : function(params) {
			return {
				summary_date_begin : $('#summary_date_begin').val(),
				summary_date_end : $('#summary_date_end').val(),
				hospital_name:gethospital_name()
			};
		},
        columns:[

// [
// {
// "title": "洗衣机统计表",
// "halign":"center",
// "align":"center",
// "colspan": 5
// }
// ],
            [
                {
                    field: 'subject',
                    title: "科目",
                    valign:"middle",
                    align:"center",
                    colspan: 1,
                    rowspan: 2
                },
                {
                    field: 'amount_received',
                    title: "实收金额",
                    valign:"middle",
                    align:"center",
                    colspan: 1,
                    rowspan: 2,
                    formatter:function(value,row,index){
                        return thousands(row.amount_received);
                      },
                },
                {
                    field: 'subject1',
                    title: "科目",
                    valign:"middle",
                    align:"center",
                    colspan: 1,
                    rowspan: 2
                },
                {
                    title: "实收",
                    valign:"middle",
                    align:"center",
                    colspan: 2,
                    rowspan: 1
                },
                {
                    title: "其中作废",
                    valign:"middle",
                    align:"center",
                    colspan: 2,
                    rowspan: 1
                },
                {
                    title: "其中退费(冲账)",
                    valign:"middle",
                    align:"center",
                    colspan: 2,
                    rowspan: 1
                }
            ],
            [
                {
                    field: 'received_no',
                    title: '张数',
                    valign:"middle",
                    align:"center",
                    formatter:function(value,row,index){
                        if(row.received_no>0)
                        {
                        	return row.received_no;
                        }
                        return "";
                      },
                },
                {
                    field: 'received_money',
                    title: '金额',
                    valign:"middle",
                    align:"center",
                    formatter:function(value,row,index){
                        return thousands(row.received_money);
                      },
                },
                {
                    field: 'discard_no',
                    title: '张数',
                    valign:"middle",
                    align:"center",
                    formatter:function(value,row,index){
                        if(row.discard_no>0)
                        {
                        	return row.discard_no;
                        }
                        return "";
                      },
                },
                {
                    field: 'discard_money',
                    title: '金额',
                    valign:"middle",
                    align:"center",
                    formatter:function(value,row,index){
                        return thousands(row.discard_money);
                      },
                },
                {
                    field: 'rush_accounts_no',
                    title: '张数',
                    valign:"middle",
                    align:"center",
                    formatter:function(value,row,index){
                        if(row.rush_accounts_no>0)
                        {
                        	return row.rush_accounts_no;
                        }
                        return "";
                      },
                },
                {
                    field: 'rush_accounts_money',
                    title: '金额',
                    valign:"middle",
                    align:"center",
                    formatter:function(value,row,index){
                        return thousands(row.rush_accounts_money);
                      },
                }
            ]
        ],
        onLoadSuccess(data)
        {
        	if(data.total!=0){
	        	console.log(data);
	        	$("#h_title").html(data.rows[0].title);
	        	
	        	var time=data.rows[0].summary_date_begin.substring(0,10)+'-'+ data.rows[0].summary_date_end.substring(0,10);
	        	
	        	$("#h_date").html("汇总日期："+time);
	        	$("#span_fhr").html(data.rows[0].reviewer);
	        	$("#span_zbr").html(data.rows[0].table_name);
	        	$("#span_zbrq").html(data.rows[0].table_date);
        	}
        	
        }
    })
}

function gethospital_name()
{
	var country_name = "";
	for(var i=0;i<$("#hospital_name").find("option:selected").length;i++){
	    if(i==0){
	        country_name = $("#hospital_name").find("option:selected")[i].innerText;
	    }else{
	        country_name = country_name + ',' + $("#hospital_name").find("option:selected")[i].innerText;
	    }
	}
	return country_name;
}
function reLoad() {
	// alert($('#hospital_name').selectpicker('val'));
	$('#exampleTable').bootstrapTable('refresh');
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['userId'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {
	});
}


function selectLoad() {
	var html = "";
			// 加载数据
// for (var i = 0; i < data.length; i++) {
// html += '<option value="' + data[i].type + '">' + data[i].description +
// '</option>'
// }
			html += '<option value="1">普通会员</option>';
			html += '<option value="2">月度会员</option>';
			html += '<option value="3">半年会员</option>';
			html += '<option value="4">年度会员</option>';
			$(".chosen-select").append(html);
			$(".chosen-select").chosen({
				maxHeight : 200
			});
			// 点击事件
			$('.chosen-select').on('change', function(e, params) {
				console.log(params.selected);
				var opt = {
					query : {
						userLevel : params.selected,
					}
				}
				$('#exampleTable').bootstrapTable('refresh', opt);
			});
}

// 编辑用户初始化
function edit(id) {
	layer.open({
		type : 2,
		title : '用户修改',
		maxmin : true,
		shadeClose : false,
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
// 设置首页用户
function setHome() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要添加首页的用户");
		return;
	}
	layer.confirm("确认要将'" + rows.length + "'条数据添加首页?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['userId'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/sethome',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {});
}


// 设置用户级别
function setUserLevel(userId,type){
	var httpUrl = prefix+'/setmember/'+userId+'/'+type;
	$.ajax({
		type:'get',
		url:httpUrl,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				reLoad();
// var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
// parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});
}

// 禁用-启用
function setisDisable(userId,type){
	var httpUrl = prefix+'/disable/'+userId+'/'+type;
	$.ajax({
		type:'get',
		async:false,
		url:httpUrl,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				reLoad();
// parent.reLoad();
// var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
// parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});
}

// 编辑用户初始化
function info(id) {
	layer.open({
		type : 2,
		title : '用户详情',
		maxmin : true,
		shadeClose : false,
		area : [ '800px', '520px' ],
		content : prefix + '/info/' + id // iframe的url
	});
}


function　thousands(num){
//	var reg = /^[1-9]\d*$/; 
//	 if(!reg.test(num)) 
//	 {
//		 return num;
//	 }
	if(num<=0){
		return "";
	}
    num = num.toString();   // 将输入的数字转换为字符串
    if(/^-?\d+\.?\d+$/.test(num)){  // 判断输入内容是否为整数或小数
        if(/^-?\d+$/.test(num)){    // 判断输入内容是否为整数
            num =num + ",00";   // 将整数转为精度为2的小数，并将小数点换成逗号
        }else{
            num = num.replace(/\./,',');    // 将小数的小数点换成逗号
        }
        while(/\d{4}/.test(num)){ //
            /*******************************************************************
			 * 判断是否有4个相连的数字，如果有则需要继续拆分，否则结束循环；
			 * 将4个相连以上的数字分成两组，第一组$1是前面所有的数字（负数则有符号）， 第二组第一个逗号及其前面3个相连的数字；
			 * 将第二组内容替换为“,3个相连的数字，”
			 ******************************************************************/
            num = num.replace(/(\d+)(\d{3}\,)/,'$1,$2');
        }
        num = num.replace(/\,(\d*)$/,'.$1');   // 将最后一个逗号换成小数点
    }
    return num
}
