<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head> <c:set var="ctx" value="${pageContext.request.contextPath}"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="${ctx}/webjars/bootstrap/3.3.1/css/bootstrap.min.css"  />
        <link type="text/css" rel="stylesheet" href="${ctx}/css/common.css"  />
        <title>${param.title}</title>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${ctx}/login">JAX-RS-Jersey MVC sample</a>
                </div>
                
                <c:if test="${user ne null}">
                <div class="navbar-form navbar-right">
                    <a href="${ctx}/logout" class="btn btn-default">logout</a>
                </div>
                </c:if>
            </div>
        </nav>
        
        <div class="container" id="main_content">
            <html:errors/>
           ${param.content}
        </div>
        
        <footer class="footer">
           <div class="container">
                <p class="text-muted text-right">2015</p>
           </div>
        </footer>
    </body>
</html>
