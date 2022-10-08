<%--
  Created by IntelliJ IDEA.
  User: lucademenego
  Date: 07/10/22
  Time: 11:35
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>User</title>
    <%@include file="common.jsp" %>
</head>
<body>
<div class="flex h-screen w-screen items-center justify-center bg-gradient-to-br from-blue-400 via-blue-200 to-blue-400">
    <div class="flex h-[70vh] w-[80vw] flex-col rounded-md bg-white drop-shadow-md items-start lg:w-[50vw] md:h-[60vh]">
        <%@include file="header.jsp" %>
        <div class="flex h-full w-full flex-col items-center justify-evenly gap-4">
            <div class="w-4/6 text-center">
                <p class="text-gray-400">Hello <%= user.getUsername() %>, welcome to World Wide Flags</p>
            </div>
            <div class="text-center">
                <p class="text-xl tracking-widest text-gray-600">CURRENT SCORE</p>
                <p class="text-6xl tracking-widest text-gray-600"><%= user.getScore() %></p>
            </div>

            <a class="flex items-center justify-center w-full" href="<%= request.getContextPath() + "/user/play" %>">
                <button class="h-[50px] w-4/6 cursor-pointer rounded-md bg-blue-600 px-5 py-2.5 text-center text-sm font-medium text-white shadow-[0px_0px_16px_#536DFE] transition-all hover:bg-blue-700 focus:outline-none focus:ring-4 focus:ring-violet-300">
                    Play
                </button>
            </a>
        </div>
    </div>
</div>
</body>
</html>
