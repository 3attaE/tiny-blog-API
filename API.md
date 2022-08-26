# tiny-blog-API 接口文档

## 说明

- 接口鉴权

标注的接口需携带 `Authorization` Token

- 成功返回
```JSON
{
  "code": 0,
  "message": "success",
  "data": {}
}
```

- 失败返回
```JSON
{
  "code": 500,
  "message": "error messgae"
}
```

- errorCode 业务错误码


|错误码|含义|
|--|--|
|400|参数校验错误|
|500|内部错误|
|510|登录模块错误|
|520|文章模块错误|
|530|点赞错误|
|540|评论错误|

## 登录模块

### 登录接口
`POST /api/users/login`

- payload

|字段|类型|备注|是否必传|
|--|--|--|--|
|username|String|用户名|是|
|password|String|密码|是|

```JSON
{
  "username": "admin",
  "password": "admin"
}
```

- success

```JSON
{
  "code": 0,
  "message": "success",
  "data": {
    "token": "xxxx"
  }
}
```

- error

```JSON
{
  "code": 510,
  "message": "error message"
}
```

### 退出接口
`POST /api/users/logout`

- header

|字段|类型|备注|是否必传|
|--|--|--|--|
|Authorization|String|--|是|


- success

```JSON
{
  "code": 0,
  "message": "success"
}
```

- error

```JSON
{
  "code": 510,
  "message": "error message"
}
```

## 文章模块

### 发布接口
`POST /api/posts/create`

- header

|字段|类型|备注|是否必传|
|--|--|--|--|
|Authorization|String|--|是|


- payload

|字段|类型|备注|是否必传|
|--|--|--|--|
|title|String|标题-限制20个字以内|是|
|content|String|文章内容|是|

```JSON
{
  "title": "title",
  "content": "content"
}
```

- success

```JSON
{
  "code": 0,
  "message": "success",
  "data": {
    "postId": 1234
  }
}
```

- error

```JSON
{
  "code": 520,
  "message": "error message"
}
```

### 修改接口
`POST /api/posts/update`

- header

|字段|类型|备注|是否必传|
|--|--|--|--|
|Authorization|String|--|是|

- payload

|字段|类型|备注|是否必传|
|--|--|--|--|
|postId|Long|文章ID|是|
|title|String|标题-限制20个字以内|否|
|content|String|文章内容|否|

```JSON
{
  "postId": 1234,
  "title": "title",
  "content": "content"
}
```

- success

```JSON
{
  "code": 0,
  "message": "success",
  "data": {
    "postId": "文章ID"
  }
}
```

- error

```JSON
{
  "code": 520,
  "message": "error message"
}
```

### 列表接口
`POST /api/posts/list`

- payload

|字段|类型|备注|是否必传|
|--|--|--|--|
|page|Integer|页码|是|
|size|Integer|分页大小|是|

```JSON
{
  "page": 1,
  "size": 10
}
```

- success

|字段|类型|备注|
|--|--|--|--|
|postId|Long|文章ID|
|title|String|标题|
|content|String|内容|
|starNum|Integer|点赞数|
|creatTime|Integer|创建时间戳|
|updateTime|Integer|更新时间戳|

```JSON
{
  "code": 0,
  "message": "success",
  "data": {
      "list": [
        {
          "postId": 123,
          "title": "title",
          "content": "content",
          "starNum": 1234,
          "creatTime": 1234,
          "updateTime": 1234,
        }
      ],
      "total": 10
  }
}
```

- error

```JSON
{
  "code": 520,
  "message": "error message"
}
```

### 评论接口
`POST /api/posts/comment`

- payload

|字段|类型|备注|是否必传|
|--|--|--|--|
|postId|Long|文章ID|是|
|comment|String|评价内容|是|


```JSON
{
  "postId": 1234,
  "comment": "content"
}
```

- success

```JSON
{
  "code": 0,
  "message": "success",
  "data": {
    "commentId": "文章ID"
  }
}
```

- error

```JSON
{
  "code": 520,
  "message": "error message"
}
```

### 点赞接口
`POST /api/posts/star`

- payload

|字段|类型|备注|是否必传|
|--|--|--|--|
|postId|Long|文章ID|是|
|starType|Inteher|点赞类型 1-点赞 2-取消点赞|是|

```JSON
{
  "postId": 1234,
  "starType": 1,
}
```

- success

```JSON
{
  "code": 0,
  "message": "success",
}
```

- error

```JSON
{
  "code": 520,
  "message": "error message"
}
```

## 搜索模块

### 全文搜索接口
`POST /api/posts/search`

- payload

|字段|类型|备注|是否必传|
|--|--|--|--|
|keywords|String|多个关键词以空格分割|是|

```JSON
{
  "keywords": "关键词1 关键词2"
}
```

- success

|字段|类型|备注|
|--|--|--|--|
|postId|Long|文章ID|
|title|String|标题|
|content|String|内容|
|starNum|Integer|点赞数|
|creatTime|Integer|创建时间戳|
|updateTime|Integer|更新时间戳|

```JSON
{
  "code": 0,
  "message": "success",
  "data": {
      "list": [
        {
          "postId": 123,
          "title": "title",
          "content": "content",
          "starNum": 1234,
          "creatTime": 1234,
          "updateTime": 1234,
        }
      ],
      "total": 10
  }
}
```

- error

```JSON
{
  "code": 520,
  "message": "error message"
}
```