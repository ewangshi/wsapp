package cn.net.gwbnsh.openapi.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.net.gwbnsh.framework.BaseController;
import cn.net.gwbnsh.framework.Result;

@Controller
@RequestMapping("")
public class IndexController extends BaseController {

	@RequestMapping("/upload.json")
	@ResponseBody
	public void upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse resp)
			throws IOException {
		Result result = new Result();

		File desFile = new File("C:\\file\\" + new Date().getTime() + ".jpg");

		FileUtils.copyInputStreamToFile(file.getInputStream(), desFile);

		super.outputJSON(result);
	}
}
