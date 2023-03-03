package org.xianghao.xmall.membership.service;

import org.xianghao.xmall.membership.domain.DeliveryAddressDTO;

import java.util.List;

/**
 * 收货地址管理service接口
 * @author xianghao
 *
 */
public interface DeliveryAddressService {

	/**
	 * 查询用户账号的所有收货地址
	 * @param userAccountId 用户账号id
	 * @return 所有收货地址
	 * @throws Exception
	 */
	List<DeliveryAddressDTO> listAllByUserAccountId(Long userAccountId) throws Exception;
	
	/**
	 * 新增收货地址
	 * @param deliveryAddress 收货地址
	 * @throws Exception
	 */
	void save(DeliveryAddressDTO deliveryAddress) throws Exception;
	
	/**
	 * 更新收货地址
	 * @param deliveryAddress 收货地址
	 * @throws Exception
	 */
	void update(DeliveryAddressDTO deliveryAddress) throws Exception; 
	
	/**
	 * 删除收货地址
	 * @param id 收货地址id
	 * @throws Exception
	 */
	void remove(Long id) throws Exception;
	
}
