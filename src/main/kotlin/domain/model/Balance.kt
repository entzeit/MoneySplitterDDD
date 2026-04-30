package main.kotlin.domain.model

import main.kotlin.domain.model.vo.Money
import main.kotlin.domain.model.vo.PersonId

//Value Object and pure derived model todo: check
typealias Balances = Map<PersonId, Money> //todo: store where?