var prefix = "/fanfan/authorize"
$(function () {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable({
            method: 'get', // 服务器数据的请求方式 get or post
            url: prefix + "/list", // 服务器数据的加载地址
            //	showRefresh : true,
            //	showToggle : true,
            //	showColumns : true,
            iconSize: 'outline',
            toolbar: '#exampleToolbar',
            striped: true, // 设置为true会有隔行变色效果
            dataType: "json", // 服务器返回的数据类型
            pagination: true, // 设置为true会在底部显示分页条
            // queryParamsType : "limit",
            // //设置为limit则会发送符合RESTFull格式的参数
            singleSelect: false, // 设置为true将禁止多选
            // contentType : "application/x-www-form-urlencoded",
            // //发送到服务器的数据编码类型
            pageSize: 10, // 如果设置了分页，每页数据条数
            pageNumber: 1, // 如果设置了分布，首页页码
            //search : true, // 是否显示搜索框
            showColumns: false, // 是否显示内容下拉框（选择显示的列）
            sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
            queryParams: function (params) {
                var result = {
                    limit: params.limit,
                    offset: params.offset
                }

                if($("#identificationState").val()!=""){
                    result.identificationState = $("#identificationState").val()
                }
                return result;
            },
            columns: [

                {
                    field: 'customerId',
                    title: '商户Id'
                },
                {
                    field: 'authorizeState',
                    title: '授权状态',
                    formatter: function (value) {
                        switch (value) {
                            case 0:
                                return '未授权';
                            case 1:
                                return '已授权';
                        }
                    }
                },
                {
                    field: 'identificationState',
                    title: '认证状态',
                    formatter: function (value) {
                        switch (value) {
                            case 0:
                                return '未认证';
                            case 1:
                                return '认证中';
                            case 2:
                                return '认证失败';
                            case 3:
                                return '已认证待确认';
                            case 4:
                                return '已认证';
                        }
                    }
                },
                {
                    field: 'payeeId',
                    title: '收款账号'
                },
                {
                    field: 'payeeName',
                    title: '收款人姓名'
                },{
                    field: 'failRemark',
                    title: '审核失败原因'
                },
                {
                    field: 'businessLicenseDate',
                    title: '营业执照截止时间'
                },
                {
                    field: 'modifyTime',
                    title: '修改时间'
                },
                {
                    field: 'businessLicensePhoto',
                    title: '营业执照',
                    formatter: function (value,row) {
                        if(value!=null){
                            return '<img src="'+value+'" onclick="downImg(\''+value+'\',\''+row.customerId+'_营业执照\')" width="100px" />';
                        }
                        return "";
                    }
                },
                {
                    field: 'idCardPhoto',
                    title: '身份证照',
                    formatter: function (value) {
                        if(value!=null){
                            return '<img src="'+value+'" onclick="downImg(\''+value+'\')" width="100px" />';
                        }
                        return "";
                    }
                },
                {
                    field: 'shopPhoto',
                    title: '店铺门面照',
                    formatter: function (value) {
                        if(value!=null){
                            return '<img src="'+value+'" onclick="downImg(\''+value+'\')" width="100px" />';
                        }
                        return "";
                    }
                },

                {
                    title: '操作',
                    field: 'id',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                            + row.id
                            + '\')"><i class="fa fa-edit"></i></a> ';
                        var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                            + row.id
                            + '\')"><i class="fa fa-remove"></i></a> ';
                        var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
                            + row.id
                            + '\')"><i class="fa fa-key"></i></a> ';
                        return e + d;
                    }
                }]
        });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function add() {
    layer.open({
        type: 2,
        title: '增加',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/add' // iframe的url
    });
}

function edit(id) {
    layer.open({
        type: 2,
        title: '编辑',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/edit/' + id // iframe的url
    });
}

function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'id': id
            },
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}

function resetPwd(id) {
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
            url: prefix + '/batchRemove',
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

//下载图片
function downImg(src,name) {
    $("#a-save-img").attr("href",src.replace(".min","")).attr("download",name+".png");
    document.getElementById("a-save-img").click();
}