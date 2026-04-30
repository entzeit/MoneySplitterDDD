# Project's Domain Driven Design Concept:

| Concept | Responsibility  |
| ------- | --------------- |
| Person  | identity        |
| Bill    | financial facts |
| Balance | computed result |


# Value Object
Use 
```kotlin
@JvmInline
value class
```
for Value Object where exactly one underlying property exists. 
For multiple properties, use:
```kotlin
data class
```



