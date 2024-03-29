package org.xianghao.xmall.membership.dao.impl;

import org.xianghao.xmall.membership.dao.DeliveryAddressDAO;
import org.xianghao.xmall.membership.domain.DeliveryAddressDO;
import org.xianghao.xmall.membership.mapper.DeliveryAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 收货地址管理DAO组件
 * @author xianghao
 *
 */
@Repository
public class DeliveryAddressDAOImpl implements DeliveryAddressDAO {

	/**
	 * 收货地址管理mapper组件
	 */
	@Autowired
	private DeliveryAddressMapper deliveryAddressMapper;
	
	/**
	 * 查询用户账号的所有收货地址
	 * @param userAccountId 用户账号id
	 * @return 所有收货地址
	 */
	@Override
	public List<DeliveryAddressDO> listAllByUserAccountId(Long userAccountId) {
		return deliveryAddressMapper.listAllByUserAccountId(userAccountId);
	}
	
	/**
	 * 新增收货地址
	 * @param deliveryAddress 收货地址
	 */
	@Override
	public void save(DeliveryAddressDO deliveryAddress) {
		deliveryAddressMapper.save(deliveryAddress); 
	}
	
	/**
	 * 更新收货地址
	 * @param deliveryAddress 收货地址
	 */
	@Override
	public void update(DeliveryAddressDO deliveryAddress) {
		deliveryAddressMapper.update(deliveryAddress);
	}
	
	/**
	 * 删除收货地址
	 * @param id 收货地址id
	 */
	@Override
	public void remove(Long id) {
		deliveryAddressMapper.remove(id);  
	}
	
}
