package $package$.zioapp
package domain
package events

import zio.*
import zio.mock.*
import zio.test.Assertion

import domain.item.Item

object EventPublisherMock extends Mock[EventPublisher]:

  object SendNewItemEvent extends Effect[Item, Nothing, Unit]

  val compose: URLayer[Proxy, EventPublisher] =
    ZLayer.fromFunction { (proxy: Proxy) =>
      new EventPublisher {
        override def sendNewItemEvent(item: Item): IO[EventError, Unit] = proxy(SendNewItemEvent, item)
      }
    }

  val optionalMockLayer: ULayer[EventPublisher] =
    EventPublisherMock.SendNewItemEvent(Assertion.anything, Expectation.value(())).optional
