<%-- 
    bootStrap input
    ブートストラップ用の雛形を作る。
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="key"%>
<%@attribute name="label"%>
<%@attribute name="labelClass"%>

<%-- any content can be specified here e.g.: --%>
<div id="fg_${key}" class="form-group  ${model.hasErrors(key)  ? 'has-error' : ''}">
    <label for="${key}" class="control-label ${labelClass}" >${label}</label>
    <jsp:doBody />
    <span class="text-danger">
    <c:if test="${model.hasErrors(key)}">${model.getErrors()[key]}</c:if>
    </span>
        
</div>