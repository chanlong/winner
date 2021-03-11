/**
 * <p>Copyright:Copyright(c) 2021</p>
 * <p>Company:Professional</p>
 * <p>Package:com.winnerinf.modules.word.model</p>
 * <p>File:WordModel.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 2021年2月3日 下午5:10:42
 */
package com.winner.commons.word.poitl.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date 2021年2月3日 下午5:10:42
 * @version 1.0.2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class WordDataModel implements Serializable {

	/** serialVersionUID(long):. */
	private static final long serialVersionUID = 1L;

	private Map<String, Object> data = new HashMap<>();

    public Map<String, Object> getData() {
		return this.data;
	}

	public void putData(String key, Object value) {
		data.put(key, value);
	}
	
	public void forEach(BiConsumer<String, Object> action) {
		data.forEach(action);
	}

	@Override
	public String toString() {
		StringBuilder stb = new StringBuilder("{");

		data.forEach((key, value) -> stb.append(key).append("=").append(value).append(","));

		stb.delete(stb.length() - 1, stb.length());

		return stb.append("}").toString();
	}

}
