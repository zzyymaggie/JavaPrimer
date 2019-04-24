package xyz.zzyymaggie.spring.aop.basic.proxy;

public class ForumServiceImpl implements ForumService {
	 public void removeTopic(int topicId) {
//	         ①
	  System.out.println("模拟删除Topic记录:"+topicId);
	  try {
	   Thread.sleep(20);
	  } catch (Exception e) {
	   throw new RuntimeException(e);
	  }
//	   ②
	 }
	 public void removeForum(int forumId) {
//	         ①
	  System.out.println("模拟删除Forum记录:"+forumId);
	  try {
	   Thread.sleep(40);
	  } catch (Exception e) {
	   throw new RuntimeException(e);
	  }
//	         ②
	 }
	}
