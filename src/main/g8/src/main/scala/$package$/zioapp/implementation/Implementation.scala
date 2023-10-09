package $package$.zioapp
package implementation

import zio.RLayer

import domain.events.EventPublisher
import domain.item.ItemRepository
import implementation.auth.AuthService
import implementation.kafka.{ EventPublisherImplementation, KafkaConfig }

import javax.sql.DataSource

import postgres.*

type ImplementationEnv = AuthService & ItemRepository & EventPublisher

def layer: RLayer[DbConfig & KafkaConfig, ImplementationEnv] =
  PostgresDataSource.layer >>> ItemRepositoryImplementation.layer ++ AuthService.layer ++ EventPublisherImplementation.layer
