package com.ibeetl.dao.jsqlbox;

import static com.github.drinkjava2.jdbpro.JDBPRO.notNull;
import static com.github.drinkjava2.jdbpro.JDBPRO.param;
import static com.github.drinkjava2.jsqlbox.JSQLBOX.gctx;
import static com.github.drinkjava2.jsqlbox.JSQLBOX.pagin;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.drinkjava2.jsqlbox.SqlBoxContext;
import com.ibeetl.dao.common.TestServiceInterface;

public class JSqlBoxPerformanceTestService implements TestServiceInterface {
	@Autowired
	DataSource ds = null;

	int index = 1;

	@PostConstruct
	public void postProcessAfterInitialization() {
		SqlBoxContext.setGlobalSqlBoxContext(new SqlBoxContext(ds));
	}

	@Override
	public void testAdd() {
		getNewUser().insert();
	}

	@Override
	public void testUnique() {
		new DemoUser().put("id", 1).load();// included unique check
		// or gctx().entityExistById(DemoUser.class, 1);
	}

	@Override
	public void testUpdateById() {
		new DemoUser().put("id", 1, "code", "abc").update();
	}

	@Override
	public void testPageQuery() {
		new DemoUser().pageQuery(pagin(1, 10), notNull(" and code=?", "abc"));
	}

	@Override
	public void testExampleQuery() {
		List<DemoUser> result = gctx().entityFindBySample(new DemoUser().put("id", 1, "code", "abc"), " or code=?",
				param("efg"));
		if (result.get(0) == null)
			throw new RuntimeException("Example query error");
	}

	@Override
	public void testOrmQUery() {
		List<DemoOrder> list = gctx().entityAutoNet(DemoOrder.class, DemoCustomer.class)
				.pickEntityList(DemoOrder.class);
		for (DemoOrder order : list) {
			DemoCustomer customer = order.getDemoCustomer();
			if (customer == null)
				throw new RuntimeException("Orm query error");
		}
	}

	private DemoUser getNewUser() {
		DemoUser user = new DemoUser();
		user.setId(index++);
		user.setCode("abc");
		return user;
	}

}
