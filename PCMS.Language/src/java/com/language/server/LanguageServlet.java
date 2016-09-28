/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.language.server;

import com.pcms.helper.LogHelper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.pcms.http.helper.HttpHelper;
import java.lang.reflect.InvocationTargetException;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;

/**
 *
 * @author wx.pan
 */
@WebServlet(name = "LanguageServlet", urlPatterns = {"/Server"})
public class LanguageServlet extends HttpServlet {

    protected final String _server;

    protected final Log _log;

    public LanguageServlet() {
        this._server = "http://localhost:8080/PCMS/Language/";
        _log = LogHelper.getLog(this.getClass());
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String content)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(content);
        }
    }

    public void initLanguge(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String type = request.getParameter("type");
        String url = String.format("%s/init/%s", this._server, type);

        String result = HttpHelper.httpGetForString(url);
        _log.info(result);
        processRequest(request, response, result);
    }

    protected JSONObject getParam(HttpServletRequest request) {
        JSONObject param = new JSONObject();
        param.put("name", request.getParameter("name"));
        param.put("value", request.getParameter("value"));
        return param;
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String url = String.format("%s/update/%s", this._server, request.getParameter("type"));
        String result = HttpHelper.httpPostForString(url, getParam(request));
        processRequest(request, response, result);
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String url = String.format("%s/add/%s", this._server,  request.getParameter("type"));
        String result = HttpHelper.httpPostForString(url, getParam(request));
        _log.info(result);
        processRequest(request, response, result);
    }

    protected void call(HttpServletRequest request, HttpServletResponse response) {
        String method = request.getParameter("method");
        try {
            this.getClass().getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class).invoke(this, request, response);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            _log.error(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.call(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.call(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
