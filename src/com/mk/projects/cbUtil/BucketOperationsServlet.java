package com.mk.projects.cbUtil;

import java.io.IOException;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BucketOperationsServlet
 */
@WebServlet("/BucketOperationsServlet")
public class BucketOperationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
    ConnectionManager cm;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("action").equalsIgnoreCase("readDocument")) {
			String id = request.getParameter("id");
			response.getWriter().write(cm.getBucketOperations().readDocument(id));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("action").equalsIgnoreCase("upsertDocument")) {
			String id = UUID.randomUUID().toString();
			String json = request.getParameter("document");
			response.getWriter().write(cm.getBucketOperations().upsertDocument(id, json));
		}
	}
	
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("action").equalsIgnoreCase("deleteDocument")) {
			String id = request.getParameter("id");
			response.getWriter().write(cm.getBucketOperations().deleteDocument(id));
		}
	}
}
