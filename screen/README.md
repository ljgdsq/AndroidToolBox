# android 屏幕适配
1. dpi：一英寸长的直线上的像素点的数量，即像素密度。标准值是160dp。
   正是因为dpi值其代表的特性，所以android项目的资源文件下存在以下目录：
     * drawable-ldpi (240*320 当dpi为120时，使用此目录下的资源)
     * drawable-mdpi (320*480 当dpi为160时，使用此目录下的资源)
     * drawable-hdpi (480*800 当dpi为240时，使用此目录下的资源)
     * drawable-xhdpi (720p 当dpi为320时，使用此目录下的资源)
     * drawable-xxhdpi (1080p 当dpi为480时，使用此目录下的资源)
Android正是根据设备DPI值得不同，选择清晰度不同的资源使用，完成屏幕的适配。


2. dp(dip)：独立像素密度。即在标准屏幕下，1个像素点的长度，标准屏幕是160dpi，可以理解为1英寸长度上有160个像素。标准屏幕中1dp=1px。


3. 分辨率：屏幕上长宽方向上像素点的数量，即一个屏幕上像素的数量。

　　　　例如：720*1280 = 屏幕x轴上有720个像素，屏幕y轴上有1280个像素

4. 屏幕的物理尺寸：屏幕对角线的长度，单位是inch

5. sp：专用于设定文字大小，受dpi影响和用户的字体偏好设定影响。

6. dp与px互相转换 [DisplayUtils](../application/app/src/main/java/com/example/androidtoolbox/utils/DisplayUtils.java) /application/app/src/main/java/com/example/androidtoolbox/utils/DisplayUtils.java



# 代码
```java

 　　 DisplayMetrics metrics = new DisplayMetrics();
     getWindowManager().getDefaultDisplay().getMetrics(metrics);
         int screenHeight = metrics.heightPixels;//屏幕高度像素
         int screenWidth = metrics.widthPixels;//屏幕宽度像素
         //density = densityDpi / 160
         float density = metrics.density;// "屏幕密度"（0.75 / 1.0 / 1.5）
         int densityDpi = metrics.densityDpi;// 屏幕密度dpi（120 / 160 / 240）每一英寸的屏幕所包含的像素数.值越高的设备，其屏幕显示画面的效果也就越精细
         int height = (int) (screenHeight / density);//屏幕高度dp

```
