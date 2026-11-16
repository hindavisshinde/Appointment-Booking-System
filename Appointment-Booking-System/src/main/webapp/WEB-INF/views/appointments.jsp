<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*,com.hindavi.model.Appointment" %>
<% List<Appointment> appointments=(List<Appointment>)request.getAttribute("appointments"); %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width,initial-scale=1">
<title>Appointment Manager</title><link rel="stylesheet" href="<%=request.getContextPath()%>/assets/style.css"></head>
<body><main class="container"><header><div><p class="eyebrow">BOOKING CONSOLE</p><h1>Appointment Manager</h1><p class="muted">Create bookings and manage appointment statuses.</p></div></header>
<% if(request.getAttribute("error")!=null){ %><div class="alert"><%=request.getAttribute("error")%></div><% } %>
<section class="panel"><h2>Book an appointment</h2><form method="post" action="<%=request.getContextPath()%>/appointments" class="form-grid">
<label>Customer name<input required maxlength="120" name="customerName"></label>
<label>Email<input required type="email" maxlength="150" name="email"></label>
<label>Phone<input name="phone" maxlength="20"></label>
<label>Service<select name="serviceName" required><option value="">Choose service</option><option>Consultation</option><option>Follow-up</option><option>Technical Support</option><option>Other</option></select></label>
<label>Date<input required type="date" min="<%=java.time.LocalDate.now()%>" name="appointmentDate"></label>
<label>Time<select required name="appointmentTime"><option value="">Choose time</option><option>09:00</option><option>10:00</option><option>11:00</option><option>12:00</option><option>14:00</option><option>15:00</option><option>16:00</option></select></label>
<label class="wide">Notes<textarea name="notes" maxlength="500" rows="2"></textarea></label>
<div class="actions"><button type="submit">Book appointment</button></div></form></section>
<section class="panel"><div class="section-head"><h2>Appointments</h2><form method="get" class="search"><select name="status"><option value="">All statuses</option><option value="BOOKED">Booked</option><option value="CANCELLED">Cancelled</option><option value="COMPLETED">Completed</option></select><button>Filter</button></form></div>
<div class="table-wrap"><table><thead><tr><th>Customer</th><th>Service</th><th>Date</th><th>Time</th><th>Status</th><th>Update</th></tr></thead><tbody>
<% for(Appointment a:appointments){ %><tr><td><strong><%=a.getCustomerName()%></strong><br><small><%=a.getEmail()%></small></td><td><%=a.getServiceName()%></td><td><%=a.getAppointmentDate()%></td><td><%=a.getAppointmentTime()%></td><td><span class="status"><%=a.getStatus()%></span></td><td>
<% if(!"CANCELLED".equals(a.getStatus())&&!"COMPLETED".equals(a.getStatus())){ %>
<a href="<%=request.getContextPath()%>/appointments?action=status&id=<%=a.getId()%>&value=COMPLETED">Complete</a> ·
<a class="danger" data-confirm="Cancel this appointment?" href="<%=request.getContextPath()%>/appointments?action=status&id=<%=a.getId()%>&value=CANCELLED">Cancel</a>
<% }else{ %>—<% } %></td></tr><% } %>
<% if(appointments.isEmpty()){ %><tr><td colspan="6" class="empty">No appointments found.</td></tr><% } %>
</tbody></table></div></section><footer>Appointment Booking System · Java + JDBC + MySQL</footer></main>
<script src="<%=request.getContextPath()%>/assets/app.js"></script></body></html>
