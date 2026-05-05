# Project's Domain Driven Design Concept:

| Concept | Responsibility         |
| ------- |------------------------|
| Person  | identity               |
| Bill    | single source of truth |
| Balance | computed result        |

# General
- making illegal states unrepresentable


# Entity

Use: 
```
class <class>
```
✔ If the constraint is simple and local
Use require inside Factory
✔ If the concept has behavior

# Value Object
A type is a Value Object if it:

Has no identity
Is defined entirely by its attributes
Is ideally immutable
Is compared by value, not reference
Is interchangeable if values are equal

Use 
```
@JvmInline
value class <class>
```
for Value Object where exactly one underlying property exists.
(ype-safe wrapper around a single value with (almost) zero runtime overhead)

For multiple properties, use:
```
data class <class>
```
“Avoid data class when structural copying could bypass domain behavior or invariants.”

For more complex Value Objects, where
- you need control over construction
- you enforce business rules/invariants 
- Equality is not trivial 
- you want to hide internal structure 
- you have behavior, not just data
Use:
```
class <class> private constructor
```


