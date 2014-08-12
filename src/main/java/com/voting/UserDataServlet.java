/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.voting;




import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/UserDataServlet")
public class UserDataServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public UserDataServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
                UserDataService usrObj = new UserDataService();
		List<Voting> records = usrObj.getVotings();
		
		DataTableObject dataTableObject = new DataTableObject();
               dataTableObject.setiTotalDisplayRecords(records.size());
               dataTableObject.setiTotalRecords(records.size());
               dataTableObject.setAaData(records);

               Gson gson = new GsonBuilder().setPrettyPrinting().create();
             String json = gson.toJson(dataTableObject);
              response.getWriter().print(json);
    }

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
