package xyz.zzyymaggie.java.primer.nonproxy;

/**
 * @link http://blog.csdn.net/Mr_Tim/article/details/51891202
 * @author sophia
 */
public class TestForumService {
	public static void main(String[] args) {
		ForumService forumService = new ForumServiceImpl();
		forumService.removeForum(10);
		forumService.removeTopic(1012);
	}
}
