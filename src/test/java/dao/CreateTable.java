package dao;

import org.junit.Test;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;

import com.zxin.mvc.auth.data.Role;
import com.zxin.mvc.auth.data.RolePermission;
import com.zxin.mvc.auth.data.User;
import com.zxin.mvc.dice.data.ChartBall;

import dao.util.DBUtil;

public class CreateTable {
	
	public static Dao dao = DBUtil.dao;
	
	@Test
	public void auth(){
//		dao.create(User.class, false);
//		dao.create(Role.class, false);
//		dao.create(RolePermission.class, false);
		dao.create(ChartBall.class, true);
//		Sql sql =Sqls.create("");
//		sql.setCallback(Sqls.callback.entities());
//		sql.setEntity(dao.getEntity(Master.class));
	}
}
