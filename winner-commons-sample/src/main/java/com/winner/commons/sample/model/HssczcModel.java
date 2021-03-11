/**
 * <p>Copyright:Copyright(c) 2021</p>
 * <p>Company:Professional</p>
 * <p>Package:com.winnerinf.modules.word.sample.model</p>
 * <p>File:SampleModel.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 2021年2月7日 下午1:24:55
 */
package com.winner.commons.sample.model;

import com.winner.commons.word.poitl.data.ColspanCells;
import com.winner.commons.word.poitl.data.ColspanRows;
import com.winner.commons.word.poitl.model.WordDataModel;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date 2021年2月7日 下午1:24:55
 * @version 1.0.2021
 */
@SuppressWarnings("serial")
public class HssczcModel extends WordDataModel {

	/** serialVersionUID(long):. */
	private static final long serialVersionUID = 1L;

	{
		putData("XM", "张三");
		putData("SQRQN", "2021");
		putData("SQRQY", "2");
		putData("SQRQR", "20");

		putData("ZYXXJL", ColspanRows.create(
			ColspanCells.of().put("ZYXXJL_XXKSRQ", "2016-02-18").put("ZYXXJL_XXJZRQ", "2020-02-18").put("ZYXXJL_SDXX", "同济大学附属第二医科大学").put("ZYXXJL_ZYMC", "临床护理").put("ZYXXJL_QDDXL", "本科").create(),
			ColspanCells.of().put("ZYXXJL_XXKSRQ", "2016-02-18").put("ZYXXJL_XXJZRQ", "2020-02-18").put("ZYXXJL_SDXX", "同济大学附属第二医科大学").put("ZYXXJL_ZYMC", "临床护理").put("ZYXXJL_QDDXL", "本科").create()
		));
	}
}
