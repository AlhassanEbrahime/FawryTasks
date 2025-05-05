package org.project;
import java.io.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;
import java.util.*;

public class ProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        String idParam = req.getParameter("id");

        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            Product product = ProductRepository.getById(id);
            if (product != null) {
                out.print(new JSONObject(product));
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.print("{\"error\":\"Product not found\"}");
            }
        } else {
            JSONArray jsonArray = new JSONArray(ProductRepository.getAll());
            out.print(jsonArray.toString());
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        JSONObject body = new JSONObject(reader.readLine());

        String name = body.getString("name");
        int quantity = body.getInt("quantity");

        Product product = new Product(0, name, quantity);
        Product created = ProductRepository.create(product);

        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.getWriter().print(new JSONObject(created));
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        BufferedReader reader = req.getReader();
        JSONObject body = new JSONObject(reader.readLine());

        String name = body.getString("name");
        int quantity = body.getInt("quantity");

        Product updatedProduct = new Product(id, name, quantity);
        boolean success = ProductRepository.update(id, updatedProduct);

        resp.setContentType("application/json");
        if (success) {
            resp.getWriter().print("{\"message\":\"Product updated\"}");
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().print("{\"error\":\"Product not found\"}");
        }
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        boolean deleted = ProductRepository.delete(id);

        resp.setContentType("application/json");
        if (deleted) {
            resp.getWriter().print("{\"message\":\"Product deleted\"}");
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().print("{\"error\":\"Product not found\"}");
        }
    }
}