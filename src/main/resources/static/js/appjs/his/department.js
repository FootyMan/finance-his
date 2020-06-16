var prefix = "/his/dep"
$(function () {
    selectLoad();
    load();
});

function load() {
    $('#exampleTable').bootstrapTable({
        dataType: "json",
        method: 'get',
        dataType: "json",
        cache: false,
        url: prefix + "/list",
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        queryParams: function (params) {
            return {
                department_name: $('#department_name').val(),
                charge_date_begin: $('#charge_date_begin').val(),
                charge_date_end: $('#charge_date_end').val(),
                countType: $('#countType').val(),
                organization_name: gethospital_name()
            };
        },

        columns: [{
            checkbox: true
        },
            {
                field: 'organization_name', // 列字段名
                title: '机构',// 列标题
                align: 'center'
            },
            {
                field: 'department_name', // 列字段名
                title: '科室',// 列标题
                align: 'center',
                sortable: true,
            }
            , {
                field: 'charge_date', // 列字段名
                title: '收费日期',// 列标题
                align: 'center',
                formatter: function (value, row, index) {
                    if (row.organization_name == "合计") {
                        return "";
                    } else {
                        return row.charge_date.substring(0, 10);
                    }
                },
            },

            {
                field: 'treatment_fee',
                title: '治疗费',
                align: 'center'
            }, {
                title: '放射费',
                field: 'radiological_fee',
                align: 'center',
            }, {
                field: 'operation_fee',
                title: '手术费',
                align: 'center'
            }, {
                field: 'materialCost',
                title: '材料费',
                align: 'center'
            }, {
                field: 'anesthesiaCost',
                title: '麻醉费',
                align: 'center'
            }, {
                field: 'inspection_fee',
                title: '检验费',
                align: 'center'
            }, {
                field: 'zjMaterialCost',
                title: '正畸材料费',
                align: 'center'
            }, {
                field: 'treatmentMedicalMaterials',
                title: '治疗医药用材',
                align: 'center'
            }, {
                field: 'checkMedicalMaterials',
                title: '检查医药用材',
                align: 'center'
            }, {
                field: 'check_fee',
                title: '检查费',
                align: 'center'
            }, {
                field: 'medical_materials',
                title: '一次性用材',
                align: 'center'
            }, {
                field: 'dental_implant_fee',
                title: '镶牙费',
                align: 'center'
            }, {
                field: 'orthodontic_fee',
                title: '正畸费',
                align: 'center'
            }, {
                field: 'planting_fee',
                title: '种植费',
                align: 'center'
            },
            // {
            //     field: 'pediatric_treatment_fee',
            //     title: '儿科治疗费',
            //     align: 'center'
            // },
            {
                field: 'plantingMaterialCost',
                title: '种植材料费',
                align: 'center'
            },
            // {
            //     field: 'subtotalMedicalTreatment',
            //     title: '医疗小计',
            //     align: 'center'
            // },
            {
                field: 'western_medicine_fee',
                title: '西药费',
                align: 'center'
            }, {
                field: 'chinese_patent_medicine',
                title: '中成药',
                align: 'center'
            },
            // {
            //     field: 'pediatric_treatment_fee2',
            //     title: '儿童治疗费',
            //     align: 'center'
            // },
            // {
            //     field: 'subtotalDrugs',
            //     title: '药品小计',
            //     align: 'center'
            // },
            {
                field: 'total_fee',
                title: '合计',
                align: 'center'
            },
            // {
            //     field: 'totalPocket',
            //     title: '自付合计',
            //     align: 'center'
            // },
            // {
            //     field: 'totalReceivables',
            //     title: '应收合计',
            //     align: 'center'
            // }
            ,],
        onLoadSuccess(data) {
            if (data != '') {
                if ($('#countType').val() == "1") {
                    $('#exampleTable').bootstrapTable('showColumn', 'charge_date');
                } else {
                    $('#exampleTable').bootstrapTable('hideColumn', 'charge_date');
                    var charge_date_begin = $('#charge_date_begin').val();
                    var charge_date_end = $('#charge_date_end').val();
                    if (charge_date_begin != '' && charge_date_end != '') {
                        $("#h_date").html("汇总日期：" + charge_date_begin + '-' + charge_date_end);
                    }
                }
                $("#span_zbr").html(data[0].table_name);
            }
        }
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
        btn: ['确定', '取消']
        // 按钮
    }, function () {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function (i, row) {
            ids[i] = row['id'];
        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            },
            url: prefix + '/batchdept',
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function () {
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
        maxHeight: 200
    });
    // 点击事件
    $('.chosen-select').on('change', function (e, params) {
        console.log(params.selected);
        var opt = {
            query: {
                userLevel: params.selected,
            }
        }
        $('#exampleTable').bootstrapTable('refresh', opt);
    });
}

// 编辑用户初始化
function edit(id) {
    layer.open({
        type: 2,
        title: '用户修改',
        maxmin: true,
        shadeClose: false,
        area: ['800px', '520px'],
        content: prefix + '/edit/' + id // iframe的url
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
        btn: ['确定', '取消']
        // 按钮
    }, function () {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function (i, row) {
            ids[i] = row['userId'];
        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            },
            url: prefix + '/sethome',
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function () {
    });
}

// 设置用户级别
function setUserLevel(userId, type) {
    var httpUrl = prefix + '/setmember/' + userId + '/' + type;
    $.ajax({
        type: 'get',
        url: httpUrl,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
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
        type: 'get',
        async: false,
        url: httpUrl,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
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

function gethospital_name() {
    var country_name = "";
    for (var i = 0; i < $("#organization_name").find("option:selected").length; i++) {
        if (i == 0) {
            country_name = $("#organization_name").find("option:selected")[i].innerText;
        } else {
            country_name = country_name + ',' + $("#organization_name").find("option:selected")[i].innerText;
        }
    }
    return country_name;
}

// 编辑用户初始化
function info(id) {
    layer.open({
        type: 2,
        title: '用户详情',
        maxmin: true,
        shadeClose: false,
        area: ['800px', '520px'],
        content: prefix + '/info/' + id // iframe的url
    });
}