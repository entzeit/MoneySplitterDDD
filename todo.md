DDD:
* run DebtorsTest
* how do organize project?
* Find Aggregate Root, Person? rethink it. It has a Balance. 
* amount: Money
* name: Name
* DDD: create ValueObjects e.g., for person.name for validation and sanitization with regex in constructor to replace general regex
    and handle validation error properly for production application
* dependancy injection
* DTOs: data class?

late todo:
* add constraint for transactions e.g. A doesn't want to send money to C but to B