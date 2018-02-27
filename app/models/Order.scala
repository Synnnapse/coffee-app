package models

case class Order(owner: User,
                 offer: Offer,
                 description: String,
                 status: OrderStatus
                )

sealed trait OrderStatus
case object OrderPending extends OrderStatus
case object OrderAccepted extends OrderStatus
case object OrderRejected extends OrderStatus
case object OrderCancelled extends OrderStatus