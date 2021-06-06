<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@include file="../includes/header.jsp"%>

<div class="row">
  <div class="col-lg-12">
    <h1 class="page-header">책 정보</h1>
  </div>
  <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">

      <div class="panel-heading">${book.name}</div>
      <!-- /.panel-heading -->
      <div class="panel-body">

        <div class="form-group">
          <label>내부 분류 ID</label> <input class="form-control" name='id'
                                         value='<c:out value="${book.id }"/>' readonly="readonly">
        </div>

        <div class="form-group">
          <label>글쓴이</label> <input class="form-control" name='writer'
                                    value='<c:out value="${book.writer }"/>' readonly="readonly">
        </div>

        <div class="form-group">
          <label>ISBN</label> <input class="form-control" name='isbn'
                                     value='<c:out value="${book.isbn }"/>' readonly="readonly">
        </div>

        <div class="form-group">
          <label>카테고리 명(코드)</label> <input class="form-control" name='categoryName'
                                           value='<c:out value="${book.categoryName}(${book.categoryCode})"/>' readonly="readonly">
        </div>

        <div class="form-group">
          <label>책 위치</label> <input class="form-control" name='location'
                                     value='<c:out value="${book.location }"/>' readonly="readonly">
        </div>

        <div class="form-group">
          <label>상태</label>
          <c:if test="${book.isBorrowed eq true}">
            <input class="form-control" name='isBorrowed'
                   value='대출 중' readonly="readonly">
          </c:if>

          <c:if test="${book.isBorrowed ne true}">
            <input class="form-control" name='isBorrowed'
                   value='대출 가능' readonly="readonly">
          </c:if>

        <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
          <c:if test="${book.isBorrowed eq true}">
            <label>현재 대출자</label>
            <input class="form-control" name='isBorrowed'
                   value='<c:out value="${borrowUser.memberName}"/>' readonly="readonly">
          </c:if>

          <c:if test="${fn:length(borrowLog) != 0}">
            <label>최근 10회 대출 기록</label>
            <textarea class="form-control" rows="10" name='borrowLog' readonly="readonly"><c:forEach items="${borrowLog}" var="log">${log.borrowTime}: ${log.memberName}&#10;</c:forEach></textarea>
          </c:if>

          <c:if test="${book.isBorrowed eq true}">
            <button data-oper='return' class="btn btn-default">반납처리</button>

            <form id='operForm' action="/book/return" method="get">
              <input type='hidden' id='id' name='id' value='<c:out value="${borrowUser.borrowId}"/>'>
            </form>
          </c:if>
        </sec:authorize>

        <sec:authorize access="isAuthenticated()">
          <c:if test="${book.isBorrowed ne true}">
            <button data-oper='return' id='borrow' class="btn btn-default">대출하기</button>
          </c:if>
        </sec:authorize>
      </div>
      <!--  end panel-body -->

    </div>
    <!--  end panel-body -->
  </div>
</div>


<script type="text/javascript">
  $(document).ready(function() {
    var operForm = $("#operForm");

    $("button[data-oper='return']").on("click", function(e){
      operForm.attr("action","/book/return").submit();
    });
  });


  $("#borrow").click(function(){
    // ajax 통신
    $.ajax({
      type : "GET",
      url : "/borrow/" + <c:out value="${book.id}"/>,
      beforeSend: function (xhr) {
        xhr.setRequestHeader("Content-type","application/json");
      },
      success : function(data) {
        // 응답코드 > 0000
        alert(data.message);
      },
      error : function(XMLHttpRequest, textStatus, errorThrown) {
        alert("통신 실패.")
      }
    });
  });
</script>

<%@include file="../includes/footer.jsp"%>