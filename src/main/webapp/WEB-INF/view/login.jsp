<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags/" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:import url="/WEB-INF/template/layout.jsp">
<c:param name="title" value="login"/>
<c:param name="content">
    <h1>login</h1>
    <form action="login" styleClass="form" method="POST">
        <div class="form-group">
            <tag:bs_input key="id" label="ID">
                <input type="text" id="id" name="id" class="form-control" />
            </tag:bs_input>
            <tag:bs_input key="password" label="password">
                <input type="password" id="password" name="password" class="form-control" />
            </tag:bs_input>
        </div>
        <input type="submit" value="Login" styleClass="btn btn-primary"/>
    </form>
    
</c:param>
</c:import>