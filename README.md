# SlidingToast
几步实现安卓滑动产生Toast收放动画效果

在开发中往往会有这种需求，在某一个layout布局中，一个下拉的手势出现一个提示框，并且提示框会有出现和隐藏的动画效果，比如说如下图：

![image](SlidingToast/GIF.gif)
其实实现思路很简单，就是用一个textview或者布局来充当toast，在这个layout布局中手势操作触控事件分发，然后给其toast布局设置进去和弹出的动画效果就ok啦。
