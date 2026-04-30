DDD:

/*
//Value Object
data class Balance(var amount: Long = 0)

//todo: changes everything and how it is stored
*/

* Balance value object? -> Aggregate?
  * derived value from bills. no sync issues. 
  * Default 0 or directly derived?
* Person? rethink it. It has a Balance?
* DDD: create ValueObjects e.g., for person.name for validation and sanitization with regex in constructor to replace general regex
    and handle validation error properly for production application
* dependancy injection
* DTOs: data class?
* only reference with id? only with aggregate root? database?
* Two people with same name can exist? How handle that? Person has id?

late todo:
* Caching: store cached and update only with the old balance value.
  Clean architecture way (best practice)

Move caching OUT of domain:

class CachedBalanceService(
private val calculator: BalanceCalculator
)

NOT inside:

Person
Bill
domain model

👉 caching is infrastructure concern

* todo: add multiple Groups
* add constraint for transactions e.g. A doesn't want to send money to C but to B

/*
Value Object:
- immutable -> only val, not var!!
- a snapshot of state
- a result of computation
- not a mutable identity-based object like an Entity
  */

ChatGPT Prompt:
I am a professional developer. 
I need some help converting a simple Kotlin project that has no architecture to Domain Driven Design and I need some advice for this.


| Concept | Responsibility  |
| ------- | --------------- |
| Person  | identity        |
| Bill    | financial facts |
| Balance | computed result |



