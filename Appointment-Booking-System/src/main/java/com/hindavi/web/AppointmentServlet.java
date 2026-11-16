package com.hindavi.web;

import com.hindavi.dao.AppointmentDAO;
import com.hindavi.model.Appointment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

@WebServlet("/appointments")
public class AppointmentServlet extends HttpServlet {
    private final AppointmentDAO dao=new AppointmentDAO();

    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        try{
            String action=req.getParameter("action");
            if("status".equals(action)){
                dao.updateStatus(Integer.parseInt(req.getParameter("id")),req.getParameter("value"));
                resp.sendRedirect(req.getContextPath()+"/appointments?message=updated");return;
            }
            req.setAttribute("appointments",dao.findAll(req.getParameter("status")));
            req.getRequestDispatcher("/WEB-INF/views/appointments.jsp").forward(req,resp);
        }catch(Exception ex){throw new ServletException("Unable to load appointments",ex);}
    }
    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        req.setCharacterEncoding("UTF-8");
        try{
            Appointment a=new Appointment();a.setCustomerName(req.getParameter("customerName"));
            a.setEmail(req.getParameter("email"));a.setPhone(req.getParameter("phone"));
            a.setAppointmentDate(Date.valueOf(req.getParameter("appointmentDate")));
            a.setAppointmentTime(Time.valueOf(req.getParameter("appointmentTime")+":00"));
            a.setServiceName(req.getParameter("serviceName"));a.setNotes(req.getParameter("notes"));
            dao.create(a);resp.sendRedirect(req.getContextPath()+"/appointments?message=booked");
        }catch(java.sql.SQLIntegrityConstraintViolationException ex){
            req.setAttribute("error","That appointment time is already booked. Please choose another slot.");
            doGet(req,resp);
        }catch(Exception ex){throw new ServletException("Unable to book appointment. Check the form values.",ex);}
    }
}
