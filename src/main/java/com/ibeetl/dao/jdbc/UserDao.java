package com.ibeetl.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.ibeetl.dao.beetlsql.entity.BeetlSqlUser;

@Repository
public class UserDao {

    @Autowired
    DataSource ds = null;

    public void addUser(BeetlSqlUser user) throws SQLException {
        Connection conn = null;
        try {
            conn = this.getConn();
            PreparedStatement ps = conn.prepareStatement("insert into sys_user (id,code) values (?,?)");
            ps.setInt(1, user.getId());
            ps.setString(2, user.getCode());
            ps.executeUpdate();
            ps.close();
            conn.commit();
        } finally {
            this.closeConn(conn);
        }

    }

    public BeetlSqlUser unqiue(Integer id) throws SQLException {

        Connection conn = null;
        BeetlSqlUser user = null;
        try {
            conn = this.getConn();
            PreparedStatement ps = conn.prepareStatement("select id,code from sys_user where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if (user != null) {
                    // 检查唯一性
                    throw new RuntimeException("find more");
                }
                user = new BeetlSqlUser();
                user.setId(rs.getInt("id"));
                user.setCode(rs.getString("code"));
            }

        } finally {
            this.closeConn(conn);
        }

        return user;

    }

    public void updateById(BeetlSqlUser user) throws SQLException {

        Connection conn = null;
        try {
            conn = this.getConn();
            PreparedStatement ps = conn.prepareStatement("update sys_user set code=? where id=?");
            ps.setString(1, user.getCode());
            ps.setInt(2, user.getId());
            ps.executeUpdate();
            conn.commit();

        } finally {
            this.closeConn(conn);
        }
    }

    public List<BeetlSqlUser> pageQuery(String code) throws SQLException {

        Connection conn = null;
        try {
            conn = this.getConn();
            StringBuilder countSql = new StringBuilder("select count(1) from sys_user where 1=1 ");
            if (StringUtils.isNotEmpty(code)) {
            	countSql.append(" and code=? ");
            }
            int i = 1;
            PreparedStatement countPs = conn.prepareStatement(countSql.toString());
            if (StringUtils.isNotEmpty(code)) {
            	countPs.setString(i++, code);
            }
            ResultSet countRs = countPs.executeQuery();
            countRs.next();
            int count = countRs.getInt(1);
            
            StringBuilder sql = new StringBuilder("select * from sys_user where 1=1 ");
            if (StringUtils.isNotEmpty(code)) {
                sql.append(" and code=? ");
            }
            // 设置翻页
            sql.append(" limit ?,? ");

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            i = 1;
            if (StringUtils.isNotEmpty(code)) {
                ps.setString(i++, code);
            }
            // 翻页
            ps.setInt(i++, 1);
            ps.setInt(i++, 10);
            ResultSet rs = ps.executeQuery();
            List<BeetlSqlUser> list = new ArrayList<>(10);
            while (rs.next()) {

                BeetlSqlUser user = new BeetlSqlUser();
                user.setId(rs.getInt("id"));
                user.setCode(rs.getString("code"));
                list.add(user);
            }
            
            
            
            
            
            return list;

        } finally {
            this.closeConn(conn);
        }
    }

    protected Connection getConn() throws SQLException {
        return ds.getConnection();
    }

    protected void closeConn(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

}
