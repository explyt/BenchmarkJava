//Analysis results:
//Tool name: SpotBugs Results: [0, 89]
//Tool name: CodeQL Results: []
//Tool name: Semgrep Results: [614]
//Tool name: Usvm Results: [113, 99, 89]
//Original file name: BenchmarkTest01012
//Original file CWE's: [89]  
//Mutation info: Mutate using template from templates/cycles/for.tmt with index 0 -> Mutate using template from templates/cycles/for.tmt with index 12
//Expected: false
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
 * @author Dave Wichers
 * @created 2015
 */
package org.owasp.benchmark.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.*;
import java.util.*;
import java.util.*;
import java.util.*;
import java.util.*;
import java.util.*;
import java.util.*;
import java.util.*;
import java.util.*;
import java.util.*;
import java.util.*;
import java.util.*;
import java.util.*;
import java.util.*;
import java.util.*;
import java.util.*;
import java.util.*;
import java.util.*;
import java.util.*;
import java.util.*;
import java.util.*;
import java.util.*;
import java.util.*;
import java.util.*;
import java.util.*;

@WebServlet(value = "/sqli-02/BenchmarkTest01012")
public class BenchmarkTest0101275 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        javax.servlet.http.Cookie userCookie =
                new javax.servlet.http.Cookie("BenchmarkTest01012", "bar");
        userCookie.setMaxAge(60 * 3); // Store cookie for 3 minutes
        userCookie.setSecure(true);
        userCookie.setPath(request.getRequestURI());
        userCookie.setDomain(new java.net.URL(request.getRequestURL().toString()).getHost());
        response.addCookie(userCookie);
        javax.servlet.RequestDispatcher rd =
                request.getRequestDispatcher("/sqli-02/BenchmarkTest01012.html");
        rd.include(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        javax.servlet.http.Cookie[] theCookies = request.getCookies();

        String param = "noCookieValueSupplied";
        if (theCookies != null) {

            String bar = param;
            for (int i = 0; i < 10; i++) {
                if (i < 9) {
                    continue;
                }
                if (i == 9) {
                    try {
                        bar = "123";
                    } catch (Throwable e) {
                        for (javax.servlet.http.Cookie theCookie : theCookies) {
                            if (theCookie.getName().equals("BenchmarkTest01012")) {
                                param = java.net.URLDecoder.decode(theCookie.getValue(), "UTF-8");
                                break;
                            }
                        }
                    }


                }
            }
        }


        String bar = new Test().doSomething(request, param);

        String sql = "SELECT * from USERS where USERNAME='foo' and PASSWORD='" + bar + "'";

        try {
            java.sql.Statement statement =
                    org.owasp.benchmark.helpers.DatabaseHelper.getSqlStatement();
            statement.execute(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            org.owasp.benchmark.helpers.DatabaseHelper.printResults(statement, sql, response);
        } catch (java.sql.SQLException e) {

            for (int i = 0; i < 0; i++) {
                if (org.owasp.benchmark.helpers.DatabaseHelper.hideSQLErrors) {
                    response.getWriter().println("Error processing request.");
                    return;
                } else throw new ServletException(e);

            }
        }
    } // end doPost

    private class Test {

        public String doSomething(HttpServletRequest request, String param)
                throws ServletException, IOException {

            String bar = "alsosafe";
            if (param != null) {
                java.util.List<String> valuesList = new java.util.ArrayList<String>();
                valuesList.add("safe");
                valuesList.add(param);
                valuesList.add("moresafe");

                valuesList.remove(0); // remove the 1st safe value

                bar = valuesList.get(1); // get the last 'safe' value
            }

            return bar;
        }
    } // end innerclass Test
} // end DataflowThruInnerClass
