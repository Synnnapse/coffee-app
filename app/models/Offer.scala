package models

import org.joda.time.LocalDate

case class Offer(owner: User,
                 outlet: String,
                 timeIn: LocalDate,
                 timeOut: LocalDate,
                 slotsAvailable: Int,
                 status: Status)

sealed trait Status
case object Open extends Status
case object InProgress extends Status
case object Cancelled extends Status
case object Complete extends Status

