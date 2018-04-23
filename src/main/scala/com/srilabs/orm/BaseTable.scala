package com.srilabs.orm

import java.sql.Timestamp
import java.time.LocalDateTime
import com.srilabs.models.Entity
import Profile.api._

abstract class BaseTable[E <: Entity](tag: Tag, tableName: String) extends Table[E](tag, tableName) {

  def createdAt = column[Long]("CREATED_AT")
  def updatedAt = column[Long]("UPDATED_AT")
  def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)

  //  implicit val localDateTimeToTimestamp: BaseColumnType[LocalDateTime] = MappedColumnType.base[LocalDateTime, Timestamp](
  //    l => Timestamp.valueOf(l),
  //    t => t.toLocalDateTime
  //  )
  //  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  //  def createdAt = column[Option[LocalDateTime]]("created_at", O.Default(null))
  //  def updatedAt = column[Option[LocalDateTime]]("updated_at", O.Default(null))
  //  def deletedAt = column[Option[LocalDateTime]]("deleted_at", O.Default(null))
}
