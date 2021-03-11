/**
 * <p>Copyright:Copyright(c) 2021</p>
 * <p>Company:Professional</p>
 * <p>Package:com.winner.commons.word.export.util</p>
 * <p>File:WordExportUtil.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 2021年2月22日 下午2:18:19
 */
package com.winner.commons.word.poitl.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.text.StrBuilder;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;
import com.winner.commons.word.poitl.config.WordConfig;
import com.winner.commons.word.poitl.model.WordDataModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date 2021年2月22日 下午2:18:19
 * @version 1.0.2021
 */
@Slf4j
@Component
public class WordUtil {
	
	/**
	 * 导出word模版.
	 * 
	 * @param inputStream 模版
	 * @param dataModel 数据
	 * @return 导出文件路径
	 * @author chanlong(陈龙)
	 * @date 2021年2月23日 上午10:37:26
	 */
	public File export(final InputStream inputStream, final WordDataModel dataModel) {
		String outFilepath = getOutputFilepath();
		try (FileOutputStream out = output(outFilepath)) {
			XWPFTemplate.compile(inputStream, configure).render(dataModel.getData(), out).close();
		} catch (IOException e) {
			log.error("poi-tl导出word失败：", e);
			return null;
		}
		return new File(outFilepath);
	} 
	
	/**
	 * 导出word模版.
	 * 
	 * @param file 模板
	 * @param dataModel 数据
	 * @return 导出文件路径
	 * @author chanlong(陈龙)
	 * @date 2021年2月23日 上午10:40:55
	 */
	public File export(final File file, final WordDataModel dataModel) {
		String outFilepath = getOutputFilepath(file);
		try (FileOutputStream out = output(outFilepath)) {
			XWPFTemplate.compile(file, configure).render(dataModel.getData(), out).close();
		} catch (IOException e) {
			log.error("poi-tl导出word失败：", e);
			return null;
		}
		return new File(outFilepath);
	}

	public void parse(String templateId, File file) {
		XWPFTemplate.compile(file, configure).render(new WordDataModel() {});
	}
	
	public static WordUtil of() {
		WordUtil inst = new WordUtil();
		inst.setConfigure();
		return inst;
	}
	
	public static WordUtil of(Configure configure) {
		WordUtil inst = new WordUtil();
		inst.setConfigure(configure);
		return inst;
	}
	
	public static WordUtil of(ConfigureBuilder builder) {
		WordUtil inst = new WordUtil();
		inst.setConfigure(builder);
		return inst;
	}
	
	public synchronized static ConfigureBuilder createBuilder() {
		String prefix = WordConfig.word.getGramerPrefix();
		String suffix = WordConfig.word.getGramerSuffix();
		return Configure.builder().buildGramer(prefix, suffix).useSpringEL(false);
	}
	
	private Configure configure;
	
	private void setConfigure() {
		this.configure = createBuilder().build();
	}
	
	private void setConfigure(Configure configure) {
		this.configure = configure;
	}
	
	private void setConfigure(ConfigureBuilder builder) {
		this.configure = builder.build();
	}
	
	private static FileOutputStream output(final String filepath) throws IOException {
		File outFile = FileUtil.file(filepath);
		if (!outFile.getParentFile().exists()) {
			outFile.getParentFile().mkdirs();
		}
		if (!outFile.exists()) {
			outFile.createNewFile();
		}
		return new FileOutputStream(outFile);
	}
	
	private static String getOutputFilepath(File file) {
		String filename = FileNameUtil.getName(file);
		return StrBuilder.create(WordConfig.word.getExportPath(), "/", filename).toString();
	}

	private static String getOutputFilepath() {
		return StrBuilder.create(WordConfig.word.getExportPath(), "/", DateTime.now().toString(DatePattern.PURE_DATETIME_MS_FORMAT), ".docx").toString();
	}
}
