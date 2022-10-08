<%--
  Created by IntelliJ IDEA.
  User: lucademenego
  Date: 07/10/22
  Time: 11:48
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="w-full h-[50px] bg-blue-600 rounded-t-md flex flex-row justify-between items-center p-5">
    <jsp:useBean id="user" class="it.unitn.disi.webarch.lucademenego.assignment2.model.UserBean" scope="session"/>
    <p class="text-white font-semibold">
        <%= user.getUsername() %>
    </p>
    <form action="${pageContext.servletContext.contextPath}/logout" id="logout-btn" method="get"
          class="relative px-3 m-0">
        <input type="submit" value="Logout"
               class="transition-all cursor-pointer text-white bg-transparent font-medium w-full p-0 py-1 text-center">
        <span class="absolute -bottom-1 left-0 w-0 transition-all h-[2px] bg-white"></span>
    </form>
</div>

<style>
    #logout-btn:hover span:last-child {
        width: 100%;
    }
</style>