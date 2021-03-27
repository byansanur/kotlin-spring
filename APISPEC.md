# API SPEC

## Users
### Register User
Request :
- Method : POST
- Endpoint : `/api/v1/user/register`
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json
{
  "name": "string",
  "user_name": "string",
  "passwords": "text"
}
```

Response :
```json
{
  "api_status": "number",
  "api_message" : "string",
  "data": {
    "id": "bigint",
    "name": "string",
    "user_name": "string",
    "created_at": "date"
  }
}
```

### Login User
Request :
- Method : POST
- Endpoint : `/api/v1/user/login`
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json
{
  "user_name": "string",
  "passwords": "text"
}
```

Response :
```json
{
  "api_status": "number",
  "api_message" : "string",
  "data": {
    "id": "bigint",
    "name": "string",
    "user_name": "string",
    "created_at": "date",
    "updated_at": "date"
  }
}
```


### Get User
Request :
- Method : GET
- Endpoint : `/api/v1/user/`
- Header :
    - Accept: application/json
- Query params :
  - id: number

Response :
```json
{
  "api_status": "number",
  "api_message" : "string",
  "data": {
    "id": "bigint",
    "name": "string",
    "user_name": "string"
  }
}
```


## Gallery
### Create Gallery
Request :
- Method : POST
- Endpoint : `/api/v1/user/galllery`
- Header :
  - Content-Type: application/json
  - Accept: application/json
- Body :

```json
{
  "id_user": "bigint",
  "description": "string",
  "path": "string"
}
```

Response :
```json
{
  "api_status": "number",
  "api_message" : "string",
  "data": {
    "id": "bigint",
    "id_user": "bigint",
    "description": "string",
    "path": "string"
  }
}
```

### Get List Gallery
Request :
- Method : GET
- Endpoint : `/api/v1/user/galllery`
- Header :
  - Accept: application/json
- Query params :
  - limit: number
  - offset: number

Response :
```json
{
  "api_status": "number",
  "api_message" : "string",
  "data": [
    {
      "id": "bigint",
      "id_user": "bigint",
      "description": "string",
      "path": "string"
    },
    {
      "id": "bigint",
      "id_user": "bigint",
      "description": "string",
      "path": "string"
    }
  ]
}
```

### Get Gallery
Request :
- Method : GET
- Endpoint : `/api/v1/user/galllery`
- Header :
  - Accept: application/json
- Query params :
  - id_gallery: number

Response :
```json
{
  "api_status": "number",
  "api_message" : "string",
  "data": {
    "id": "bigint",
    "id_user": "bigint",
    "description": "string",
    "path": "string"
  }
}
```

### Delete Gallery
Request :
- Method : DELETE
- Endpoint : `/api/v1/user/galllery`
- Header :
  - Content-Type: application/json
  - Accept: application/json
- Body :

```json
{
  "id": "bigint"
}
```

Response :
```json
{
  "api_status": "number",
  "api_message" : "string",
  "data": null
}
```

### Update Gallery
Request :
- Method : POST
- Endpoint : `/api/v1/user/galllery`
- Header :
  - Content-Type: application/json
  - Accept: application/json
- Body :

```json
{
  "id_user": "bigint",
  "description": "string",
  "path": "string"
}
```

Response :
```json
{
  "api_status": "number",
  "api_message" : "string",
  "data": {
    "id": "bigint",
    "id_user": "bigint",
    "description": "string",
    "path": "string"
  }
}
```

## Package
### Create Package
Request :
- Method : POST
- Endpoint : `/api/v1/user/package`
- Header :
  - Content-Type: application/json
  - Accept: application/json
- Body :

```json
{
  "id_user": "bigint",
  "description": "string",
  "path": "string",
  "price": "number"
}
```

Response :
```json
{
  "api_status": "number",
  "api_message" : "string",
  "data": {
    "id": "bigint",
    "id_user": "bigint",
    "description": "string",
    "path": "string",
    "price": "number"
  }
}
```

### Get List Package
Request :
- Method : GET
- Endpoint : `/api/v1/user/package`
- Header :
  - Accept: application/json
- Query params : 
  - limit: number 
  - offset: number

Response :
```json
{
  "api_status": "number",
  "api_message" : "string",
  "data": [
    {
      "id": "bigint",
      "id_user": "bigint",
      "description": "string",
      "path": "string",
      "price": "number"
    },
    {
      "id": "bigint",
      "id_user": "bigint",
      "description": "string",
      "path": "string",
      "price": "number"
    }
  ]
}
```

### Get Package
Request :
- Method : GET
- Endpoint : `/api/v1/user/package`
- Header :
  - Accept: application/json
- Query params : 
  - id_gallery: number

Response :
```json
{
  "api_status": "number",
  "api_message" : "string",
  "data": {
    "id": "bigint",
    "id_user": "bigint",
    "description": "string",
    "path": "string",
    "price": "number"
  }
}
```

### Delete Package
Request :
- Method : DELETE
- Endpoint : `/api/v1/user/package`
- Header :
  - Content-Type: application/json
  - Accept: application/json
- Body :

```json
{
  "id": "bigint"
}
```

Response :
```json
{
  "api_status": "number",
  "api_message" : "string",
  "data": null
}
```

### Update Package
Request :
- Method : POST
- Endpoint : `/api/v1/user/package`
- Header :
  - Content-Type: application/json
  - Accept: application/json
- Body :

```json
{
  "id_user": "bigint",
  "description": "string",
  "path": "string",
  "price": "number"
}
```

Response :
```json
{
  "api_status": "number",
  "api_message" : "string",
  "data": {
    "id": "bigint",
    "id_user": "bigint",
    "description": "string",
    "path": "string",
    "price": "number"
  }
}
```

