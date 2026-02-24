* fun calculateBalances(bills: List<Bill>) {
  //todo: improve by using a temporary map
  //val debtMap = mutableMapOf<Person, Debt>()
  /*
  Why is a map faster? In the Set I check for the person id and in the Map I check for hashcode.
  */ + reduce. 0 default value not necessary?
* rethink Person. Person has a Balance and Debts?
* DDD: create ValueObjects e.g., for person.name for validation and sanitization with regex in constructor to replace general regex
    and handle validation error properly for production application
* general DDD



late todo:
* add constraint for transactions e.g. A doesn't want to send money to C but to B