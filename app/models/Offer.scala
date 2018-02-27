package models

import java.util.UUID

import org.joda.time.LocalTime

// TODO - add an id to each offer. Make a companion object for an offer that can generate ids

case class Offer(
                  owner: User,
                  outlet: Option[String],
                  timeIn: Option[LocalTime],
                  timeOut: Option[LocalTime],
                  slotsAvailable: Option[Int],
                  status: Option[OfferStatus],
                  id: String = UUID.randomUUID.toString
)

sealed trait OfferStatus
case object OfferOpen extends OfferStatus
case object OfferInProgress extends OfferStatus
case object OfferCancelled extends OfferStatus
case object OfferComplete extends OfferStatus
