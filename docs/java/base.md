# 基础

- [基础](#基础)
  - [数据类型](#数据类型)
    - [值类型](#值类型)
    - [引用类型](#引用类型)
  - [访问修饰符](#访问修饰符)
  - [关键字](#关键字)
  - [面向对象](#面向对象)
  - [类与接口](#类与接口)
  - [内部类](#内部类)
    - [内部类的优点](#内部类的优点)
    - [内部类有哪些应用场景](#内部类有哪些应用场景)
  - [引用](#引用)

## 数据类型

### 值类型

- 数值型
  - 整数类型(byte,short,int,long)
  - 浮点类型(float,double)
- 字符型(char)
- 布尔型(boolean) 

### 引用类型

- 类(class) 
- 接口(interface) 
- 数组([])

## 访问修饰符

- private: 在同一类内可见。使用对象：变量、方法
- default(即什么都不写): 在同一包内可见
- protected: 对同一包内的类和所有子类可见
- public: 对所有类可见

## 关键字

- goto
> goto 是 Java 中的保留字，在目前版本的 Java 中没有使用。

- final
> 用于修饰类、属性和方法
> - 被final修饰的类不可以被继承
> - 被final修饰的方法不可以被重写
> - 被final修饰的变量不可以被改变
> - 被final修饰不可变的是变量的引用, 不可变是引用，不是内容

- this
> this是自身的一个对象

- super
> 指向父类指针

<hr>

## 面向对象

- 三大特性
  - 封装
  > 隐藏对象的属性和实现细节，仅对外提供公共访问方式
  - 继承
  > 继承是使用已存在的类的定义作为基础建立新类的技术，新类的定义可以 增加新的数据或新的功
    能，也可以用父类的功能，但不能选择性地继承父类。通过使用继承可以提高代码复用性。
  - 多态
  > 父类或接口定义的引用变量可以指向子类或具体实现类的实例对象。提 高了程序的拓展性  
  > 在Java中有两种形式可以实现多态  
  > - 继承(多个子类对同一方法的重写)
  > - 接口(实现接口并覆盖接口中同一方法)  

<br> 

- 五大基本原则
  1. 单一职责原则
  2. 对拓展开放，对修改封闭
  3. 里式替换原则
  4. 依赖倒置原则
  5. 接口分离原则

## 类与接口

抽象类和接口的对比

| 参数     | 抽象类                       | 接口                           |
| -------- | ---------------------------- | ------------------------------ |
| 声明     | abstract                     | interface                      |
| 实现     | 子类使用extends继承          | 子类使用implements关键字来实现 |
| 构造器   | 可以有构造器                 | 不能有构造器                   |
| 修饰符   | 任意访问修饰符               | public                         |
| 多继承   | 一个类最多只能继承一个抽象类 | 一个类可以实现多个接口         |
| 字段声明 | 任意                         | static 和 final                |


## 内部类  

- 静态内部类

```java
public class Outer {
    private string name;
    private static int radius = 1;

    public static class StaticInner {
        public int getRedius() {
            return radius;
        }
    }
}
```
> 静态内部类可以访问外部类所有的静态变量，而不可访问外部类的非静态变量

- 成员内部类
  
```java
public class Outer {
    private string name;
    private static int radius = 1;

    public class StaticInner {
        public int getRedius() {
            return radius;
        }
    }
}
```
> 成员内部类可以访问外部类所有的变量和方法，包括静态和非静态，私有和公有
 
- 局部内部类

```java
public class Outer {
    private string name;
    private static int radius = 1;

    public void execute() {
        public class Inner {

        }
        Inner inner = new Inner();
    }
}
```
> 定义在实例方法中的局部类可以访问外部类的所有变量和方法，定义在静态方法 中的局部类只能访问外
部类的静态变量和方法

- 匿名内部类

```java
public class Outer {
    private string name;
    private static int radius = 1;

    public void execute() {
        new Service() {
            public void method() {

            }
        }.method();
    }
}
```

> 匿名内部类就是没有名字的内部类。

### 内部类的优点

- 一个内部类对象可以访问创建它的外部类对象的内容，包括私有数据！ 
- 内部类不为同一包的其他类所见，具有很好的封装性； 
- 内部类有效实现了“多重继承”，优化 java 单继承的缺陷。 
- 匿名内部类可以很方便的定义回调。

### 内部类有哪些应用场景

1. 一些多算法场合 
2. 解决一些非面向对象的语句块。 
3. 适当使用内部类，使得代码更加灵活和富有扩展性。 
4. 当某个类除了它的外部类，不再被其他的类使用时。

## 引用

[https://github.com/WilsonPan/java-developer](https://github.com/WilsonPan/java-developer)