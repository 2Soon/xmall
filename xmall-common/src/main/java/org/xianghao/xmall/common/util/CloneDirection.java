package org.xianghao.xmall.common.util;

/**
 * 克隆方向
 * @author xianghao
 *
 */
public class CloneDirection {

	/**
	 * 正向克隆：从VO->DTO，DTO->DO
	 */
	public static final Integer FORWARD = 1;
	/**
	 * 反向克隆：从DO->DTO，DTO->VO
	 */
	public static final Integer OPPOSITE = 2;
	
	private CloneDirection() {
		
	}
	
}
