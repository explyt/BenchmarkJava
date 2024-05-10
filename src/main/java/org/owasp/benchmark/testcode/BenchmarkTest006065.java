//Analysis results:
//Tool name: SpotBugs Results: [22, 89, 0]
//Tool name: CodeQL Results: [22, 89]
//Tool name: Semgrep Results: []
//Tool name: Usvm Results: [22, 73]
//Original file name: BenchmarkTest00606
//Original file CWE's: [89]  
//Mutation info: Insert template from templates/constructors/constructorWithInitializer.tmt with index 1 -> Insert template from templates/pathSensitivity/loops.tmt with index 10
//Expected: true
//Program:
/**
 * OWASP Benchmark Project v1.2
 *
 * <p>This file is part of the Open Web Application Security Project (OWASP) Benchmark Project. For
 * details, please see <a
 * href="https://owasp.org/www-project-benchmark/">https://owasp.org/www-project-benchmark/</a>.
 *
 * <p>The OWASP Benchmark is free software: you can redistribute it and/or modify it under the terms
 * of the GNU General Public License as published by the Free Software Foundation, version 2.
 *
 * <p>The OWASP Benchmark is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE. See the GNU General Public License for more details.
 *
 * @author Nick Sanidas
 * @created 2015
 */
package org.owasp.benchmark.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.benchmark.helpers.InstanceInitializer;

import java.util.*;
import java.util.*;
import java.io.*;

@WebServlet(value = "/sqli-01/BenchmarkTest00606")
public class BenchmarkTest006065 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String param = "";
        boolean flag = true;
        java.util.Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements() && flag) {
            String name = (String) names.nextElement();
            String[] values = request.getParameterValues(name);
            if (values != null) {
                for (int i = 0; i < values.length && flag; i++) {
                    String value = values[i];
                    try (BufferedReader br = new BufferedReader(new FileReader(request.getRemoteUser()))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            if (line.contains(request.getAuthType())) name += line;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (value.equals("BenchmarkTest00606")) {
                        param = name;
                        flag = false;
                    }
                }
            }
        }

        String bar;

        // Simple if statement that assigns param to bar on true condition
        int num = 196;
        if ((500 / 42) + num > 200) bar = param;
        else bar = "This should never happen";
        InstanceInitializer.value = param;
        InstanceInitializer ii = new InstanceInitializer();
        bar = ii.getValue();
        String sql = "INSERT INTO users (username, password) VALUES ('foo','" + bar + "')";

        try {
            java.sql.Statement statement =
                    org.owasp.benchmark.helpers.DatabaseHelper.getSqlStatement();
            int count = statement.executeUpdate(sql);
            org.owasp.benchmark.helpers.DatabaseHelper.outputUpdateComplete(sql, response);
        } catch (java.sql.SQLException e) {
            if (org.owasp.benchmark.helpers.DatabaseHelper.hideSQLErrors) {
                response.getWriter().println("Error processing request.");
                return;
            } else throw new ServletException(e);
        }
    }
}
