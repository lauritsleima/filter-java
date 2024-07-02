# Laurits ja tema filtrid
___

## Endpointid:
- **api/filter/upsert**

**PUT** päring salvestab uue kirje, kui request body's on filterID `null` või puudu. 
Kui ID on sisendis olemas, **uuendatakse** olemasolevat filtrit.
Kui ID on sisendis olemas, kuid mitteeksisteeriv, tagastatakse vastav **veateade**.

Request body näide:
```
{
    "filterId": null,
    "filterName": "filterName",
    "criteria": [
        {
            "id": null,
            "type": "number",
            "comparison": "equals",
            "valueNumber": 1234
        }
    ]
}
```

- **api/filter/get/**

**GET** päring tagastab baasist kõik filtrid.

- **api/filter/get/{filterId}/**

**GET** päring tagastab baasist filtrit vastavalt ID-le.

- **api/filter/delete/{filterId}/**

**DELETE** päring kustutab baasist filtrit vastavalt ID-le.

___
## Baasist ilusa tabeli välja võlumine:

```
select f.id as filter_id, name, type, comparison, 
value_text, value_number, value_date, created_at from filter f
left join criteria c on f.id = c.filter_id;
```
