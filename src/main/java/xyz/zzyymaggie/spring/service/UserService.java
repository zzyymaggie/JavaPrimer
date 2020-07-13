package xyz.zzyymaggie.spring.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import xyz.zzyymaggie.spring.dao.UserDao;
import xyz.zzyymaggie.spring.model.User;

public class UserService implements InitializingBean, DisposableBean {

	private UserDao userDAO;

	@PostConstruct
	public void init() {
		System.out.println("init");
	}

	public void add(User user) {
		userDAO.save(user);
	}

	public UserDao getUserDAO() {
		return userDAO;
	}

	@Autowired
	public void setUserDAO(@Qualifier("userDaoImpl") UserDao userDAO) {
		this.userDAO = userDAO;
	}


	@PostConstruct
    public void postConstruct() {
        System.out.println("[初始化步骤]调用@PostConstruct方法 ...");
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("[初始化步骤]调用InitializingBean.afterPropertiesSet ...");
    }

    public void myInit() {
        System.out.println("[初始化步骤]调用自定义初始化方法myInit ...");
    }

	@PreDestroy
	public void preDestory() {
		System.out.println("开始执行 @PreDestroy方法... ");
	}

    /**
     * Bean销毁时不被调用，仅容器销毁时调用
     */
	public void myDestroy() {
		System.out.println("开始执行自定义销毁方法 myDesctroy ....");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("调用DisposableBean.destroy的实现方法 ...");
	}
}
