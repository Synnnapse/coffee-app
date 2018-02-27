package models

import org.joda.time.LocalDate

case class Offer(owner: User,
                 outlet: String,
                 timeIn: LocalDate,
                 timeOut: LocalDate,
                 slotsAvailable: Int,
                 status: OfferStatus)

sealed trait OfferStatus
case object OfferOpen extends OfferStatus
case object OfferInProgress extends OfferStatus
case object OfferCancelled extends OfferStatus
case object OfferComplete extends OfferStatus

