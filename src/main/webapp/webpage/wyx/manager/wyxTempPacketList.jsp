<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="wyxTempPacketList" checkbox="false" pagination="true" fitColumns="true" title="红包模板" actionUrl="wyxTempPacketController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="口令"  field="tpToken"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="复制口令后文案"  field="tpCopyMsg"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="提示分享文案"  field="tpShareMsg"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否删除"  field="tpDelete"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人"  field="createId"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="wyxTempPacketController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="wyxTempPacketController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="wyxTempPacketController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="wyxTempPacketController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="wyxTempPacketController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/wyx/manager/wyxTempPacketList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'wyxTempPacketController.do?upload', "wyxTempPacketList");
}

//导出
function ExportXls() {
	JeecgExcelExport("wyxTempPacketController.do?exportXls","wyxTempPacketList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("wyxTempPacketController.do?exportXlsByT","wyxTempPacketList");
}

 </script>