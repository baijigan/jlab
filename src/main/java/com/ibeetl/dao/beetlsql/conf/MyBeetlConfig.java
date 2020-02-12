package com.ibeetl.dao.beetlsql.conf;

import java.util.Collection;

import org.beetl.core.Context;
import org.beetl.core.Function;
import org.beetl.core.GroupTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ibeetl.starter.BeetlTemplateCustomize;

@Configuration
public class MyBeetlConfig {
	 @Bean
	  public BeetlTemplateCustomize beetlTemplateCustomize(){
	    return new BeetlTemplateCustomize(){
	      public void customize(GroupTemplate groupTemplate){
	    	  groupTemplate.registerFunction("join", new JoinFunction());
	    	  groupTemplate.getConf().setStatementStart("@");
	    	  groupTemplate.getConf().setStatementEnd(null);
	      }
	    };
	  }
}

class JoinFunction implements Function{

	@Override
	public Object call(Object[] paras, Context ctx) {
		Collection<?> c = (Collection<?>)paras[0];
		StringBuilder sb = new StringBuilder();
		for(Object o:c) {
			if(o instanceof String) {
				sb.append("\"").append((String)o).append("\",");
			}else {
				sb.append(o).append(",");
			}
		}
		sb.setLength(sb.length()-1);
		return sb.toString();
	}
	
}
