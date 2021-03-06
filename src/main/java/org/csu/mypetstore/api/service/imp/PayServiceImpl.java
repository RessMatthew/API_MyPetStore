package org.csu.mypetstore.api.service.imp;

import com.alipay.api.AlipayApiException;
import org.csu.mypetstore.api.service.PayService;
import org.csu.mypetstore.api.util.AlipayUtil;
import org.csu.mypetstore.api.vo.OrderVO;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Date 2022/3/16 11:48 下午
 * @Author RessMatthew
 * @Version 1.0
 **/

/* 支付服务 */
@Service(value = "alipayOrderService")
public class PayServiceImpl implements PayService {
    @Override
    public String aliPay(OrderVO orderVo) throws AlipayApiException {
        return AlipayUtil.connect(orderVo);
    }
}
