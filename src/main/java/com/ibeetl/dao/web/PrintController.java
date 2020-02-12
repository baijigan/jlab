package com.ibeetl.dao.web;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ibeetl.dao.BeanchmarkApplication;
import com.ibeetl.dao.common.DaoResult;

@Controller
public class PrintController {
	@RequestMapping("/index.do")
	public ModelAndView show(@RequestParam(name="target",required=false) String target) {
		String[] targets = null;
		if(target==null) {
			targets= getAllTargets();
		}else {
			targets = target.split(",");
		}
		List<DaoResult> result = getDaoResult(targets);
		ModelAndView view = new ModelAndView("/index.html");
		view.addObject("result", result);
		view.addObject("daoTypes",Arrays.asList(targets));
		view.addObject("opTypes",result.get(0).getRet().keySet());
		return view;
	}

	private String[] getAllTargets() {
		String root = BeanchmarkApplication.projectRoot + "result";
		File[] files = new File(root).listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				if(name.endsWith(".txt")) {
					return true;
				}else {
					return false;
				}
			}
			
		});
		
		String[] daoTypes = new String[files.length];
		int i=0;
		for(File file:files) {
			String fileName = getDaoType(file.getName());
			daoTypes[i++] = fileName;
		}
		return daoTypes;
	}
	
	private String getDaoType(String fileName) {
		return fileName.substring(0,fileName.length()-4);
	}
	private List<DaoResult> getDaoResult(String[] targets) {
		String root = BeanchmarkApplication.projectRoot + "result";
		List<DaoResult> list = new ArrayList<>();
		for (String target : targets) {
			DaoResult result = new DaoResult();
			result.setDaoName(target);
			Properties ps = new Properties();
			try {
				ps.load(new FileReader(new File(root, target + ".txt")));
				for (Entry<Object, Object> entry : ps.entrySet()) {
					result.getRet().put((String) entry.getKey(), Integer.valueOf((String) entry.getValue()));
				}
				list.add(result);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return list;
	}
}
