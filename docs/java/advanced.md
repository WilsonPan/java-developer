
# 进阶

- [进阶](#进阶)
  - [反射](#反射)
    - [什么是反射机制？](#什么是反射机制)
    - [反射机制优缺点](#反射机制优缺点)
    - [Java获取反射的三种方法](#java获取反射的三种方法)
    - [调用方法](#调用方法)

## 反射

### 什么是反射机制？

JAVA反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意一个方法和属性；这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制。

静态编译和动态编译  

**静态编译**：在编译时确定类型，绑定对象  
**动态编译**：运行时确定类型，绑定对象

### 反射机制优缺点

**优点**： 运行期类型的判断，动态加载类，提高代码灵活度。  
**缺点**： 性能瓶颈：反射相当于一系列解释操作，通知 JVM 要做的事情，性能比直接的java代码要慢很多。

### Java获取反射的三种方法

1. 通过建立对象

```java
Class cls = new Runner().getClass();
```

2. 通过类名

```java
Class cls = Runner.class;
```

3. 通过Class.forName

```java
Class cls = Class.forName("com.app.demo.Runner");
```

### 调用方法

```java
// 1. 获取Method
Method method = cls.getDeclaredMethod("getName");

// 2. 调用
Object result = method.invoke(cls.newInstance());
```
