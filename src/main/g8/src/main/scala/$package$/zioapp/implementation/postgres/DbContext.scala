package $package$.zioapp
package implementation
package postgres

import $package$.quill.CustomQuillNamingStrategy
import io.getquill.PostgresZioJdbcContext

lazy val DbContext = new PostgresZioJdbcContext(CustomQuillNamingStrategy)
