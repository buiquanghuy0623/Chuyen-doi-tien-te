package com.codegym;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Định tuyến URL nhận request POST từ thuộc tính action="convert" của Form
@WebServlet(name = "ConverterServlet", urlPatterns = {"/convert"})
public class ConverterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Thiết lập charset UTF-8 để hiển thị Tiếng Việt không bị lỗi font
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            try {
                // 1. Lấy dữ liệu gửi từ Form qua thuộc tính 'name'
                float rate = Float.parseFloat(request.getParameter("rate"));
                float usd = Float.parseFloat(request.getParameter("usd"));
                
                // 2. Tính toán kết quả
                float vnd = rate * usd;
                
                // 3. In kết quả ra màn hình trình duyệt
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head><title>Result</title></head>");
                out.println("<body style='font-family: Arial, sans-serif; text-align: center; margin-top: 100px;'>");
                out.println("<h2 style='color: #1b2a7a;'>KẾT QUẢ CHUYỂN ĐỔI</h2>");
                out.println("<p style='font-size: 18px;'>Tỉ giá: " + rate + " VND/USD</p>");
                out.println("<p style='font-size: 18px;'>Số tiền USD: $" + usd + "</p>");
                out.println("<h3 style='color: #27ae60; font-size: 24px;'>Thành tiền VNĐ: " + vnd + " VNĐ</h3>");
                out.println("<br><a href='index.jsp' style='text-decoration: none; padding: 10px 20px; background-color: #1b2a7a; color: white; border-radius: 4px;'>Quay lại</a>");
                out.println("</body>");
                out.println("</html>");
                
            } catch (NumberFormatException e) {
                // Xử lý khi dữ liệu nhập vào không hợp lệ
                out.println("<!DOCTYPE html>");
                out.println("<html><body>");
                out.println("<h2 style='color: red; text-align: center; margin-top: 100px;'>Lỗi: Vui lòng nhập số hợp lệ!</h2>");
                out.println("<div style='text-align: center;'><a href='index.jsp'>Quay lại</a></div>");
                out.println("</body></html>");
            }
        }
    }
}
