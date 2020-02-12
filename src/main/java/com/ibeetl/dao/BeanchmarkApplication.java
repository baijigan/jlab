package com.ibeetl.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.ibeetl.dao.common.TestServiceInterface;

@SpringBootApplication
public class BeanchmarkApplication {

	public static String projectRoot = System.getProperty("user.dir") + File.separator;

	@Autowired
	TestServiceInterface testService;
	@Value("${test.warmCount}")
	private int warmCount;
	@Value("${test.count}")
	private int testCount;

	@Value("${test.target}")
	private String target;

	private Map<String, Long> map = new HashMap<String, Long>();

	public static void main(String[] args) {
		SpringApplication.run(BeanchmarkApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			if (args.length==0) {
				test(warmCount, false);
				test(testCount, true);
				logFile(target);
				// 测试完后退出
				System.exit(0);
			} else {
				// 打开浏览器显示性能对比
				openExplore(args);
			}
	
		};
	}

	public void test(int time, boolean log) {
		TestRun addRun = new TestRun("testAdd",new Runnable() {

			@Override
			public void run() {
				testService.testAdd();
			}
		});
		
		
		TestRun uniqiueRun = new TestRun("testUnique",new Runnable() {

			@Override
			public void run() {
				testService.testUnique();
			}
			
		});
		
		TestRun updateByIdRun = new TestRun("testUpdateById",new Runnable() {

			@Override
			public void run() {
				testService.testUpdateById();
			}
		});
		
		TestRun pageQuerydRun = new TestRun("testPageQuery",new Runnable() {

			@Override
			public void run() {
				testService.testPageQuery();
			}
		});
		
		TestRun exampleRun = new TestRun("testExample",new Runnable() {

			@Override
			public void run() {
				testService.testExampleQuery();
				
			}
			
		});
		
		
		TestRun ormQueryRun = new TestRun("testOrmQuery",new Runnable() {

			@Override
			public void run() {
				testService.testOrmQUery();
			}
		});

		addRun.test(log, time);
		pageQuerydRun.test(log, time);
		uniqiueRun.test(log, time);
		updateByIdRun.test(log, time);
		exampleRun.test(log, time);
		ormQueryRun.test(log, time);
		

	}

	public void logFile(String target) throws IOException {
		String root = projectRoot + "result";
		new File(root).mkdirs();
		File file = new File(root, target + ".txt");
		Properties ps = new Properties();
		for (Entry<String, Long> entry : map.entrySet()) {
			ps.put(entry.getKey(), String.valueOf(getTPS(entry.getValue())));
		}
		ps.store(new FileWriter(file), target + ",total=" + testCount + " tps:");
		System.out.println(ps);
	}
	
	class TestRun{
		Runnable run = null;
		String  name = null;
		public TestRun(String name,Runnable run) { 
			this.run = run;
			this.name = name;
		}
		public void test(boolean log,int time) {
			if (log) {
				start(name);
			}
			for (int i = 0; i < time; i++) {
				try {
					run.run();
				}catch(UnsupportedOperationException ex) {
					notSupport(name);
					return;
					
				}
				
			}
			if (log) {
				end(name);
			}
		}
		
	}

	private long getTPS(Long misc) {
		if (misc == 0) {
			// 有些测试部支持，返回0
			return 0;
		}
		return 1000 * testCount / misc;
	}


	

	public void start(String key) {
		map.put(key, System.currentTimeMillis());
	}

	public void end(String key) {
		Long start = map.get(key);
		Long end = System.currentTimeMillis();
		map.put(key, end - start);
	}
	
	public void notSupport(String key) {
		map.put(key,0L);
	}

	private void openExplore(String[] args) {
		
		
		try {
			if(args.length==1&&args[0].equals("all")) {
				browse("http://127.0.0.1:8080/index.do");
			}else {
				String para = StringUtils.join(args, ",");
				browse("http://127.0.0.1:8080/index.do?target="+para);
			}
		} catch (Exception e) {
			throw new RuntimeException("无法打开浏览器", e);
		}
	}

	private static void browse(String url) throws Exception {
		// 获取操作系统的名字
		String osName = System.getProperty("os.name", "");
		if (osName.startsWith("Mac OS")) {
			// 苹果的打开方式
			Class fileMgr = Class.forName("com.apple.eio.FileManager");
			Method openURL = fileMgr.getDeclaredMethod("openURL", new Class[] { String.class });
			openURL.invoke(null, new Object[] { url });
		} else if (osName.startsWith("Windows")) {
			// windows的打开方式。
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
		} else {
			// Unix or Linux的打开方式
			String[] browsers = { "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape" };
			String browser = null;
			for (int count = 0; count < browsers.length && browser == null; count++)
				// 执行代码，在brower有值后跳出，
				// 这里是如果进程创建成功了，==0是表示正常结束。
				if (Runtime.getRuntime().exec(new String[] { "which", browsers[count] }).waitFor() == 0)
					browser = browsers[count];
			if (browser == null)
				throw new Exception("Could not find web browser");
			else
				// 这个值在上面已经成功的得到了一个进程。
				Runtime.getRuntime().exec(new String[] { browser, url });
		}
	}

}
