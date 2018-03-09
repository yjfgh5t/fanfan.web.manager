<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="wyxStatisticalList" checkbox="false" pagination="true" fitColumns="true" title="点击统计" actionUrl="wyxStatisticalController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="分享表主键"  field="sid"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="查看次数"  field="sSee"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="点击次数"  field="sClick"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="分享次数"  field="sShare"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="wyxStatisticalController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="wyxStatisticalController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="wyxStatisticalController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="wyxStatisticalController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="wyxStatisticalController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/wyx/manager/wyxStatisticalList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'wyxStatisticalController.do?upload', "wyxStatisticalList");
}

//导出
function ExportXls() {
	JeecgExcelExport("wyxStatisticalController.do?exportXls","wyxStatisticalList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("wyxStatisticalController.do?exportXlsByT","wyxStatisticalList");
}

 </script>