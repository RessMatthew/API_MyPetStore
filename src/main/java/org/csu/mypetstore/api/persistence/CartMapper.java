package org.csu.mypetstore.api.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.mypetstore.api.entity.Cart;
import org.springframework.stereotype.Repository;

@Repository
public interface CartMapper extends BaseMapper<Cart> {
}
