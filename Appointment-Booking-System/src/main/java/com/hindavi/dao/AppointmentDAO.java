package com.hindavi.dao;

import com.hindavi.model.Appointment;
import com.hindavi.util.DBUtil;
import java.sql.*;
import java.util.*;

public class AppointmentDAO {
    public List<Appointment> findAll(String status) throws SQLException {
        String sql="SELECT a.*,c.full_name,c.email,c.phone FROM appointments a JOIN customers c ON a.customer_id=c.id " +
                   "WHERE (? IS NULL OR a.status=?) ORDER BY a.appointment_date DESC,a.appointment_time DESC";
        List<Appointment> rows=new ArrayList<>();
        try(Connection c=DBUtil.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            if(status==null||status.isBlank()){ps.setNull(1,Types.VARCHAR);ps.setNull(2,Types.VARCHAR);}
            else{ps.setString(1,status);ps.setString(2,status);}
            try(ResultSet rs=ps.executeQuery()){while(rs.next())rows.add(map(rs));}
        }return rows;
    }
    public void create(Appointment a) throws SQLException {
        String customerSql="INSERT INTO customers(full_name,email,phone) VALUES(?,?,?) " +
          "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id),full_name=VALUES(full_name),phone=VALUES(phone)";
        String appointmentSql="INSERT INTO appointments(customer_id,appointment_date,appointment_time,service_name,notes) VALUES(?,?,?,?,?)";
        try(Connection c=DBUtil.getConnection()){
            c.setAutoCommit(false);
            try{
                int customerId;
                try(PreparedStatement ps=c.prepareStatement(customerSql,Statement.RETURN_GENERATED_KEYS)){
                    ps.setString(1,a.getCustomerName());ps.setString(2,a.getEmail());ps.setString(3,a.getPhone());ps.executeUpdate();
                    try(ResultSet keys=ps.getGeneratedKeys()){if(!keys.next())throw new SQLException("Could not save customer");customerId=keys.getInt(1);}
                }
                try(PreparedStatement ps=c.prepareStatement(appointmentSql)){
                    ps.setInt(1,customerId);ps.setDate(2,a.getAppointmentDate());ps.setTime(3,a.getAppointmentTime());
                    ps.setString(4,a.getServiceName());ps.setString(5,a.getNotes());ps.executeUpdate();
                }
                c.commit();
            }catch(SQLException ex){c.rollback();throw ex;}finally{c.setAutoCommit(true);}
        }
    }
    public void updateStatus(int id,String status) throws SQLException {
        if(!List.of("BOOKED","CANCELLED","COMPLETED").contains(status)) throw new SQLException("Invalid status");
        try(Connection c=DBUtil.getConnection();PreparedStatement ps=c.prepareStatement("UPDATE appointments SET status=? WHERE id=?")){
            ps.setString(1,status);ps.setInt(2,id);ps.executeUpdate();
        }
    }
    private Appointment map(ResultSet rs)throws SQLException{
        Appointment a=new Appointment();a.setId(rs.getInt("id"));a.setCustomerId(rs.getInt("customer_id"));
        a.setCustomerName(rs.getString("full_name"));a.setEmail(rs.getString("email"));a.setPhone(rs.getString("phone"));
        a.setAppointmentDate(rs.getDate("appointment_date"));a.setAppointmentTime(rs.getTime("appointment_time"));
        a.setServiceName(rs.getString("service_name"));a.setStatus(rs.getString("status"));a.setNotes(rs.getString("notes"));return a;
    }
}
