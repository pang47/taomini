import com.taomini.util.HttpUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author chentao
 * @create 2019/9/11
 * @since 1.0.0
 */

public class TaoSDKTest {

    @Test
    public void getPayMonth(){
        Map<String, Object> param = new HashMap<>();
        param.put("month", "201909");
        System.out.println(HttpUtils.doPost("https://www.pangt.xyz/taomini_dev/record/getPayByMonth", param));
    }
}