/**
 * <p>Copyright:Copyright(c) 2021</p>
 * <p>Company:Professional</p>
 * <p>Package:com.winnerinf.base.controller</p>
 * <p>File:DownloadController.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 2021年2月23日 下午2:08:37
 */
package com.winner.commons.base.controller;

import cn.hutool.core.io.FileUtil;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date 2021年2月23日 下午2:08:37
 * @version 1.0.2021
 */
public abstract class BaseDownloadController {

	@ToString
	@AllArgsConstructor
	protected enum ContentType {
		PNG("image/png"),
		GIF("image/gif"),
		JPG("image/jpeg"),
		
		HTML("text/html"),
		TEXT("text/plain"),
		
		PDF("application/pdf"),
		XML("application/xml"),
		JSON("application/json"),
		
		DOC("application/msword"),
		DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
		
		XLS("application/vnd.ms-excel"),
		XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
		
		PPT("application/application/vnd.ms-powerpoint"),
		PPTX("application/vnd.openxmlformats-officedocument.presentationml.presentation"),
		
		STREAM("application/octet-stream");

		public final String value;
	}
	
	@Autowired
	private HttpServletResponse response;
	
	protected BaseDownloadController getResponse(ContentType type, String filename) {
		response.setHeader("content-type", type.value);
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        response.setContentType(ContentType.STREAM.value);
		return this;
	}

	public OutputStream getOutputStream() throws IOException {
		return response.getOutputStream();
	}

	public void downlaod(File file) {
		try(OutputStream out = response.getOutputStream()){
			FileUtil.writeToStream(file, out);
		} catch (Exception e) {
			throw new RuntimeException("下载文件出错：", e);
		}
	}
}
