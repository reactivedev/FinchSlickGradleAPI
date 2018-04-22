package com.srilabs.orm

import com.srilabs.models.Entity
import com.twitter.util.{Future => TFuture}
import scala.concurrent.Future
import com.srilabs.util.FutureUtils._
import Profile.api._

abstract class Repository[E <: Entity, T <: BaseTable[E]](query: TableQuery[T]) {

  def all(): TFuture[Seq[E]] =
    db.run(query.result).asTwitter

  def find(id: Long): Future[Option[E]] =
    db.run(query.filter(_.id === id).result.headOption)

  // TODO: check if exists by composit key? How would we handle that?
  // Best place to validate is using unique key on the DB side, outside the scope of Repository pattern
  def insert(rec: E): TFuture[Int] =
    db.run(query += rec).asTwitter

  // TODO: check if exists
  def update(rec: E): TFuture[Int] =
    db.run(query += rec).asTwitter

  // TODO: check if exists
//  def delete(id: Long): TFuture[Int] =
//    db.run(query.drop(id)).asTwitter

}
