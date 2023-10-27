package $package$.zioapp
package api

import zio.*

import api.item.*
import domain.*
import domain.item.*

final case class PublicApiHandler(itemService: ItemService):

  def health: UIO[String] = ZIO.succeed("ok")

  def listItems: IO[RepositoryError.DbEx, List[ItemResult]] =
    itemService.getAllItems.map(_.map(ItemResult.fromDomain(_)))

  def getItem(id: ItemId)
      : IO[RepositoryError.DbEx | RepositoryError.MissingEntity | RepositoryError.ConversionError | RequestError, ItemResult] =
    itemService.getItemById(id).map(ItemResult.fromDomain(_))

object PublicApiHandler:
  val layer: RLayer[ItemService, PublicApiHandler] = ZLayer.derive[PublicApiHandler]
