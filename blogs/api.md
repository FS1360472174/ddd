# 背景
我们在APP上有个功能，需要获取用户当前定位，然后当用户关闭了GPS后，没有获取到用户定位，会触发一个bug,弹窗内容如下。


![](https://user-gold-cdn.xitu.io/2019/6/7/16b2f66061ddaf07?w=410&h=270&f=png&s=10058)

# 问题分析
这个问题的直接原因就是移动端的值取不到，导致没有给变量赋值，就将"undefined"传给了后端，后端的这个值定义的Integer，类型转换失败，报错。

深层原因是异常处理机制有问题，于是后端和前端开始撕逼了


**前端观点**: 后端代码太不健壮了， 就算前端传错了，也应该具备容错性;此外APP是有版本的，就算hotfix，用户也不一定升级，上一版本用户还是会有问题，所以这种问题尽量是后端来修复。

**后端观点**：前端没有异常兜底机制，用户不应该看到任何这种程序异常。对于有定制需求的异常，报特定异常，没有应该显示通用异常，比如弹窗"服务不可用"。另外这种属于http请求层面的约束，前端不遵从约束，还来怪我。我后端框架层面就给你拦截了，没到业务代码。

双方说的都好有道理，谁也说服不了谁。但是关于目标大家达成一致：坚决不能让用户看到这种类型的弹窗异常。

既然说服不了对方，就只能从更深入的分析问题，看看更合理的解法

## 通用异常的处理方式

http通常错误有

- 4开头：客户端参数有问题，需要后端提供debug信息。理论上应该只是联调的时候会出现，但是实际上不一定（这不就打脸了吗）
- 5开头：服务器端有错误，客户端有统一提供的异常处理
- 2开头：业务异常，如果有UI要求，后端返回一个code码，前端根据code码，展示UI。如果没有UI要求，前端直接展示后端返回的错误消息。

为了统一异常处理，一般公司的做法都是API统一返回一套数据格式，
```
{
    "error_code": "xx", // code码，1代表正常，其他表示异常。
    "error_msg": "xx" // msg，错误提示消息
    "data": "xx" // 正常数据
}

```
我们也是，并且将4开头的都统一处理成这套统一的数据格式。

那么前端处理异常的逻辑


![](https://user-gold-cdn.xitu.io/2019/6/7/16b2f8c2753a5eea?w=601&h=856&f=png&s=29048)

这次的问题就是走到2的分支了。


前后端都没做错，问题是后端对于异常模型的抽象有问题，`客户端参数有问题，需要后端提供debug信息`，而不是给用户展示的错误信息。

其实服务端对于异常就分三种

- 客户端参数有问题的异常（前端需要debug信息和错误msg信息） 
- 需要用户知道的业务异常，前端需要根据code展示的（前端需要code码）
- 通用的服务端异常，包装成消息给前端。（前端需要错误msg信息）


# 解法


分析清楚了问题后，就有了解法。

解法1：错误消息和debug消息分离，返回的API统一格式中增加一种字段。`debug_msg` 给开发看的,`error_msg` 还是给用户看的

解法2：定义几个枚举code。作为开发的约定

|code	|error_msg|	参数错误含义|
|--|--|--|
|010000|系统错误[010000]|请求方法不支持|
|010001|系统错误[010001]|缺少路径参数|
|010002|系统错误[010002]|缺少必须的请求参数|
|010003|系统错误[010003]|类型不匹配||
|010004|系统错误[010004]|请求体异常|
|010005|系统错误[010005]|// 参数校验异常(body 或 param)|
|010006|系统错误[010006]|参数绑定异常(表单)|

解法1定义比较清晰，但是为了这种corner case增加了一个字段的开销，网络传输代价高了。另外还需要前端配合更改，改动成本比较大。

解法2兼容了现在的实现，前端不用更改，但是对于`客户端参数有问题`这种错误提醒不是很友好，不能向前端显示具体的参数错误了。只能打日志。

和前端讨论了下，确定了解法2。


# 总结

所以这个问题，最后的解法

- 前端获取不到定位时，将`undefined`这个变量赋值
- 后端针对移动端这个服务，改动了异常处理机制

```
@RestControllerAdvice
public class ApiStandardException {
    private static final String ERROR_MSG = "系统错误";
 
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Result> handle(final Exception ex, final WebRequest request)
            throws Exception {

        try {
            if (ex instanceof HttpRequestMethodNotSupportedException) {
                // 请求方法不支持
                LOG.warn("Request method is not supported");
                throw new BusinessException(WebRequestErrorEnum.METHOD_ERR.getCode(), ERROR_MSG);
            } else if (ex instanceof MissingPathVariableException) {
                LOG.warn("MISSING_PATHVAR" + ex.getMessage());
                // 缺少路径参数
                throw new BusinessException(WebRequestErrorEnum.MISSING_PARAM.getCode(), ERROR_MSG);
            } else if (ex instanceof MissingServletRequestParameterException) {
                // 缺少必须的请求参数
            }
            // 省略其他异常处理
```

**关注【方丈的寺院】，第一时间收到文章的更新，与方丈一起开始技术修行之路**
![在这里插入图片描述](https://user-gold-cdn.xitu.io/2019/5/30/16b095554461841d?w=344&h=344&f=jpeg&s=10747)

