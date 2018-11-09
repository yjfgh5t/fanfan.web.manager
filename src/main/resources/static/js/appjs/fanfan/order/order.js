var prefix = "/fanfan/order"
$(function () {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
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

                    var result =  {
                        limit: params.limit,
                        offset: params.offset
                    };

                    if($("#customerId").val()!=""){
                        result.customerId = $("#customerId").val();
                    }

                    if($("#orderNum").val()!=""){
                        result.orderNum = $("#orderNum").val();
                    }

                    if($("#orderState").val()!=""){
                        result.orderState = $("#orderState").val();
                    }

                    return result;
                },
                columns: [
                    {
                        checkbox: true
                    }, {
                        field: 'orderNum',
                        title: '订单号'
                    }, {
                        field: 'orderStateText',
                        title: '订单状态',
                    }, {
                        field: 'orderTotal',
                        title: '订单金额'
                    }, {
                        field: 'orderPay',
                        title: '支付金额'
                    }, {
                        field: 'orderDiscountTotal',
                        title: '优惠金额'
                    }, {
                        field: 'orderCommodityTotal',
                        title: '商品总数'
                    }, {
                        field: 'orderPayType',
                        title: '支付类型  ',
                        formatter: function (val) {
                            switch (val) {
                                case 1:
                                    return "支付宝";
                                case 2:
                                    return "微信";
                                case 3:
                                    return "现金";
                            }
                        }
                    }, {
                        field: 'userNick',
                        title: '下单用户'
                    }, {
                        field: 'createTime',
                        title: '下单时间'
                    }, {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm ' + s_detail_h + '" href="#" mce_href="#" title="详情" onclick="detail(\''
                                + row.id
                                + '\')"><i class="fa fa-ellipsis-h"></i></a> ';
                            return e;
                        }
                    }]
            });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

//查看详情
function detail(id) {

    var index = layer.open({
        type: 2,
        title: '详情',
        maxmin: true,
        shadeClose: true, // 点击遮罩关闭层
        area: ['80%', '70%'],
        content: prefix + '/detail/' + id // iframe的url
    });
    layer.full(index);
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