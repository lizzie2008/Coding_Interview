/**
 * 
 * 【剑指Offer】面试题2 ： 实现Singleton 模式
 * 【  题目描述 】设计一个类，我们只能生成该类的一个实例
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t;

public class Example02 {

	/**
	 * 饿汉式，线程安全
	 */
	public static class Singleton1 {
		private final static Singleton1 INSTANCE = new Singleton1();

		private Singleton1() {
		}

		public static Singleton1 getInstance() {
			return INSTANCE;
		}
	}

	/**
	 * 变种，线程安全
	 */
	public static class Singleton2 {
		private static Singleton2 instance = null;

		static {
			instance = new Singleton2();
		}

		private Singleton2() {
		}

		public static Singleton2 getInstance() {
			return instance;
		}
	}

	/**
	 * 懒汉式，线程不安全
	 */
	public static class Singleton3 {
		private static Singleton3 instance = null;

		private Singleton3() {
		}

		public static Singleton3 getInstance() {
			if (instance == null) {
				instance = new Singleton3();
			}
			return instance;
		}
	}

	/**
	 * 加锁解决线程同步，线程安全
	 */
	public static class Singleton4 {
		private static Singleton4 instance = null;

		private Singleton4() {
		}

		public static synchronized Singleton4 getInstance() {
			if (instance == null) {
				instance = new Singleton4();
			}

			return instance;
		}
	}

	/**
	 * 使用静态内部类，线程安全【推荐】
	 */
	public static class Singleton5 {
		private final static class SingletonHolder {
			private static final Singleton5 INSTANCE = new Singleton5();
		}

		private Singleton5() {
		}

		public static Singleton5 getInstance() {
			return SingletonHolder.INSTANCE;
		}
	}

	/**
	 * 使用枚举方式，线程安全【推荐】
	 */
	public enum Singleton6 {
		INSTANCE;

		public void whateverMethod() {
		}
	}

	/**
	 * 使用双重校验锁，线程安全【推荐】
	 */
	public static class Singleton7 {
		private volatile static Singleton7 instance = null;

		private Singleton7() {
		}

		public static Singleton7 getInstance() {
			if (instance == null) {
				synchronized (Singleton7.class) {
					if (instance == null) {
						instance = new Singleton7();
					}
				}
			}

			return instance;
		}
	}

	public static void main(String[] args) {
		System.out.println(Singleton1.getInstance() == Singleton1.getInstance());
		System.out.println(Singleton2.getInstance() == Singleton2.getInstance());
		System.out.println(Singleton3.getInstance() == Singleton3.getInstance());
		System.out.println(Singleton4.getInstance() == Singleton4.getInstance());
		System.out.println(Singleton5.getInstance() == Singleton5.getInstance());
		//System.out.println(Singleton6.INSTANCE == Singleton6.INSTANCE);
		System.out.println(Singleton7.getInstance() == Singleton7.getInstance());
	}

}
