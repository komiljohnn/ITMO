package org.example;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/areaCheck")
public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем массив значений X
        String xParam = request.getParameter("x");
        String yParam = request.getParameter("y");
        String[] rValues = request.getParameterValues("r");
        Date startDate = new Date();

        // Проверяем наличие параметров
        if (xParam == null || yParam == null || rValues == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Недостаточно параметров");
            return;
        }

        // Парсим Y и R
        double y;
        double x;
        try {
            y = Double.parseDouble(yParam);
            x = Double.parseDouble(xParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Некорректные значения Y или R");
            return;
        }

        // Список новых результатов
        List<Model> newResults = new ArrayList<>();

        // Обрабатываем каждое значение X
        for (String rValue : rValues) {
            try {
                double r = Float.parseFloat(rValue);
                boolean isHit = checkHit(x, y, r);
                Model model = new Model(x, y, r, isHit);
                model.setExecutionTime(new Date().getTime() - startDate.getTime());
                newResults.add(model);
            } catch (NumberFormatException e) {
                // Если значение X некорректное, пропускаем его
                continue;
            }
        }

        List<Model> appResults = (List<Model>) getServletContext().getAttribute("results");
        if (appResults == null) {
            appResults = new ArrayList<>();
        }

        appResults.addAll(newResults);
        getServletContext().setAttribute("results", appResults);

        // Возвращаем список результатов в формате JSON
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(newResults));
    }

    private boolean checkHit(double x, double y, double r) {
        if ((x <= 0) && (y >= 0) && ((x * x + y * y) <= r * r))
            return true;
        if ((x >= 0) && (y >= 0) && (x <= r / 2) && (y <= r))
            return true;
        if ((x >= 0) && (y <= 0) && (y + r - x >= 0))
            return true;
        return false;
    }
}