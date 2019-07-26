package com.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.test.mapper.MfSystemRoleMapper;
import com.test.po.SystemRole;

/**
 * 测试基础案例
 * 基于xml
 * @author Administrator
 *
 */
public class TestClass {

	private SqlSessionFactory sqlSessionFactory;
	
	@Before
	public void init() throws IOException{
		/**
		 * 加载全局配置文件，同时把映射文件也加载了
		 * 读取SqlMapConfig.xml
		 */
		String sqlMapConfig = "mybatis/SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(sqlMapConfig);
		//sqlsessionFactory需要通过sqlsessionFactoryBuilder读取全局配置文件信息
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
	}
	@Test
	public void executeInfer(){
		//创建mapper对象,通过sqlsessionfactory工厂创建sqlsession会话
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//找到mapper的代理对象
		MfSystemRoleMapper mapper = sqlSession.getMapper(MfSystemRoleMapper.class);
		//调用mapper下的api接口
		SystemRole systemRole = mapper.selectByPrimaryKey(70937592372031488L);
		System.out.println(systemRole.getName());
	}
	
	@Test
	public void testJsonString(){
	 String str = "我的名字（测试），.。weew/";
	 String regEx="[\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
				
		 //去除特殊符号后字符串内容：
		 System.out.println("字符串str：" + m.replaceAll(""));
	}
}
