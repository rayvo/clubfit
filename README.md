# 轻牛蓝牙Android SDK 

## SDK文件说明
### 统一引入
#### 导入demo项目的lib中的jar文件(qnsdk-xxx.jar)和对应的so文件

### 操作
#### QNBleApi
该类为SDK的主要工作类，提供SDK的各种方法的操作

### 配置
#### QNConfig
该类为SDK的设置类，包括扫描的配置、连接的配置、秤单位显示等配置

### 测量过程主要使用方法
#### QNBleDeviceDiscoveryListener
提供扫描秤时的回调,回调的秤数据对象为QNBleDevice
#### QNBleDevice
扫描设备的对象

#### QNUser
由app向SDK提供的用户信息对象，设置连接时需要用到

#### QNBleConnectionChangeListener
提供秤在使用过程中各种蓝牙状态的回调以及测量过程中秤的测量状态

#### QNDataListener
提供测量数据的回调，包括实时体重、测量结果、存储数据
#### QNScaleData
测量结果数据对象
#### QNScaleStoreData
存储数据对象,可以通过
#### QNScaleItemData
每个指标的详细数据对象


### 错误信息
#### CheckStatus
展示了SDK中所有的错误信息


## SDK调用步骤

1. 初始化SDK `(QNBleApi)initSdk`
2. 开启扫描 `(QNBleApi)startBleDeviceDiscovery`
	+ 开启扫描需要先设置进行扫描设备的监听 `(QNBleApi)setBleDeviceDiscoveryListener`
	+ 开启扫描需要配置扫描对象QNConfig,可以新建对象或获取上次设置的对象 `(QNBleApi)getConfig`
3. 设置扫描配置 （步骤2中的设置对象）
 	+ 获取配置信息 `(QNConfig *)new QNConfig()`
	+ 设置是否只扫描开机的秤 `onlyScreenOn`
	+ 设置扫描到秤时是否返回多次 `allowDuplicates`
	+ 设置扫描的时间 `duration`
	+ 设置秤端显示的单位 `unit`
4. 设置连接状态的监听 `(QNBleApi)setBleConnectionChangeListener`
5. 设置测量数据的监听 `(QNBleApi)setDataListener`
6. 构建连接秤的用户对象 `(QNBleApi)buildUser:String userId,int height,String gender,Date birthday,QNResultCallback callback`
7. 连接设备 `connectDevice:QNBleDevice device,QNUser user,QNResultCallback callback`



## 注意事项
- 必须在清单文件中申请蓝牙权限、位置权限、网络权限（离线SDK不需要）
- 必须在清单文件中添加SDK需要使用到的服务：com.qingniu.qnble.scanner.BleScanService，com.qingniu.scale.ble.ScaleBleService
- targetSdkVersion 在23及以上，需要先获取定位权限，才能扫描到设备，需要开发者自己申请