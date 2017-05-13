import com.google.code.kaptcha.text.TextProducer;
import com.google.code.kaptcha.util.Configurable;

import java.util.Random;

/**
 * Created by zhangbaoning on 2017/5/13.
 */
public class ChineseText  extends Configurable implements  TextProducer {
    @Override
    public String getText() {
        int length = this.getConfig().getTextProducerCharLength();
        String[] s = {"张","三","李","四","王","麻","子","李"};
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            stringBuffer.append(s[random.nextInt(s.length)]);
        }
        return  stringBuffer.toString();
    }
}
