<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="wyxShareIconList" checkbox="false" pagination="true" fitColumns="true" title="分享的图标" actionUrl="wyxShareIconController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="排序"  field="siOrder"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所在分类"  field="siType"  queryMode="single"  dictionary="wyx_share_type,id,st_name"  width="120"></t:dgCol>
   <t:dgCol title="图标路径"  field="siSrc"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否删除"  field="siDelete"  hidden="true"  queryMode="single"  dictionary="yesorno"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="wyxShareIconController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="wyxShareIconController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="wyxShareIconController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="wyxShareIconController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="wyxShareIconController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/wyx/manager/wyxShareIconList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'wyxShareIconController.do?upload', "wyxShareIconList");
}

//导出
function ExportXls() {
	JeecgExcelExport("wyxShareIconController.do?exportXls","wyxShareIconList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("wyxShareIconController.do?exportXlsByT","wyxShareIconList");
}

 </script>