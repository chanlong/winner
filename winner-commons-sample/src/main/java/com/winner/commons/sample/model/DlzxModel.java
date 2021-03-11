/**
 * <p>Copyright:Copyright(c) 2021</p>
 * <p>Company:Professional</p>
 * <p>Package:com.winnerinf.matters.web.model.word</p>
 * <p>File:Dlzx.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 2021年2月21日 下午5:14:48
 */
package com.winner.commons.sample.model;

import com.winner.commons.word.poitl.data.ColspanCells;
import com.winner.commons.word.poitl.data.ColspanRows;
import com.winner.commons.word.poitl.model.WordDataModel;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date 2021年2月21日 下午5:14:48
 * @version 1.0.2021
 */
@SuppressWarnings("serial")
public class DlzxModel extends WordDataModel {

	/** serialVersionUID(long):. */
	private static final long serialVersionUID = 3353233298590970681L;

	{
		putData("FRXM", "张三");
		putData("SFWT", 1);
		putData("WTRXM", "李四");
		putData("CLQK", ColspanRows.create(
			ColspanCells.of().put("CLQK_CPH", "沪A9B870").put("CLQK_XSZDJRQ", "2020-02-18").put("CLQK_ZZL", "0.6").put("CLQK_HDZZL", "0.5").put("CLQK_DPRQ", "2020-02-18").put("CLQK_DJ_TEXT", "A级").put("CLQK_CLJYFW_TEXT", "运输").create(),
			ColspanCells.of().put("CLQK_CPH", "沪A9B870").put("CLQK_XSZDJRQ", "2020-02-18").put("CLQK_ZZL", "0.6").put("CLQK_HDZZL", "0.5").put("CLQK_DPRQ", "2020-02-18").put("CLQK_DJ_TEXT", "A级").put("CLQK_CLJYFW_TEXT", "运输").create()
		));
	}
}
