package $package$.zioapp
package implementation
package postgres

import zio.*
import zio.test.*
import zio.test.TestAspect.*

import domain.*
import domain.common.*
import domain.item.*

import java.util.UUID

import com.dimafeng.testcontainers.PostgreSQLContainer

object ItemRepositoryImplementationSpec extends ZIOSpecDefault:

  val itemId0 = ItemId(new UUID(0, 0))
  val itemId1 = ItemId(new UUID(0, 1))
  val itemId2 = ItemId(new UUID(0, 2))
  val itemId3 = ItemId(new UUID(0, 3))

  override def spec = (suite("item repository test with postgres test container")(
    test("save items returns their ids") {
      for {
        item1 <- ItemRepository.add(Item(itemId1, "first item", Money(1), ProductType.Clothes))
        item2 <- ItemRepository.add(Item(itemId2, "second item", Money(2), ProductType.Electronics))
        item3 <- ItemRepository.add(Item(itemId3, "third item", Money(3), ProductType.Toys))
      } yield assert(item1.id)(Assertion.equalTo(itemId1)) &&
      assert(item2.id)(Assertion.equalTo(itemId2)) &&
      assert(item3.id)(Assertion.equalTo(itemId3))
    },
    test("get all returns 3 items") {
      for {
        items <- ItemRepository.getAll
      } yield assert(items)(Assertion.hasSize(Assertion.equalTo(3)))
    },
    test("delete first item") {
      for {
        _    <- ItemRepository.delete(itemId1)
        item <- ItemRepository.getById(itemId1).exit
      } yield assert(item)(Assertion.failsWithA[RepositoryError.MissingEntity])
    },
    test("get item 2") {
      for {
        item <- ItemRepository.getById(itemId2)
      } yield assert(item.id)(Assertion.equalTo(itemId2)) &&
      assert(item.name)(Assertion.equalTo("second item")) &&
      assert(item.price)(Assertion.equalTo(Money(2)))
    },
    test("update item 3") {
      for {
        _    <- ItemRepository.update(
                  itemId3,
                  UpdateItemInput[ValidationStatus.Validated.type]("updated item", Money(3), ProductType.VideoGames),
                )
        item <- ItemRepository.getById(itemId3)
      } yield assert(item.id)(Assertion.equalTo(itemId3)) &&
      assert(item.name)(Assertion.equalTo("updated item")) &&
      assert(item.price)(Assertion.equalTo(Money(3))) &&
      assert(item.productType)(Assertion.equalTo(ProductType.VideoGames))
    },
  ) @@ sequential @@ beforeAll(Migration.run)).provideShared(
    containerLayer,
    dbConfigLayer,
    dataSourceLayer,
    ItemRepositoryImplementation.layer,
  )
