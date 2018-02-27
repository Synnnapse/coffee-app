package models

case class Order(owner: User,
                 offer: Offer,
                 description: String,
                 status: Status
                )

sealed trait Status
case object Pending extends Status
case object Accepted extends Status
case object Rejected extends Status
case object Cancelled extends Status