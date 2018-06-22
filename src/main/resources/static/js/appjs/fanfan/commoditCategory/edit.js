$().ready(function() {
	validateRule();
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
		url : "/fanfan/commoditCategory/update",
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
		rules : {
			name : {
				required : true
			},
            order : {
                required : true,
                number:true
            }
		},
		messages : {
			name : {
				required : "请输入类别名称"
			},
            order:{
                required: "请输入排序号",
                number: "排序号必须是数字"
			}
		}
	})
}