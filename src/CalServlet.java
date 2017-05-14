import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.servlet.KaptchaServlet;
import com.google.code.kaptcha.util.Config;

import javax.imageio.ImageIO;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by zhangbaoning on 2017/5/14.
 */
/*      修改验证码长度，让输出的数字符合计算的位数
        <init-param>
            <description>验证码长度 5</description>
            <param-name>kaptcha.textproducer.char.length</param-name>
            <param-value>1</param-value>
        </init-param>
        修改生成验证码实现类
         <servlet-name>Kaptcha</servlet-name>
            <servlet-class>
            com.google.code.kaptcha.servlet.KaptchaServlet
            </servlet-class>
            修改生成验证码的字符全为数字
        <init-param>
            <param-name>kaptcha.textproducer.char.string</param-name>
            <param-value>1234567890</param-value>
        </init-param>
        */
public class CalServlet extends HttpServlet implements Servlet {
    private Properties props = new Properties();
    private Producer kaptchaProducer = null;
    private String sessionKeyValue = null;

    public CalServlet() {
    }

    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
        ImageIO.setUseCache(false);
        Enumeration initParams = conf.getInitParameterNames();

        while (initParams.hasMoreElements()) {
            String key = (String) initParams.nextElement();
            String value = conf.getInitParameter(key);
            this.props.put(key, value);
        }

        Config config = new Config(this.props);
        this.kaptchaProducer = config.getProducerImpl();
        this.sessionKeyValue = config.getSessionKey();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setDateHeader("Expires", 0L);
        resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        resp.addHeader("Cache-Control", "post-check=0, pre-check=0");
        resp.setHeader("Pragma", "no-cache");
        resp.setContentType("image/jpeg");
        String capText = this.kaptchaProducer.createText();
        //
        int number1 = Integer.parseInt(capText.substring(0,1));
        int number2 = Integer.parseInt(capText.substring(1));
        System.out.println(number1);
        System.out.println(number2);
        String sum = "" + (number1 + number2);//计算后的答案放在session中
        capText = "" + number1 + "+" + number2 + "=";//分割后的数字写在图片上
        req.getSession().setAttribute(this.sessionKeyValue, sum);
        BufferedImage bi = this.kaptchaProducer.createImage(capText);
        ServletOutputStream out = resp.getOutputStream();
        ImageIO.write(bi, "jpg", out);

        try {
            out.flush();
        } finally {
            out.close();
        }

    }

}
