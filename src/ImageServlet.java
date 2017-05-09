import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created by zhangbaoning on 2017/5/9.
 */
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //BufferedImage相当于一张纸
        BufferedImage bufferedImage = new BufferedImage(68,22,BufferedImage.TYPE_INT_BGR);
        Graphics graphics = bufferedImage.getGraphics();
        //Graphics相当于一直画笔，setColor()就是蘸取颜料。
        Color color = new Color(200,150,255);
        //背景
        graphics.setColor(color);
        graphics.fillRect(0,0,68,22);//填充指定的矩形。
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuffer stringBuffer = new StringBuffer(); //用来保存生成使用过的字符串
        Random random = new Random();
        int length = chars.length;
        int index;
        for (int i = 0; i < 4; i++) {
            index = random.nextInt(length);
            graphics.setColor(new Color(
                    random.nextInt(255),
                    random.nextInt(255),
                    random.nextInt(255)));
            graphics.drawString(chars[index]+"",i*15,18);
            stringBuffer.append(chars[i]);
        }
        //将字符串保存到session中
        req.getSession().setAttribute("pic_code",stringBuffer.toString());
        ImageIO.write(bufferedImage,"jpg",resp.getOutputStream());

    }
}
