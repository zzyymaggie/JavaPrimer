package xyz.zzyymaggie.spring.aop.basic.nonproxy;

/**
 * @link http://blog.csdn.net/Mr_Tim/article/details/51891202
 * 这个例子来源《精通Spring4.x 企业应用开发实践》第7章 Spring AOP基础。这篇博客是把这一章节整理了一下。
 * @author sophia
 */
public class TestForumService {
	public static void main(String[] args) {
		ForumService forumService = new ForumServiceImpl();
		forumService.removeForum(10);
		forumService.removeTopic(1012);
	}
}
