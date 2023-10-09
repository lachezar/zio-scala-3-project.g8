package $package$.zioapp
package api

import zio.json.JsonEncoder

import domain.item.ItemValidationError

final case class ValidationError(`type`: String, message: String) derives JsonEncoder
