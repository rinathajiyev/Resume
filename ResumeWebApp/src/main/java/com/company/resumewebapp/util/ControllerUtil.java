package com.company.resumewebapp.util;

import javax.servlet.http.*;
import java.io.*;

public class ControllerUtil {

    public static void errorPage(HttpServletResponse resp, Exception ex){
        try {
            ex.printStackTrace();
            resp.sendRedirect("error.jsp?msg="+ex.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
