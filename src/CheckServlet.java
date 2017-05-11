import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zhangbaoning on 2017/5/11.
 */
public class CheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session获得生成的验证码字符串
        String pic_code = (String) request.getSession().getAttribute("pic_code");
        //获得从表单提交的验证码
        String check_code = request.getParameter("check_code");
        //乱码问题一定要在获得输出流之前设置编码
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        //设置以html方式输出，可以使用标签
        response.setContentType("text/html;charset=UTF-8");

        if (pic_code.equalsIgnoreCase(check_code)) {
            out.write("验证码正确");
        } else {
            out.write("验证码错误");
        }
        out.flush();
        out.close();
    }


}
