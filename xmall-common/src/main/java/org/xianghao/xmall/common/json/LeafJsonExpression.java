package org.xianghao.xmall.common.json;

import com.alibaba.fastjson.JSONObject;

/**
 * 叶子表达式
 * @author xianghao
 *
 */
public class LeafJsonExpression implements JsonExpression {

	private String jsonNodeName;
	
	public LeafJsonExpression(String jsonNodeName) {
		this.jsonNodeName = jsonNodeName;
	}
	
	@Override
	public Object interpret(JsonExpressionContext context) throws Exception {
		JSONObject currentJsonNode = context.getCurrentJsonNode();
		if(currentJsonNode == null) {
			return context.getTargetJson().get(jsonNodeName);
		} else {
			return currentJsonNode.get(jsonNodeName);
		}
	}
	
}
