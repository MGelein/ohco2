package edu.holycross.shot

package object ohco2 {

  /** Create two-column OHCO2 representation from
  * a string in 82XF form. Note that 82XF input
  * must already be ordered.
  *
  * @param xf String in 82XF form.
  * @param inputDelimiter String delimiting columns
  * of 82XF input.
  * @param outputDelimiter String to use to delimit
  * columns in two-column output.
  */
  def twoColumnsFrom82xf(xf: String, inputDelimiter: String = "#", outputDelimiter: String = "#"): String = {
    val lines = xf.split("\n").toVector.map(_.split(inputDelimiter))


    val twoColumns = lines.map(v => {
      v.size match {
        case n if n == 5 =>  v(0) + outputDelimiter + v(4)
        //case n if n ==
        case _ => ""
        }
      }
    )
    twoColumns.mkString("\n")
  }
}
