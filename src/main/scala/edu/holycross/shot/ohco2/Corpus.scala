package edu.holycross.shot.ohco2

import edu.holycross.shot.cite._
import scala.io.Source

case class Corpus (nodes: Vector[CitableNode]) {
  def urnMatch(filterUrn: CtsUrn) : Vector[CitableNode]= {
    filterUrn.isRange match {
      // range filter:
      case true => {
        val u1 = CtsUrn(filterUrn.dropPassage.toString + filterUrn.rangeBeginRef)
        val u2 = CtsUrn(filterUrn.dropPassage.toString + filterUrn.rangeEndRef)
        try {
          val idx1 = nodes.indexOf(getFirstNode(u1))
          val idx2 = nodes.indexOf(getLastNode(u2)) + 1
          nodes.slice(idx1,idx2)
        } catch {
          case oe: Ohco2Exception => Vector.empty[CitableNode]
        }
      }
      //node filter:
      case false =>  {
       nodes.filter(_.urn.urnMatch(filterUrn))
     }
    }
  }
  def getValidReff(filterUrn: CtsUrn): Vector[CtsUrn] = {
    urnMatch(filterUrn).map(_.urn)
  }
  def getTextContents(filterUrn: CtsUrn, connector: String = "\n"): String = {
    urnMatch(filterUrn).map(_.text).mkString(connector)
  }
  def getFirstNodeOption(filterUrn: CtsUrn): Option[CitableNode] = {
    val matching = urnMatch(filterUrn)
    matching.isEmpty match {
      case true => None
      case false => Some(matching.head)
    }
  }
  def getFirstNode(filterUrn: CtsUrn): CitableNode = {
    getFirstNodeOption(filterUrn) match {
      case None => throw Ohco2Exception("No node matching " + filterUrn)
      case n: Some[CitableNode] => n.get
    }
  }

  def getLastNodeOption(filterUrn: CtsUrn): Option[CitableNode] = {
    val matching = urnMatch(filterUrn)
    matching.isEmpty match {
      case true => None
      case false => Some(matching.last)
    }
  }

  def getLastNode(filterUrn: CtsUrn): CitableNode = {
    getLastNodeOption(filterUrn) match {
      case None => throw Ohco2Exception("No node matching " + filterUrn)
      case n: Some[CitableNode] => n.get
    }
  }
  def citedWorks: Vector[CtsUrn] = {
    nodes.map(_.urn.dropPassage).distinct
  }


  def to82xfVector: Vector[XfColumns] = {
    val ids = nodes.map ( n => n.urn)
    val templateVector = Vector.empty[String]
    val nextColumn = templateVector  ++ ids.drop(1) ++ Vector("")
    val prevColumn = templateVector ++ Vector("") ++ ids.dropRight(1)
    val nextPrev = nextColumn.zip(prevColumn)
    nodes.zip(nextPrev).map {
      case (n,(nxt,prv)) => XfColumns(n.urn.toString,nxt.toString,prv.toString,n.text)
    }
  }
}


object Corpus {
  def apply(f: String, separator: String = "\t"): Corpus = {
    val stringPairs = Source.fromFile(f).getLines.toVector.map(_.split(separator))
    val citableNodes = stringPairs.map( arr => CitableNode(CtsUrn(arr(0)), arr(1)))
    Corpus(citableNodes)
  }
}


case class XfColumns(urn: String, nxt: String, prv: String, txt: String)
