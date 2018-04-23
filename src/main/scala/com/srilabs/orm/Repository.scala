package com.srilabs.orm

import com.srilabs.models.Entity
import com.twitter.util.{Future => TFuture}
import scala.concurrent.{Await, Future}
import com.srilabs.util.FutureUtils._
import Profile.api._



abstract class Repository[E <: Entity, T <: BaseTable[E]](query: TableQuery[T]) {

  def all(): TFuture[Seq[E]] =
    db.run(query.result).asTwitter

  def find(id: Long): Future[Option[E]] =
    db.run(query.filter(_.id === id).result.headOption)

  def insert(rec: E): TFuture[E] =
    db.run(query returning query += rec)
      .asTwitter

  def upsert(rec: E): TFuture[Option[Long]] =
    db.run((query returning query.map(_.id)).insertOrUpdate(rec))
      //.map(_id => rec.copy(id = Some(_id))
      .asTwitter

  def delete(id: Long): TFuture[Int] =
    db.run(query.filter(_.id === id).delete)
      .asTwitter

//  // Both MySql & H2 doesn't allow returning the whole record
//  // slick.SlickException: This DBMS allows only a single column to be returned from an INSERT,
//  // and that column must be an AutoInc column.
//  def upsert(rec: E): TFuture[Option[E]] =
//    db.run((query returning query).insertOrUpdate(rec))
//      .asTwitter

}
