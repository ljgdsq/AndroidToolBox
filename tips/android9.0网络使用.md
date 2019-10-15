# android 9.0 对网路安全限制增强
> Google表示，为保证用户数据和设备的安全，针对下一代 Android 系统(Android P) 的应用程序，将要求默认使用加密连接，这意味着 Android P 将禁止 App 使用所有未加密的连接，因此运行 Android P 系统的安卓设备无论是接收或者发送流量，未来都不能明码传输，需要使用下一代(Transport Layer Security)传输层安全协议，而 Android Nougat 和 Oreo 则不受影响。


## 解决
1. 使用https
2. targetVersion< 27
3. 在 res 下新增一个 xml 目录然后创建一个名为：network_security_config.xml 文件（名字自定）
```
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <base-config cleartextTrafficPermitted="true" />
</network-security-config>

```
在applicaiotn标签中添加
```
<application
 android:networkSecurityConfig="@xml/network_security_config"
/>
```
