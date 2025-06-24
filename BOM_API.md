---
title: miniBOM
language_tabs:
  - shell: Shell
  - http: HTTP
  - javascript: JavaScript
  - ruby: Ruby
  - python: Python
  - php: PHP
  - java: Java
  - go: Go
toc_footers: []
includes: []
search: true
code_clipboard: true
highlight_theme: darkula
headingLevel: 2
generator: "@tarslib/widdershins v4.0.30"

---

# miniBOM

Base URLs:

# Authentication

# miniBOM/BOM相关

## POST 创建BOMLink

POST /localhost:8080/miniBOM/BOM/create

> Body 请求参数

```json
{
  "sourceId": 30,
  "targetId": 31,
  "sequenceNumber": 1,
  "quantity": 4,
  "referenceDesignator": "sint et non qui mollit"
}
```

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|body|body|object| 否 ||none|
|» sourceId|body|number| 是 ||none|
|» targetId|body|number| 是 ||none|
|» sequenceNumber|body|number| 是 ||none|
|» quantity|body|number| 是 ||none|
|» referenceDesignator|body|string| 是 | 位号|none|

> 返回示例

> 200 Response

```json
{
  "code": 26,
  "result": "laboris",
  "data": {
    "id": 12,
    "quantity": 33,
    "sequenceNumber": 64,
    "referenceDesignator": "officia labore ut enim",
    "sourceId": 60,
    "sourceName": "顿国荣",
    "targetId": 32,
    "targetName": "路娜"
  },
  "errors": {}
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|number|true|none||none|
|» result|string|true|none||none|
|» data|object|false|none||none|
|»» id|number|true|none||none|
|»» quantity|number|true|none||none|
|»» sequenceNumber|number|true|none||none|
|»» referenceDesignator|string|true|none||none|
|»» sourceId|number|true|none||none|
|»» sourceName|string|false|none||none|
|»» targetId|number|true|none||none|
|»» targetName|string|false|none||none|
|» errors|object¦null|false|none||none|

## POST 创建bomlink时创建子part

POST /localhost:8080/miniBOM/BOM/createPart

> Body 请求参数

```json
{
  "sourceId": 64,
  "targetId": 5,
  "sequenceNumber": 14,
  "quantity": 92,
  "referenceDesignator": "dolor voluptate culpa non",
  "partCreateDTO": {
    "code": "70",
    "name": "巨伟",
    "defaultUnit": "nulla ut",
    "category": "aliqua minim sit",
    "attributes": {}
  }
}
```

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|body|body|object| 否 ||none|
|» sourceId|body|number| 是 ||none|
|» targetId|body|number| 是 ||none|
|» sequenceNumber|body|number| 是 ||none|
|» quantity|body|number| 是 ||none|
|» referenceDesignator|body|string| 是 ||none|
|» partCreateDTO|body|object| 是 ||none|
|»» code|body|string| 是 ||none|
|»» name|body|string| 是 ||none|
|»» defaultUnit|body|string| 是 ||none|
|»» category|body|string| 是 ||none|
|»» attributes|body|object| 是 ||和创建part一致|

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "result": "string",
  "data": {},
  "errors": {}
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|number|true|none||none|
|» result|string|true|none||none|
|» data|object|true|none||none|
|» errors|object|true|none||none|

## PUT 修改bom信息

PUT /localhost:8080/miniBOM/BOM/update

> Body 请求参数

```json
{
  "bomLinkId": 46,
  "sequenceNumber": 47,
  "quantity": 1,
  "referenceDesignator": "dolor sunt aute quis"
}
```

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|body|body|object| 否 ||none|
|» bomLinkId|body|number| 是 ||none|
|» sequenceNumber|body|number| 是 ||none|
|» quantity|body|number| 是 ||none|
|» referenceDesignator|body|string| 是 ||none|

> 返回示例

> 200 Response

```json
{
  "code": 88,
  "result": "nostrud magna aliquip proident nulla",
  "data": {
    "id": 93,
    "quantity": 63,
    "sequenceNumber": 64,
    "referenceDesignator": "mollit aute ad non",
    "sourceId": 95,
    "sourceName": "告国芳",
    "targetId": 9,
    "targetName": "任治文"
  },
  "errors": {}
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» result|string|true|none||none|
|» data|object|true|none||none|
|»» id|integer|true|none||none|
|»» quantity|integer|true|none||none|
|»» sequenceNumber|integer|true|none||none|
|»» referenceDesignator|string|true|none||none|
|»» sourceId|integer|true|none||none|
|»» sourceName|string|true|none||none|
|»» targetId|integer|true|none||none|
|»» targetName|string|true|none||none|
|» errors|object|true|none||none|

## GET 展示所有子part

GET /localhost:8080/miniBOM/BOM/show

> Body 请求参数

```yaml
sourceId: 0

```

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|body|body|object| 否 ||none|
|» sourceId|body|number| 否 ||none|

> 返回示例

> 200 Response

```json
{
  "code": 1,
  "result": "pariatur in sit fugiat",
  "data": [
    {
      "BOMLinkId": 76,
      "quantity": 32,
      "sequenceNumber": 78,
      "targetId": 95,
      "targetName": "赧洁",
      "referenceDesignator": "nostrud ut Ut occaecat fugiat"
    },
    {
      "BOMLinkId": 70,
      "quantity": 47,
      "sequenceNumber": 65,
      "targetId": 20,
      "targetName": "惠刚",
      "referenceDesignator": "in voluptate"
    },
    {
      "BOMLinkId": 61,
      "quantity": 73,
      "sequenceNumber": 22,
      "targetId": 82,
      "targetName": "勇家明",
      "referenceDesignator": "labore"
    }
  ],
  "errors": {}
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|number|true|none||none|
|» result|string|true|none||none|
|» data|[object]|true|none||none|
|»» BOMLinkId|number|true|none||none|
|»» quantity|number|true|none||none|
|»» sequenceNumber|number|true|none||none|
|»» targetId|number|true|none||none|
|»» targetName|string|true|none||none|
|»» referenceDesignator|string|true|none||none|
|» errors|object|true|none||none|

## GET 展示部件父项

GET /localhost:8080/miniBOM/BOM/showFather

> Body 请求参数

```yaml
partId: 0

```

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|body|body|object| 否 ||none|
|» partId|body|number| 否 ||none|

> 返回示例

> 200 Response

```json
{
  "code": 84,
  "result": "dolor",
  "data": [
    {
      "BOMLinkId": 64,
      "quantity": 81,
      "sequenceNumber": 30,
      "sourceId": 9,
      "sourceName": "裴丹",
      "referenceDesignator": "eiusmod ad voluptate"
    }
  ],
  "errors": {}
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» result|string|true|none||none|
|» data|[object]|true|none||none|
|»» BOMLinkId|integer|true|none||none|
|»» quantity|integer|true|none||none|
|»» sequenceNumber|integer|true|none||none|
|»» sourceId|integer|true|none||none|
|»» sourceName|string|true|none||none|
|»» referenceDesignator|string|true|none||none|
|» errors|object|true|none||none|

## GET 展示part根显示完整清单

GET /localhost:8080/miniBOM/BOM/showRoot

> Body 请求参数

```yaml
partId: 0

```

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|body|body|object| 否 ||none|
|» partId|body|number| 否 ||none|

> 返回示例

> 200 Response

```json
{
  "code": 92,
  "result": "nostrud est",
  "data": {
    "BOMLinkId": 74,
    "quantity": 91,
    "sequenceNumber": 28,
    "sourceId": 87,
    "sourceName": "仁秀珍",
    "referenceDesignator": "ut do aute id sint"
  },
  "errors": {}
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» result|string|true|none||none|
|» data|object|true|none||none|
|»» BOMLinkId|integer|true|none||none|
|»» quantity|integer|true|none||none|
|»» sequenceNumber|integer|true|none||none|
|»» sourceId|integer|true|none||none|
|»» sourceName|string|true|none||none|
|»» referenceDesignator|string|true|none||none|
|» errors|object|true|none||none|

## DELETE 删除bomlink/删除子part

DELETE /localhost:8080/miniBOM/BOM/delete

> Body 请求参数

```yaml
bomLinkId: 0

```

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|body|body|object| 否 ||none|
|» bomLinkId|body|number| 否 ||none|

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "result": "删除成功",
  "data": {},
  "errors": {}
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|number|true|none||none|
|» result|string|true|none||none|
|» data|object|true|none||none|
|» errors|object|true|none||none|

# 数据模型

