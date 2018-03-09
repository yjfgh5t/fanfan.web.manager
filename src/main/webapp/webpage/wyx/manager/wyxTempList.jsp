<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="wyxTempList" checkbox="false" pagination="true" fitColumns="true" title="模板表" actionUrl="wyxTempController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="模板标题"  field="tTitle"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="模板类型"  field="tType"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="缩略图"  field="tIconSrc"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="提示图"  field="tSrcarry"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否删除"  field="tDelete"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="wyxTempController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="wyxTempController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="wyxTempController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="wyxTempController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="wyxTempController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/wyx/manager/wyxTempList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'wyxTempController.do?upload', "wyxTempList");
}

//导出
function ExportXls() {
	JeecgExcelExport("wyxTempController.do?exportXls","wyxTempList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("wyxTempController.do?exportXlsByT","wyxTempList");
}

 </script>