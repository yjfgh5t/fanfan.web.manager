$().ready(function() {
	//验证
	validateRule();

	//上传文件
    upload();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});

function update() {

	$.ajax({
		cache : true,
		type : "POST",
		url : "/fanfan/commodit/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}

function validateRule() {
	$("#signupForm").validate({
        errorPlacement:function (error,element) {
            layer.msg(error[0].innerText);
        },
        onkeyup:false,
        ignore: [],
		rules : {
            commoditTitle : {
				required : true,
                rangelength:[2,12]
			},
            commoditImg:{
				required:true,
			},
            commoditPrice:{
                required:true,
                number:true,
			},
            commoditSalePrice:{
                required:true,
                number:true,
			},
            commoditFiexNum:{
                required:true,
                number:true,
			},
            commoditRemark:{
                maxlength:50,
			}
		},
		messages : {
            commoditTitle : {
				required :"请输入商品名称",
                rangelength:"名称长度在2-12之间"
			},
            commoditImg:{
                required:"请上传商品图片",
            },
            commoditPrice:{
                numbe:'请输入商品原价',
            },
            commoditSalePrice:{
                numbe:'请输入商品售价',
            },
            commoditFiexNum:{
                number:'请输入商品最大日销量',
            },commoditRemark:{
                maxlength:'描述最大长度不能超过50个汉字',
            }
		}
	})
}

function upload() {

    layui.use('upload', function () {
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#uploadImg', //绑定元素
            url: '/common/sysFile/upload', //上传接口
            size: 1000,
            accept: 'file',
            done: function (r) {
                layer.msg(r.msg);
                $("#commoditImg").val(r.fileName);
                $("#img-commoditImg").attr("src",r.fileName);
            },
            error: function (r) {
                layer.msg(r.msg);
            }
        });
    });

}