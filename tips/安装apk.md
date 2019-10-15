# android 7.0 以前
```java
Intent intent = new Intent(Intent.ACTION_VIEW); 
intent.setDataAndType(Uri.fromFile(apkFile), “application/vnd.android.package-archive”); 
context.startActivity(intent);
```


