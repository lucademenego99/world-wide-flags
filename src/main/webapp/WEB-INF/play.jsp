<%--
  Created by IntelliJ IDEA.
  User: lucademenego
  Date: 07/10/22
  Time: 11:35
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Room</title>
    <%@include file="common.jsp" %>
</head>
<body>
<div class="flex h-screen w-screen items-center justify-center bg-gradient-to-br from-blue-400 via-blue-200 to-blue-400">
    <div class="flex h-[90vh] w-[80vw] flex-col items-start rounded-md bg-white drop-shadow-md lg:w-[40vw]">
        <%@include file="header.jsp" %>
        <jsp:useBean id="flags" class="it.unitn.disi.webarch.lucademenego.assignment2.model.FlagsBean" scope="session" />
        <form action="${pageContext.servletContext.contextPath}/user/play" method="post" id="submit-answers" class="flex h-full min-h-0 w-full flex-col justify-between gap-5 overflow-auto p-3">
            <div class="flex h-[220px] flex-initial flex-row flex-wrap items-center justify-center gap-5">
                <%= flags.flagsListToHTML("flex h-[40px] w-3/12 flex-row items-center justify-evenly rounded-tl-3xl rounded-br-3xl bg-gray-200 text-blue-600", "flex h-[40px] w-3/12 flex-row items-center justify-evenly rounded-tl-3xl rounded-br-3xl bg-blue-600 text-gray-200") %>
            </div>

            <div class="flex items-center">
                <div class="flex-grow border-t border-gray-400"></div>
                <span class="mx-4 flex-shrink text-gray-400">Flags</span>
                <div class="flex-grow border-t border-gray-400"></div>
            </div>

            <div class="flex flex-1 flex-col justify-around">
                <%= flags.guessListToHTML("flex flex-row justify-start items-center gap-8", "flex flex-row justify-end items-center gap-8", "w-2/6 rounded-md border border-gray-300 bg-gray-50 p-2 text-sm text-gray-900 outline-0 h-[50px] transition-all focus:border-blue-500 focus:ring-blue-500") %>
            </div>

            <!-- Error message got from the request -->
            <p class="text-sm text-red-500 mb-2"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></p>

            <div class="flex w-full flex-initial flex-col gap-1 md:flex-row md:gap-4">
                <div class="md:flex-1">
                    <button id="info-button" type="button" class="w-full cursor-pointer rounded-md border-2 border-gray-400 bg-transparent px-5 py-2.5 text-center text-sm font-medium text-gray-500 transition-all hover:bg-gray-100 focus:outline-none focus:ring-4 focus:ring-violet-300">How to play</button>
                </div>
                <div class="md:flex-1">
                    <input type="submit" value="Submit answers" class="w-full cursor-pointer rounded-md border-2 border-blue-500 bg-transparent px-5 py-2.5 text-center text-sm font-medium text-blue-500 transition-all hover:bg-blue-100 focus:outline-none focus:ring-4 focus:ring-violet-300" />
                </div>
            </div>
        </form>
    </div>
    <div id="info-modal" tabindex="-1" aria-hidden="true" class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 flex items-center justify-center w-full h-full bg-[#000000cc]">
        <div class="p-4 w-full max-w-2xl h-auto">
            <!-- Modal content -->
            <div class="relative bg-white rounded-lg shadow">
                <!-- Modal header -->
                <div class="flex justify-between items-start p-4 rounded-t border-b">
                    <h3 class="text-xl font-semibold text-gray-900">
                        How to Play World Wide Flags
                    </h3>
                    <button onclick="document.getElementById('info-modal').style.display = 'none'" type="button" class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm p-1.5 ml-auto inline-flex items-center">
                        <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z" clip-rule="evenodd"></path></svg>
                        <span class="sr-only">Close modal</span>
                    </button>
                </div>
                <!-- Modal body -->
                <div class="p-6 space-y-6">
                    <p class="text-base leading-relaxed text-gray-500">
                        The game is simple. You are presented various capitals, and you need to associate the respective capital with three flags of states.
                    </p>
                    <p class="text-base leading-relaxed text-gray-500">
                        In each input field you are required to put the number seen in the list on top of the page.
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    // Show the modal "How to play" when clicking on the info-button
    document.getElementById("info-button").onclick = () => {
        document.getElementById("info-modal").style.display = "flex";
    }
</script>

</body>
</html>
