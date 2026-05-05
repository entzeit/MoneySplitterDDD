* add multiple Groups
* remove negative money and add debt concept?
* Debors: 
  * Remove debtor
  * Add debtor
  * Equal shares vs weighted shares
* implement multiple people with same name 
  * PersonId = identity, PersonName = attribute (not uniqueness constraint) 
  * only unique for creating from file but not in general
  * find ways for humans to differentiate
* Caching: store cached and update only with the old balance value.
  * caching is infrastructure concern
  * possible ways:
    * CachedBalanceCalculator
    * structural Caching with BillVersioned?
    * CachedBalanceService -> Clean Architecture style
* CSP problem: add constraint for transactions e.g. A doesn't want to send money to C but to B

ChatGPT Prompt:
I am a professional developer. 
I need some help converting a simple Kotlin project that has no architecture to Domain Driven Design and I need some advice for this.





