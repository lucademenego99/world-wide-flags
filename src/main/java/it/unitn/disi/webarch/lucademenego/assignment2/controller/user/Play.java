package it.unitn.disi.webarch.lucademenego.assignment2.controller.user;

import it.unitn.disi.webarch.lucademenego.assignment2.model.FlagsBean;
import it.unitn.disi.webarch.lucademenego.assignment2.model.UserBean;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "Play", value = "/user/play")
public class Play extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FlagsBean flagsBean = (FlagsBean) request.getSession().getAttribute("flags");
        if (flagsBean == null) {
            flagsBean = new FlagsBean();
            request.getSession().setAttribute("flags", flagsBean);
        }

        request.getRequestDispatcher("/WEB-INF/play.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int score = 3;

        // Read the flags bean
        FlagsBean flagsBean = (FlagsBean) request.getSession().getAttribute("flags");
        if (flagsBean == null) {
            flagsBean = new FlagsBean();
            request.getSession().setAttribute("flags", flagsBean);
        }

        List<String> responses = new ArrayList<>();

        for (int i = 1; i < flagsBean.getFlagsToGuess().size()+1; i++) {
            String param = request.getParameter("flag" + i);
            if (param == null || param.isEmpty()) {
                request.setAttribute("error", "Please fill all the fields");
                request.getRequestDispatcher("/WEB-INF/play.jsp").forward(request, response);
                return;
            }
            if (Integer.parseInt(param) > flagsBean.getFlags().size() || Integer.parseInt(param) < 1) {
                request.setAttribute("error", "The numbers must be between 1 and " + flagsBean.getFlags().size());
                request.getRequestDispatcher("/WEB-INF/play.jsp").forward(request, response);
                return;
            }
            responses.add(param);
        }

        for (int i = 0; i < flagsBean.getFlagsToGuess().size(); i++) {
            if (!Objects.equals(flagsBean.getFlagsToGuess().get(i).snd, flagsBean.getFlags().get(Integer.parseInt(responses.get(i))-1).snd)) {
                score = 2;
            }
        }

        // Read the user bean
        UserBean userBean = (UserBean) request.getSession().getAttribute("user");
        if (userBean == null) {
            request.setAttribute("status", "401");
            request.setAttribute("errorTitle", "Unauthorized");
            request.setAttribute("error", "It seems you are not logged in anymore");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        } else {
            userBean.setScore(userBean.getScore() + score);
            response.sendRedirect("home");
        }
    }
}
