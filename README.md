# PagerIndicatorView
方便易用的ViewPager指示器

导入：

1、project的.gradle文件添加：

allprojects {

    repositories {
    
        maven { url "https://raw.githubusercontent.com/hahafather007/openProjects/master" }
        
    }
    
}

2.app的.gradle添加：

    implementation 'haha.father007:pager-indicator:0.0.1'

使用：

界面初始化调用 indicator.attachTo(viewPager)   方法将其关联，数据刷新时调用  indicator.refresh() 即可
