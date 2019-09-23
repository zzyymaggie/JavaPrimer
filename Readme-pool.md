## Java对象的生命周期分析

　　Java对象的生命周期大致包括三个阶段：对象的创建，对象的使用，对象的清除。因此，对象的生命周期长度可用如下的表达式表示：T = T1 + T2 +T3。其中T1表示对象的创建时间，T2表示对象的使用时间，而T3则表示其清除时间。由此，我们可以看出，只有T2是真正有效的时间，而T1、T3则是对象本身的开销。下面再看看T1、T3在对象的整个生命周期中所占的比例。

　　我们知道，Java对象是通过构造函数来创建的，在这一过程中，该构造函数链中的所有构造函数也都会被自动调用。另外，默认情况下，调用类的构造函数时，Java会把变量初始化成确定的值：所有的对象被设置成null，整数变量（byte、short、int、long）设置成0，float和double变量设置成0.0，逻辑值设置成false。所以用new关键字来新建一个对象的时间开销是很大的，如表1所示。

　　表1 一些操作所耗费时间的对照表

|运算操作      | 示例         | 标准化时间 |
| ------------|:-----------: | --------:|
| 本地赋值     | i= n         | 1.0      |
| 实例赋值     | this.i = n   | 1.2      |
| 方法调用     | Funct()      | 5.9      |
| 新建对象     | New Object() | 980      |
| 新建数组     | New int[10]  | 3100     |

从表1可以看出，新建一个对象需要980个单位的时间，是本地赋值时间的980倍，是方法调用时间的166倍，而若新建一个数组所花费的时间就更多了。

  再看清除对象的过程。我们知道，Java语言的一个优势，就是Java程序员勿需再像C/C++程序员那样，显式地释放对象，而由称为垃圾收集器（Garbage Collector）的自动内存管理系统，定时或在内存凸现出不足时，自动回收垃圾对象所占的内存。凡事有利总也有弊，这虽然为Java程序设计者提供了极大的方便，但同时它也带来了较大的性能开销。这种开销包括两方面，首先是对象管理开销，GC为了能够正确释放对象，它必须监控每一个对象的运行状态，包括对象的申请、引用、被引用、赋值等。其次，在GC开始回收“垃圾”对象时，系统会暂停应用程序的执行，而独自占用CPU。

　　因此，如果要改善应用程序的性能，一方面应尽量减少创建新对象的次数；同时，还应尽量减少T1、T3的时间，而这些均可以通过对象池技术来实现。

## 对象池技术的基本原理

### 1.基本原理
对象池技术基本原理的核心有两点：缓存和共享，即对于那些被频繁使用的对象，在使用完后，不立即将它们释放，而是将它们缓存起来，以供后续的应用程序重复使用，从而减少创建对象和释放对象的次数，进而改善应用程序的性能。

### 2.使用场景
并非所有对象都适合拿来池化――因为维护对象池也要造成一定开销。对生成时开销不大的对象进行池化，反而可能会出现“维护对象池的开销”大于“生成新对象的开销”，从而使性能降低的情况。但是对于生成时开销可观的对象，池化技术就是提高性能的有效策略了。
常见，如数据库连接池、Redis连接池

### 3.代码
xyz.zzyymaggie.pool.ObjectPool.java
测试代码： xyz.zzyymaggie.pool.ObjectPool.ObjectPoolTest.java

### 4.扩展
commons-pool提供了一套很好用的对象池组件。使用也很简单，不过对一些简单的对象使用对象池就没必要了。

ObjectPool定义了一个简单的池化接口，有三个对应实现
GenericObjectPool：实现了可配置的后进先出或先进先出(LIFO/FIFO)行为，默认是作为一个后进先出队列，这意味当对象池中有可用的空闲对象时，borrowObject 将返回最近的对象实例，如果将lifo 属性设置为false，则按FIFO行为返回对象实例。
StackObjectPool ：实现了后进先出(LIFO)行为。
SoftReferenceObjectPool： 实现了后进先出(LIFO)行为。另外，对象池还在SoftReference 中保存了每个对象引用，允许垃圾收集器针对内存需要回收对象。

KeyedObjectPool定义了一个以任意的key访问对象的接口（可以池化对种对象），有两种对应实现。
GenericKeyedObjectPool ：实现了先进先出(FIFO)行为。
StackKeyedObjectPool ： 实现了后进先出(LIFO)行为。

PoolableObjectFactory 定义了池化对象的生命周期方法，我们可以使用它分离被池化的不同对象和管理对象的创建，持久，销毁。
BasePoolableObjectFactory这个实现PoolableObjectFactory 接口的一个抽象类，我们可用扩展它实现自己的池化工厂。

一个对象池使用的简单例子：
```java
import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.StackObjectPool;
 
public class Pool {
 
	public static void main(String[] args) throws Exception {
		ObjectPool pool = new StackObjectPool(new UserFactory());
		User u = (User) pool.borrowObject(); // 从池中借出一个对象
		u.setName("me");
		u.sayHello();
		pool.returnObject(u); // 归还对象
	}
 
	static class UserFactory extends BasePoolableObjectFactory {
		/**
		 * 产生一个新对象
		 */
		public Object makeObject() {
			return new User();
		}
 
		/**
		 * 还原对象状态
		 */
		public void passivateObject(Object obj) {
			User u = (User) obj;
			u.clear();
		}
	}
 
	static class User {
		String name;
 
		void setName(String name) {
			this.name = name;
		}
 
		void sayHello() {
			System.out.println("hello, " + name);
		}
 
		void clear() {
			name = "";
		}
	}
}
```

1.参考来源： https://blog.csdn.net/shimiso/article/details/9814917