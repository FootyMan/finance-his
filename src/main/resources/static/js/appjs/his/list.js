var prefix = "/his/finance"
$(function() {
	selectLoad();
	load();
});

function load() {
	$('#exampleTable').bootstrapTable({
		dataType : "json",
		method : 'get',
		dataType : "json",
		cache : false,
		url : prefix + "/list",
		queryParams : function(params) {
			return {
				pay_type : $('#pay_type').val(),
				charge_date_begin : $('#charge_date_begin').val(),
				charge_date_end : $('#charge_date_end').val(),
			};
		},
		columns : [ {
			checkbox : true
		}, {
			field : 'case_number', // 列字段名
			title : '病历号',// 列标题
			align : 'center'
		}, {
			field : 'name',
			title : '姓名',
			align : 'center'
		},
		 {
		 field : 'sex',
		 title : '性别',
		 align : 'center'
		 },
		 {
		 field : 'age',
		 title : '年龄',
		 align : 'center'
		 },
		{
			field : 'nature',
			title : '性质',
			align : 'center'
		}, {
			field : 'patient_nature',
			title : '病人性质',
			align : 'center'
		},
		 {
		 field : 'invoice_number',
		 title : '发票号',
		 align : 'center'
		 },
		 {
		 field : 'yb_number',
		 title : '医保号',
		 align : 'center',
		 },
		 {
		 field : 'pay_type',
		 title : '支付方式',
		 align : 'center'
		 },
		{
			field : 'visiting_time',
			title : '出诊时间',
			align : 'center',
            formatter:function(value,row,index){
                if(row.case_number=="合计")
                {
                	return "";
                }
                else
                {
                	return row.visiting_time;
                }
              },
		}, {
			title : '收费时间',
			field : 'charge_time',
			align : 'center',
            formatter:function(value,row,index){
                if(row.case_number=="合计")
                {
                	return "";
                }
                else
                {
                	return row.charge_time;
                }
              },
		}, 
		{
			field : 'amount',
			title : '金额',
			align : 'center'
		}, {
			field : 'phone',
			title : '手机号',
			align : 'center'
		}, 
//		{
//			field : 'department',
//			title : '科室',
//			align : 'center'
//		}, {
//			field : 'doctor',
//			title : '医生',
//			align : 'center'
//		}, {
//			field : 'diagnosis_msg',
//			title : '出诊信息',
//			align : 'center'
//		}, 
		]
	});
}
function reLoad() {
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
			ids[i] = row['id'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchPatient',
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
	}, function() {
	});
}

// 设置用户级别
function setUserLevel(userId, type) {
	var httpUrl = prefix + '/setmember/' + userId + '/' + type;
	$.ajax({
		type : 'get',
		url : httpUrl,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				reLoad();
				// var index = parent.layer.getFrameIndex(window.name); //
				// 获取窗口索引
				// parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});
}

// 禁用-启用
function setisDisable(userId, type) {
	var httpUrl = prefix + '/disable/' + userId + '/' + type;
	$.ajax({
		type : 'get',
		async : false,
		url : httpUrl,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				reLoad();
				// parent.reLoad();
				// var index = parent.layer.getFrameIndex(window.name); //
				// 获取窗口索引
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
// function memberLevelName(level)
// {
// var levelName="普通会员";
// switch(level)
// {
// case 1:
// levelName="普通会员";
// break;
// case 2:
// levelName="包月会员";
// break;
// case 3:
// levelName="半年会员";
// break;
// case 4:
// levelName="年费会员";
// break;
// default:
// levelName="普通会员";
// }
// return levelName;
// }
