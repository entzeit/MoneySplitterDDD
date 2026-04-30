# Project's Domain Driven Design Concept:

| Concept | Responsibility         |
| ------- |------------------------|
| Person  | identity               |
| Bill    | single source of truth |
| Balance | computed result        |


# Value Object
Use 
```
@JvmInline
value class
```
for Value Object where exactly one underlying property exists. 
For multiple properties, use:
```
data class
```



