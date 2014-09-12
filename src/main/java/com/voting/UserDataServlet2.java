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
@WebServlet("/UserDataServlet2")
public class UserDataServlet2 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public UserDataServlet2() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
                UserDataService2 usrObj = new UserDataService2();
		List<Voting2> records2 = usrObj.getVotings2();
		
		DataTableObject2 dataTableObject2 = new DataTableObject2();
               dataTableObject2.setiTotalDisplayRecords(records2.size());
               dataTableObject2.setiTotalRecords(records2.size());
               dataTableObject2.setAaData(records2);

               Gson gson2 = new GsonBuilder().setPrettyPrinting().create();
             String json2 = gson2.toJson(dataTableObject2);
          
             response.getWriter().print(json2);
          
		
	}
    

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
