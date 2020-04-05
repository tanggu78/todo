<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>TODO</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="/resources/images/icons/favicon.ico">
    <link rel="apple-touch-icon" href="/resources/images/icons/favicon.png">
    <link rel="apple-touch-icon" sizes="72x72" href="/resources/images/icons/favicon-72x72.png">
    <link rel="apple-touch-icon" sizes="114x114" href="/resources/images/icons/favicon-114x114.png">
    <!--Loading bootstrap css-->
    <link type="text/css" rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,400,300,700">
    <link type="text/css" rel="stylesheet" href="http://fonts.googleapis.com/css?family=Oswald:400,700,300">
    <link type="text/css" rel="stylesheet" href="/resources/styles/jquery-ui-1.12.1.custom.min.css">
    <link type="text/css" rel="stylesheet" href="/resources/styles/font-awesome.min.css">
    <link type="text/css" rel="stylesheet" href="/resources/styles/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="/resources/styles/animate.css">
    <link type="text/css" rel="stylesheet" href="/resources/styles/all.css">
    <link type="text/css" rel="stylesheet" href="/resources/styles/main.css">
    <link type="text/css" rel="stylesheet" href="/resources/styles/style-responsive.css">
    <link type="text/css" rel="stylesheet" href="/resources/styles/zabuto_calendar.min.css">
    <link type="text/css" rel="stylesheet" href="/resources/styles/pace.css">
    <link type="text/css" rel="stylesheet" href="/resources/styles/jquery.news-ticker.css">
    <link type="text/css" rel="stylesheet" href="/resources/styles/jplist-custom.css">
    
    <link type="text/css" rel="stylesheet" href="//cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">
     
  <style>
  #sortable1, #sortable2 {
    border: 1px solid #eee;
    width: 100%;
    min-height: 20px;
    list-style-type: none;
    margin: 0;
    padding: 5px 0 0 0;
    float: left;
    margin-right: 10px;
  }
  #sortable1 li, #sortable2 li {
    margin: 0 0px 2px 0px;
    padding: 5px;
    font-size: 0.7em;
  }
  .tbl-item {
  	cursor: pointer;
  }
  
  .modal-body .panel-body {
  	padding : 7px;
  }

  </style>     
     
</head>
<body>
    <div>
        <!--END THEME SETTING-->
        <!--BEGIN BACK TO TOP-->
        <a id="totop" href="#"><i class="fa fa-angle-up"></i></a>
        <!--END BACK TO TOP-->
        <!--BEGIN TOPBAR-->
        <div id="header-topbar-option-demo" class="page-header-topbar">
            <nav id="topbar" role="navigation" style="margin-bottom: 0;" data-step="3" class="navbar navbar-default navbar-static-top">
	            <div class="navbar-header">
	                <button type="button" data-toggle="collapse" data-target=".sidebar-collapse" class="navbar-toggle"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
	                <a id="logo" href="#" onclick="return false;" class="navbar-brand"><span class="fa fa-rocket"></span><span class="logo-text">TODO</span></a>
	            </div>
				<%@ include file="/WEB-INF/views/todo/news_alert.jsp" %> 
	        </nav>
	    </div>
        <!--END TOPBAR-->
        <div id="wrapper">
            <div id="page-wrapper">
                <!--END TITLE & BREADCRUMB PAGE-->
                <!--BEGIN CONTENT-->
                <div class="page-content">
                    <div id="tab-general">
                        <div class="row mbl">
                            <div class="col-lg-12">
                            	<div class="page-content">
					                <div class="row">
					                    <div class="col-lg-12">
					                        <div class="panel">
					                            <div class="panel-body">
					                                <div id="grid-layout-table-1" class="box jplist">
					                                    <div class="jplist-ios-button"><i class="fa fa-sort"></i>jPList Actions</div>
					                                    <div class="jplist-panel box panel-top">
					                                    
					                                        <select class="form-control select" id="v_todoSts" name="v_todoSts" style="margin: 10px 10px 0 0;">
					                                        	<option value="">진행상태</option>
					                                        	<option value="I">진행중</option>
					                                        	<option value="C">완료</option>
					                                        </select>
					                                        					                                    
					                                        <select class="form-control select" id="v_todoImptTyp" name="v_todoImptTyp" style="margin: 10px 10px 0 0;">
					                                        	<option value="">중요도</option>
					                                        	<option value="MNR">낮음</option>
					                                        	<option value="NOR">일반</option>
					                                        	<option value="MJR">중요</option>
					                                        </select>			
					                                        
					                                        <select class="form-control select" id="v_searchTyp" name="v_searchTyp" style="margin: 10px 10px 0 0;">
					                                        	<option value="">내용검색</option>
					                                        	<option value="NM">Todo명</option>
					                                        	<option value="CONT">Todo내용</option>
					                                        </select>

					                                        <div class="text-filter-box">
					                                            <div class="input-group" style="width:500px;">
					                                            	<span class="input-group-addon">
					                                            		<i class="fa fa-search"></i>
					                                            	</span><input type="text" value="" id="v_searchKeyword" name="v_searchKeyword" placeholder="내용 검색을 선택 후 검색어를 입력해 주세요." data-control-type="textbox" data-control-name="title-filter" data-control-action="filter" class="form-control" style="width:500px;"/>
					                                            </div>
					                                        </div>

															<button type="button" id="searchBtn" class="jplist-reset-btn btn btn-default">조회<i class="fa fa-share mls"></i></button>		
															<button type="button" id="insertBtn" class="btn btn-primary" style="float:right;">등록 <i class="fa fa-edit fa-fw mrs"></i></button>			                                        
					                                    </div>
					                                    
					                                    <div class="box text-shadow">
					                                        <table id="todoList" class="demo-tbl table table-hover table-bordered" style="margin-top:20px;">
										                        <colgroup>
										                            <col width="5%" />
										                            <col width="*" />
										                        </colgroup>					
																<thead>
																	<tr>
																		<th>번호</th>
																		<th>내용</th>
																	</tr>
																</thead>                                  
					                                        </table>
					                                    </div>
					                                </div>
					                            </div>
					                        </div>
					                    </div>
					                </div>
					            </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--END CONTENT-->
                <!--BEGIN FOOTER-->
                <div id="footer">
                    <div class="copyright">
                        <a href="http://themifycloud.com">Copyright Tanggu All rights reserved.</a></div>
                </div>
                <!--END FOOTER-->
            </div>
            <!--END PAGE WRAPPER-->
        </div>
    </div>
    
	<!-- 검색 조건 컴퍼넌트 -->
	<form id="todoFrm">
		<input type="hidden" id="todoSts" name="todoSts"/>	
		<input type="hidden" id="todoImptTyp" name="todoImptTyp"/>
		<input type="hidden" id="searchTyp" name="searchTyp"/>
		<input type="hidden" id="searchKeyword" name="searchKeyword"/>
	</form>
    
    
   <!--BEGIN MODAL CONFIG PORTLET-->
   <div id="todoRefModal" class="modal fade">
		<%@ include file="/WEB-INF/views/todo/popup_todoDetail.jsp" %> 	
   </div>
   <!--END MODAL CONFIG PORTLET-->    

    <script src="/resources/script/jquery-1.10.2.min.js"></script>
    <script src="/resources/script/jquery-migrate-1.2.1.min.js"></script>
    <script src="/resources/script/jquery-ui.js"></script>
    <script src="/resources/script/jquery.ui.touch-punch.min.js"></script> 
    <script src="/resources/script/bootstrap.min.js"></script>
    <script src="/resources/script/bootstrap-hover-dropdown.js"></script>
    <script src="/resources/script/html5shiv.js"></script>
    <script src="/resources/script/respond.min.js"></script>
    <script src="/resources/script/jquery.metisMenu.js"></script>
    <script src="/resources/script/jquery.slimscroll.js"></script>
    <script src="/resources/script/jquery.cookie.js"></script>
    <script src="/resources/script/icheck.min.js"></script>
    <script src="/resources/script/custom.min.js"></script>
    <script src="/resources/script/jquery.news-ticker.js"></script>
    <script src="/resources/script/jquery.menu.js"></script>
    <script src="/resources/script/pace.min.js"></script>
    <script src="/resources/script/holder.js"></script>
    <script src="/resources/script/responsive-tabs.js"></script>
    <script src="/resources/script/zabuto_calendar.min.js"></script>
    <script src="/resources/script/modernizr.min.js"></script>
    <script src="/resources/script/jplist.min.js"></script>
    <script src="/resources/script/jplist.js"></script>
    <script src="/resources/script/bootstrap-select.js"></script>
    <script src="/resources/script/paramValidate.js"></script>        
    <script src="//cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>    

    <!--CORE JAVASCRIPT-->
    <script src="/resources/script/main.js"></script>
	
	<!-- 화면 js -->
	    <script src="/resources/script/paramValidate.js"></script>
	<script src="/resources/js/common.js"></script>
	<script src="/resources/js/todo/todoList.js"></script>
	
</body>
</html>
