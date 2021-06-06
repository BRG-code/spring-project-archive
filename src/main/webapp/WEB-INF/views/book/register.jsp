<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>


<div class="row">
  <div class="col-lg-12">
    <h1 class="page-header">책 추가</h1>
  </div>
  <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">

      <div class="panel-heading"></div>
      <!-- /.panel-heading -->
      <div class="panel-body">

        <form role="form" action="/book/register" method="post">
          <div class="form-group">
            <label>책 제목</label>
            <input class="form-control" name='name'/>
          </div>

          <div class="form-group">
            <label>ISBN</label>
            <input class="form-control" name='isbn'/>
          </div>

          <div class="form-group">
            <label>카테고리</label>
            <select name='category'>
              <c:forEach items="${categories}" var="category">
                <option value="${category.code}"
                        <c:out value="${category.code}"/>>${category.code} - ${category.name}</option>
              </c:forEach>
            </select>
          </div>

          <div class="form-group">
            <label>소재 위치</label>
            <select name='location'>
              <c:forEach items="${locations}" var="location">
                <option value="${location.id}"
                        <c:out value="${location.id}"/>> ${location.name}</option>
              </c:forEach>
            </select>
          </div>

          <div class="form-group">
            <label>글쓴이</label>
            <input class="form-control" name='writer'/>
          </div>

          <div class="form-group">
            <label>책의 수</label>
            <input class="form-control" name='count'/>
          </div>

          <button type="submit" class="btn btn-default">제출</button>
          <button type="reset" class="btn btn-default">초기화</button>
        </form>

      </div>
      <!--  end panel-body -->

    </div>
    <!--  end panel-body -->
  </div>
  <!-- end panel -->
</div>
<!-- /.row -->
<%@include file="../includes/footer.jsp"%>
