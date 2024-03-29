package org.xianghao.xmall.membership.service.impl;

import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.common.util.ObjectUtils;
import org.xianghao.xmall.membership.dao.DeliveryAddressDAO;
import org.xianghao.xmall.membership.domain.DeliveryAddressDO;
import org.xianghao.xmall.membership.domain.DeliveryAddressDTO;
import org.xianghao.xmall.membership.service.DeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 收货地址管理service组件
 * @author xianghao
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DeliveryAddressServiceImpl implements DeliveryAddressService {

	/**
	 * 收货地址管理DAO组件
	 */
	@Autowired
	private DeliveryAddressDAO deliveryAddressDAO;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	
	/**
	 * 查询用户账号的所有收货地址
	 * @param userAccountId 用户账号id
	 * @return 所有收货地址
	 */
	@Override
	public List<DeliveryAddressDTO> listAllByUserAccountId(
			Long userAccountId) throws Exception {
		return ObjectUtils.convertList(deliveryAddressDAO.listAllByUserAccountId(userAccountId), 
				DeliveryAddressDTO.class); 
	}
	
	/**
	 * 新增收货地址
	 * @param deliveryAddress 收货地址
	 */
	@Override
	public void save(DeliveryAddressDTO deliveryAddress) throws Exception {
		deliveryAddress.setGmtCreate(dateProvider.getCurrentTime()); 
		deliveryAddress.setGmtModified(dateProvider.getCurrentTime()); 
		deliveryAddressDAO.save(deliveryAddress.clone(DeliveryAddressDO.class));   
	}
	
	/**
	 * 更新收货地址
	 * @param deliveryAddress 收货地址
	 */
	@Override
	public void update(DeliveryAddressDTO deliveryAddress) throws Exception {
		deliveryAddress.setGmtModified(dateProvider.getCurrentTime()); 
		deliveryAddressDAO.update(deliveryAddress.clone(DeliveryAddressDO.class));   
	}
	
	/**
	 * 删除收货地址
	 * @param id 收货地址id
	 */
	@Override
	public void remove(Long id) throws Exception {
		deliveryAddressDAO.remove(id);
	}
	
}
