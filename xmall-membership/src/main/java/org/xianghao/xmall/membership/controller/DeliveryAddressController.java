package org.xianghao.xmall.membership.controller;

import org.xianghao.xmall.common.util.ObjectUtils;
import org.xianghao.xmall.membership.domain.DeliveryAddressDTO;
import org.xianghao.xmall.membership.domain.DeliveryAddressVO;
import org.xianghao.xmall.membership.service.DeliveryAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 收货地址管理controller组件
 * @author xianghao
 *
 */
@RestController
@RequestMapping("/membership/delivery/address")  
public class DeliveryAddressController {
	
	private static final Logger logger = LoggerFactory.getLogger(
			DeliveryAddressController.class);

	/**
	 * 收货地址管理service组件
	 */
	@Autowired
	private DeliveryAddressService deliveryAddressService;
	
	/**
	 * 查询指定用户的收货地址
	 * @param userAccountId
	 * @return
	 */
	@GetMapping("/{userAccountId}") 
	public List<DeliveryAddressVO> listByUserAccountId(
			@PathVariable("userAccountId") Long userAccountId) {
		try {
			return ObjectUtils.convertList(
					deliveryAddressService.listAllByUserAccountId(userAccountId), 
					DeliveryAddressVO.class);
		} catch (Exception e) {
			logger.error("error", e);
			return new ArrayList<DeliveryAddressVO>();
		}
	}
	
	/**
	 * 新增收货地址
	 * @param deliveryAddress 收货地址
	 */
	@PostMapping("/")  
	public Boolean save(@RequestBody DeliveryAddressVO deliveryAddress) {
		try {
			deliveryAddressService.save(deliveryAddress.clone(DeliveryAddressDTO.class));  
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 更新收货地址
	 * @param deliveryAddress 收货地址
	 */
	@PutMapping("/{id}")   
	public Boolean update(@RequestBody DeliveryAddressVO deliveryAddress) {
		try {
			deliveryAddressService.update(deliveryAddress.clone(DeliveryAddressDTO.class));  
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 删除收货地址
	 * @param deliveryAddress 收货地址
	 */
	@DeleteMapping("/{id}")   
	public Boolean remove(@PathVariable("id") Long id) { 
		try {
			deliveryAddressService.remove(id);  
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
}
