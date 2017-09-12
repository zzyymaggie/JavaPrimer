package xyz.zzyymaggie.java.primer.nonproxy;

public class ForumServiceImpl implements ForumService {
	public void removeTopic(int topicId) {
		// 开始性能监视
		PerformanceMonitor.begin("xyz.zzyymaggie.java.primer.proxy.ForumServiceImpl.removeTopic");
		System.out.println("模拟删除Topic记录:" + topicId);
		try {
			Thread.sleep(20);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		// 结束监视、并给出性能报告信息
		PerformanceMonitor.end();
	}

	public void removeForum(int forumId) {
		// 开始性能监视
		PerformanceMonitor.begin("xyz.zzyymaggie.java.primer.proxy.removeForum");
		System.out.println("模拟删除Forum记录:" + forumId);
		try {
			Thread.sleep(40);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		// 结束监视、并给出性能报告信息
		PerformanceMonitor.end();
	}
}
