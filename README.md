# Hulk Store
App Test para Todo1.

## Comenzando
Este proyecto responde a una práctica sobre Hulk Store. Fue realizada con Spring Boot.

### Pre requisitos
Es necesario tener instalado Java para poder utilizar la aplicación.

## Api

GET: http://localhost:8080/api/v1/products

```
[
    {
        "id": 5,
        "code": 1,
        "description": "VASO",
        "brand": {
            "id": 1,
            "description": "DC Comics"
        }
    },
    {
        "id": 7,
        "code": 2,
        "description": "CAMISETA",
        "brand": {
            "id": 1,
            "description": "DC Comics"
        }
    },
    {
        "id": 9,
        "code": 3,
        "description": "COMICS",
        "brand": {
            "id": 1,
            "description": "DC Comics"
        }
    },
	...
]
```
GET: http://localhost:8080/api/v1/products/5

```
{
    "id": 5,
    "code": 1,
    "description": "VASO",
    "brand": {
        "id": 1,
        "description": "DC Comics"
    }
}
```
POST: http://localhost:8080/api/v1/products
```
BODY{
        "code": 1,
        "description": "VASO-PEQUEÑO",
        "brandId": 1
    }
```
PUT: http://localhost:8080/api/v1/products/35
```
BODY{
        "code": 1,
        "description": "VASO-PEQUEÑO",
        "brandId": 1
    }
```
DELETE: http://localhost:8080/api/v1/products/31


GET: http://localhost:8080/api/v1/stocks

```
[
    {
        "id": 4,
        "description": null,
        "quantity": 100,
        "product": {
            "id": 5,
            "code": 1,
            "description": "VASO",
            "brand": {
                "id": 1,
                "description": "DC Comics"
            }
        }
    },
    {
        "id": 6,
        "description": null,
        "quantity": 100,
        "product": {
            "id": 7,
            "code": 2,
            "description": "CAMISETA",
            "brand": {
                "id": 1,
                "description": "DC Comics"
            }
        }
    },
    {
        "id": 8,
        "description": null,
        "quantity": 100,
        "product": {
            "id": 9,
            "code": 3,
            "description": "COMICS",
            "brand": {
                "id": 1,
                "description": "DC Comics"
            }
        }
    },
	...
]
```
GET: http://localhost:8080/api/v1/stocks/4

```
{
    "id": 4,
    "description": null,
    "quantity": 100,
    "product": {
        "id": 5,
        "code": 1,
        "description": "VASO",
        "brand": {
            "id": 1,
            "description": "DC Comics"
        }
    }
}
```
 PUT  http://localhost:8080/api/v1/stocks/4

```
 BODY: {"type": 2, "quantity":104 }
```
### Test
Para la realización de test se utilizó SpringBootTest, JUnit y Mockito.

## Autor
* **Gregori Battista, Facundo** -

Y recuerda que este prototipo es solo para fines demostrativos. ;)